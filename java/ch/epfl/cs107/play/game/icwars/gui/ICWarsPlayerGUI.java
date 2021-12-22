package ch.epfl.cs107.play.game.icwars.gui;

import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.window.Canvas;

public class ICWarsPlayerGUI implements Graphics {

    private ICWarsPlayer player;
    private Unit selectedUnit;

    public ICWarsPlayerGUI (float cameraScaleFactor, ICWarsPlayer player) {
        this.player = player;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    @Override
    public void draw (Canvas canvas) {
        selectedUnit.drawRangeAndPathTo(player.getCurrentCells().get(0), canvas);
    }
}
