package ch.epfl.cs107.play.game.ICWars.actor.players;

import ch.epfl.cs107.play.game.ICWars.area.ICWarsRange;
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

//    public  int rangeIdentifier(){
//        if(Hp == 4){    // needs to be changed with getCurrentMainCellCoordinates().equals(units[0].getCurrentCells().get(0))
//                        // it checks for the position of units and also the position of the player.
//            return Tank.getRange();
//        }
//        return Soldier.getRange();
//    }

    public Unit(Area owner, DiscreteCoordinates coordinates, String belongs ){
        super(owner, coordinates, belongs);


//        range.addNode(new DiscreteCoordinates(6,4), true, true, true, true);

//        createRange(owner, coordinates);


        // somehow need to include damage taken (- Hp)
        // also need to include healing (+ Hp)

    }

    public void createRange(Area owner, DiscreteCoordinates coordinates, int maxRange) {
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

                System.out.println("added");
                range.addNode(new DiscreteCoordinates(coordinates.x + x, coordinates.y + y),
                        left, up, right, down);
            }
        }
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

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
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
