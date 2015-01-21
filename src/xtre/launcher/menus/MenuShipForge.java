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
	
	private ShipForge forge = new ShipForge();
	
	public MenuShipForge(MenuManager manager) {
		super(manager);
		
		buttons.add(new MenuButton(0, 0, 100, 32, "SHIP_FORGE", font, sound));
		buttons.add(new MenuButton(0, 0, 100, 32, "Back", font, sound));
		
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).setPosition(125, (Gdx.graphics.getHeight()/2)-(i*40));
		}
		
		titleFont = HUDFont.title_font.largeFont;
		titleFont.setColor(.8f, .8f, 1.0f, 1f);
		title = "Are you sure?";
		titleX = (int)(GlobalScreen.GAME_WIDTH/2)-128;
		titleY = (GlobalScreen.GAME_HEIGHT/2)-4;
	}

	@Override
	public void renderScreen(SpriteBatch batch) {
		if(isDialogBoxOpen)
			titleFont.draw(batch, title, titleX, titleY);
		forge.render(batch);
	}

	@Override
	public void process() {
		forge.update(mouseX, mouseY, mouseLeftPress);
		
		if(buttonPressed.equals("Back") && !isDialogBoxOpen){
			float x=GlobalScreen.GAME_WIDTH/2;
			float y=GlobalScreen.GAME_HEIGHT/2;
			float width=256;
			float height=128;
			
			openDialogBox(x-(height/2)-128, y-(height/2)-64, width, height);
			
			MenuButton saveAndExit = new MenuButton((int)x-164, (int)y-40, 100, 32, "Save and Exit", font, sound);
			MenuButton exit = new MenuButton((int)x-164, (int)y-80, 100, 32, "Exit", font, sound);
			buttons.add(saveAndExit);
			buttons.add(exit);
		}else if(isDialogBoxOpen){
			if(buttonPressed.equals("Save and Back")){
				manager.setMenu(new MenuStartGame(manager));
			}
			
			if(buttonPressed.equals("Exit")){
				manager.setMenu(new MenuStartGame(manager));
			}
		}
	}
	
	public void dispose(){
		forge.dispose();
		titleFont.dispose();
	}
}
