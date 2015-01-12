package xtre.game.game_gui.heads_up_display.utils.menu_bar;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButtonAction;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.font.FontEntity;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu {
	
	
	public Sprite sprite;
	public BitmapFont font;
	private String title="";
	private GameButtonAction menuBarAction;
	private float fontPositionOnButtonX, fontPositionOnButtonY, titleFontOffsetX, titleFontOffsetY;
	
	public boolean isMenuBarOpen=false;
	private boolean isMenuBarProcessable=false;
	
	private List<GameButton> buttons = new ArrayList<>();
	
	public GameMenu(){}
	public GameMenu(Sprite menuBarSprite) {
		this.sprite = menuBarSprite;
	}
	
	public void addButton(GameButton button, int x, int y){
		button.setPosition(sprite.getX() + x, sprite.getY() + y);
		buttons.add(button);
	}
	
	public void render(SpriteBatch batch){
		sprite.draw(batch);
		
		if(font!=null)font.draw(batch, title, fontPositionOnButtonX + titleFontOffsetX, fontPositionOnButtonY + titleFontOffsetY);
		
		if(isMenuBarProcessable){
			for(GameButton b:buttons)
				b.render(batch);
		}
	}
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		if(isMenuBarProcessable){
			checkOpenMenu(mouseX, mouseY, mouseLeftPress);
		}	
		
		if(isMenuBarProcessable)
			isMenuBarOpen = true;
		else
			isMenuBarOpen = false;
		
		boolean hovers = GlobalsInterface.withinSquareBounds(mouseX, mouseY, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		if(checkIfShouldDoAction(hovers, mouseLeftPress)){
			isMenuBarProcessable = true;
		}else if(mouseLeftPress && isMenuBarProcessable)
			isMenuBarProcessable = false;
	}
	
	private void checkOpenMenu(float mouseX, float mouseY, boolean mouseLeftPress){
		for(GameButton b:buttons){
			if(b.isClicked(mouseX, mouseY, mouseLeftPress)){
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
	
	private boolean checkIfShouldDoAction(boolean hovers, boolean mouseLeftPress){
		if(mouseLeftPress && hovers){
			if(menuBarAction!=null){
				menuBarAction.doAction();
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
	
	public void setAction(GameButtonAction menuBarAction) {
		this.menuBarAction = menuBarAction;
	}
	
	public void setActionToButton(int i, GameButtonAction gba) {
		buttons.get(i).setAction(gba);
	}
	
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		fontPositionOnButtonX = sprite.getX(); 
		fontPositionOnButtonY = sprite.getY();

		for(GameButton gb:buttons){
			gb.setOffset(x, y);
		}
	}
	
	public void setFont(FontEntity font, float x, float y){
		this.font = font.font;
		title = font.text;
		titleFontOffsetX = x;
		titleFontOffsetY = y;
	}
}
