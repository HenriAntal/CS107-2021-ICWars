package ch.epfl.cs107.play.game.icwars.actor.players;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsRange;
import ch.epfl.cs107.play.game.icwars.handler.ICWarsInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;

abstract public class ICWarsPlayer extends ICWarsActor implements Interactor, Interactable {

    private Sprite sprite;
    protected Unit[] units = new Unit[2];
    protected Unit selectedUnit;
    protected ArrayList<RealPlayer> realPlayers = new ArrayList<RealPlayer>();
    protected ArrayList<Unit> usedNumbers = new ArrayList<>();
    public enum State {IDLE, NORMAL, SELECT_CELL, MOVE_UNIT, SELECTION_ACTION, ACTION}
    public State s;


    /**
     * Constructor for the ICWarsPlayer, the parent class of the RealPlayer
     * @param owner
     * @param coordinates
     * @param belongs
     * @param units
     */
        public ICWarsPlayer(Area owner, DiscreteCoordinates coordinates, String belongs, Unit... units){
            super(owner, coordinates, belongs);

            s = State.IDLE;

            for (int i = 0; i < units.length; ++i) {
                (this.units)[i] = units[i];
                owner.registerActor(units[i]);
            }
        }

    public Unit[] getUnits() { return units; }

    @Override
    public boolean takeCellSpace() {
        return false;
    }


    /**
     * if the State is equal to IDLE then we cna change it to NORMAL with this method.
     */
    public void startTurn() {
        if (s.equals(State.IDLE)) {
            s = State.NORMAL;
        }
    }


    //Interactor
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return false;
    }

    @Override
    public void interactWith(Interactable other) {

    }
    //Interactor methods end

    //Interactable override

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((ICWarsInteractionVisitor)v).interactWith(this);
    }

    /**
     * This method works together with the doubleUsed method. We basically add the Units that already got moved onto to the list. When we select a new
     * Unit we check if that Unit is already in the List if the return value is true then we are not allowed to move or use the Unit again.
     * @param unit
     * @return
     */
    public boolean notAlreadyUsed(Unit unit){
        if(usedNumbers.size() == 0){
            usedNumbers.add(unit);
            return true;
        }
        usedNumbers.add(unit);
        if(doubleUsed(unit)){
            return false;
        }
        return true;
    }

    public boolean doubleUsed(Unit unit){
        for(int k = 0; k < usedNumbers.size()-1; ++k){
            if(unit == usedNumbers.get(k)){
                return true;
            }
        }
        return false;
    }

    public void addUsedUnit(Unit unit) {
        usedNumbers.add(unit);
    }

}




