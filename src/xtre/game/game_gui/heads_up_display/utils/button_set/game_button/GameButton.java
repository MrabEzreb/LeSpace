package xtre.game.game_gui.heads_up_display.utils.button_set.game_button;

import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameButton {	
	
	public Sprite sprite;
	
	public float supraX, supraY;
	
	private float x=0, y=0;
	
	private float labelX, labelY;
	public String title = "";
	private BitmapFont font;
	
	protected GameButtonAction buttonAction;
	
	public GameButton(){}	
	public GameButton(Sprite sprite){
		this.sprite = sprite;
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
	
	public boolean isClicked(float mouseX, float mouseY, boolean mouseLeftPress) {
		if(mouseLeftPress && GlobalsInterface.withinSquareBounds(mouseX, mouseY, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()))
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
		this.x = x;
		this.y = y;
		sprite.setPosition(this.x, this.y);
	}
	
	public void setAction(GameButtonAction buttonAction) {
		this.buttonAction = buttonAction;
	}
	
	public void dispose(){
		sprite.getTexture().dispose();
		font.dispose();
	}
	public void setOffset(float x, float y) {
		sprite.setPosition(this.x + x, this.y + y);
	}
}
