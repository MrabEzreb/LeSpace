package xtre.launcher.menus;

import xtre.globals.GlobalScreen;
import xtre.graphics.font.HUDFont;
import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;
import xtre.ship_forge.ShipForge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuShipForge extends Menu {

	private BitmapFont titleFont;
	private float titleX, titleY;
	private String title;
	
	private ShipForge shipForge = new ShipForge();
	
	public MenuShipForge(MenuManager manager) {
		super(manager);
		
		buttons.add(new MenuButton(32, GlobalScreen.LAUNCHER_HEIGHT-32, 100, 32, "Back", font, sound));
		
		titleFont = HUDFont.title_font.largeFont;
		titleFont.setColor(.8f, .8f, 1.0f, 1f);
		title = "Save, exit?";
		titleX = (GlobalScreen.GAME_WIDTH/2)-128;
		titleY = (GlobalScreen.GAME_HEIGHT/2)-4;
	}

	@Override
	public void renderScreen(SpriteBatch batch) {
		if(isDialogBoxOpen)
			titleFont.draw(batch, title, titleX, titleY);
		shipForge.render(batch);
	}

	@Override
	public void clicked() {
		shipForge.update(mouseX, mouseY, mouseLeftPress);
		
		if(buttonPressed.equals("Back") && !isDialogBoxOpen){
			float x=GlobalScreen.GAME_WIDTH/2;
			float y=GlobalScreen.GAME_HEIGHT/2;
			float width=256;
			float height=128;
			
			openDialogBox(x-(height/2)-147, y-(height/2)-64, width, height);
			MenuButton saveAndExit = new MenuButton((int)x-178, (int)y-50, 148, 32, "Save and exit", font, sound);
			MenuButton exit = new MenuButton((int)x-178, (int)y-85, 100, 32, "Exit", font, sound);
			addDialogButton(saveAndExit);
			addDialogButton(exit);
		}
		
		if(isDialogBoxOpen){
			if(buttonPressed.equals("Save and exit")){
				manager.setMenu(new MenuOptions(manager));
			}
			
			if(buttonPressed.equals("Exit")){
				manager.setMenu(new MenuOptions(manager));
			}
		}
	}
	
	public void dispose(){
		shipForge.dispose();
		titleFont.dispose();
	}

	@Override
	public void checks(float mouseX, float mouseY, boolean mouseLeftPress) {
		shipForge.checks(mouseX, mouseY, mouseLeftPress);
	}
}
