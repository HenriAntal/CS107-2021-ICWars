package ch.epfl.cs107.play.game.ICWars.actor.players;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;

public class ICWarsPlayer extends ICWarsActor{

    private Sprite sprite;
        //TODO
        public ICWarsPlayer(Area owner, DiscreteCoordinates coordinates, String belongs ){
            super(owner, coordinates, belongs);

        }
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }


    public boolean playerWon() {
        if (playerList.size() == 0){
            System.out.println("Player1 or 2 lost no more Units");
            return false;
        }
        return true;
    }

    public ArrayList<ICWarsPlayer> playerList = new ArrayList<ICWarsPlayer>();

    public void actorAdder(ICWarsPlayer player) {
        playerList.add(player);
    }



}




