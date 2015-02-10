package xtre.game.menus.menu;

import xtre.launcher.menus.utils.DialogBox;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenuMain {
	
	private DialogBox box;
	
	public float x, y, width, height;
	
	public GameMenuMain(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new DialogBox(x, y, width, height);
	}
	
	public void update() {
	}

	public void render(SpriteBatch batch) {
		box.render(batch);
	}

	public void dispose() {
	}
}
