package xtre.game.physics_objects.player;

import java.util.Random;

import xtre.game.physics_objects.PhysicsEntity;
import xtre.globals.GlobalScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class SpaceRock extends PhysicsEntity {
	
	public Sprite rockSprite;
	
	private Body body;
	private BodyDef bodyDef;
	private	FixtureDef fixtureDef;
	
	public float torque = 0f;
	
	private Random r = new Random();
	
	public SpaceRock(float x, float y, Sprite rockSprite, World world){
		System.out.println("rock");
		
		this.x = x;
		this.y = y;
		
		this.rockSprite = rockSprite;
				
		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(5);
		//shape.setAsBox(5f, 1f);
		
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.filter.groupIndex = -1;
		fixtureDef.density = 1.01f;
		fixtureDef.friction = 1.1f;
		fixtureDef.restitution = 0f;

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setTransform(x, y, 0);
		
		shape.dispose();
	
	}

	public void update(float camX, float camY){
		x = body.getPosition().x;
		y = body.getPosition().y;
		
		float mx = Gdx.input.getX(), my = -Gdx.input.getY()+Gdx.graphics.getHeight();
		
		float rx = (x + -camX) + (GlobalScreen.MPP((GlobalScreen.GAME_WIDTH/2)) - GlobalScreen.MPP(rockSprite.getWidth()/2));
		float ry = (y + -camY) + ((GlobalScreen.MPP(GlobalScreen.GAME_HEIGHT/2)) - GlobalScreen.MPP(rockSprite.getHeight()/2));		
		
		rockSprite.setPosition(GlobalScreen.PPM(rx), GlobalScreen.PPM(ry));
		rockSprite.setRotation(body.getAngle()*MathUtils.radiansToDegrees);
		
	}

	public void render(SpriteBatch batch) {
		
		rockSprite.draw(batch);
	}

	public void create(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);

	}
	
}
