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
		mainMenu = new InGameMenuMain();
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