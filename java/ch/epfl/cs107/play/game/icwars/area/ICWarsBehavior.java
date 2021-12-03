package ch.epfl.cs107.play.game.icwars.area;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior;
import ch.epfl.cs107.play.window.Window;

public class ICWarsBehavior extends AreaBehavior {
    //TODO update to fit ICWars.

        public enum ICWarsCellType{
            //https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
            NULL(0, false),
            WALL(-16777216, false),
            IMPASSABLE(-8750470, false),
            INTERACT(-256, true),
            DOOR(-195580, true),
            WALKABLE(-1, true),;

            /*
            NONE(0,0), // Should never be used except
            // in the toType method
            ROAD(-16777216, 0), // the second value is the number
            // of defense stars
            PLAIN(-14112955, 1),
            WOOD(-65536, 3),
            RIVER(-16776961, 0),
            MOUNTAIN(-256, 4),
            CITY(-1,2);

            gets added later
            */


            final int type;
            final boolean isWalkable;

            ICWarsCellType(int type, boolean isWalkable){
                this.type = type;
                this.isWalkable = isWalkable;
            }

            public static ICWarsBehavior.ICWarsCellType toType(int type){
                for(ICWarsBehavior.ICWarsCellType ict : ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCellType.values()){
                    if(ict.type == type)
                        return ict;
                }
                // When you add a new color, you can print the int value here before assign it to a type
                System.out.println(type);
                return NULL;
            }
        }

        /**
         * Default Tuto2Behavior Constructor
         * @param window (Window), not null
         * @param name (String): Name of the Behavior, not null
         */
        public ICWarsBehavior(Window window, String name){
            super(window, name);
            int height = getHeight();
            int width = getWidth();
            for(int y = 0; y < height; y++) {
                for (int x = 0; x < width ; x++) {
                    ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCellType color = ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCellType.toType(getRGB(height-1-y, x));
                    setCell(x,y, new ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCell(x,y,color));
                }
            }
        }

        /**
         * Cell adapted to the Tuto2 game
         */
        public class ICWarsCell extends AreaBehavior.Cell {
            /// Type of the cell following the enum
            private final ICWarsBehavior.ICWarsCellType type;

            /**
             * Default Tuto2Cell Constructor
             * @param x (int): x coordinate of the cell
             * @param y (int): y coordinate of the cell
             * @param type (EnigmeCellType), not null
             */
            public  ICWarsCell(int x, int y, ch.epfl.cs107.play.game.icwars.area.ICWarsBehavior.ICWarsCellType type){
                super(x, y);
                this.type = type;
            }

            @Override
            protected boolean canLeave(Interactable entity) {
                return true;
            }


            // TODO France check french on page 31 TUTO
            @Override
            protected boolean canEnter(Interactable entity) {
                if (getCurrentCells().isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            }


            @Override
            public boolean isCellInteractable() {
                return true;
            }

            @Override
            public boolean isViewInteractable() {
                return false;
            }

            @Override
            public void acceptInteraction(AreaInteractionVisitor v) {
            }

        }
    }


