package ch.epfl.cs107.play.game.icwars.actor.players;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.game.icwars.actor.unit.action.Action;
import ch.epfl.cs107.play.game.icwars.area.ICWarsRange;
import ch.epfl.cs107.play.game.icwars.gui.ICWarsPlayerGUI;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.handler.ICWarsInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class RealPlayer extends ICWarsPlayer {
    private Sprite sprite;
    /// Animation duration in frame number
    private final static int MOVE_DURATION = 2;
    private ICWarsPlayerGUI gui = new ICWarsPlayerGUI(getOwnerArea().getCameraScaleFactor(), this);
    private int order;
    //    private Unit selectedUnit;
    private ICWarsPlayerInteractionHandler handler = new ICWarsPlayerInteractionHandler();
    // FRANCE is stupid
    private ArrayList<Unit> usedUnits = new ArrayList<>();
    private DiscreteCoordinates oldPosition;
    private Action action;


    /**
     * Constructor for the RealPlayer if belongs is equal to ally the friendly cursor gets spawned otherwise it is the enemy cursor.
     * @param owner
     * @param coordinates
     * @param belongs
     * @param units
     */
    public RealPlayer(Area owner, DiscreteCoordinates coordinates, String belongs, Unit... units) {
        super(owner, coordinates, belongs, units);

        if (belongs.equals("ally")) {
            sprite = new Sprite("icwars/allyCursor", 1.f, 1.f, this, null, new Vector(0f, 0f));
        } else {
            sprite = new Sprite("icwars/enemyCursor", 1.f, 1.f, this, null, new Vector(0f, 0f));
        }
        resetMotion();
    }


    /**
     * Center the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();

        /**
         * Movement so we can move the RealPlayer around with the Arrow-Keys.
         */
        if (s.equals(State.NORMAL) || s.equals(State.SELECT_CELL) || s.equals(State.MOVE_UNIT)) {
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        }

        /**
         * The Switch cases for the RealPlayer's turn.
         *
         * IDLE - clearing all information used earlier and initializing for the NORMAL movement.
         *
         * NORMAL - The mode where the RealPlayer can freely roam the Area and can select his Unit he wants to
         *          move by going over it and pressing ENTER.
         *
         * SELECT_CELL - Affirming that we selected a Unit and putting it on the used List.
         *
         * MOVE_UNIT -  In this state we can move around in the white square, if we move outside the square or if we press TAB we go back to NORMAL
         *              mode and remove the used Status from the Unit. The other possibility is to click ENTER, therefore the position of the Unit changes
         *              to the new position, and we get into ACTION_SELECT.
         *
         * ACTION_SELECT - In this State we can choose which kind of action we want to take with our Unit.
         *
         * ACTION - Here the action gets executed
         */
        switch (s) {
            case IDLE:
                //sprite.setAlpha(0f);
                clearUsedNumbers();
                //TODO later
                break;

            case NORMAL:
                sprite.setAlpha(1f);
                if (keyboard.get(Keyboard.ENTER).isReleased() && playerOnUnit()) {

                    //stores the Position of the RealPlayer when you select a unit
                    oldPosition = getCurrentMainCellCoordinates();
                    s = State.SELECT_CELL;
                } else if (keyboard.get(Keyboard.TAB).isReleased() || usedUnits.size() == units.length) {
                    for (int i = 0; i < units.length; ++i) {
                        units[i].changeSprite(1);
                    }

                    s = State.IDLE;
//                } else if (keyboard.get(Keyboard.ENTER).isReleased() && !playerOnUnit()) {
//                    s = State.NORMAL;
                }
                break;

            case SELECT_CELL:
                getUnitNr();
                if (notAlreadyUsed(units[order])) {
//                    selectUnit();
                    handler.interactWith(selectUnit());
                    s = State.MOVE_UNIT;
                }
                if (!playerOnUnit()) {
                    s = State.NORMAL;
                }
                break;

            case MOVE_UNIT:
                if (keyboard.get(Keyboard.ENTER).isReleased() && !playerOnUnit()
                        && changedPosition(selectedUnit.getCurrentCells().get(0), getCurrentMainCellCoordinates())) {
                    units[order].changePosition(getCurrentMainCellCoordinates());
                    //here we check if the old position and the current position of the Unit are the same, if that is the case
                    //then we stay in the MOVE_UNIT state.
//                    if (changedPosition(oldPosition, units[order].getCurrentCells().get(0))) {
//                        s = State.ACTION_SELECTION;
                    s = State.SELECTION_ACTION;
//                    } else {
//                        s = State.MOVE_UNIT;
//                    }

                } else if (keyboard.get(Keyboard.TAB).isReleased() || !inRange()) {
                    s = State.NORMAL;
                    usedUnits.remove(usedUnits.size() - 1);
                }
                break;

            case SELECTION_ACTION:
                sprite.setAlpha(0f);
                //If the user presses W we get the Wait Action
                for (Action a : selectedUnit.getActions()) {
                    if (keyboard.get(a.getKey()).isReleased()) {
                        action = a;
                        action.doAction(deltaTime, this, this.getOwnerArea().getKeyboard());
                        selectedUnit.changeSprite(0.5f);
                        s = State.ACTION;

                    }
                }


                break;

            case ACTION:
                //TODO later
                action.doAction(deltaTime, this, this.getOwnerArea().getKeyboard());
//                selectedUnit.clearRange();

                break;
        }

        super.update(deltaTime);

    }

    /**
     * Orientate and Move this player in the given orientation if the given button is down
     *
     * @param orientation (Orientation): given orientation, not null
     * @param b           (Button): button corresponding to the given orientation, not null
     */
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                move(MOVE_DURATION);
            }
        }
    }


    /**
     * Leave an area by unregister this player
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * with this method it is possible to enter the area with a RealPlayer, we use it to switch between ally and enemy so
     * the RealPlayer we don't need anymore leaves the are and the new one enters the area.
     * @param area     (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * draws the RealPlayers Sprite.
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        if (s != State.IDLE && s != State.ACTION) {
            sprite.draw(canvas);
        }

        gui.draw(canvas);

        if (s.equals(State.ACTION)){
            action.draw(canvas);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     *     checks if the RealPlayer is in Range of the selected unit, so it checks if is still on a Node, if not you go back to Normal State.
     */
    public boolean inRange() {
        if (selectUnit().getRange().nodeExists(getCurrentMainCellCoordinates())) {
            return true;
        }
        return false;
    }

    /**
     * checks if the RealPlayer is currently standing on top of a unit.
     * @return
     */
    private boolean playerOnUnit() {
        if (getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))) {
            return true;
        }
        if (getCurrentMainCellCoordinates().equals(units[1].getCurrentCells().get(0))) {
            return true;
        }

        return false;
    }

    /**
     * This method works together with the doubleUsed method. We basically add the Units that already got moved onto to the list. When we select a new
     * Unit we check if that Unit is already in the List if the return value is true then we are not allowed to move or use the Unit again.
     * @param unit
     * @return
     */
    public boolean notAlreadyUsed(Unit unit) {
        if (usedUnits.size() == 0) {
            usedUnits.add(unit);
            return true;
        }
        usedUnits.add(unit);
        if (doubleUsed(unit)) {
            return false;
        }
        return true;
    }

    public boolean doubleUsed(Unit unit) {
        for (int k = 0; k < usedUnits.size() - 1; ++k) {
            if (unit == usedUnits.get(k)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method sets the value "order" according to the correct Unit. So when we pick up a Unit we want to know it's kind
     * we do this with this method.
     */
    public void getUnitNr() {
        //        this.getCurrentMainCellCoordinates();
        if (getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))) {
            // Soldier
            this.order = 0;
        } else if (getCurrentMainCellCoordinates().equals(units[1].getCurrentCells().get(0))) {
            // Tank
            this.order = 1;
        }
    }

    /**
     * Similar to getUnitNr but it doesn't set the order to the correct value, it returns the whole Unit, but the order
     * had to be already set for this function to works
     * @return
     */
    public Unit selectUnit() {

        gui.setSelectedUnit((super.units)[this.order]);
        return (super.units)[this.order];

    }

    /**
     * Once a player starts his turn he has to be able to move his units again so. So the ArrayList that stored the
     * Units which got moved in the last Turn gets emptied.
     */
    public void clearUsedNumbers() {
        usedUnits.clear();
    }

    public boolean changedPosition(DiscreteCoordinates newPosition, DiscreteCoordinates oldPosition) {
        if (newPosition.equals(oldPosition)) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * handles Interactions TODO
     */
    private class ICWarsPlayerInteractionHandler implements ICWarsInteractionVisitor {

        @Override
        public void interactWith(RealPlayer realPlayer) {
            if (!isDisplacementOccurs()) {
                // this method doesn't get used because we used the a different approach in the State.MOVE_UNIT.
            }
        }

        @Override
        public void interactWith(Unit unit) {
            if (!isDisplacementOccurs() && s.equals(State.SELECT_CELL) && belongs.equals(unit.getBelongs())) {
                selectedUnit = selectUnit();
            } else {
                selectedUnit = null;
            }
            gui.setSelectedUnit(selectedUnit);
        }
    }
}