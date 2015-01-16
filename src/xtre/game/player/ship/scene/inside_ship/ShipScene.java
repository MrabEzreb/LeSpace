package xtre.game.player.ship.scene.inside_ship;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.player.Player;
import xtre.game.player.ship.Ship;
import xtre.game.player.ship.scene.inside_ship.graphics.InsideShip;
import xtre.game.scene_manager.GameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class ShipScene extends GameScene {
	
	private GameInterfaceManager gim;
	private Ship ship;
	
	private InsideShip insideShip;
	
	public ShipScene(GameInterfaceManager gim, World world, Player player){
		super(world, player);
		insideShip = new InsideShip();
	}

	public void setGameInterfaceManager(GameInterfaceManager gim){
		this.gim = gim;
	}
	
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
	@Override
	public void update(float camX, float camY, float mouseX, float mouseY, boolean mouseLeftPress) {
		insideShip.update(mouseX, mouseY, mouseLeftPress);
	}
	
	@Override
	public void render(SpriteBatch batch){
		insideShip.render(batch);
	}

	@Override
	public void dispose() {
		insideShip.dispose();
	}
}