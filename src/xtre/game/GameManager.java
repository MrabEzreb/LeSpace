package xtre.game;

import xtre.game.game_gui.heads_up_display.HUDManager;
import xtre.game.game_gui.player.PlayerInterface;
import xtre.game.player.Player;
import xtre.game.space_world.SpaceScene;
import xtre.globals.hud.GameInputs;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager {
	
	public SpaceScene spaceWorld;
	
	private HUDManager hudManager;
	
	private GameInputs inputs = new GameInputs();
	
	/**
	 *  Manages the scene;
	 *  From either Space or on a planet and then others soon.
	 */

	public GameManager(){
		hudManager = new HUDManager();
		spaceWorld = new SpaceScene(hudManager);
	}
	
	public Player getPlayer(int playerID){
		return spaceWorld.player[playerID];
	}

	public void render(SpriteBatch batch, float dt) {
		spaceWorld.render(batch);
		hudManager.render(batch);
	}
	
	public void update(float camX, float camY, float mouseX, float mouseY){
		boolean justPressedLeftMouseButton = false;
		
		if(inputs.mouseJustClicked(Buttons.LEFT))
			justPressedLeftMouseButton = true;
		
		hudManager.update(mouseX, mouseY, justPressedLeftMouseButton);
		spaceWorld.update(camX, camY, mouseX, mouseY, justPressedLeftMouseButton);
	}
	
	public void updateDegugMonitor(int data){
		
	}
	
	public void dispose(){
		spaceWorld.dispose();
		hudManager.dispose();
	}
}
