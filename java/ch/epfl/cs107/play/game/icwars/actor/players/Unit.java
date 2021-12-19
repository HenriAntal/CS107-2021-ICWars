package ch.epfl.cs107.play.game.icwars.actor.players;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icwars.area.ICWarsRange;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

public abstract class Unit extends ICWarsActor {

    int Hp;
    int attackDamage;
    int maxRange;
    ICWarsRange range = new ICWarsRange();
    int coordsX, coordsY;
    boolean used = false;
    protected Sprite sprite;

//    public  int rangeIdentifier(){
//        if(Hp == 4){    // needs to be changed with getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))
//                        // it checks for the position of units and also the position of the player.
//            return Tank.getRange();
//        }
//        return Soldier.getRange();
//    }

    public Unit(Area owner, DiscreteCoordinates coordinates, String belongs ){
        super(owner, coordinates, belongs);
        coordsX = coordinates.x;
        coordsY = coordinates.y;


//        range.addNode(new DiscreteCoordinates(6,4), true, true, true, true);

//        createRange(owner, coordinates);


        // somehow need to include damage taken (- Hp)
        // also need to include healing (+ Hp)

    }

    public ICWarsRange createRange(Area owner, DiscreteCoordinates coordinates, int maxRange, ICWarsRange range) {

        for (int x = -maxRange; x <= maxRange; ++x) {
            for (int y = -maxRange; y <= maxRange; ++y) {
                int newX = coordinates.x + x;
                int newY = coordinates.y + y;

                if (newX < 0) { continue; }
                if (newY > owner.getHeight() - 1) { continue; }
                if (newX > owner.getWidth() - 1) { continue; }
                if (newY < 0) { continue; }

                boolean left = true, up = true, right = true, down = true;
                if (newX == 0 || x == -maxRange) { left = false; }
                if (newY == owner.getHeight() - 1 || y == maxRange) { up = false; }
                if (newX == owner.getWidth() - 1 || x == maxRange) { right = false; }
                if (newY == 0 || y == -maxRange) { down = false; }

//                System.out.println("added");
                range.addNode(new DiscreteCoordinates(coordinates.x + x, coordinates.y + y),
                        left, up, right, down);
            }
        }
        return range;
    }

    public int damageTaken(){ return Hp-attackDamage;} //TODO Attack Damage of other unit is the correct variable

    public int healing() { return 1;} // we don't know the healing amount yet.

    public int getHp() {
        return Hp;
    }

    public int getDamage(){
        return attackDamage;
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
        return true;
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
            newRange = createRange(getOwnerArea(), newPosition, maxRange, newRange);
            range = newRange;
            coordsX = newPosition.x;
            coordsY = newPosition.y;
            return true;
        }
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
}
