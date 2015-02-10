package xtre.game.game_gui.graphics_user_interface.gui_parts;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.button.GameButton;
import xtre.game.game_gui.heads_up_display.button.GameButtonAction;
import xtre.game.game_gui.heads_up_display.menu.GameMenu;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.font.FontEntity;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DropMenu {
	
	private float x, y, width, height, mouseX, mouseY;
	private boolean mouseLeftPress;
	
	private Sprite mainButton;
	private BitmapFont font;
	private String mainButtonTitle;
	private List<GameMenu> subMenus = new ArrayList<>();
	
	private boolean showingSubMenus = false;
	
	/**
	 * @param mainButton
	 * @param mainButtonTitle
	 * @param subMenuButtons
	 * @param font
	 */
	
	public DropMenu(float x, float y, Sprite mainButton, FontEntity font){
		//super(gim, GI_ID, x, y, mainButton.getWidth(), mainButton.getHeight());
		
		this.x = x;
		this.y = y;
		this.width = mainButton.getWidth();
		this.height = mainButton.getHeight();
		
		mainButton.setPosition(x, y);
		
		this.mainButton = mainButton;
		this.mainButtonTitle = font.text;
		this.font = font.font;
		
	}

	public void addSubMenu(int titleX, int titleY, float menuBarWidth, float menuBarHeight, Sprite menuBarSprite, FontEntity font) {
		GameMenu gm = new GameMenu(menuBarSprite);
		gm.setFont(font, titleX, titleY);
		subMenus.add(gm);
	}
	
	public void addButtonToSubMenu(int i, Sprite button, int x, int y){
		subMenus.get(i).addButton(button, x, y);
	}
	
	public void setSubMenuButtonAction(int i, int j, GameButtonAction gba){
		subMenus.get(i).setActionToButton(j, gba);
	}
	
	public void updateInterface(float mouseX, float mouseY, boolean leftMousePress) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = leftMousePress;
		
		if(showingSubMenus)
			for(GameMenu gm:subMenus)
				gm.update();
		
		if(leftMousePress && GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height))
			showingSubMenus = true;
		else if(leftMousePress)
			showingSubMenus = false;
		
	}

	public void renderInterface(SpriteBatch batch) {
		if(showingSubMenus)
			for(GameMenu gm:subMenus){
				gm.render(batch);
			}
		
		font.draw(batch, mainButtonTitle, mainButton.getX()+(mainButton.getWidth()/2) - 20, mainButton.getY() + (mainButton.getHeight()/2) + 5);
		mainButton.draw(batch);
	}

//	public boolean isActive(float mouseX, float mouseY, boolean mouseLeftPress){
//		this.mouseX = mouseX;
//		this.mouseY = mouseY;
//		
//		boolean active = false;
//
//		if(showingSubMenus)
//			active = true;
//		
//		if(!mouseOutOfBounds())
//			active = true;
//		
//		return active;
//	}
	
	public void dispose() {
	}
}
