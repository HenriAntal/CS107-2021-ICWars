package ch.epfl.cs107.play.game.tutosSolution;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Ferme;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto2 extends AreaGame {
	
	public final static float CAMERA_SCALE_FACTOR = 13.f;

	private GhostPlayer player;
	private final String[] areas = {"zelda/Ferme", "zelda/Village"};
	
	private int areaIndex;
	/**
	 * Add all the areas
	 */
	private void createAreas(){

		addArea(new Ferme());
		addArea(new Village());

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
		if(player.isWeak()){
			switchArea();
		}

	}

	private void moveIfPressed(String letter, Button b){
		if(b.isDown() && (letter.equals("R"))) {
			begin(getWindow(), getFileSystem());
		}

		if(b.isDown() && (letter.equals("N"))){
			switchArea();
		}
	}



	@Override
	public void end() {
	}

	@Override
	public String getTitle() {
		return "Tuto2";
	}

	protected void switchArea() {

		player.leaveArea();

		areaIndex = (areaIndex==0) ? 1 : 0;

		Tuto2Area currentArea = (Tuto2Area)setCurrentArea(areas[areaIndex], false);
		player.enterArea(currentArea, currentArea.getPlayerSpawnPosition());

		player.strengthen();
	}

}
