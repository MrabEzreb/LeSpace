package xtre.game.game_gui.heads_up_display.utils.menu_bar;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;
import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu {
	
	private float menuBarX, menuBarY, menuBarWidth, menuBarHeight;

	public Sprite sprite;
	
	private MenuBarAction menuBarAction;
	
	public BitmapFont font;
	private String menuTitle="";
	private float fontPositionOnButtonX, fontPositionOnButtonY;
	
	public boolean isMenuBarOpen=false;
	private boolean isMenuBarProcessable=false;
	
	private List<GameButton> buttons = new ArrayList<>();
	
	public GameMenu(){}
	public GameMenu(float menuBarX, float menuBarY, float menuBarWidth, float menuBarHeight, Sprite menuBarSprite) {
		this.menuBarX = menuBarX;
		this.menuBarY = menuBarY;
		this.menuBarWidth = menuBarSprite.getWidth();
		this.menuBarHeight = menuBarSprite.getHeight();
		this.sprite = menuBarSprite;
		
		this.sprite.setPosition(menuBarX, menuBarY);
	}
	
	public void setBitmapFont(BitmapFont font, float fontPositionOnButtonX, float fontPositionOnButtonY, String menuTitle){
		this.font = font;
		this.fontPositionOnButtonX = fontPositionOnButtonX;
		this.fontPositionOnButtonY = fontPositionOnButtonY;
		this.menuTitle = menuTitle;
	}
	
	public void addButton(GameButton button, float buttonX, float buttonY){
		if(button.sprite.getWidth() < menuBarWidth)
			button.setPosition(buttonX, buttonY);
		else
			button.setPosition(menuBarX+(menuBarWidth+(button.sprite.getWidth()-menuBarWidth)), menuBarY);
		button.setPosition(button.sprite.getX(), button.sprite.getY()-(buttons.size()*button.sprite.getHeight()));
		buttons.add(button);
	}
	
	public void render(SpriteBatch batch){
		sprite.draw(batch);
		
		if(font!=null)font.draw(batch, menuTitle, fontPositionOnButtonX, fontPositionOnButtonY);
		
		if(isMenuBarProcessable){
			for(GameButton b:buttons)
				b.render(batch);
		}
	}
	
	public void update(float mouseX, float mouseY, boolean justPressedL){
		if(isMenuBarProcessable)
			isMenuBarOpen = true;
		else
			isMenuBarOpen = false;
		
		boolean hovers = GlobalsInterface.withinSquareBounds(mouseX, mouseY, menuBarX, menuBarY, menuBarWidth, menuBarHeight);
		if(checkIfShouldDoAction(hovers, justPressedL)){
			isMenuBarProcessable = true;
		}else if(justPressedL && isMenuBarProcessable)
			isMenuBarProcessable = false;
		
		if(isMenuBarProcessable){
			checkOpenMenu(mouseX, mouseY, justPressedL);
		}	
	}
	
	private void checkOpenMenu(float mouseX, float mouseY, boolean justPressedL){
		for(GameButton b:buttons){
			if(b.isClicked(mouseX, mouseY, justPressedL)){
				b.doAction();
				
				isMenuBarProcessable = false;
			}
			else if(b.isWithin(mouseX, mouseY)){
				b.sprite.setColor(1, 1, 1, 1);
			}
			else{
				b.sprite.setColor(.8f,.8f,.8f,.8f);
			}
		}
	}
	
	private boolean checkIfShouldDoAction(boolean hovers, boolean justPressedL){
		if(justPressedL && hovers){
			if(menuBarAction!=null){
				menuBarAction.action();
				if(getButtons().size()>=0)
					return true;
				else
					return false;
			}else
				return true;
			
		}else if(hovers){
			this.sprite.setColor(1, 1, 1, 1);
			return false;
		}
		else{
			sprite.setColor(.8f,.8f,.8f,.8f);
			return false;
		}
	}
	
	public List<GameButton> getButtons(){
		return buttons;
	}
	
	public void setAction(MenuBarAction menuBarAction) {
		this.menuBarAction = menuBarAction;
	}
}
