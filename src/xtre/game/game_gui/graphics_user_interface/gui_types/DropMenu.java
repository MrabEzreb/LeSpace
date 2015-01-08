package xtre.game.game_gui.graphics_user_interface.gui_types;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.utils.menu_bar.GameMenu;
import xtre.globals.GlobalScreen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DropMenu extends GraphicsUserInterface {
	
	private Sprite mainButton;
	private BitmapFont font;
	private String mainButtonTitle;
	private List<GameMenu> subMenus = new ArrayList<>();
	
	private boolean showingSubMenus = false;
	
	/**
	 * 
	 * @param GI_ID
	 * @param mainButton
	 * @param mainButtonTitle
	 * @param subMenuButtons
	 * @param font
	 */
	
	public DropMenu(int GI_ID, float x, float y, Sprite mainButton, String mainButtonTitle, BitmapFont font){
		super(GI_ID, x, y, mainButton.getWidth(), mainButton.getHeight());
		
		mainButton.setPosition(x, y);
		
		this.mainButton = mainButton;
		this.mainButtonTitle = mainButtonTitle;
		this.font = font;
		
	}

	public void addSubMenu(GameMenu subMenu) {
		subMenus.add(subMenu);
	}
	
	@Override
	public void updateInterface(float mouseX, float mouseY, boolean mouseLeftClicked) {
		if(showingSubMenus)
			for(GameMenu gm:subMenus)
				gm.update(mouseX, mouseY, mouseLeftClicked);
		
		if(mouseLeftClicked && !mouseOutOfBounds())
			showingSubMenus = true;
		else if(mouseLeftClicked)
			showingSubMenus = false;
		
	}

	@Override
	public void renderInterface(SpriteBatch batch) {
		if(showingSubMenus)
			for(GameMenu gm:subMenus){
				gm.render(batch);
			}
		
		font.draw(batch, mainButtonTitle, mainButton.getX()+(mainButton.getWidth()/2) - 20, mainButton.getY() + (mainButton.getHeight()/2) + 5);
		mainButton.draw(batch);
	}
	
	@Override
	public void dispose() {
	}
}
