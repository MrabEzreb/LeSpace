package xtre.game.player.ship;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;

public class ShipDataPack {
	
	public float shipX=0, shipY=0;
	
	public boolean applyForce = false;
	
	public float fuelAmount = 100;
	public float fuelEfficiency = .005f;

	public boolean outOfFuel;
	
	ShipTile[] shipTiles;
}
