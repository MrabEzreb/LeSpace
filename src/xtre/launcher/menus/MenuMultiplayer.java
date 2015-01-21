package xtre.launcher.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

public class MenuMultiplayer extends Menu{
	
	public MenuMultiplayer(MenuManager manager, BitmapFont font){
		super(manager);
		this.font = font;
		
		for(int i = 0; i < 3; i++){
			buttons.add(new MenuButton(125, (Gdx.graphics.getHeight()/2)-(i*40), 100, 32, "", font, sound));	//0
		}
		
		buttons.get(0).setTitle("Join Server");
		buttons.get(1).setTitle("Back");
	}

	@Override
	public void process() {
		if(buttonPressed.equals("Join Server")){

		}
		
		if(buttonPressed.equals("Host Server")){
			
		}
		
		if(buttonPressed.equals("Back")){
			dispose();
			manager.setMenu(new MenuStartGame(manager));
		}
	}

	@Override
	public void renderScreen(SpriteBatch batch) {
	}
}
