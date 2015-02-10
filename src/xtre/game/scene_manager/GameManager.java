package xtre.game.scene_manager;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.menus.InGameMenu;
import xtre.game.player.Player;
import xtre.game.space_world.SpaceScene;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpritesSpaceGame;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameManager {

	public GameScene scene;
	private Player player;
	private World world = new World(new Vector2(0,0), true);
	private InGameMenu inGameMenu;
	
	private SceneChanger sceneChanger;
	
	private GameInterfaceManager gim;
	
	/**
	 *  Manages the scene;
	 *  From either Space or on a planet and then others soon.
	 */

	public GameManager(){
		gim = new GameInterfaceManager(this);
		
		player = new Player(0, 0, GameSprite.getSprite(SpritesSpaceGame.player_ship), world, gim);
		
		sceneChanger = new SceneChanger(this, gim, world, player);
		scene = new SpaceScene(gim, world, player);
		
		inGameMenu = new InGameMenu();
	}
	
	public Player getPlayer(int playerID){
		return player;
	}
	
	public void update(float camX, float camY){		
		inGameMenu.update();
		if(InGameMenu.open)
			gim.updateInterfaces();
		else {
			gim.updateInteractives();
			gim.updateInterfaces();
		}
		System.out.println(InGameMenu.open);
			
		scene.update(camX, camY);
	}
	
	public void render(SpriteBatch batch, float dt){
		inGameMenu.render(batch);
		scene.render(batch);
		
		if(!InGameMenu.open){
			gim.renderInteractives(batch);
			gim.renderInterfaces(batch);
		}else
			gim.renderInterfaces(batch);
	}

	public void updateDegugMonitor(int data){
		
	}
	
	public void dispose(){
		scene.dispose();
		gim.dispose();
	}

	public void setScene(GameScene scene) {
		gim.closeFrames();
		this.scene = scene;
	}

	public SceneChanger setScene() {
		gim.reset();
		return sceneChanger;
	}
}
