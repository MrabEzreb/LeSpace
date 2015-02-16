package xtre.game.menus.menu;

import xtre.game.game_gui.heads_up_display.menu.GameMenu;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.launcher.menus.utils.DialogBox;
import xtre.ship_forge.components.button.Action;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InGameMenuMain {
	
	private DialogBox box;
	private GameMenu menu;
	
	Sprite exitSprite = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_with_light_off);
	
	private float x, y, width, height;
	private float menuX, menuY;

	private int padding = 8;
	
	public InGameMenuMain() {
		box = new DialogBox(0, 0, 0, 0);
		menu = new GameMenu(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar), new FontEntity(HUDFont.title_font.largeFont,  new Color(.7f, .8f, .8f, .9f), "Exit", 0, 0), 0, 0, padding);
		updatePositionAndSize();
		
		
		Action a1 = new Action(){
			public void action(){
				Gdx.app.exit();
			}
		};
		menu.addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button), new FontEntity(HUDFont.title_font.smallFont, new Color(.7f, .8f, .8f, .9f), "Yes"));
		menu.setActionToButton(0, a1);
		Action a2 = new Action(){
			public void action(){
				
			}
		};
		menu.addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button), new FontEntity(HUDFont.title_font.smallFont, new Color(.7f, .8f, .8f, .9f), "No"));
		menu.setActionToButton(1, a2);
	}
	
	public void updatePositionAndSize() {
		width = 512;
		height = 372;
		x = (GlobalScreen.GAME_WIDTH/2)-(width/2);
		y = ((GlobalScreen.GAME_HEIGHT/2))-(height/2);
		
		menuX = x+(width/2)-(exitSprite.getWidth()/2);
		menuY = y+(height/2)-(exitSprite.getHeight()/2);
		menu.setPosition(menuX, menuY);
		
		box.setPosition(x, y);
		box.setSize(width, height);
		
	}

	public void update() {
		updatePositionAndSize();
		menu.update();
		
	}

	public void render(SpriteBatch batch) {
		box.render(batch);
		menu.render(batch);
	}

	public void dispose() {
		menu.dispose();
	}
}
