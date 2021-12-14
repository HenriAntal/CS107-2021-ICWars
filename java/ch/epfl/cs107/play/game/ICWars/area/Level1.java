package ch.epfl.cs107.play.game.ICWars.area;

import ch.epfl.cs107.play.game.ICWars.actor.players.Soldier;
import ch.epfl.cs107.play.game.ICWars.actor.players.Tank;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends ICWarsArea {

    @Override
    public String getTitle() {
        return "icwars/Level1";
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(2,5);
    }

    protected void createArea() {
        // Base
        registerActor(new Background(this));
    }

}

