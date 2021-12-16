package ch.epfl.cs107.play.game.ICWars.actor.players;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Tank extends Unit{
    static int maxRange;
    private String name;
    private Sprite sprite;

    public Tank(Area owner, DiscreteCoordinates coordinates, String belongs) {
        super(owner, coordinates, belongs);
        maxRange = 4;
        attackDamage = 7;
        Hp = 10;


        if (belongs.equals("ally")) {
            name = "icwars/friendlyTank";
        } else {
            name = "icwars/enemyTank";
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

    public static int getRange() {
        return maxRange;
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