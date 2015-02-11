package xtre.game.scene_manager;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.player.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameScene {
	public final World world;
	public final Player player;
	
	public GameScene(World world, Player player){
		this.world = world;
		this.player = player;
	}
	
	protected GameInterfaceManager gim;

	public abstract void updateInterfaces(float camX, float camY);
	public abstract void updateInteractives(float camX, float camY);
	public abstract void renderInterfaces(SpriteBatch batch);
	public abstract void renderInteractives(SpriteBatch batch);
	public abstract void dispose();
	
}
