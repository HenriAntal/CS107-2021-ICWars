package ch.epfl.cs107.play.game.ICWars.actor.player;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Soldier extends Unit {
    private String name;
    private Sprite sprite;


    public Soldier(Area owner, DiscreteCoordinates coordinates, String belongs) {
        super(owner, coordinates, belongs);
        this.maxRange = 2;
        this.attackDamage = 2;
        this.Hp = 5;

        if (belongs.equals("ally")) {
            name = "icwars/friendlySoldier";
        } else {
            name = "icwars/enemySoldier";
        }
        this.sprite = new Sprite(name, 1.5f, 1.5f, this, null, new Vector(-0.25f, -0.25f));
    }

    // TODO not sure if these 2 methods below work.
    @Override
    public int getHp() {
        return Hp;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {

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


