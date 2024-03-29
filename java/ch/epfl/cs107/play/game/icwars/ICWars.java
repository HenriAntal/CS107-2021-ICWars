package ch.epfl.cs107.play.game.icwars;

import ch.epfl.cs107.play.game.icwars.actor.players.*;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.icwars.actor.unit.Soldier;
import ch.epfl.cs107.play.game.icwars.actor.unit.Tank;
import ch.epfl.cs107.play.game.icwars.actor.unit.Unit;
import ch.epfl.cs107.play.game.icwars.area.ICWarsArea;
import ch.epfl.cs107.play.game.icwars.area.Level0;
import ch.epfl.cs107.play.game.icwars.area.Level1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public class ICWars extends AreaGame {

    public final static float CAMERA_SCALE_FACTOR = 14.f;
    public Unit[] units1 = new Unit[2];
    public Unit[] units2 = new Unit[2];

    public enum Dynamics {INIT, CHOOSE_PLAYER, START_PLAYER_TURN, PLAYER_TURN, END_PLAYER_TURN, END_TURN, END}
    public Dynamics d;

    private ArrayList<ICWarsPlayer> playersWaitingCurrent = new ArrayList<ICWarsPlayer>();
    private ArrayList<ICWarsPlayer> playersWaitingForNext = new ArrayList<ICWarsPlayer>();
//    private ArrayList<ICWarsPlayer> activePlayer = new ArrayList<>();
    private ICWarsPlayer activePlayer;
    private ArrayList<ICWarsPlayer> playersAmount = new ArrayList<>();

    private final String[] areas = {"icwars/Level0", "icwars/Level1"};
    private int areaIndex = 0;

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
            d = Dynamics.INIT;
            return true;
        }
        return false;
    }

    /**
     * Keyboards for Inputs
     * @return
     */
    public final Keyboard getKeyboard() {
        return super.getWindow().getKeyboard();
    }


    /**
     * we call the initArea to get the Area setup to work properly, we can also adjust the amount of units aswell the amount
     * of RealPlayers in here
     * @param areaKey
     */
    private void initArea(String areaKey) {

        ICWarsArea area = (ICWarsArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        DiscreteCoordinates enemyCoords = area.getEnemySpawnPosition();

        units1[0] = new Soldier(area, new DiscreteCoordinates(3, 5), "ally");
        units1[1] = new Tank(area, new DiscreteCoordinates(2, 5), "ally");

        units2[0] = new Soldier(area, new DiscreteCoordinates(9, 5), "enemy");
        units2[1] = new Tank(area, new DiscreteCoordinates(8, 5), "enemy");

        playersWaitingCurrent.add(new RealPlayer(area, coords, "ally", units1));
        playersWaitingCurrent.add(new RealPlayer(area, enemyCoords, "enemy", units2));

        area.resetUnitList();

        for (ICWarsPlayer player : playersWaitingCurrent) {
            player.enterArea(area, player.getCurrentCells().get(0));
            for (int i = 0; i < player.getUnits().length; ++i) {
                player.getUnits()[i].enterArea(area, player.getUnits()[i].getCurrentCells().get(0));
                area.addUnitToList(player.getUnits()[i]);
            }
        }


        playersAmount.addAll(playersWaitingCurrent);

    }

    @Override
    public void update(float deltaTime) {



        Keyboard keyboard = getKeyboard();

        if (keyboard.get(Keyboard.R).isReleased()) {
            areaIndex = 0;
            playersWaitingCurrent = new ArrayList<ICWarsPlayer>();
            playersWaitingForNext = new ArrayList<ICWarsPlayer>();
            d = Dynamics.INIT;
        }

        if (keyboard.get(Keyboard.N).isReleased()) {
            d = Dynamics.END;
        }

//        if (keyboard.get(Keyboard.U).isReleased()) {
//            ((RealPlayer)player).selectUnit(0); // 0, 1 ...
//            player.gogoSetter();
//            player.gogoReset();
//        }x

        switch (d) {
            case INIT:
                //TODO all players in the list
                System.out.println("INIT");
                playersWaitingCurrent.clear();
                playersAmount.clear();
                playersWaitingForNext.clear();
                initArea(areas[areaIndex]);
                d = Dynamics.CHOOSE_PLAYER;
                break;

            case CHOOSE_PLAYER:
                // TODO CHOOSE_PLAYER If the list of players waiting to play the current round is empty,
                //switch to the stateEND_TURN (end of round); otherwise choose the new active player
                //and remove it from the list of players waiting to play the current round; and go to the
                //state START_PLAYER_TURN;

                System.out.println("Choose Player");
                if (playersWaitingCurrent.isEmpty()) {
                    d = Dynamics.END_TURN;
                } else {
//                    activePlayer.add(playersWaitingCurrent.get(0));
                    activePlayer = playersWaitingCurrent.get(0);
                    activePlayer.centerCamera();

                    playersWaitingCurrent.remove(0);
                    d = Dynamics.START_PLAYER_TURN;
                }
                break;

            case START_PLAYER_TURN:
                //TODO START_PLAYER_TURN invoke method start_turn on the currently active player and
                //switch to the state PLAYER_TURN
                System.out.println("Start Player Turn");
                activePlayer.startTurn();
                d = Dynamics.PLAYER_TURN;
                break;

            case PLAYER_TURN:
                // TODO PLAYER_TURN if the currently active player has finished his turn (his status is changed
                //to IDLE), change to stateEND_PLAYER_TURN;
                activePlayer.centerCamera();
                if (activePlayer.s.equals(ICWarsPlayer.State.IDLE)) {
                    System.out.println("Player Turn");
                    d = Dynamics.END_PLAYER_TURN;
                }
                break;

            case END_PLAYER_TURN:
                System.out.println("END Player Turn");
                // TODO If the currently active player is defeated, remove him from the playing area, other-
                //wise add him to the list of players waiting to play the next round and go to the
                //state CHOOSE_PLAYER ; It will be necessary to ensure that all its units become usable
                //again (from a visualization aspect in particular);
                if(playersWaitingCurrent.size() == 0){
                    d = Dynamics.END_TURN;
                }

                if (activePlayer.getUnits().length == 0) {
                    unitsLeaveArea(activePlayer);
                    activePlayer.leaveArea();
                } else {
                    playersWaitingForNext.add(activePlayer);
                    activePlayer = null;
                    //activePlayer.get(0).s.

                }
                d = Dynamics.CHOOSE_PLAYER;
                break;

            case END_TURN:
                System.out.println("END TURN");
                // TODO END_TURN remove all defeated players (from the list of players waiting to play the
                //next round and from the game in general). If there is only one player left in the list
                //of players waiting for to play in the next round, go to the status END, otherwise move
                //all the players on the list waiting to play the next round to the one waiting to play
                //the current round and return to the state CHOOSE_PLAYER

                if (playersWaitingForNext.size() == 1) {
                    d = Dynamics.END;
                } else {
                    for (ICWarsPlayer player : playersWaitingForNext) {
                        playersWaitingCurrent.add(player);
                        d = Dynamics.CHOOSE_PLAYER;
                    }
                }
                break;
            case END:
                System.out.println("END");
                // TODO manage the end of the game
                playerUnitsLeaveArea(playersWaitingCurrent);
                playerUnitsLeaveArea(playersWaitingForNext);
//                playerUnitsLeaveArea(playersAmount);
                switchArea();
                playersWaitingCurrent = new ArrayList<ICWarsPlayer>();
                playersWaitingForNext = new ArrayList<ICWarsPlayer>();

                d = Dynamics.INIT;
                break;
        }
        super.update(deltaTime);

    }


    public void checkForWin(){

    }

    @Override
    public void end() {
        super.end();
    }

    @Override
    public String getTitle() {
        return "ICWars";
    }


    // either switches to the next level or ends the game.
    protected void switchArea() {

//        for (ICWarsPlayer player : players) {
//            player.leaveArea();
//        }
//        playersWaitingCurrent.clear();
//        playersWaitingForNext.clear();

        if (areaIndex == areas.length - 1) {
            System.out.println("GAME OVER BABEEEE");
            super.getWindow().dispose();
            System.exit(0);


        } else {
            areaIndex += 1;
//            for (int i = 0; i < units1.length; ++i) {
//                units1[i].leaveArea();
//            }
//            initArea(areas[areaIndex]);
            d = Dynamics.INIT;
        }

    }

    public void unitsLeaveArea(ICWarsPlayer player) {
        for (int j = 0; j < player.getUnits().length; ++j) {
            player.getUnits()[j].leaveArea();
        }
    }

    public void playerUnitsLeaveArea (List<ICWarsPlayer> players) {
        for (ICWarsPlayer player : players) {
            unitsLeaveArea(player);
        }
    }

}

