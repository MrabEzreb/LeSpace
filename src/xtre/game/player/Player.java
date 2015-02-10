package xtre.game.player;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.player.PlayerInterface;
import xtre.game.player.ship.Ship;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Player {
	
	public Ship ship;
	private PlayerInterface playersGUI;
	
	public Player(float x, float y, Sprite sprite, World world, GameInterfaceManager gim) {
		ship = new Ship(x, y, sprite, world);
		playersGUI = new PlayerInterface(gim, this);
	}
	
	public void update(float camX, float camY){
		if(!playersGUI.setMetreLevel(ship.getStats().fuelAmount)){
			ship.getStats().outOfFuel=true;
			System.out.println("(Player.java:74) out of fuel");
		}
		ship.update(camX, camY);
		ship.physics.update();
	}
	
	public void render(SpriteBatch batch){
		ship.render(batch);
	}
}
