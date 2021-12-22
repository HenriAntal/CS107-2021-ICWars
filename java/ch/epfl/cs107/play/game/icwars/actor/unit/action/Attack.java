package ch.epfl.cs107.play.game.icwars.actor.unit.action;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class Attack extends Action {

    private Unit unit;
    private Unit attackedUnit;

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
        if (keyboard.get(Keyboard.LEFT).isReleased()) {
            --counter;
            if (counter < 0) {
                counter = enemyUnitList.size() - 1;
            }
        } else if (keyboard.get(Keyboard.RIGHT).isReleased()) {
            ++counter;
            if (counter >= enemyUnitList.size()) {
                counter = 0;
            }
        }
        attackedUnit = enemyUnitList.get(counter);
        attackedUnit.centerCamera();
        if (keyboard.get(Keyboard.ENTER).isReleased()) {
            attackedUnit.damageTaken(unit);
            player.addUsedUnit(attackedUnit);
            player.centerCamera();
            player.s = ICWarsPlayer.State.NORMAL;
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
