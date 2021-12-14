package ch.epfl.cs107.play.game.ICWars.gui;

import ch.epfl.cs107.play.game.ICWars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.ICWars.actor.players.RealPlayer;
import ch.epfl.cs107.play.game.ICWars.actor.players.Unit;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.window.Canvas;

public class ICWarsPlayerGUI implements Graphics {

    private Unit unit;
    private ICWarsPlayer player;

    public ICWarsPlayerGUI (float cameraScaleFactor, ICWarsPlayer player) {
        this.player = player;
    }

    @Override
    public void draw(Canvas canvas) {
        unit.drawRangeAndPathTo(player.getCurrentCells().get(0), canvas);
    }
}
