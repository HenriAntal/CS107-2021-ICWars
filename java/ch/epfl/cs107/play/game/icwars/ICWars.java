package ch.epfl.cs107.play.game.icwars;

import ch.epfl.cs107.play.game.icwars.actor.players.*;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.area.Level0;
import ch.epfl.cs107.play.game.icwars.area.Level1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICWars extends AreaGame {

    public final static float CAMERA_SCALE_FACTOR = 14.f;
    public Unit[] units1 = new Unit[2];
    public Unit[] units2 = new Unit[2];

    public enum Dynamics {INIT, CHOOSE_PLAYER, START_PLAYER_TURN, PLAYER_TURN, END_PLAYER_TURN, END_TURN, END}

    public Dynamics d;
    //public ArrayList<Unit> units2 = new ArrayList<Unit>();

    private RealPlayer[] player = new RealPlayer[2];

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
        DiscreteCoordinates enemyCoords = area.getEnemySpawnPosition();

        units1[0] = new Soldier(area, new DiscreteCoordinates(3, 5), "ally");
        units1[1] = new Tank(area, new DiscreteCoordinates(2, 5), "ally");

        units2[0] = new Soldier(area, new DiscreteCoordinates(8, 5), "enemy");
        units2[1] = new Tank(area, new DiscreteCoordinates(9, 5), "enemy");

        player[0] = new RealPlayer(area, coords, "ally", units1);
        player[1] = new RealPlayer(area, enemyCoords, "enemy", units2);

        player[0].enterArea(area, coords);
        //player[1].enterArea(area, enemyCoords);

        player[0].centerCamera();
        player[0].s = ICWarsPlayer.State.NORMAL;
    }

    @Override
    public void update(float deltaTime) {


        switch (d) {
            case INIT:

                break;

            case CHOOSE_PLAYER:

                break;

            case START_PLAYER_TURN:

                break;

            case PLAYER_TURN:

                break;

            case END_PLAYER_TURN:

                break;

            case END_TURN:

                break;

            case END:

                break;
        }

        Keyboard keyboard = getKeyboard();

        if (keyboard.get(Keyboard.R).isReleased()) {
            begin(getWindow(), getFileSystem());
        }

        if (keyboard.get(Keyboard.N).isReleased()) {
            switchArea();
        }

//        if (keyboard.get(Keyboard.U).isReleased()) {
//            ((RealPlayer)player).selectUnit(0); // 0, 1 ...
//            player.gogoSetter();
//            player.gogoReset();
//        }

        if (units1.length == 0) {
            System.out.println("Enemy Won!");
            switchArea();
        }
        if (units2.length == 0) {
            System.out.println("You Won!");
            switchArea();
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

        player[0].leaveArea();
//        player[1].leaveArea();

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

