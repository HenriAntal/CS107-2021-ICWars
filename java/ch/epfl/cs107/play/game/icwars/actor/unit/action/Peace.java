package ch.epfl.cs107.play.game.icwars.actor.unit.action;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.List;

public class Peace extends Action {

    private Unit unit;
    private ImageGraphics sprite;

    public Peace(Area area, Unit unit) {
        super(area, unit);
        setKey(81);
        this.unit = unit;
        sprite = new Sprite(ResourcePath.getSprite("icwars/index"), 1f, 1f, unit);
        setName("(P)eace");
    }

    @Override
    public void doAction(float dt, ICWarsPlayer player, Keyboard keyboard) {
        this.unit = player.getUnits()[0];


    }


    @Override
    public void draw(Canvas canvas) {

        sprite.draw(canvas);

    }
}
