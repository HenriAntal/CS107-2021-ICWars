package ch.epfl.cs107.play.game.icwars.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.actor.players.RealPlayer;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;

public interface ICWarsInteractionVisitor extends AreaInteractionVisitor {

    public default void interactWith(RealPlayer realPlayer) {}
    public default void interactWith(Unit unit) {}

}
