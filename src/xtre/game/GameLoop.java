package xtre.game;

import xtre.Main;
import xtre.game.scene_manager.GameManager;
import xtre.globals.GlobalScreen;
import xtre.launcher.LauncherLoop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class GameLoop implements Screen {
	
	private Main main;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;
	
	private GameManager gameManager;
	
	public GameLoop(Main main){
		this.main = main;
		
	}
		
	public void show() {
		Gdx.graphics.setDisplayMode((int)GlobalScreen.GAME_WIDTH, (int)GlobalScreen.GAME_HEIGHT, false);
		Gdx.graphics.setTitle("Awake in Space");
		gameManager = new GameManager();

		camera = new OrthographicCamera(GlobalScreen.MPP(GlobalScreen.GAME_WIDTH), GlobalScreen.MPP(GlobalScreen.GAME_HEIGHT));
		camera.position.set(new Vector2(0,0), 0);
		camera.update();
		
		debugRenderer = new Box2DDebugRenderer();

		batch = new SpriteBatch();
		
//		createShip(10, 10);
//		
//		for(int x = 0; x < 500; x++){
//
//			createShape(0, -60 + 0.1f*x);
//		}
//		
//		createBounds();

	}
	
	public void render(float dt) {
		//RENDER
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//debugRenderer.render(world, camera.combined);
		
		batch.begin();
		gameManager.render(batch, dt);
		batch.end();
		//
		
		//UPDATE

		gameManager.update(camera.position.x, camera.position.y, Gdx.input.getX(), -Gdx.input.getY()+GlobalScreen.GAME_HEIGHT);

		camera.position.set((gameManager.getPlayer(0).ship.body.getPosition().x), (gameManager.getPlayer(0).ship.body.getPosition().y), 0);
		if(Gdx.input.isKeyPressed(Keys.Q)){
			camera.zoom--;
		}
		camera.update();
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
		
		//
	}
	
	public void resize(int width, int height) {
		System.out.println("GameLoop resize");
		GlobalScreen.GAME_WIDTH = Gdx.graphics.getWidth();
		GlobalScreen.GAME_HEIGHT = Gdx.graphics.getHeight();
	}
	
	public void hide() {
		System.out.println("GameLoop hide");
	}
	
	public void pause() {
		System.out.println("GameLoop pause");
	}
	
	public void resume() {
		System.out.println("GameLoop resume");
	}
	
	public void dispose() {
		System.out.println("GameLoop dispose");
		main.setScreen(new LauncherLoop(main));

	}

}
//public class GameLoop implements Screen{
//
//	
//	private SpriteBatch batch;
//	private OrthographicCamera camera;
//
//	private Main main;
//    private GameManager gameManager;
//	public Box2DDebugRenderer debugRenderer;
//
//	public static final int WIDTH = 1300, HEIGHT = 800;
//	public static float PIXELS_TO_METERS = .3125f;
//	public static float METERS_TO_PIXELS = 32f;	
//	Sprite sprite = new Sprite(new Texture("test.png"));
//	
//	public GameLoop(Main main){
//		System.out.println("gameloop");
//		this.main = main;
//		
//	}
//
//	public void show() {				
//		Gdx.graphics.setDisplayMode(WIDTH, HEIGHT, false);
//		gameManager = new GameManager();
//		debugRenderer = new Box2DDebugRenderer();
//		
//		camera = new OrthographicCamera(Gdx.graphics.getWidth()/40f, Gdx.graphics.getHeight()/40f);
//		//camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		camera.translate(WIDTH/2, HEIGHT/2);
//		camera.update();
//		
////		BodyDef ballDef = new BodyDef();
////		ballDef.type = BodyType.DynamicBody;
////		ballDef.position.set(0, 1);
////		
////		CircleShape shape = new CircleShape();
////		shape.setRadius(.5f);
////		
////		FixtureDef fixtureDef = new FixtureDef();
////		fixtureDef.shape = shape;
////		fixtureDef.density = 2.5f;
////		fixtureDef.restitution = .75f;
////		
////		world.createBody(ballDef).createFixture(fixtureDef);
////		
////		shape.dispose();
//
//		
//		BodyDef ballDef = new BodyDef();
//		ballDef.type = BodyType.StaticBody;
//		ballDef.position.set(0, 0);
//		
//		ChainShape shape = new ChainShape();
//		//System.out.println("--------\nshape\nscale["+scale+"], x["+x+"], y["+y+"]\n--------");
//	
//		float scale = 8.9f, x = 5.3f;
//		shape.createChain(new Vector2[]{
//				
//				new Vector2(-scale-x, -scale),  //Bottom left
//				new Vector2(scale+x, -scale),	  //Bottom right
//				new Vector2(scale+x, scale),	  //Top right
//				new Vector2(-scale-x, scale),	  //Top left
//				new Vector2(-scale-x, -scale),  //Bottom left
//
//		});
//
//		
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = shape;
//		fixtureDef.density = 0f;
//		fixtureDef.restitution = 0.1f;
//		
//		
//		//gameManager.spaceWorld.world.createBody(ballDef).createFixture(fixtureDef);
//		
//		shape.dispose();
//		
//		batch = new SpriteBatch();
//		
//	}
//	
//	private final float TIMESTEP = 1f/20f;
//	private final int velocityIterations = 80, positionIterations = 30;
//
//	public void render(float delta){
//  		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		debugRenderer.render(gameManager.spaceWorld.world, camera.combined);
//
//		batch.begin();
//		gameManager.render(batch, delta);
//		batch.end();
//
//		camera.position.set((gameManager.player[0].body.getPosition().x), (gameManager.player[0].body.getPosition().y), 0);
//		camera.update();
//		gameManager.update();
//		gameManager.spaceWorld.world.step(TIMESTEP, velocityIterations, positionIterations);
//
//	}
//	
////
////		//batch.begin();
////		//sprite.draw(batch);
////	//	gameManager.render(batch, delta);
////		//batch.end();
////		debugRenderer.render(gameManager.spaceWorld.world, cam.combined);
////
////				
////		moveCameraAround();
////	    batch.setProjectionMatrix(cam.combined);
////		cam.update();
////
////		gameManager.spaceWorld.world.step(delta, 8, 3);
////		gameManager.update();
//	
////	private void moveCameraAround(){
////		if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(0, 0.5f);
////		if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(0, -0.5f);
////		if(Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(-0.5f, 0);
////		if(Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(0.5f, 0);
////	}
//
//	public void resize(int width, int height) {
//	}
//	
//	public void hide() {
//		dispose();
//	}
//
//	public void pause() {
//	}
//
//	public void resume() {
//	}
//
//	public void dispose() {
//		//gameManager.dispose();
//	}
//}
