package xtre.launcher.menus;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuManager;

public class BlankMenu extends Menu{

	public BlankMenu(MenuManager manager, BitmapFont font) {
		super(manager);
		this.font = font;
	}

	@Override
	public void processButtons() {
	}

}
