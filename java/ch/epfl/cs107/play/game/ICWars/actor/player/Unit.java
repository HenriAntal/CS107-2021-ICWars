package ch.epfl.cs107.play.game.ICWars.actor.player;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

abstract class Unit extends ICWarsActor {

    int Hp;
    private Sprite sprite;
    int attackDamage;
    int maxRadius;

    public Unit(Area owner, DiscreteCoordinates coordinates, String belongs ){
        super(owner, coordinates, belongs);

        // somehow need to include damage taken (- Hp)
        // also need to include healing (+ Hp)

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public int damageTaken(){ return Hp-attackDamage;} //TODO Attack Damage of other unit is the correct variable

    public int healing() { return 1;} // we don't know the healing amount yet.

    public int getHp() {
        return Hp;
    }

    public int getDamage(){
        return attackDamage;
    }


}
