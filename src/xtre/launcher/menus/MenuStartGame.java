package xtre.launcher.menus;

import xtre.Main;
import xtre.game.GameLoop;
import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuStartGame extends Menu {
	
	MenuManager manager;
	
	public MenuStartGame(MenuManager manager){
		this.manager = manager;
		
		BitmapFont menuFont = new BitmapFont(Gdx.files.internal("font/default.fnt"));
		

		//Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/button_press_edited.ogg")).play();
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/bedip_button.ogg"));
		//Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/button_press.wav")).play();
		
		for(int i = 0; i < 5; i++){
			buttons.add(new MenuButton(125, (Gdx.graphics.getHeight()/2)-(i*40), 100, 32, "", menuFont, sound));	//0
		}
		
		buttons.get(0).setTitle("Single Player");
		buttons.get(1).setTitle("Multiplayer");
		buttons.get(2).setTitle("Options");
		buttons.get(3).setTitle("Credits");
		buttons.get(4).setTitle("Exit");

	}

	@Override
	public void processButtons() {
		if(buttonPressed.equals("Single Player")){
			manager.main.setScreen(new GameLoop(manager.main));
			dispose();
		}
		
		if(buttonPressed.equals("Multiplayer")){
			manager.setMenu(new MenuMultiplayer(manager));
			System.out.println("lol");
		}
		
		if(buttonPressed.equals("Options")){
			
		}
		
		if(buttonPressed.equals("Credits")){
			
		}
		
		if(buttonPressed.equals("Exit")){
			dispose();
			manager.main.dispose();
			Gdx.app.exit();
		}
	}
	
}
