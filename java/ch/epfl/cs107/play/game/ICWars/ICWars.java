package ch.epfl.cs107.play.game.ICWars;

import ch.epfl.cs107.play.game.ICWars.actor.players.Soldier;
import ch.epfl.cs107.play.game.ICWars.actor.players.Tank;
import ch.epfl.cs107.play.game.ICWars.actor.players.Unit;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.ICWars.actor.players.RealPlayer;
import ch.epfl.cs107.play.game.ICWars.area.ICWarsArea;
import ch.epfl.cs107.play.game.ICWars.area.Level0;
import ch.epfl.cs107.play.game.ICWars.area.Level1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public class ICWars extends AreaGame {

    public final static float CAMERA_SCALE_FACTOR = 14.f;
    public Unit[] units1 = new Unit[2];
    //public ArrayList<Unit> units2 = new ArrayList<Unit>();

    private RealPlayer player;
    private final String[] areas = {"icwars/Level0", "icwars/Level1"};

    private int areaIndex;

    /**
     * Add all the areas
     */
    private void createAreas() {

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
    public final Keyboard getKeyboard() {
        return super.getWindow().getKeyboard();
    }

    private void initArea(String areaKey) {

        ICWarsArea area = (ICWarsArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();

        units1[0] = new Soldier(area , new DiscreteCoordinates(8,4),"ally");
        units1[1] = new Tank(area , new DiscreteCoordinates(8,7),"ally");

        player = new RealPlayer(area, coords, "ally", units1);
        player.enterArea(area, coords);
        player.centerCamera();

    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getKeyboard();

        if (keyboard.get(Keyboard.R).isReleased()) {
            begin(getWindow(), getFileSystem());
        }

        if (keyboard.get(Keyboard.N).isReleased()) {
            switchArea();
        }

        if (keyboard.get(Keyboard.ENTER).isReleased()) {
            ((RealPlayer)player).selectUnit(0); // 0, 1 ...
        }

        super.update(deltaTime);

    }


    @Override
    public void end() {
        super.end();
    }

    @Override
    public String getTitle() {
        return "ICWars";
    }

    protected void switchArea() {

        player.leaveArea();

        if (areaIndex == areas.length - 1) {
            System.out.println("GAME OVER BABEEEE");
            super.getWindow().dispose();
            System.exit(0);


        } else {
            areaIndex += 1;
            for (int i = 0; i < units1.length; ++i) {
                units1[i].leaveArea();
            }
            initArea(areas[areaIndex]);
        }



    }

}

