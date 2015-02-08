package xtre.game.player.ship.body_physics;

import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.hud.GameInputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class ShipBodyPhysics {

	public Body body;
	public BodyDef bodyDef;
	public FixtureDef fixtureDef;
	
	private Vector2 force = new Vector2();
	private Vector2 mp = new Vector2();
	
	public float energyUsage=0, shipRotationalAngle=0;
	
	public boolean useFuel, applyForce;
	
	public ShipBodyPhysics(float x, float y, World world){
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
	}
	
	public void update(float mouseX, float mouseY){
		mp.x = mouseX;
		mp.y = mouseY;
		
		//float angle = MathUtils.atan2((ScreenGlobals.HEIGHT/2) - mp.y, (ScreenGlobals.WIDTH/2) - mp.x);
		
		float xx = MathUtils.cos(body.getAngle());
		float yy = MathUtils.sin(body.getAngle());
		
		energyUsage = 0;
		if(!GameInputs.keyHolding(Keys.SHIFT_LEFT))
			body.setLinearDamping(.2f);
		else
			body.setLinearDamping(0);
		body.setAngularDamping(4);

		force.x = (xx*MathUtils.radiansToDegrees)/20;
		force.y = (yy*MathUtils.radiansToDegrees)/20;
		
		if(applyForce && Math.abs(body.getLinearVelocity().x+body.getLinearVelocity().y) < 10){
			if(Gdx.input.isKeyPressed(Keys.A)) {
				body.applyTorque(0.7f, true);
			}
			if(Gdx.input.isKeyPressed(Keys.D)){
				body.applyTorque(-0.7f, true);
			}		
			
			if(Gdx.input.isKeyPressed(Keys.W)){
				body.applyForceToCenter(force, true);
				
				if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
					energyUsage = .05f;
					body.applyForceToCenter(force, true);
					body.setLinearDamping(0);
				}else{
					energyUsage = .0005f;
				}
			}else if(Gdx.input.isKeyPressed(Keys.S)){
				useFuel = true;
				body.setLinearDamping(0.78f);
//				body.applyForceToCenter(-(force.x*3)/4, -(force.y*3)/4, true);
			}
		}else{
			body.setLinearDamping(0.50f);
		}

		shipRotationalAngle =(body.getAngle()-(90*MathUtils.degreesToRadians))*MathUtils.radiansToDegrees; 
	}
	
	public void create(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}

	public void dispose() {
	}
}
