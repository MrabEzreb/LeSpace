package xtre.game.menus;

import xtre.game.menus.menu.InGameMenuMain;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.hud.GameInputs;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InGameMenuManager {
	
	private InGameMenuMain mainMenu;
	
	public static boolean open;
	
	public InGameMenuManager(){
		float xx = GlobalScreen.GAME_WIDTH/2, yy = (GlobalScreen.GAME_HEIGHT/2), w = 512, h = 372;
		mainMenu = new InGameMenuMain(xx - w/2, yy - h/2, w, h);
	}
	
	public void update(){
		if(GameInputs.keyPressed(Keys.ESCAPE)){
			if(!open)
				open = true;
			else
				open = false;
		}

		if(open){
			mainMenu.update();
		}
	}
	
	public void render(SpriteBatch batch){
		if(open)
			mainMenu.render(batch);
	}
}
