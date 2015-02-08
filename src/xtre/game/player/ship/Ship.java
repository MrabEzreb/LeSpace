package xtre.game.player.ship;

import xtre.game.player.ship.body_physics.ShipBodyPhysics;
import xtre.globals.GlobalScreen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Ship {

	public ShipBodyPhysics physics;
	public Inventory inventory;
	
	public Sprite sprite;
	private float x=0, y=0;

	
	private ShipGUIDataCalculator shipGUICalculator;
	
	public Ship(float x, float y, Sprite sprite, World world){
		System.out.println("ship");
		shipGUICalculator = new ShipGUIDataCalculator();
		shipGUICalculator.stats.shipX = x;
		shipGUICalculator.stats.shipY = y;
		
		this.sprite = sprite;

		physics = new ShipBodyPhysics(x, y, world);

	}
	
	public void update(float camX, float camY, float mouseX, float mouseY, boolean mouseLeftPress){
		updateCanMove();
		shipGUICalculator.update(mouseX, mouseY);
	}

	public void render(SpriteBatch batch){
		sprite.draw(batch);
		
	}
	
	private void updateCanMove(){
		x = physics.body.getPosition().x;
		y = physics.body.getPosition().y;

		float sox = (GlobalScreen.GAME_WIDTH/2) - (sprite.getWidth()/2), soy = (GlobalScreen.GAME_HEIGHT/2) - (sprite.getHeight()/2);
		sprite.setPosition(sox, soy);
		sprite.setRotation(physics.shipRotationalAngle);
		
		if(shipGUICalculator.stats.outOfFuel)
			physics.applyForce = false;
		else
			physics.applyForce = true;
	}

	public void dispose() {
		physics.dispose();
	}

	public void slowToStop(boolean applyForce) {
		shipGUICalculator.stats.applyForce = applyForce;
	}

	public float getFuelAmount() {
		return shipGUICalculator.stats.fuelAmount;
	}
	

	
//	private void createInventory(){
//		Sprite[] s = new Sprite[]{
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tm),
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bm),
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_lm),
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_rm),
//				
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_mm),
//				
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tl),
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tr),
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bl),
//				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_br),
//		};
//		
//		inventory = new Inventory(gim, shipStorageSizeX, shipStorageSizeY, s);
//	}

	public ShipDataPack getStats() {
		return shipGUICalculator.stats;
	}

	public Vector2 getPosition() {
		return physics.body.getPosition();
	}
}
