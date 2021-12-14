package ch.epfl.cs107.play.game.ICWars.area;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Queue;

/**
 * A drawable AreaGraph
 */
public class ICWarsRange extends AreaGraph implements Graphics {

    public ICWarsRange(Area owner, DiscreteCoordinates coordinates, int maxRange) {

    }

    @Override
    public void addNode(DiscreteCoordinates coordinates, boolean hasLeftEdge, boolean hasUpEdge, boolean hasRightEdge, boolean hasDownEdge) {
        if (hasLeftEdge && hasUpEdge && hasRightEdge && hasDownEdge)
        getNodes().putIfAbsent(coordinates, new RangeNode(coordinates, hasLeftEdge, hasUpEdge, hasRightEdge, hasDownEdge));
    }

    private class RangeNode extends AreaNode implements Graphics {
        private final ImageGraphics nodeSprite;

        private RangeNode(DiscreteCoordinates coordinates, boolean hasLeftEdge, boolean hasUpEdge, boolean hasRightEdge, boolean hasDownEdge) {
            super(coordinates, hasLeftEdge, hasUpEdge, hasRightEdge, hasDownEdge);

            if (!hasUpEdge && !hasRightEdge && !hasDownEdge && !hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(3 * 18, 5 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (!hasUpEdge && hasRightEdge && hasDownEdge && !hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(0 * 18, 5 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (!hasUpEdge && hasRightEdge && hasDownEdge && hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(1 * 18, 5 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (!hasUpEdge && !hasRightEdge && hasDownEdge && hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(2 * 18, 5 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (hasUpEdge && !hasRightEdge && hasDownEdge && hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(2 * 18, 6 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (hasUpEdge && !hasRightEdge && !hasDownEdge && hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(2 * 18, 7 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (hasUpEdge && hasRightEdge && !hasDownEdge && hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(1 * 18, 7 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (hasUpEdge && hasRightEdge && !hasDownEdge && !hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(0 * 18, 7 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else if (hasUpEdge && hasRightEdge && hasDownEdge && !hasLeftEdge)
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(0 * 18, 6 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);

            else
                nodeSprite = new ImageGraphics(ResourcePath.getSprite("icwars/UIpackSheet"), 1f, 1f,
                        new RegionOfInterest(1 * 18, 6 * 18, 16, 16), coordinates.toVector(), 0.6f, 500);
        }

        @Override
        public void draw(Canvas canvas) {
            nodeSprite.draw(canvas);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (AreaNode node : getNodes().values()) {
            ((RangeNode) node).draw(canvas);
        }
    }

    /**
     * Draw the unit's range and a path from the unit position to
     * destination
     *
     * @param destination path destination
     * @param canvas      canvas
     */
    /*
    public void drawRangeAndPathTo(DiscreteCoordinates destination, Canvas canvas) {

        ICWarsRange range = null;
        assert range != null;
        range.draw(canvas);
        Queue<Orientation> path = range.shortestPath(getCurrentMainCellCoordinates(), destination);

//Draw path only if it exists (destination inside the range)

        if (path != null) {new Path(getCurrentMainCellCoordinates().toVector(), path).draw(canvas);
        }
    }*/

    private DiscreteCoordinates getCurrentMainCellCoordinates() {

        return null;
    }
}
