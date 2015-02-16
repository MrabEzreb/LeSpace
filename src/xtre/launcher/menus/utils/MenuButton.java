package xtre.launcher.menus.utils;

import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuButton {

	public int x, y, width, height;
	public boolean pressed = false;
	public BitmapFont font;
	public String title;
	public Sound btnSound;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param title
	 * @param font
	 * @param btnSound
	 */
	public MenuButton(int x, int y, int width, int height, String title, BitmapFont font, Sound btnSound) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = font;
		this.title = title;
		this.btnSound = btnSound;
	}
	public MenuButton(){}

	public void setTitle(String title){
		this.title = title;
	}
	
	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}
	
	public boolean isPressed() {
		int mx = Gdx.input.getX(), my = -Gdx.input.getY() + Gdx.graphics.getHeight();
		if(mx > x && mx < x+width && my > y-height && my < y){
			return true;
		}else{
			return false;
		}
	}
	
	public void render(SpriteBatch batch){
		int mx = Gdx.input.getX(), my = -Gdx.input.getY() + Gdx.graphics.getHeight();
		if(GlobalsInterface.withinSquareBounds(mx, my+20, x-10, y-10, font.getBounds(title).width+20, height+10)){
			font.setColor(.8f, .8f, 1.0f, 1f);
			font.draw(batch, title, x, y);
		}else{
			font.setColor(.4f, .4f, .7f, 1f);
			font.draw(batch, title, x, y);
		}
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
