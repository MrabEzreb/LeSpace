package xtre.game.game_gui.heads_up_display.utils.menu_bar;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;
import xtre.globals.hud.Glbls;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu {
	
	private float menuBarX, menuBarY, menuBarWidth, menuBarHeight;

	public Sprite menuBarSprite;
	
	private MenuBarAction menuBarAction;
	
	public BitmapFont font;
	private String menuTitle="";
	private float fontPositionOnButtonX, fontPositionOnButtonY;
	
	public boolean isMenuBarOpen=false;
	private boolean isMenuBarProcessable=false;
	
	private List<GameButton> buttons = new ArrayList<>();
	
	public GameMenu(){}
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
		
		boolean hovers = Glbls.withinSquareBounds(mouseX, mouseY, menuBarX, menuBarY, menuBarWidth, menuBarHeight);
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
				menuBarAction.doAction();
				if(getButtons().size()>=0)
					return true;
				else
					return false;
			}else
				return true;
			
		}else if(hovers){
			this.menuBarSprite.setColor(1, 1, 1, 1);
			return false;
		}
		else{
			menuBarSprite.setColor(.8f,.8f,.8f,.8f);
			return false;
		}
	}
	
	public List<GameButton> getButtons(){
		return buttons;
	}
	
	public void addAction(MenuBarAction menuBarAction) {
		this.menuBarAction = menuBarAction;
	}
}
