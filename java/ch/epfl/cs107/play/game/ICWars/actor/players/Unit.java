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

    ICWarsRange range;

    public Unit(Area owner, DiscreteCoordinates coordinates, String belongs ){
        super(owner, coordinates, belongs);

        if(belongs.equals("ally")){
            allyList.add(this);
        } else {
            enemyList.add(this);
        }

        range = new ICWarsRange(owner, coordinates, maxRange);

        // somehow need to include damage taken (- Hp)
        // also need to include healing (+ Hp)

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
    public void drawRangeAndPathTo(DiscreteCoordinates destination ,
                                   Canvas canvas) {
        range.draw(canvas);
        Queue<Orientation> path =
                range.shortestPath(getCurrentMainCellCoordinates(), destination);
        //Draw path only if it exists (destination inside the range)
        if (path != null){
            new Path(getCurrentMainCellCoordinates().toVector(), path).draw(canvas);
        }
    }

}
