package ch.epfl.cs107.play.game.icwars;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icwars.actor.RealPlayer;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Ferme;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class ICWars extends AreaGame{
//TODO it's file from tuto2 make it fit ICWars.
        public final static float CAMERA_SCALE_FACTOR = 13.f;

        private RealPlayer player;
        private final String[] areas = {"zelda/Ferme", "zelda/Village"};

        private int areaIndex;
        /**
         * Add all the areas
         */
        private void createAreas(){

            addArea(new Ferme());
            addArea(new Village());
//haha
        }

        @Override
        public boolean begin(Window window, FileSystem fileSystem) {


            if (super.begin(window, fileSystem)) {
                createAreas();
                areaIndex = 0;
                initArea(areas[areaIndex]);
                return true;
            }
            return false;
        }

        private void initArea(String areaKey) {

            Tuto2Area area = (Tuto2Area)setCurrentArea(areaKey, true);
            DiscreteCoordinates coords = area.getPlayerSpawnPosition();
            player = new GhostPlayer(area, Orientation.DOWN, coords,"ghost.1");
            player.enterArea(area, coords);
            player.centerCamera();

        }
        @Override
        public void update(float deltaTime) {
            if(player.isWeak()){
                switchArea();
            }
            super.update(deltaTime);

        }

        @Override
        public void end() {
        }

        @Override
        public String getTitle() {
            return "ICWars";
        }

        protected void switchArea() {

            player.leaveArea();

            areaIndex = (areaIndex==0) ? 1 : 0;

            Tuto2Area currentArea = (Tuto2Area)setCurrentArea(areas[areaIndex], false);
            player.enterArea(currentArea, currentArea.getPlayerSpawnPosition());

            player.strengthen();
        }

    }

