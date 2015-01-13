package xtre.launcher.menus;

import xtre.game.GameLoop;
import xtre.graphics.sprites.sprite_types.space.SpriteSpacePlanets;
import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuStartGame extends Menu {
	
	private Sprite planet = se.getSprite(SpriteSpacePlanets.earth_like);
	
	public MenuStartGame(MenuManager manager){
		super(manager);
		
		for(int i = 0; i < 5; i++){
			buttons.add(new MenuButton(125, (Gdx.graphics.getHeight()/2)-(i*40), 100, 32, "", font, sound));	//0
		}
		
		buttons.get(0).setTitle("Single Player");
		buttons.get(1).setTitle("Multiplayer");
		buttons.get(2).setTitle("Options");
		buttons.get(3).setTitle("Credits");
		buttons.get(4).setTitle("Exit");
		
		planet.setPosition(500, 40);
	}

	@Override
	public void processButtons() {
		if(buttonPressed.equals("Single Player")){
			manager.main.setScreen(new GameLoop(manager.main));
			dispose();
		}
		
		if(buttonPressed.equals("Multiplayer")){
			manager.setMenu(new MenuMultiplayer(manager, font));
		}
		
		if(buttonPressed.equals("Options")){
			manager.setMenu(new MenuOptions(manager, font));
		}
		
		if(buttonPressed.equals("Credits")){
			
		}
		
		if(buttonPressed.equals("Exit")){
			dispose();
			manager.main.dispose();
			Gdx.app.exit();
		}
	}

	@Override
	public void renderScreen(SpriteBatch batch) {
		planet.draw(batch);
	}
}
