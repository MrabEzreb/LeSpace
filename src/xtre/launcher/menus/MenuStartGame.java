package xtre.launcher.menus;

import xtre.game.GameLoop;
import xtre.game.space_world.Stars;
import xtre.game.utils.Timer;
import xtre.globals.GlobalScreen;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpriteSpacePlanets;
import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuStartGame extends Menu {
	
	private Sprite planet = GameSprite.getSprite(SpriteSpacePlanets.earth_like);
	private Stars stars;
	private Timer t = new Timer();
	
	private float camX=0, camY=0;
	
	public MenuStartGame(MenuManager manager){
		super(manager);
		
		buttons.add(new MenuButton(0,0, 100, 32, "Single Player", font, sound));
		buttons.add(new MenuButton(0,0, 100, 32, "Multiplayer", font, sound));
		buttons.add(new MenuButton(0,0, 100, 32, "Options", font, sound));
		buttons.add(new MenuButton(0,0, 100, 32, "Credits", font, sound));
		buttons.add(new MenuButton(0,0, 100, 32, "Exit", font, sound));

		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setPosition(100, (Gdx.graphics.getHeight()/2)-(i*40));
		}
		
		planet.setPosition((GlobalScreen.LAUNCHER_WIDTH/2) - (planet.getWidth()/2), (GlobalScreen.LAUNCHER_HEIGHT/2)-(planet.getHeight()/2));
		stars = new Stars(15000, 4, 2);
	}

	@Override
	public void clicked() {
		if(buttonPressed.equals("Single Player")){
			dispose();
			manager.main.setScreen(new GameLoop(manager.main));
		}
		
		if(buttonPressed.equals("Multiplayer")){
			manager.setMenu(new MenuMultiplayer(manager, font));
		}
		
		if(buttonPressed.equals("Options")){
			manager.setMenu(new MenuOptions(manager));
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
		stars.render(batch, 0, 0);
		planet.draw(batch);
	}

	@Override
	public void checks(float mouseX, float mouseY, boolean mouseLeftPress) {
			camX+=0.03f;
		stars.update(camX, camY);
	}
}
