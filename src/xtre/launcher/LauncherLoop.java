package xtre.launcher;

import xtre.Main;
import xtre.globals.GlobalScreen;
import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class LauncherLoop implements Screen{
	
	private SpriteBatch batch;
	public OrthographicCamera cam;
	
	private final Main main;

	MenuManager menus;
	
	public static final int WIDTH = 1152, HEIGHT = 648;
		
	public LauncherLoop(Main main){
		this.main = main;
		this.resize(WIDTH, HEIGHT);
	}

	public void show() {
		Gdx.graphics.setDisplayMode((int)WIDTH, (int)HEIGHT, false);

		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.position.set(WIDTH/2, HEIGHT/2, 0);

		//Menus
		menus = new MenuManager(main);
		
		batch = new SpriteBatch();		
		//
		
	}	
	
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		menus.render(batch);
		batch.end();
	    batch.setProjectionMatrix(cam.combined);
		cam.update();		
		moveCameraAround();
		menus.update(main);
	}
	
	private void moveCameraAround(){
		if(Gdx.input.isKeyPressed(Keys.W) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(0, 0.5f);
		if(Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(0, -0.5f);
		if(Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(-0.5f, 0);
		if(Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))cam.translate(0.5f, 0);
	}

	public void resize(int width, int height) {
	}
	
	public void hide() {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
	}

}
