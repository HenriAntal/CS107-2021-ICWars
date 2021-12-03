package ch.epfl.cs107.play.game.icwars.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

    public class Level0 extends ICWarsArea {

        @Override
        public String getTitle() {
            return "ICWars/Level0";
        }

        @Override
        public DiscreteCoordinates getPlayerSpawnPosition() {
            return new DiscreteCoordinates(2,10);
        }

        protected void createArea() {
            // Base
            registerActor(new Background(this));
            registerActor(new Foreground(this));
        }

    }

