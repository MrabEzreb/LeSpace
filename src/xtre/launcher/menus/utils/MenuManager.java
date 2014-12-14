package xtre.launcher.menus.utils;

import xtre.Main;
import xtre.game.GameLoop;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.SpaceGUISprites;
import xtre.launcher.menus.MenuStartGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MenuManager {
	
	public Main main;
	
	private Menu menu;
	
	public MenuManager(Main main){
		this.main = main;
		
		menu = new MenuStartGame(this);
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Menu getCurrentMenu() {
		return menu;
	}
	
	public void update(Main main){
		menu.update();
	}
	
	public void render(SpriteBatch batch){
		menu.render(batch);
	}
}
