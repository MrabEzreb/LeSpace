package xtre.game.game_gui.heads_up_display.button;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.font.FontEntity;
import xtre.ship_forge.components.button.Action;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class GameButton {	
	
	public Sprite sprite;
	
	public FontEntity title;
	
	protected Action buttonAction;
	
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
		buttonAction.action();
	}
	
	public boolean isClicked(){
		boolean hover = isWithin();
		
		if(hover && GameInputs.mousePressed(Buttons.LEFT))
			return true;
		
		return false;
	}
	
	public boolean isWithin() {
		if(GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight())){
			sprite.setScale(1f);
			sprite.setColor(1f,1f,1f,1f);
			return true;
		}else{
			sprite.setScale(.99f);
			sprite.setColor(.95f,.95f,.95f,.95f);
			return false;
		}
	}

	public void setLabel(String text){
		title.text = text;
	}
	
//	public void setPosition(float x, float y) {
//		sprite.setPosition(x, y);
//	}
	
	public void setAction(Action buttonAction) {
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
