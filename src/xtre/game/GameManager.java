package xtre.game;

import xtre.game.heads_up_display.HUDManager;
import xtre.game.player.Player;
import xtre.game.world.SpaceWorld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager {
	
	public SpaceWorld spaceWorld;
	private HUDManager hudManager;
	
	public GameManager(){
		System.out.println("game_manager");
		
		hudManager = new HUDManager();
		
		//SpaceWorld
		spaceWorld = new SpaceWorld(hudManager);
		
	}
	
	public Player getPlayer(int playerID){
		return spaceWorld.player[playerID];
	}

	public void render(SpriteBatch batch, float dt) {
		spaceWorld.render(batch);
		hudManager.render(batch);
	}
		
	public void update(float camX, float camY){
		hudManager.update();
		spaceWorld.update(camX, camY);
	}
	
	public void updateDegugMonitor(int data){
		
	}
	
	public void dispose(){
		spaceWorld.dispose();
		hudManager.dispose();
	}
	
	
	
}
