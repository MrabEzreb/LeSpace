package xtre.game.player;

import xtre.game.physics_objects.PhysicsEntity;
import xtre.globals.ScreenGlobals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends PhysicsEntity {
	
	public boolean slowing = false;
	
	public Player(float x, float y, Sprite sprite, World world) {
		System.out.println("player");
		this.x = x;
		this.y = y;
		this.sprite = sprite;

		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(ScreenGlobals.MPP(15.5f));
		
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
		body.setTransform(ScreenGlobals.MPP(x), ScreenGlobals.MPP(y), 0);
		Vector2 v = new Vector2();
		body.getWorldVector(v);
		shape.dispose();
	
	}
	
	private Vector2 force = new Vector2();
	private Vector2 point = new Vector2();
	private Vector2 mp = new Vector2();
	
	public void update(float camX, float camY, float mouseX, float mouseY){
		mp.x = mouseX;
		mp.y = mouseY;
		
		float angle = MathUtils.atan2((ScreenGlobals.HEIGHT/2) - mp.y, (ScreenGlobals.WIDTH/2) - mp.x);
		
		float xx = MathUtils.cos(body.getAngle());
		float yy = MathUtils.sin(body.getAngle());
		
		
		body.setAngularDamping(4);

		force.x = (xx*MathUtils.radiansToDegrees)/20;
		force.y = (yy*MathUtils.radiansToDegrees)/20;
		
		point.x = 01;
		point.y = -5;

		if(!slowing){
			if(Gdx.input.isKeyPressed(Keys.A)) {
				body.applyTorque(2, true);
			}
			if(Gdx.input.isKeyPressed(Keys.D)){
				body.applyTorque(-2, true);
			}		
			
	
			if(Gdx.input.isKeyPressed(Keys.W)){
				body.applyForceToCenter(force, true);
				
				if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
					body.applyForceToCenter(force, true);
	
				}
				
			}else if(Gdx.input.isKeyPressed(Keys.S)){
				body.applyForceToCenter(-(force.x*3)/4, -(force.y*3)/4, true);
			}
			body.setLinearDamping(0);
		}else{
			body.setLinearDamping(1);
		}

		//Set sprite position to match body position
		float sox = (ScreenGlobals.WIDTH/2) + (x-sprite.getHeight()/2), soy = (ScreenGlobals.HEIGHT/2) + (y-sprite.getWidth()/2);

		sprite.setPosition(sox, soy);
		sprite.setRotation((body.getAngle()-(90*MathUtils.degreesToRadians))*MathUtils.radiansToDegrees);
		
	}

	public void render(SpriteBatch batch){
		sprite.draw(batch);
	}
	

	public void create(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}

	public void dispose() {
	}

	public void slowToStop(boolean slow) {
		slowing = slow;
	}
	
}
