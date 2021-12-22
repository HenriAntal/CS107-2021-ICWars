package ch.epfl.cs107.play.game.icwars.actor.unit.action;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Action implements Graphics {

    private Area area;
    private Unit unit;
    private String name;
    private int key;

    public Action(Area area, Unit unit, String name) {
        this.area = area;
        this.unit = unit;
        this.name = name;
    }
    public Unit getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {}

    @Override
    public void draw(Canvas canvas) {}

}
