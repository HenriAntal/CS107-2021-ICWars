package ch.epfl.cs107.play.game.icwars.actor.players;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;

public class ICWarsPlayer extends ICWarsActor implements Interactor, Interactable {

    private Sprite sprite;
    protected Unit[] units = new Unit[2];
    protected Unit selectedUnit;
    protected ArrayList<RealPlayer> realPlayers = new ArrayList<RealPlayer>();
    public enum State {IDLE, NORMAL, SELECT_CELL, MOVE_UNIT, ACTION_SELECTION, ACTION}
    public State s;


    //TODO
        public ICWarsPlayer(Area owner, DiscreteCoordinates coordinates, String belongs, Unit... units){
            super(owner, coordinates, belongs);

            s = State.IDLE;



            for (int i = 0; i < units.length; ++i) {
                (this.units)[i] = units[i];
                owner.registerActor(units[i]);
            }


            /*if(belongs.equals("ally")){
                playerList = allyList;
            } else {
                playerList = enemyList;
            }*/

        }
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }


//    public boolean playerWon() {
//        if (allyList.isEmpty()) {
//            System.out.println("Allies lost, you lost!");
//            return false;
//        } else if (enemyList.isEmpty()) {
//            System.out.println("Enemies lost, you won!");
//            return false;
//        } else {
//            return true;
//        }
//    }

    /*public void actorAdder(ICWarsPlayer player) {
        playerList.add(player);
    }*/

    @Override
    public void onLeaving(List<DiscreteCoordinates> coordinates) {
//        super.onLeaving(coordinates);
//        if (haveSelectedUnit == null)
//            s = State.NORMAL;
    }

    public void startTurn() {
        if (s.equals(State.IDLE)) {
            s = State.NORMAL;
        }
    }

    public void startNormal() {
        s = State.NORMAL;
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
}




