package ch.epfl.cs107.play.game.icwars.actor.player;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Unit extends ICWarsActor {

    String name;
    int Hp;
    private Sprite sprite;
    int attackDamage;
    int maxRadius;

    public Unit(String name, Area owner, Orientation orientation, DiscreteCoordinates coordinates, String belongs ){
        super(owner, orientation, coordinates, belongs);

        this.name = name;
        // somehow need to include damage taken (- Hp)
        // also need to include healing (+ Hp)

    }
    public String getName() {
        return name;
    }

    public int getHp() {
        return Hp;
    }

    public int getDamage(){
        return attackDamage;
    }


    public class Soldier extends Unit{

        Soldier(String name, Area owner, Orientation orientation, DiscreteCoordinates coordinates, String belongs) {
            super(name, owner, orientation, coordinates, belongs);
            super.maxRadius = 2;
            super.attackDamage = 2;
            super.Hp = 5;
            if (belongs.equals("ally")) {
                Sprite sprite = new Sprite(name, 1.f, 1.f, this, null, new Vector(-0.25f, -0.25f));
            } else {
                Sprite sprite = new Sprite(name , 1.f, 1.f, this, null, new Vector(-0.25f, -0.25f));
            }

        }

        // TODO not sure if these 2 methods below work.
        @Override
        public int getHp() {
            return super.getHp();
        }

        public String getNameSoldier() {
            return super.getName();
        }

        @Override
        public int getDamage() {
            return super.getDamage();
        }


        // maybe no draw Canvas. i don't know if this is correct
        public void draw(Canvas canvas) {
            sprite.draw(canvas);
        }

        @Override
        public boolean takeCellSpace() {
            return true;
        }

    }

    public class Tank extends Unit{

        Tank(String name, Area owner, Orientation orientation, DiscreteCoordinates coordinates, String belongs) {
            super(name, owner, orientation, coordinates, belongs);
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
            return super.getHp();
        }

        @Override
        public int getDamage() {
            return super.getDamage();
        }

        public void draw(Canvas canvas) {
            sprite.draw(canvas);
        }

        @Override
        public boolean takeCellSpace() {
            return true;
        }

    }







}
