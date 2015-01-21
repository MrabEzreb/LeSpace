package xtre.launcher.menus;

import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuOptions extends Menu{

	public MenuOptions(MenuManager manager, BitmapFont font) {
		super(manager);
		
		buttons.add(new MenuButton(0,0, 100, 32, "Audio", font, sound));
		buttons.add(new MenuButton(0,0, 100, 32, "Ship Forge", font, sound));
		buttons.add(new MenuButton(0,0, 100, 32, "Controls", font, sound));
		buttons.add(new MenuButton(0,0, 100, 32, "Back", font, sound));

		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setPosition(125, (Gdx.graphics.getHeight()/2)-(i*40));
		}
	}

	@Override
	public void processButtons() {
		
		if(buttonPressed.equals("Audio")){
			
		}
		
		if(buttonPressed.equals("Ship Forge")){
			manager.setMenu(new MenuShipForge(manager));
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
