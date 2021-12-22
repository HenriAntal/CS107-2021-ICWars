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
        player.addUsedUnit(unit);
        boolean checka = false;
        List<Unit> enemyUnitList = player.enemyList;
        System.out.println("supper");


        if (keyboard.get(Keyboard.LEFT).isReleased()) {
            --counter;
            if (counter < 0) {
                counter = enemyUnitList.size() - 1;
            }
        } else if (keyboard.get(Keyboard.RIGHT).isReleased()) {
            ++counter;
            System.out.println("sucker");
            if (counter > enemyUnitList.size() - 1) {
                counter = 0;
            }
        }
        if (keyboard.get(Keyboard.ENTER).isPressed()) {
            checka = true;
            System.out.println("This Bitch got attacked");
            player.s = ICWarsPlayer.State.NORMAL;
        }

        //DamageConversion in here

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
