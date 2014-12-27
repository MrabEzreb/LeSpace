package xtre.game.game_gui.heads_up_display.utils.button_set.game_button;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;
import xtre.globals.hud.Glbls;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameButton {	
	
	public Sprite sprite;
	public float x, y, width, height;
	
	protected HeadsUpDisplay hud;
	protected GameButtonAction buttonAction;
	
	private BitmapFont font;
	private String title = "";
	private float labelX, labelY;
	
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
	
	public void setLabel(float labelX, float labelY, String title, BitmapFont font){
		this.font = font;
		this.title = title;
		this.labelX = labelX;
		this.labelY = labelY;
	}

	public final void render(SpriteBatch batch){
		sprite.draw(batch);
		if(font!=null){
			font.draw(batch, title, labelX, labelY);
		}
	}
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		this.x = x;
		this.y = y;
	}
	
	public void doAction() {
		buttonAction.doAction();
	}
	
	public boolean isClicked(float mouseX, float mouseY, boolean justPressedL) {
		if(justPressedL && Glbls.withinSquareBounds(mouseX, mouseY, x, y, width, height))
			return true;
		else
			return false;
	}
	public boolean isWithin(float mouseX, float mouseY) {
		if(Glbls.withinSquareBounds(mouseX, mouseY, x, y, width, height))
			return true;
		else
			return false;
	}
}
