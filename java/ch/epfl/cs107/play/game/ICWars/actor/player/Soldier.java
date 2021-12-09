package ch.epfl.cs107.play.game.ICWars.actor.player;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Soldier extends Unit {

    Soldier(Area owner, DiscreteCoordinates coordinates, String belongs) {
        super(owner, coordinates, belongs);
        super.maxRadius = 2;
        super.attackDamage = 2;
        super.Hp = 5;
        String name;

        if (belongs.equals("ally")) {
            name = "icwars / friendlySoldier";
        } else {
            name = "icwars / enemySoldier";
        }

        Sprite sprite = new Sprite(name, 1.f, 1.f, this, null, new Vector(-0.25f, -0.25f));

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


