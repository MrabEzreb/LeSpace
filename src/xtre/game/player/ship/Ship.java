package xtre.game.player.ship;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.globals.GlobalScreen;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ship {

	public Inventory inventory;
	private GameInterfaceManager gim;
	
	private int shipStorageSizeX = 3, shipStorageSizeY = 3;
	
	public Sprite sprite;

	public Body body;
	public BodyDef bodyDef;
	public FixtureDef fixtureDef;
	
	private ShipStatsDataPack stats = new ShipStatsDataPack();
	
	public Ship(GameInterfaceManager gim, float x, float y, Sprite sprite, World world){
		System.out.println("ship");
		this.gim = gim;
		stats.x = x;
		stats.y = y;
		this.sprite = sprite;

		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(GlobalScreen.MPP(15.5f));
		
//		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(5,5);

		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.groupIndex = -1;
		fixtureDef.density = 1.9f;
		fixtureDef.friction = 11.0f;
		fixtureDef.restitution = .2f;

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setTransform(GlobalScreen.MPP(x), GlobalScreen.MPP(y), 0);
		Vector2 v = new Vector2();
		body.getWorldVector(v);
		shape.dispose();
	
		createInventory();
	}
	
	public void update(float camX, float camY, float mouseX, float mouseY, boolean mouseLeftPress){
		updateFuelLevel(updateMovement(mouseX, mouseY));
	}

	public void render(SpriteBatch batch){
		sprite.draw(batch);
	}
	
	private void updateFuelLevel(boolean usingFuel){
		if(usingFuel) stats.fuelAmount -= stats.fuelEfficiency;
	}
	
	private Vector2 force = new Vector2();
	private Vector2 mp = new Vector2();
	
	private boolean updateMovement(float mouseX, float mouseY){
		boolean usingFuel = false;
		if(!stats.outOfFuel){
			mp.x = mouseX;
			mp.y = mouseY;
			
			//float angle = MathUtils.atan2((ScreenGlobals.HEIGHT/2) - mp.y, (ScreenGlobals.WIDTH/2) - mp.x);
			
			float xx = MathUtils.cos(body.getAngle());
			float yy = MathUtils.sin(body.getAngle());
			
			if(!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
				body.setLinearDamping(.2f);
			else
				body.setLinearDamping(0);
			body.setAngularDamping(4);
	
			force.x = (xx*MathUtils.radiansToDegrees)/20;
			force.y = (yy*MathUtils.radiansToDegrees)/20;
			
			if(!stats.slowing && Math.abs(body.getLinearVelocity().x+body.getLinearVelocity().y) < 10){
				if(Gdx.input.isKeyPressed(Keys.A)) {
					usingFuel = true;
					body.applyTorque(0.7f, true);
				}
				if(Gdx.input.isKeyPressed(Keys.D)){
					usingFuel = true;
					body.applyTorque(-0.7f, true);
				}		
				
				if(Gdx.input.isKeyPressed(Keys.W)){
					usingFuel = true;
					body.applyForceToCenter(force, true);
					
					if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
						stats.fuelEfficiency = .05f;
						body.applyForceToCenter(force, true);
						body.setLinearDamping(0);
						
						float sox = (GlobalScreen.GAME_WIDTH/2) + (stats.x-sprite.getHeight()/2), soy = (GlobalScreen.GAME_HEIGHT/2) + (stats.y-sprite.getWidth()/2);
	
						sprite.setPosition(sox, soy);
						sprite.setRotation((body.getAngle()-(90*MathUtils.degreesToRadians))*MathUtils.radiansToDegrees);
						return usingFuel;
					}else{
						stats.fuelEfficiency = .0005f;
					}
				}else if(Gdx.input.isKeyPressed(Keys.S)){
					usingFuel = true;
					body.setLinearDamping(0.78f);
	//				body.applyForceToCenter(-(force.x*3)/4, -(force.y*3)/4, true);
				}
			}else{
				body.setLinearDamping(0.50f);
			}

			//Set sprite position to match body position
			float sox = (GlobalScreen.GAME_WIDTH/2) + (stats.x-sprite.getHeight()/2), soy = (GlobalScreen.GAME_HEIGHT/2) + (stats.y-sprite.getWidth()/2);
	
			sprite.setPosition(sox, soy);
			sprite.setRotation((body.getAngle()-(90*MathUtils.degreesToRadians))*MathUtils.radiansToDegrees);
		}
		return usingFuel;
	}

	public void create(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}

	public void dispose() {
	}

	public void slowToStop(boolean slow) {
		stats.slowing = slow;
	}

	public float getFuelAmount() {
		return stats.fuelAmount;
	}
	

	
	private void createInventory(){
		Sprite[] s = new Sprite[]{
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tm),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bm),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_lm),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_rm),
				
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_mm),
				
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tl),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tr),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bl),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_br),
		};
		
		inventory = new Inventory(gim, shipStorageSizeX, shipStorageSizeY, s);
	}

	public ShipStatsDataPack getStats() {
		return stats;
	}
}
