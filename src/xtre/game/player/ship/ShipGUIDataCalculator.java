package xtre.game.player.ship;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipGUIDataCalculator {
	
	public ShipDataPack stats = new ShipDataPack();
	
	public void updateFuelLevel(boolean usingFuel){
		if(usingFuel) stats.fuelAmount -= stats.fuelEfficiency;
		if(stats.fuelAmount < 1){
			stats.outOfFuel = true;
		}else
			stats.outOfFuel = false;
	}
	
	public void update(float mouseX, float mouseY){

	}
	
	public void render(SpriteBatch batch){
		
	}
}
