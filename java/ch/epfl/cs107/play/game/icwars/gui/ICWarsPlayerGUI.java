package ch.epfl.cs107.play.game.icwars.gui;

import ch.epfl.cs107.play.game.ICWars.gui.ICWarsActionsPanel;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.window.Canvas;

public class ICWarsPlayerGUI implements Graphics {

    private ICWarsPlayer player;
    private Unit selectedUnit;
    private ICWarsActionsPanel actionsPanel;
    private ICWarsInfoPanel infoPanel;
    public static float FONT_SIZE;

    public ICWarsPlayerGUI (float cameraScaleFactor, ICWarsPlayer player) {
        this.player = player;
        FONT_SIZE = 20.f;
        actionsPanel = new ICWarsActionsPanel(cameraScaleFactor);
        infoPanel = new ICWarsInfoPanel(cameraScaleFactor);
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    @Override
    public void draw (Canvas canvas) {
        if (player.s.equals(ICWarsPlayer.State.MOVE_UNIT)) {
            selectedUnit.drawRangeAndPathTo(player.getCurrentCells().get(0), canvas);
        }
        if (player.s.equals(ICWarsPlayer.State.SELECTION_ACTION)) {
            actionsPanel.setActions(selectedUnit.getActions());
            actionsPanel.draw(canvas);
        }
    }
}
