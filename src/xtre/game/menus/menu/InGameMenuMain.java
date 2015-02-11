package xtre.game.menus.menu;

import xtre.game.game_gui.heads_up_display.button.GameButtonAction;
import xtre.game.game_gui.heads_up_display.menu.GameMenu;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.launcher.menus.utils.DialogBox;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InGameMenuMain {
	
	private DialogBox box;
	private GameMenu menu;
	
	public float x, y, width, height;
	
	public InGameMenuMain(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new DialogBox(x, y, width, height);
		Sprite s = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar);
		float menuX = x + (s.getWidth()/2) + (width/2), menuY = y + (s.getHeight()/2) + (height/2);
		menu = new GameMenu(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar), new FontEntity(HUDFont.title_font.mediumFont, "menu button", menuX, menuY), menuX, menuY, 8);
		GameButtonAction a1 = new GameButtonAction(){
			public void action(){
				System.out.println("in game menu button 1");
			}
		};
		menu.addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button));
		menu.setActionToButton(0, a1);
		GameButtonAction a2 = new GameButtonAction(){
			public void action(){
				System.out.println("in game menu button 2");
			}
		};
		menu.addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button));
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
