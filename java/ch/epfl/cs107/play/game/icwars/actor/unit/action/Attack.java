package ch.epfl.cs107.play.game.icwars.actor.unit.action;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Attack extends Action {

    public Attack(Area area, Unit unit, String name) {
        super(area, unit, name);
        setKey(65);
    }

    @Override
    public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
