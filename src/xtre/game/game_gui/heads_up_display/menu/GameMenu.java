package xtre.game.game_gui.heads_up_display.menu;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.button.GameButton;
import xtre.game.game_gui.heads_up_display.button.GameButtonAction;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMenu {

	public Sprite sprite;
	public float x, y, width, height;
	public FontEntity title;
	
	private GameButtonAction menuBarAction;
	
	private boolean showingButtons;
	
	private List<GameButton> buttons = new ArrayList<>();
	
	private int padding;
	
	public GameMenu(Sprite sprite, FontEntity title, float x, float y, int padding){
		this.x = x;
		this.y = y;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		
		sprite.setPosition(x, y);
		this.sprite = sprite;
		title.x = sprite.getX() + (sprite.getWidth()/2) - (title.font.getBounds(title.text).width/2);
		title.y = sprite.getY() + (sprite.getHeight()/2) + (title.font.getBounds(title.text).height/2);
		this.title = title;
		this.padding = padding;
	}
	
	public void addButton(Sprite s){
		s.setPosition(sprite.getX(), sprite.getY()-sprite.getHeight() - (buttons.size()*(s.getHeight()+padding)));
		
		GameButton gb = new GameButton(s, new FontEntity(HUDFont.title_font.mediumFont, (title.text + " button[" + (buttons.size()) + "]"), s.getX(), s.getY()));
		buttons.add(gb);
	}
	
	public void update(){
		if(GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), x, y, width, height)){
			sprite.setColor(.8f,.8f,.8f,.8f);
			if(GameInputs.mousePressed(Buttons.LEFT) && !showingButtons)
				showingButtons = true;
		}else{
			sprite.setColor(1f,1f,1f,1f);
			if(GameInputs.mousePressed(Buttons.LEFT) && showingButtons)
				showingButtons = false;
		}
		for(GameButton b:buttons){
			if(b.isClicked())b.doAction();
		}
	}
	
	public void render(SpriteBatch batch){
		sprite.draw(batch);
		if(title!=null && title.font!=null)
			title.font.draw(batch, title.text, title.x, title.y);
		
		if(showingButtons)
			for(GameButton b:buttons)
				b.render(batch);
	}
	
//	public void update(){
//		if(isMenuBarProcessable){
//			checkOpenMenu();
//		}	
//		
//		if(isMenuBarProcessable)
//			isMenuBarOpen = true;
//		else
//			isMenuBarOpen = false;
//		
//		boolean hovers = GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
//		if(checkIfShouldDoAction(hovers)){
//			isMenuBarProcessable = true;
//		}else if(GameInputs.keyPressed(Buttons.LEFT) && isMenuBarProcessable)
//			isMenuBarProcessable = false;
//	}
	
//	private void checkOpenMenu(){
//		for(GameButton b:buttons){
//			if(b.isClicked()){
//				b.doAction();
//				isMenuBarProcessable = false;
//			}
//			else if(b.isWithin()){
//				b.sprite.setColor(1, 1, 1, 1);
//			}
//			else{
//				b.sprite.setColor(.8f,.8f,.8f,.8f);
//			}
//		}
//	}
	
//	private boolean checkIfShouldDoAction(boolean hovers){
//		if(GameInputs.keyPressed(Buttons.LEFT) && hovers){
//			if(menuBarAction!=null){
//				menuBarAction.doAction();
//				if(getButtons().size()>=0)
//					return true;
//				else
//					return false;
//			}else
//				return true;
//			
//		}else if(hovers){
//			this.sprite.setColor(1, 1, 1, 1);
//			return false;
//		}
//		else{
//			sprite.setColor(.8f,.8f,.8f,.8f);
//			return false;
//		}
//	}
	
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

		for(GameButton gb:buttons){
			gb.setOffset(x, y);
		}
	}
	
	public void setTitle(String title, float x, float y){
		this.title.text = title;
	}
	public void dispose() {
		if(sprite!=null)
			sprite.getTexture().dispose();
		
		if(title!=null)
			title.dispose();
	}
}
