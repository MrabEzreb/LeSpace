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
	
	private GUIViewStarOptions menu;
	
	public GUIStarSelection(GameInterfaceManager gim, int GI_ID, Sprite highlight, Stars stars) {
		super(gim, GI_ID, 0, 0, 1300, 800);
		
		this.highlight = highlight;
		this.stars = stars;		
		
		this.isAlwaysActive = true;
		
		menu = new GUIViewStarOptions(gim, GI_ID, new BackPanel(0, 0, 15, 8, UIGraphics.getBoxHUDGraphics()));
	}

	@Override
	public void updateInterface(float mouseX, float mouseY, boolean mouseLeftPress) {		
		if(mouseLeftPress){
			if(menu.mouseOutOfBounds(mouseX, mouseY, mouseLeftPress)){
				if(menu.isClosable()){
					menu.active = false;
					System.out.println("Set menu active to false");
				}
			}
			if(!menu.active)
			for(int i = 0; i < stars.length; i++){
				if(stars.getStar(i).onScreen){
					if(GlobalsInterface.withinSquareBounds(mouseX, mouseY, stars.getStar(i).getX()-16, stars.getStar(i).getY()-16, 32, 32)){
						menu.setPosition(stars.getStar(i).getX(), stars.getStar(i).getY());
						menu.active = true;
						break;
					}				
				}
			}
		}
	}
	
	@Override
	public void renderInterface(SpriteBatch batch) {
		if(selectedStar>-1)
			highlight.draw(batch);
	}

	@Override
	public void setPosition(float x, float y) {
		menu.setPosition(x, y);
	}

	@Override
	public void dispose() {
	}
}

