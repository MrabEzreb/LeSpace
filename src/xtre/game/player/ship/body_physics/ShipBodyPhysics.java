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
	
	public float energyUsage=0, shipRotationalAngle=0;
	private float shipAccelerationRate = -0.005f, shipThrust=1;
	
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
	
	public void update(){
		body.setAngularDamping(5);
		body.setLinearDamping(shipThrust);
		
		double xx = MathUtils.cos(body.getAngle())/10;
		double yy = MathUtils.sin(body.getAngle())/10;
		force.x = (float) ((xx*MathUtils.radiansToDegrees));
		force.y = (float) ((yy*MathUtils.radiansToDegrees));
		
		if(GameInputs.keyHolding(Keys.W)){
			body.applyForceToCenter(force.x, force.y, true);
		}
		
		if(GameInputs.keyHolding(Keys.A)){
			body.applyTorque(shipThrust, true);
		}
		
		if(GameInputs.keyHolding(Keys.D)){
			body.applyTorque(-shipThrust, true);
		}
		
		System.out.println(shipThrust);
		
		shipRotationalAngle =(body.getAngle()-(90*MathUtils.degreesToRadians))*MathUtils.radiansToDegrees; 

		
		
//		//float angle = MathUtils.atan2((ScreenGlobals.HEIGHT/2) - mp.y, (ScreenGlobals.WIDTH/2) - mp.x);
//		
//		energyUsage = 0;
//		if(!GameInputs.keyHolding(Keys.SHIFT_LEFT))
//			body.setLinearDamping(.2f);
//		else
//			body.setLinearDamping(0);
//		body.setAngularDamping(4);
//
//		double xx = MathUtils.cos(body.getAngle());
//		double yy = MathUtils.sin(body.getAngle());
//		
//		force.x = (float) (xx*MathUtils.radiansToDegrees);
//		force.y = (float) (yy*MathUtils.radiansToDegrees);
//		
//		if(applyForce && Math.abs(body.getLinearVelocity().x+body.getLinearVelocity().y) < 3){
//			if(Gdx.input.isKeyPressed(Keys.A)) {
//				body.applyTorque(0.7f, true);
//			}
//			if(Gdx.input.isKeyPressed(Keys.D)){
//				body.applyTorque(-0.7f, true);
//			}		
//			
//			if(Gdx.input.isKeyPressed(Keys.W)){
//				body.applyForceToCenter(force.x/5f, force.y/5f, true);
//				
//				if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
//					energyUsage = .05f;
//					body.applyForceToCenter(force, true);
//					body.setLinearDamping(0);
//				}else{
//					energyUsage = .0005f;
//				}
//			}else if(Gdx.input.isKeyPressed(Keys.S)){
//				useFuel = true;
//				body.setLinearDamping(0.78f);
////				body.applyForceToCenter(-(force.x*3)/4, -(force.y*3)/4, true);
//			}
//		}else{
//			body.setLinearDamping(0.50f);
//		}
//
//		shipRotationalAngle =(body.getAngle()-(90*MathUtils.degreesToRadians))*MathUtils.radiansToDegrees; 
	}
	
	public void create(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}

	public void dispose() {
	}
}
