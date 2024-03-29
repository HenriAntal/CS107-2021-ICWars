package ch.epfl.cs107.play.game.icwars.actor.players;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsRange;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public abstract class ICWarsActor extends MovableAreaEntity {

    String belongs;

    public ICWarsActor(Area owner, DiscreteCoordinates coordinates, String belongs) {
        super(owner, Orientation.UP, coordinates);

        if (belongs.equals("ally")) {
            this.belongs = "ally";
        } else {
            this.belongs = "enemy";
        }

        // Orientation has to be up so 0

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return null;
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }

    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
    }

    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    public String getBelongs() {
        return belongs;
    }

    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

}
