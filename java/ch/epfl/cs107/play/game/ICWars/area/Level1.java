package ch.epfl.cs107.play.game.ICWars.area;

import ch.epfl.cs107.play.game.ICWars.actor.player.Soldier;
import ch.epfl.cs107.play.game.ICWars.actor.player.Tank;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
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
        registerActor(new Soldier(this , new DiscreteCoordinates(3,5),"ally"));
        //super.soldierAdder(new Soldier(this , new DiscreteCoordinates(3,5),"ally"));
        registerActor(new Tank(this , new DiscreteCoordinates(2,5),"ally"));
    }

}

