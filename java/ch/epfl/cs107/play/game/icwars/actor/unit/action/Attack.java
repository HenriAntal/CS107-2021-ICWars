package ch.epfl.cs107.play.game.icwars.actor.unit.action;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.List;

public class Attack extends Action {

    private Unit unit;
    private Unit attackedUnit;
    private List<Unit> enemyUnitList;
    private ImageGraphics cursor = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
            new RegionOfInterest(4*18, 26*18, 16, 16));

    public Attack(Area area, Unit unit) {
        super(area, unit);
        setKey(65);
        this.unit = unit;
        setName("(A)ttack");
    }

    @Override
    public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
        int counter = 0;
        player.addUsedUnitChecked(unit);
//        boolean checka = false;
        enemyUnitList = unit.enemyInRangeList();
        System.out.println("supper");
//        for (Unit u : enemyUnitList) {
//            u.changeSprite(0.5f);
//        }

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

        attackedUnit = enemyUnitList.get(counter);

        if (keyboard.get(Keyboard.ENTER).isReleased()) {
            System.out.println("This Bitch got attacked");
            attackedUnit.damageTaken(unit);
            player.addUsedUnitChecked(attackedUnit);
            player.centerCamera();
            player.s = ICWarsPlayer.State.NORMAL;
        }

        if (enemyUnitList.isEmpty() || keyboard.get(Keyboard.TAB).isReleased()) {
            player.centerCamera();
            player.s = ICWarsPlayer.State.SELECTION_ACTION;
        }

            //DamageConversion in here

        }


    @Override
    public void draw(Canvas canvas) {
        boolean hasTargetUnit = false;
        for (Unit u : enemyUnitList) {
            if (attackedUnit.equals(u)) {
                hasTargetUnit = true;
            }
        }
//        if (hasTargetUnit) {
        if (enemyUnitList.size() > 0) {
            attackedUnit.centerCamera();
            cursor.setAnchor(canvas.getPosition().add(1,0));
            cursor.draw(canvas);
        }
    }
}
