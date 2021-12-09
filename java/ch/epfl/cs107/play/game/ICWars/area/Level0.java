package ch.epfl.cs107.play.game.ICWars.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends ICWarsArea {

        @Override
        public String getTitle() {
            return "icwars/Level0";
        }

        @Override
        public DiscreteCoordinates getPlayerSpawnPosition() {return new DiscreteCoordinates(1,1);}


        /*
        @Override
        public DiscreteCoordinates addActor(){
            Unit.Soldier soldier1 = new Unit.Soldier("soldier1", "Level0", Orientation.UP, 2, 5, "ally");
        }

         */


        protected void createArea() {
            // Base
            registerActor(new Background(this));
            registerActor(new Foreground(this));
            //registerActor(new Unit.Soldier("icwars/friendlySoldier" , this, UP,  ,"ally");
        }



    }

