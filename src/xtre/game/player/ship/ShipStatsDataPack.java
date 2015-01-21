package xtre.game.player.ship;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;

public class ShipStatsDataPack {
	
	public float x=0, y=0;
	
	public boolean slowing = false;
	
	public float fuelAmount = 100;
	public float fuelEfficiency = .005f;

	public boolean outOfFuel;
	
	ShipTile[] shipTiles;
}
