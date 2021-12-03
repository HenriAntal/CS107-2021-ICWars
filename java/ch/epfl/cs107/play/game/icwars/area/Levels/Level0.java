package ch.epfl.cs107.play.game.icwars.area.Levels;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 {
    // TODO update to Level0
    /**
     * Specific area
     */
    public class Ferme extends Tuto2Area {

        @Override
        public String getTitle() {
            return "zelda/Ferme";
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
}
