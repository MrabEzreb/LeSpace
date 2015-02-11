package xtre.game.menus.menu;

import xtre.game.game_gui.heads_up_display.menu.GameMenu;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.launcher.menus.utils.DialogBox;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenuMain {
	
	private DialogBox box;
	private GameMenu menu;
	
	public float x, y, width, height;
	
	public GameMenuMain(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new DialogBox(x, y, width, height);
		Sprite s = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar);
		menu = new GameMenu(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar), x + (s.getWidth()/2) + (width/2), y + (s.getHeight()/2) + (height/2), 8);
		menu.addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button));
		menu.addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button));
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
