package ch.epfl.cs107.play.game.ICWars.actor.players;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;

public class ICWarsPlayer extends ICWarsActor{

    private Sprite sprite;
    private ArrayList<Unit> playerList;

    //TODO
        public ICWarsPlayer(Area owner, DiscreteCoordinates coordinates, String belongs){
            super(owner, coordinates, belongs);

            if(belongs.equals("ally")){
                playerList = allyList;
            } else {
                this.belongs = "enemy";
                playerList = enemyList;
            }

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

    /*public void actorAdder(ICWarsPlayer player) {
        playerList.add(player);
    }*/


}




