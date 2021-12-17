package ch.epfl.cs107.play.game.ICWars.actor.players;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.ICWars.area.ICWarsRange;
import ch.epfl.cs107.play.game.ICWars.gui.ICWarsPlayerGUI;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
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
    // henri is stupid
    private int gogoCase = 0;

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
            case IDLE: {

            }
            case NORMAL: {
                if (keyboard.get(Keyboard.ENTER).isReleased() && playerOnUnit() && gogoCase == 0) {
//                    Vector originalUnitCoords = new Vector(getCurrentMainCellCoordinates().x, getCurrentMainCellCoordinates().y);
                    gogoCase = 1;
                    s = State.SELECT_CELL;
                } else if (keyboard.get(Keyboard.TAB).isReleased()) {
                    s = State.IDLE;
                }
            }
            case SELECT_CELL:
             if (gogoCase == 1){
                    selectUnit();
                    gogoCase = 2;
                    s = State.MOVE_UNIT;
                }
            case MOVE_UNIT:
                if (keyboard.get(Keyboard.ENTER).isReleased() && !playerOnUnit() && gogoCase == 2) {
                    units[order].changePosition(getCurrentMainCellCoordinates());
//                    ICWarsRange newRange = new ICWarsRange();
//                    units[order].createRange(getOwnerArea(),getCurrentMainCellCoordinates(), units[order].maxRange, newRange);
//                    units[order].range = newRange;

                    gogoCase = 0;
                    s = State.NORMAL;
                } else if(keyboard.get(Keyboard.TAB).isReleased()){
                    gogoCase = 0;
                    s = State.NORMAL;
                }
            case ACTION_SELECTION: {}
            case ACTION: {}
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

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
    }

    private boolean playerOnUnit(){
        if(getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))){ return true;}
        if(getCurrentMainCellCoordinates().equals(units[1].getCurrentCells().get(0))){ return true;}


        return false;
    }

    public Unit selectUnit() {
//        this.getCurrentMainCellCoordinates();
        if (getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))) {
            // Soldier
            this.order = 0;
        } else if (getCurrentMainCellCoordinates().equals(units[1].getCurrentCells().get(0))) {
            // Tank
            this.order = 1;
        }
        if (order == 0 || order == 1) {
//            gui.draw(canvas, (super.units)[this.order]);
            gui.setSelectedUnit((super.units)[this.order]);
            return (super.units)[this.order];
        } else {
            return null;
        }
    }
}