package ch.epfl.cs107.play.game.icwars.actor.unit;

import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.actor.players.ICWarsActor;
import ch.epfl.cs107.play.game.icwars.actor.unit.action.Action;
import ch.epfl.cs107.play.game.icwars.actor.unit.action.Attack;
import ch.epfl.cs107.play.game.icwars.actor.unit.action.Wait;
import ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior;
import ch.epfl.cs107.play.game.icwars.area.ICWarsRange;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.handler.ICWarsInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public abstract class Unit extends ICWarsActor implements Interactor {

    int Hp;
    int attackDamage;
    int maxRange;
    ICWarsRange range = new ICWarsRange();
    int coordsX, coordsY;
    boolean used = false;
    protected Sprite sprite;
    private int cellStars;


    public Unit(Area owner, DiscreteCoordinates coordinates, String belongs ){
        super(owner, coordinates, belongs);
        coordsX = coordinates.x;
        coordsY = coordinates.y;


//        range.addNode(new DiscreteCoordinates(6,4), true, true, true, true);

//        createRange(owner, coordinates);


        // somehow need to include damage taken (- Hp)
        // also need to include healing (+ Hp)

    }


    public int damageTaken(Unit other) {
        ICWarsPlayerInteractionHandler handler = new ICWarsPlayerInteractionHandler();
        return Hp - other.attackDamage + cellStars;
    }

    public int healing() { return 1;} // we don't know the healing amount yet.

    public int getHp() {
        return Hp;
    }

    public int getDamage(){
        return attackDamage;
    }

    public void setHp(int newHp) {
        Hp = newHp;
    }

    public void setCellStars(int stars) {
        cellStars = stars;
    }

    public ICWarsRange initRange(Area owner, DiscreteCoordinates coordinates, int maxRange) {

        for (int x = -maxRange; x <= maxRange; ++x) {
            for (int y = -maxRange; y <= maxRange; ++y) {
                int newX = coordinates.x + x;
                int newY = coordinates.y + y;

                if (newX < 0) {
                    continue;
                }
                if (newY > owner.getHeight() - 1) {
                    continue;
                }
                if (newX > owner.getWidth() - 1) {
                    continue;
                }
                if (newY < 0) {
                    continue;
                }

                boolean left = true, up = true, right = true, down = true;
                if (newX == 0 || x == -maxRange) {
                    left = false;
                }
                if (newY == owner.getHeight() - 1 || y == maxRange) {
                    up = false;
                }
                if (newX == owner.getWidth() - 1 || x == maxRange) {
                    right = false;
                }
                if (newY == 0 || y == -maxRange) {
                    down = false;
                }

//                System.out.println("added");
                range.addNode(new DiscreteCoordinates(coordinates.x + x, coordinates.y + y),
                        left, up, right, down);
            }
        }
        return range;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    public void changeSprite(float number){
        sprite.setAlpha(number);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
    }


    @Override
    public boolean hasBeenMoved(DiscreteCoordinates newPosition) {
        if (!range.nodeExists(newPosition) || !super.hasBeenMoved(newPosition)) {
            return false;
        } else {
            ICWarsRange newRange = new ICWarsRange();
            newRange = initRange(getOwnerArea(), newPosition, maxRange);
            range = newRange;
            coordsX = newPosition.x;
            coordsY = newPosition.y;
            return true;
        }
    }

    public ICWarsRange getRange(){
        return range;
    }

    /**
     * Draw the unit's range and a path from the unit position to
     destination
     * @param destination path destination
     * @param canvas canvas
     */
    public void drawRangeAndPathTo(DiscreteCoordinates destination, Canvas canvas) {
        range.draw(canvas);
        Queue<Orientation> path = range.shortestPath(getCurrentMainCellCoordinates(), destination);
        //Draw path only if it exists (destination inside the range)
        if (path != null){
            new Path(getCurrentMainCellCoordinates().toVector(), path).draw(canvas);
        }
    }

    private class ICWarsPlayerInteractionHandler implements ICWarsInteractionVisitor {

        @Override
        public void interactWith(ICWarsBehavior.ICWarsCellType cell) {
            setCellStars(cell.getStars());
        }
    }
}
