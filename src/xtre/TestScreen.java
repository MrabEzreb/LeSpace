
package xtre;

import java.util.Random;

import xtre.Main;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.SpaceGameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class TestScreen implements Screen {
	
	public static final float WIDTH = 1300, HEIGHT = 800;
	public static final float PPM = 10f;
	
	private final float timeStep = 1f/30f;
	private final int velocityIterations = 80, positionIterations = 30;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;
	
	private World world;
	
	private Sprite playerSprite;
	
	SpriteEntity se = new SpriteEntity();

	public TestScreen(Main main){}
		
	public void show() {
		Gdx.graphics.setDisplayMode((int)WIDTH, (int)HEIGHT, false);

		world = new World(new Vector2(0, 0), true);

		camera = new OrthographicCamera((WIDTH+500)/PPM, (HEIGHT+500)/PPM);
		camera.position.set(new Vector2(0,0), 0);
		camera.update();
		
		debugRenderer = new Box2DDebugRenderer();

		batch = new SpriteBatch();
		
		createShip(10, 10);
		
		for(int x = 0; x < 500; x++){

			createShape(0, -60 + 0.1f*x);
		}
		playerSprite = new Sprite(se.getSprite(SpaceGameSprites.player_ship));
		
		createBounds();

	}

	private Vector2 mp = new Vector2(0,0), gravityOff = new Vector2(0,0), gravityOn = new Vector2(0, 0);
	
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		debugRenderer.render(world, camera.combined);

		batch.begin();
		playerSprite.draw(batch);
		batch.end();
		world.step(timeStep, velocityIterations, positionIterations);
		if(Gdx.input.isKeyPressed(Keys.W)){
			mp.x = (Gdx.input.getX());
			mp.y = -(Gdx.input.getY()-HEIGHT);
			
			if(Gdx.input.isKeyPressed(Keys.F)){
				world.setGravity(gravityOff);
				body.applyForceToCenter( (mp.x-WIDTH/2)*50f, (mp.y-HEIGHT/2)*50, true );
			}
			else{
				world.setGravity(gravityOn);
				body.applyForce( (mp.x-WIDTH/2)*50f, (mp.y-HEIGHT/2)*50,  (mp.x-WIDTH/2)*50f, (mp.y-HEIGHT/2)*50, true );
			}

		}
		
		camera.position.set((body.getPosition().x), (body.getPosition().y), 0);
		camera.update();
		playerSprite.setPosition( (((body.getPosition().x*PPM)+WIDTH/2)-playerSprite.getWidth()/2) - (camera.position.x*PPM), (((body.getPosition().y*PPM)+HEIGHT/2)-playerSprite.getWidth()/2) - (camera.position.y)*PPM);

		
	}
	
	private void createBounds(){
		BodyDef areaDef = new BodyDef();
		areaDef.type = BodyType.StaticBody;
		areaDef.position.set(0, 0);
		
		ChainShape shape2 = new ChainShape();
		//System.out.println("--------\nshape\nscale["+scale+"], x["+x+"], y["+y+"]\n--------");
	
		float scale = 16f * 1f, x = 9f * 2f;
		shape2.createChain(new Vector2[]{
				
				new Vector2(-scale-x, -scale-x*4),  //Bottom left
				new Vector2(scale+x, -scale-x*4),	  //Bottom right
				new Vector2(scale+x, scale*9),	  //Top right
				new Vector2(-scale-x, scale*9),	  //Top left
				new Vector2(-scale-x, -scale-x*4),  //Bottom left

		});
		
		FixtureDef fixtureDef2 = new FixtureDef();
		fixtureDef2.shape = shape2;
		fixtureDef2.density = 0.5f;
		fixtureDef2.restitution = 0.1f;
		
		
		world.createBody(areaDef).createFixture(fixtureDef2);
		shape2.dispose();

	}
	
	Body body;
	
	private void createShip(float xx, float yy){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(5f, 1f);
		
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 11.01f;
		fixtureDef.friction = 0.3f;
		fixtureDef.restitution = 0.0f;

						
		body = world.createBody(bodyDef);
		body.getPosition().set(xx, yy);
		body.createFixture(fixtureDef);
		
		shape.dispose();
	
	}
	
	Random r = new Random();
	private void createShape(float xx, float yy){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		
		
		CircleShape shape = new CircleShape();
		shape.setRadius(0.3f + r.nextFloat());
		System.out.println(shape.getRadius());
		//shape.setAsBox(5f, 1f);
		
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1.01f;
		fixtureDef.friction = 12.3f;
		fixtureDef.restitution = 0.2f;

		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setTransform(xx, yy,0);

		shape.dispose();
	
	}
	
	public void resize(int width, int height) {
	}
	
	public void hide() {
		dispose();
	}
	
	public void pause() {
	}
	
	public void resume() {
	}
	
	public void dispose() {
		
	}

}
