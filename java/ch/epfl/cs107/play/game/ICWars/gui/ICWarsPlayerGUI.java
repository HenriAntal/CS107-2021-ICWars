package ch.epfl.cs107.play.game.ICWars.gui;

import ch.epfl.cs107.play.game.ICWars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.ICWars.actor.players.RealPlayer;
import ch.epfl.cs107.play.game.ICWars.actor.players.Unit;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
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
