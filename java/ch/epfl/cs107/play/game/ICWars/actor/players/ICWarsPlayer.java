package ch.epfl.cs107.play.game.ICWars.actor.players;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;

public class ICWarsPlayer extends ICWarsActor{

    private Sprite sprite;
    protected Unit[] units = new Unit[2];

    //TODO
        public ICWarsPlayer(Area owner, DiscreteCoordinates coordinates, String belongs, Unit... units){
            super(owner, coordinates, belongs);
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


    public boolean playerWon() {
        if (allyList.isEmpty()) {
            System.out.println("Allies lost, you lost!");
            return false;
        } else if (enemyList.isEmpty()) {
            System.out.println("Enemies lost, you won!");
            return false;
        } else {
            return true;
        }
    }

    /*public void actorAdder(ICWarsPlayer player) {
        playerList.add(player);
    }*/


}




