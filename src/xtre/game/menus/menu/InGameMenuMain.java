package xtre.game.menus.menu;

import xtre.game.game_gui.heads_up_display.menu.GameMenu;
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
	
	public float x, y, width, height;
	private int padding = 8;
	
	public InGameMenuMain(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new DialogBox(x, y, width, height);
		Sprite s = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_with_light_off);
		float menuX = x + (width/2) - (s.getWidth()/2), menuY = y+height-s.getHeight() - padding;
		menu = new GameMenu(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar), new FontEntity(HUDFont.title_font.largeFont,  new Color(.7f, .8f, .8f, .9f), "Exit", menuX, menuY), menuX, menuY, 8);
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
	
	public void update() {
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
