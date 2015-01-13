package xtre.launcher.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

public class MenuOptions extends Menu{

	public MenuOptions(MenuManager manager, BitmapFont font) {
		super(manager);
		
		for(int i = 0; i < 5; i++){
			buttons.add(new MenuButton(125, (Gdx.graphics.getHeight()/2)-(i*40), 100, 32, "", font, sound));
		}
		
		buttons.get(0).setTitle("Audio");
		buttons.get(1).setTitle("Controls");
		buttons.get(2).setTitle("Back");
	}

	@Override
	public void processButtons() {
		
		if(buttonPressed.equals("Audio")){
			
		}
		
		if(buttonPressed.equals("Controls")){
			manager.setMenu(new MenuControls(manager));
		}
		
		if(buttonPressed.equals("Back")){
			manager.setMenu(new MenuStartGame(manager));
		}		
	}

	@Override
	public void renderScreen(SpriteBatch batch) {
	}
}
