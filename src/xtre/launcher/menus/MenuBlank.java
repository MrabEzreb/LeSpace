package xtre.launcher.menus;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuManager;

public class MenuBlank extends Menu{

	public MenuBlank(MenuManager manager, BitmapFont font) {
		super(manager);
		this.font = font;
	}

	@Override
	public void process() {
	}

	@Override
	public void renderScreen(SpriteBatch batch) {
	}

	@Override
	public void checks(float mouseX, float mouseY, boolean mouseLeftPress) {
	}

}
