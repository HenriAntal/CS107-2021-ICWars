package ch.epfl.cs107.play.game.icwars.actor.unit.action;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Wait extends Action {

    private Unit unit;

    public Wait(Area area, Unit unit) {
        super(area, unit);
        this.unit = unit;
        setKey(87);
        setName("(W)ait");
    }

    public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
        player.addUsedUnitChecked(unit);
        player.s = ICWarsPlayer.State.NORMAL;
        System.out.println("wait to normal");
    }

    @Override
    public void draw(Canvas canvas) {

        //player.s = ICWarsPlayer.State.NORMAL;
    }
}
