package xtre.game.menus;

import xtre.game.menus.menu.GameMenuMain;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.hud.GameInputs;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InGameMenu {
	
	private GameMenuMain mainMenu;
	
	public static boolean open; /* TODO When open, only allow mouse to interact with InGameMenu.
								 * TODO Have an if(!open) on every other gui update so to skip all non InGameMenu interactions.
								 */
	public InGameMenu(){
		float xx = GlobalScreen.GAME_WIDTH/2, yy = (GlobalScreen.GAME_HEIGHT/2), w = 512, h = 372;
		mainMenu = new GameMenuMain(xx - w/2, yy - h/2, w, h);
	}
	
	public void update(float mouseX, float mouseY){
		if(GameInputs.keyPressed(Keys.ESCAPE)){
			if(!open)
				open = true;
			else
				open = false;
		}

		if(open){
			mainMenu.update(mouseX, mouseY, false);
		}
	}
	
	public void render(SpriteBatch batch){
		if(open)
			mainMenu.render(batch);
	}
}
