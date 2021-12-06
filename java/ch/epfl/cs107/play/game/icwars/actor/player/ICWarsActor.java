package ch.epfl.cs107.play.game.icwars.actor.player;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class ICWarsActor extends MovableAreaEntity{

    String belongs;

    public ICWarsActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String belongs){
        super(owner, orientation, coordinates);

        if(belongs.equals("ally")){
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

// TODO pretty sure this is wrong.
    @Override
    public void draw(Canvas canvas) {

    }
}
