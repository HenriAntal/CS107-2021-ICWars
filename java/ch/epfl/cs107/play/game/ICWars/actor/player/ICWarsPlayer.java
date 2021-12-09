package ch.epfl.cs107.play.game.ICWars.actor.player;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class ICWarsPlayer extends ICWarsActor{

        private Sprite sprite;
        //TODO
        public ICWarsPlayer(Area owner, DiscreteCoordinates coordinates, String belongs ){
            super(owner, coordinates, belongs);



        }
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }


    }


