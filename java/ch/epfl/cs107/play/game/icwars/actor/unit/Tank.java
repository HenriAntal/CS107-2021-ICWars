package ch.epfl.cs107.play.game.icwars.actor.unit;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.actor.unit.action.Action;
import ch.epfl.cs107.play.game.icwars.actor.unit.action.Attack;
import ch.epfl.cs107.play.game.icwars.actor.unit.action.Wait;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Tank extends Unit {

    private List<Action> actions = new ArrayList<Action>();

    /**
     * Constructor for the Unit -> Tank
     * @param owner
     * @param coordinates
     * @param belongs
     */
    public Tank(Area owner, DiscreteCoordinates coordinates, String belongs) {
        super(owner, coordinates, belongs);
        super.maxRange = 4;
        attackDamage = 7;
        Hp = 10;
        range = initRange(owner, coordinates, super.maxRange);
        actions.add(new Wait(getOwnerArea(), this));
        actions.add(new Attack(getOwnerArea(), this));

        this.sprite = new Sprite(getName(), 1.5f, 1.5f, this, null, new Vector(-0.25f, -0.25f));


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

    //methods from Interactor
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
        return false;
    }

    @Override
    public void interactWith(Interactable other) {}
    //end methods Interactor

    @Override
    public List<Action> getActions() {
        return actions;
    }

}