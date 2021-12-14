package ch.epfl.cs107.play.game.ICWars.area;

import ch.epfl.cs107.play.game.ICWars.actor.players.Soldier;
import ch.epfl.cs107.play.game.ICWars.actor.players.Tank;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Level0 extends ICWarsArea {

        @Override
        public String getTitle() {
            return "icwars/Level0";
        }

        @Override
        public DiscreteCoordinates getPlayerSpawnPosition() {return new DiscreteCoordinates(0,0);}


        /*
        @Override
        public DiscreteCoordinates addActor(){
            Unit.Soldier soldier1 = new Unit.Soldier("soldier1", "Level0", Orientation.UP, 2, 5, "ally");
        }

         */

    @Override
    public DiscreteCoordinates getRelativeMouseCoordinates() {
        return super.getRelativeMouseCoordinates();
    }

    protected void createArea() {
            // Base
            registerActor(new Background(this));
        }



    }

