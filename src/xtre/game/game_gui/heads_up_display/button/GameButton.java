package xtre.game.game_gui.heads_up_display.button;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.font.FontEntity;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameButton {	
	
	public Sprite sprite;
	
	public FontEntity title;
	
	protected GameButtonAction buttonAction;
	
	public GameButton(){}	
	public GameButton(Sprite sprite, FontEntity title){
		this.sprite = sprite;
		
		title.x = sprite.getX() + (sprite.getWidth()/2) - (title.font.getBounds(title.text).width/2);
		title.y = sprite.getY() + (sprite.getHeight()/2) + (title.font.getBounds(title.text).height/2);
		this.title = title;
	}

	public final void render(SpriteBatch batch){
		sprite.draw(batch);
		if(title!=null && title.font!=null){
			title.font.draw(batch, title.text, title.x, title.y);
		}
	}
	
	public void doAction() {
		buttonAction.doAction();
	}
	
	boolean buttonIn = false;
	public boolean isClicked() {
		System.out.println(title.text);
		if(!buttonIn && GameInputs.mouseHolding(Buttons.LEFT))
			buttonIn = true;
		else if(!GameInputs.mouseHolding(Buttons.LEFT)){
			buttonIn = false;
			return true;
		}
		
		return false;
	}
	
	public boolean isWithin() {
		if(GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()))
			return true;
		else
			return false;
	}

	public void setLabel(String text){
		title.text = text;
	}
	
//	public void setPosition(float x, float y) {
//		sprite.setPosition(x, y);
//	}
	
	public void setAction(GameButtonAction buttonAction) {
		this.buttonAction = buttonAction;
	}
	
	public void dispose(){
		sprite.getTexture().dispose();
		if(title!=null)
			title.dispose();
	}
	public void setOffset(float x, float y) {
		System.out.println("TODO setOffset in (GameButton.java:78)");
	}
}
