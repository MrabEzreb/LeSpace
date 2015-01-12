package xtre.game.space_world;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics.UIGraphics;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.graphics_user_interface.gui_parts.DropMenu;
import xtre.game.game_gui.heads_up_display.hud_parts.BackPanel;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;
import xtre.game.game_gui.heads_up_display.utils.menu_bar.GameMenu;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIStarSelection extends GraphicsUserInterface {
	
	private Sprite highlight;
	private Stars stars;
	private int selectedStar =-1;
	
	private ViewStarOptions menu;
	
	private boolean menuJustOpened = false, menuActive = false;
	
	public GUIStarSelection(GameInterfaceManager gim, int GI_ID, Sprite highlight, Stars stars) {
		super(gim, GI_ID, 0, 0, 15*16, 8*16);
		
		this.highlight = highlight;
		this.stars = stars;		
		
		this.isAlwaysActive = true;
		
		menu = new ViewStarOptions(new BackPanel(0, 0, 15, 8, UIGraphics.getBoxHUDGraphics()));
	}

	@Override
	public void dispose() {
	}

	@Override
	public void updateInterface(float mouseX, float mouseY, boolean mouseLeftPress) {
		selectedStar = -1;
		for(int i = 0; i < stars.length; i++){
			if(stars.getStar(i).onScreen){
				if(GlobalsInterface.isWithin(mouseX, mouseY, stars.getStar(i).getX(), stars.getStar(i).getY(), 32)){
					selectedStar = i;
					break;
				}
			}
		}
		
		if(selectedStar>-1 &! menuActive){
			highlight.setPosition(stars.getStar(selectedStar).getX(), stars.getStar(selectedStar).getY());
		
			if(mouseLeftPress)
				menuJustOpened = true;
		}else if(mouseLeftPress){
			menuJustOpened = false;
			menuActive = !menu.isClosable();
			
		}

		if(menuJustOpened){
			menu.setPosition(stars.getStar(selectedStar).getX(), stars.getStar(selectedStar).getY());
			super.x = stars.getStar(selectedStar).getX();
			super.y = stars.getStar(selectedStar).getY();
			menuJustOpened = false;
			menuActive = true;
		}
		
		if(menuActive){
			menu.update(mouseX, mouseY, mouseLeftPress);
		}
	}

	@Override
	public void renderInterface(SpriteBatch batch) {
		if(selectedStar>-1 &! menuActive)
			highlight.draw(batch);

		if(menuActive){
			menu.render(batch);
		}
	}
	
	public boolean isActive(float mouseX, float mouseY, boolean mouseLeftPressed){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		if(!mouseOutOfBounds()){
			return true;
		}
		else
			return false;
	}
}
