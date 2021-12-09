package ch.epfl.cs107.play.game.ICWars.actor.player;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Tank extends Unit{

    Tank(Area owner, DiscreteCoordinates coordinates, String belongs) {
        super(owner, coordinates, belongs);
        super.maxRadius = 4;
        super.attackDamage = 7;
        super.Hp = 10;
        if (belongs.equals("ally")) {
            Sprite sprite = new Sprite("icwars / friendlyTank" , 1.f, 1.f, this, null, new Vector(-0.25f, -0.25f));
        } else {
            Sprite sprite = new Sprite("icwars / enemyTank" , 1.f, 1.f, this, null, new Vector(-0.25f, -0.25f));
        }

    }

    // TODO not sure if these 2 methods below work.
    @Override
    public int getHp() {
        return Hp;
    }

    @Override
    public int getDamage() {
        return attackDamage;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

}