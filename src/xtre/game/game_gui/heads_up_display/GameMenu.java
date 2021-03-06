package xtre.game.game_gui.heads_up_display;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.button_interface.button_set.space.GameButton;
import xtre.globals.hud.Glbls;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu {
	
	private float menuBarX, menuBarY, menuBarWidth, menuBarHeight;

	Sprite menuBarSprite;
	
	BitmapFont font;
	private String menuTitle="";
	private float fontPositionOnButtonX, fontPositionOnButtonY;
	
	public boolean isMenuBarOpen=false, open=false;
	
	private List<GameButton> buttons = new ArrayList<>();
	
	public GameMenu(float menuBarX, float menuBarY, float menuBarWidth,	float menuBarHeight, Sprite menuBarSprite) {
		this.menuBarX = menuBarX;
		this.menuBarY = menuBarY;
		this.menuBarWidth = menuBarWidth;
		this.menuBarHeight = menuBarHeight;
		this.menuBarSprite = menuBarSprite;
		
		this.menuBarSprite.setPosition(menuBarX, menuBarY);
	}
	
	public void setBitmapFont(BitmapFont font, float fontPositionOnButtonX, float fontPositionOnButtonY, String menuTitle){
		this.font = font;
		this.fontPositionOnButtonX = fontPositionOnButtonX;
		this.fontPositionOnButtonY = fontPositionOnButtonY;
		this.menuTitle = menuTitle;
	}
	
	public void setMenuTitle(String menuTitle){
		this.menuTitle = menuTitle;
	}
	
	public void addButton(GameButton button, float buttonX, float buttonY){
		if(button.width < menuBarWidth)
			button.setPosition(buttonX, buttonY);
		else
			button.setPosition(menuBarX+(menuBarWidth+(button.width-menuBarWidth)), menuBarY);
		button.setPosition(button.x, button.y-(buttons.size()*button.height));
		buttons.add(button);
	}
	
	public void render(SpriteBatch batch){
		menuBarSprite.draw(batch);
		
		if(font!=null)font.draw(batch, menuTitle, fontPositionOnButtonX, fontPositionOnButtonY);
		
		if(open){
			for(GameButton b:buttons)
				b.render(batch);
		}
	}

	private boolean checkMenuBarPressed(float mouseX, float mouseY, boolean justPressedL){
		boolean hovers = Glbls.withinSquareBounds(mouseX, mouseY, menuBarX, menuBarY, menuBarWidth, menuBarHeight);
		if(justPressedL && hovers)
			return true;
		else if(hovers){
			this.menuBarSprite.setColor(1, 1, 1, 1);
			return false;
		}
		else{
			menuBarSprite.setColor(.8f,.8f,.8f,.8f);
			return false;
		}
	}
	
	public void update(float mouseX, float mouseY, boolean justPressedL){
		if(open){
			for(GameButton b:buttons){
				if(b.isClicked(mouseX, mouseY, justPressedL)){
					b.doAction();
				}
				else if(b.isWithin(mouseX, mouseY)){
					b.sprite.setColor(1, 1, 1, 1);
				}
				else{
					b.sprite.setColor(.8f,.8f,.8f,.8f);
				}
			}
		}
		
		boolean menuBarPressed = checkMenuBarPressed(mouseX, mouseY, justPressedL);
		
		if(!open)isMenuBarOpen = false;
		if(menuBarPressed){
			open = true;
			isMenuBarOpen = true;
		}else if(justPressedL){
			open = false;
		}
	}
	
	public List<GameButton> getButtons(){
		return buttons;
	}
}
