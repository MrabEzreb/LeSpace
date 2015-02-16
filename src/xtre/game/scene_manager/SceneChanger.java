package xtre.game.scene_manager;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.player.Player;
import xtre.game.player.ship.scene.inside_ship.ShipScene;

import com.badlogic.gdx.physics.box2d.World;

public class SceneChanger {
	
	private GameManager gm;
	private GameInterfaceManager gim;
	private World world;
	private Player player;
	
	public SceneChanger(GameManager gm, GameInterfaceManager gim, World world, Player player){
		this.gm = gm;
		this.gim = gim;
		this.world = world;
		this.player = player;
	}
	
	public final void interiorShip(){
		gm.setScene(new ShipScene(gim, world, player));
	}
}
