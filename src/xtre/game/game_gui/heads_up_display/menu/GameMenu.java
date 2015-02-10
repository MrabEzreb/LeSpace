package xtre.game.game_gui.heads_up_display.menu;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.button.GameButton;
import xtre.game.game_gui.heads_up_display.button.GameButtonAction;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.font.FontEntity;

import com.badlogic.gdx.Input.Buttons;
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
	public GameMenu(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void addButton(Sprite s, float x, float y){
		s.setPosition(x+sprite.getX(), y+sprite.getY());
		GameButton gb = new GameButton(s);
		buttons.add(gb);
	}
	
	public void render(SpriteBatch batch){
		sprite.draw(batch);
		
		if(font!=null)font.draw(batch, title, fontPositionOnButtonX + titleFontOffsetX, fontPositionOnButtonY + titleFontOffsetY);
		
		if(isMenuBarProcessable){
			for(GameButton b:buttons)
				b.render(batch);
		}
	}
	
	public void update(){
		if(isMenuBarProcessable){
			checkOpenMenu();
		}	
		
		if(isMenuBarProcessable)
			isMenuBarOpen = true;
		else
			isMenuBarOpen = false;
		
		boolean hovers = GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		if(checkIfShouldDoAction(hovers)){
			isMenuBarProcessable = true;
		}else if(GameInputs.keyPressed(Buttons.LEFT) && isMenuBarProcessable)
			isMenuBarProcessable = false;
	}
	
	private void checkOpenMenu(){
		for(GameButton b:buttons){
			if(b.isClicked()){
				b.doAction();
				isMenuBarProcessable = false;
			}
			else if(b.isWithin()){
				b.sprite.setColor(1, 1, 1, 1);
			}
			else{
				b.sprite.setColor(.8f,.8f,.8f,.8f);
			}
		}
	}
	
	private boolean checkIfShouldDoAction(boolean hovers){
		if(GameInputs.keyPressed(Buttons.LEFT) && hovers){
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
	public void dispose() {
		if(sprite!=null)
			sprite.getTexture().dispose();
		
		if(font!=null)
			font.dispose();
	}
}
