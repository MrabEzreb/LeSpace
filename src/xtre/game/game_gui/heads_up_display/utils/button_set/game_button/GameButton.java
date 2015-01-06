package xtre.game.game_gui.heads_up_display.utils.button_set.game_button;

import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameButton {	
	
	public Sprite sprite;
	
	protected GameButtonAction buttonAction;
	
	private BitmapFont font;
	private String title = "";
	private float labelX, labelY;
	
	public GameButton(){}	
	public GameButton(Sprite sprite){
		this.sprite = sprite;
	}
	public GameButton(Sprite sprite, GameButtonAction buttonAction) {
		this.sprite = sprite;
		this.buttonAction = buttonAction;
	}

	public final void render(SpriteBatch batch){
		sprite.draw(batch);
		if(font!=null){
			font.draw(batch, title, labelX, labelY);
		}
	}
	
	public void doAction() {
		buttonAction.doAction();
	}
	
	public boolean isClicked(float mouseX, float mouseY, boolean justPressedL) {
		if(justPressedL && GlobalsInterface.withinSquareBounds(mouseX, mouseY, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()))
			return true;
		else
			return false;
	}
	
	public boolean isWithin(float mouseX, float mouseY) {
		if(GlobalsInterface.withinSquareBounds(mouseX, mouseY, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()))
			return true;
		else
			return false;
	}
	
	public void setLabel(float labelX, float labelY, String title, BitmapFont font){
		this.font = font;
		this.title = title;
		this.labelX = labelX;
		this.labelY = labelY;
	}
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}
	
	public void setAction(GameButtonAction buttonAction) {
		this.buttonAction = buttonAction;
	}
	
	public void dispose(){
		sprite.getTexture().dispose();
		font.dispose();
	}
}
