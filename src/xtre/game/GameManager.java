package xtre.game;

import xtre.game.game_gui.heads_up_display.utils.HUDManager;
import xtre.game.game_gui.space_gui.PlayerInterface;
import xtre.game.in_space.player.Player;
import xtre.game.world.space_scene.SpaceScene;
import xtre.globals.hud.GameInputs;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager {
	
	public SpaceScene spaceWorld;
	
	private HUDManager hudManager;
	private PlayerInterface playersGUI;
	
	private GameInputs inputs = new GameInputs();
	
	/**
	 *  Manages the scene;
	 *  From either Space or on a planet and then others soon.
	 */

	public GameManager(){
		hudManager = new HUDManager();
		
		spaceWorld = new SpaceScene(hudManager);
		
		playersGUI = new PlayerInterface();
	}
	
	public Player getPlayer(int playerID){
		return spaceWorld.player[playerID];
	}

	public void render(SpriteBatch batch, float dt) {
		spaceWorld.render(batch);
		hudManager.render(batch);
		playersGUI.render(batch);
	}
	
	public void update(float camX, float camY, float mouseX, float mouseY){
		boolean justPressedLeftMouseButton = false;
		
		if(inputs.mouseJustClicked(Buttons.LEFT))
			justPressedLeftMouseButton = true;
		
		hudManager.update(mouseX, mouseY, justPressedLeftMouseButton);
		spaceWorld.update(camX, camY, mouseX, mouseY, justPressedLeftMouseButton);
		playersGUI.updateInterface(mouseX, mouseY, justPressedLeftMouseButton);
	}
	
	public void updateDegugMonitor(int data){
		
	}
	
	public void dispose(){
		spaceWorld.dispose();
		hudManager.dispose();
	}
}
