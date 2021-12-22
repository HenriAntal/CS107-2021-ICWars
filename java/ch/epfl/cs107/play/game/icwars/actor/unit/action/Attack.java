package ch.epfl.cs107.play.game.icwars.actor.unit.action;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class Attack extends Action {

    private Unit unit;
    public Attack(Area area, Unit unit) {
        super(area, unit);
        setKey(65);
        this.unit = unit;
        setName("(A)ttack");
    }

    @Override
    public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
        int counter = 0;
        List<Unit> enemyUnitList = player.enemyList;

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
