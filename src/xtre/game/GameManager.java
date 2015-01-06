package xtre.game;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.player.Player;
import xtre.game.space_world.SpaceScene;
import xtre.globals.game_interface.hud.GameInputs;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager {
	
	public SpaceScene spaceWorld;
	
	private GameInterfaceManager gim;
	
	private GameInputs inputs = new GameInputs();
	
	/**
	 *  Manages the scene;
	 *  From either Space or on a planet and then others soon.
	 */

	public GameManager(){
		gim = new GameInterfaceManager();
		spaceWorld = new SpaceScene(gim);
	}
	
	public Player getPlayer(int playerID){
		return spaceWorld.player[playerID];
	}

	public void render(SpriteBatch batch, float dt) {
		spaceWorld.render(batch);
		gim.render(batch);
	}
	
	public void update(float camX, float camY, float mouseX, float mouseY){
		boolean justPressedLeftMouseButton = false;
		
		if(inputs.mouseJustClicked(Buttons.LEFT))
			justPressedLeftMouseButton = true;
		
		gim.update(mouseX, mouseY, justPressedLeftMouseButton);
		spaceWorld.update(camX, camY, mouseX, mouseY, justPressedLeftMouseButton);
	}
	
	public void updateDegugMonitor(int data){
		
	}
	
	public void dispose(){
		spaceWorld.dispose();
		gim.dispose();
	}
}
