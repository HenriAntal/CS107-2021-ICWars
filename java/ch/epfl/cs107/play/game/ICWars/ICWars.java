package ch.epfl.cs107.play.game.ICWars;

import ch.epfl.cs107.play.game.ICWars.actor.player.Soldier;
import ch.epfl.cs107.play.game.ICWars.actor.player.Tank;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.ICWars.actor.player.RealPlayer;
import ch.epfl.cs107.play.game.ICWars.area.ICWarsArea;
import ch.epfl.cs107.play.game.ICWars.area.Level0;
import ch.epfl.cs107.play.game.ICWars.area.Level1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import static ch.epfl.cs107.play.game.areagame.actor.Orientation.*;

public class ICWars extends AreaGame{

        public final static float CAMERA_SCALE_FACTOR = 10.f;

        private RealPlayer player;
        private Soldier soldier;
        private Tank tank;
        private final String[] areas = {"icwars/Level0", "icwars/Level1"};

        private int areaIndex;
        /**
         * Add all the areas
         */
        private void createAreas(){

            addArea(new Level0());
            addArea(new Level1());
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



        // N and R to restart or skip levels
        public final Keyboard getKeyboard () {
            return super.getWindow().getKeyboard();
        }

        @Override
        public void update(float deltaTime) {
            Keyboard keyboard = getKeyboard();

            moveIfPressed("R", keyboard.get(Keyboard.R));
            moveIfPressed("N", keyboard.get(Keyboard.N));


        super.update(deltaTime);

        }

        private void moveIfPressed(String letter, Button b){
            if(b.isDown() && (letter.equals("R"))) {
                begin(getWindow(), getFileSystem());
            }

            if(b.isDown() && (letter.equals("N"))){
                switchArea();
            }
        }

        private void initArea(String areaKey) {

            ICWarsArea area = (ICWarsArea) setCurrentArea(areaKey, true);
            DiscreteCoordinates coords = area.getPlayerSpawnPosition();
            player = new RealPlayer(area, coords,"ally");
            //soldier = new Unit.Soldier(area, UP,  coords, "ally");
            player.enterArea(area, coords);
            player.centerCamera();

        }

        /*

        @Override
        public void update(float deltaTime) {
            super.update(deltaTime);

        }
        */

        @Override
        public void end() {
        }

        @Override
        public String getTitle() {
            return "ICWars";
        }

        protected void switchArea() {

            player.leaveArea();

            if(areaIndex > areas.length) {
                System.out.println("GAME OVER BABEEEE");
                end();
            } else {
                areaIndex += 1;
                initArea(areas[areaIndex]);
            }


            ICWarsArea currentArea = (ICWarsArea) setCurrentArea(areas[areaIndex], false);
            player.enterArea(currentArea, currentArea.getPlayerSpawnPosition());

        }

    }

