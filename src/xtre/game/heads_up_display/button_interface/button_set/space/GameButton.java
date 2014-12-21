package xtre.game.heads_up_display.button_interface.button_set.space;

import xtre.game.heads_up_display.utils.HeadsUpDisplay;
import xtre.globals.hud.Glbls;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameButton {	
	
	public Sprite sprite;
	public float x, y, width, height;
	
	protected HeadsUpDisplay hud;
	protected GameButtonAction buttonAction;
	
	public GameButton(){}	
	public GameButton(float x, float y, HeadsUpDisplay hud, Sprite sprite, GameButtonAction buttonAction) {
		this.x = x;
		this.y = y;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.hud = hud;
		this.sprite = sprite;
		this.sprite.setPosition(x, y);
		this.buttonAction = buttonAction;
	}

	public final void render(SpriteBatch batch){
		sprite.draw(batch);
	}
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		this.x = x;
		this.y = y;
	}
	
	public void doAction() { buttonAction.doAction(); }
	
	public boolean isOutOfBounds(float mouseX, float mouseY, boolean justPressedLeftMouse) {
		if(justPressedLeftMouse && Glbls.withinSquareBounds(mouseX, mouseY, x, y, width, height))
			return false;
		else
			return true;
	}
}
