package ch.epfl.cs107.play.game.icwars.actor.players;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.icwars.ICWars;
import ch.epfl.cs107.play.game.icwars.gui.ICWarsPlayerGUI;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
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




    /**
     * Demo actor
     *
     */
    public RealPlayer(Area owner, DiscreteCoordinates coordinates, String belongs, Unit... units) {
        super(owner, coordinates, belongs, units);

        if (belongs.equals("ally")) {
            sprite = new Sprite("icwars/allyCursor" , 1.f, 1.f, this, null, new Vector(0f, 0f));
        } else {
            sprite = new Sprite("icwars/enemyCursor" , 1.f, 1.f, this, null, new Vector(0f, 0f));
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

        if (s.equals(State.NORMAL) || s.equals(State.SELECT_CELL) || s.equals(State.MOVE_UNIT)) {
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        }

        switch (s) {
            case IDLE:
                //TODO later
                break;
            case NORMAL:
                if (keyboard.get(Keyboard.ENTER).isReleased() && playerOnUnit()) {
                    s = State.SELECT_CELL;
                } else if (keyboard.get(Keyboard.TAB).isReleased()) {
                    for(int i = 0; i < units.length; ++i){
                        units[i].changeSprite(1);
                    }
                    s = State.IDLE;
                } else if (keyboard.get(Keyboard.ENTER).isReleased() && !playerOnUnit()) {
                    s = State.NORMAL;
                }
                break;
            case SELECT_CELL:
                getUnitNr();
                if (notAlreadyUsed(units[order])){
//                    selectUnit();
                    handler.interactWith(selectUnit());
                    s = State.MOVE_UNIT;
                }
                if(!playerOnUnit()) {
                    s = State.NORMAL;
                }
                break;
            case MOVE_UNIT:
                if (keyboard.get(Keyboard.ENTER).isReleased() && !playerOnUnit()) {
                    units[order].changePosition(getCurrentMainCellCoordinates());
                    units[order].changeSprite(0.5f);
                    s = State.NORMAL;
//                    ICWarsRange newRange = new ICWarsRange();
//                    units[order].createRange(getOwnerArea(),getCurrentMainCellCoordinates(), units[order].maxRange, newRange);
//                    units[order].range = newRange;
                } else if(keyboard.get(Keyboard.TAB).isReleased() || !gogoInRange()){
                    s = State.NORMAL;
                    usedNumbers.remove(usedNumbers.size()-1);
                }

                break;
            case ACTION_SELECTION:
                //TODO later
                break;
            case ACTION:
                //TODO later
                break;
        }

        super.update(deltaTime);

    }
    /**
     * Orientate and Move this player in the given orientation if the given button is down
     * @param orientation (Orientation): given orientation, not null
     * @param b (Button): button corresponding to the given orientation, not null
     */
    private void moveIfPressed(Orientation orientation, Button b){
        if(b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                move(MOVE_DURATION);
            }
        }
    }

    /**
     * Leave an area by unregister this player
     */
    public void leaveArea(){
        getOwnerArea().unregisterActor(this);
    }

    /**
     *
     * @param area (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }


    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
//        step1
//        if (selectUnit(order) != null && (gogoCase%2) == 1) {
        if (s.equals(State.MOVE_UNIT)) {
            gui.draw(canvas);
        }
    }


    ///Ghost implements Interactable

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

    public void interactWith(){

    }

    // checks if the RealPlayer is in Range of the selected unit, so it checks if is still on a Node, if not you go back to Normal State.
    public boolean gogoInRange(){
        if(selectUnit().range.nodeExists(getCurrentMainCellCoordinates())){
            return true;
        }
        return false;
    }

    private boolean playerOnUnit(){
        if(getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))){ return true;}
        if(getCurrentMainCellCoordinates().equals(units[1].getCurrentCells().get(0))){ return true;}

        return false;
    }

    public void getUnitNr(){
        //        this.getCurrentMainCellCoordinates();
        if (getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))) {
            // Soldier
            this.order = 0;
        } else if (getCurrentMainCellCoordinates().equals(units[1].getCurrentCells().get(0))) {
            // Tank
            this.order = 1;
        }
    }

    public Unit selectUnit() {

//            gui.draw(canvas, (super.units)[this.order]);
        gui.setSelectedUnit((super.units)[this.order]);
        return (super.units)[this.order];

    }

    private class ICWarsPlayerInteractionHandler implements ICWarsInteractionVisitor{

        @Override
        public void interactWith(RealPlayer realPlayer) {
            if (!isDisplacementOccurs()) {
                //TODO sth
            }
        }

        @Override
        public void interactWith(Unit unit) {
            if (!isDisplacementOccurs() && s.equals(State.SELECT_CELL) && belongs.equals(unit.belongs)) {
                selectedUnit = selectUnit();
            } else {
                selectedUnit = null;
            }
            gui.setSelectedUnit(selectedUnit);
        }
    }
}