package xtre.game.game_gui.heads_up_display.button;

import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameButton {	
	
	public Sprite sprite;
	
	private float labelX, labelY, xPin=0, yPin=0;
	public String title = "";
	private BitmapFont font;
	
	protected GameButtonAction buttonAction;
	
	public GameButton(){}	
	public GameButton(Sprite sprite){
		this.sprite = sprite;
		xPin = sprite.getX();
		yPin = sprite.getY();
	}

	public final void render(SpriteBatch batch){
		sprite.draw(batch);
		if(font!=null){
			font.draw(batch, title, sprite.getX()+labelX, sprite.getY()+labelY);
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
		this.labelX = labelX + sprite.getX();
		this.labelY = labelY + sprite.getY();
		
		System.out.println(this.labelX+ " label position " + this.labelY);
	}
	
	public void setPosition(float x, float y) {
		this.xPin = x;
		this.yPin = y;
		sprite.setPosition(this.xPin, this.yPin);
	}
	
	public void setAction(GameButtonAction buttonAction) {
		this.buttonAction = buttonAction;
	}
	
	public void dispose(){
		sprite.getTexture().dispose();
		if(font!=null)
			font.dispose();
	}
	public void setOffset(float x, float y) {
		sprite.setPosition(this.xPin + x, this.yPin + y);
	}
}
