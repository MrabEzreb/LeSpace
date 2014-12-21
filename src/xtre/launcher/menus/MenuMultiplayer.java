package xtre.launcher.menus;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import xtre.Main;
import xtre.game.GameLoop;
import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

public class MenuMultiplayer extends Menu{
	
	MenuManager manager;
	
	public MenuMultiplayer(MenuManager manager){
		this.manager = manager;

		BitmapFont menuFont = new BitmapFont(Gdx.files.internal("font/default.fnt"));
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/bedip_button.ogg"));

		for(int i = 0; i < 3; i++){
			buttons.add(new MenuButton(125, (Gdx.graphics.getHeight()/2)-(i*40), 100, 32, "", menuFont, sound));	//0
		}
		
		buttons.get(0).setTitle("Join Server");
		buttons.get(1).setTitle("Back");
	
	}

	@Override
	public void processButtons() {
		if(buttonPressed.equals("Join Server")){

		}
		
		if(buttonPressed.equals("Host Server")){
			
		}
		
		if(buttonPressed.equals("Back")){
			dispose();
			manager.setMenu(new MenuStartGame(manager));
		}
	
	}

}
