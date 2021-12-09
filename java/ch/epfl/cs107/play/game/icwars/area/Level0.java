package ch.epfl.cs107.play.game.icwars.area;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icwars.actor.player.Unit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import static ch.epfl.cs107.play.game.areagame.actor.Orientation.UP;

public class Level0 extends ICWarsArea {

        @Override
        public String getTitle() {
            return "ICWars/Level0";
        }

        @Override
        public DiscreteCoordinates getPlayerSpawnPosition() {
            return new DiscreteCoordinates(0,0);
        }


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
            registerActor(new Unit.Soldier("icwars/friendlySoldier" , this, UP,  ,"ally");
        }



    }

