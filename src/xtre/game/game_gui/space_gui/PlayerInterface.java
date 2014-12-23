package xtre.game.game_gui.space_gui;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;
import xtre.game.game_gui.heads_up_display.hud_interfaces.BoxHUD;
import xtre.game.game_gui.heads_up_display.menu_bar.MenuBarAction;
import xtre.game.game_gui.heads_up_display.utils.HUDManager;
import xtre.globals.hud.HUDGlobals;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerInterface extends HeadsUpDisplay{
	
	BoxHUD hotbar;
	HUDManager hud;
	
	public PlayerInterface(HUDManager hud){
		this.hud = hud;
//		background = new Sprite(se.getSprite(SpritesTextArea.gui_box_mid));
//		background.setColor(1.4f,1,1,.5f);
//		background.setPosition((ScreenGlobals.WIDTH/2)-(background.getWidth()/2), 7+(background.getHeight()/2));
//		background.setScale(40, 1.5f);

		Sprite[] sprites = new Sprite[]{
				se.getSprite(SpritesHeadsUpDisplay.paneling_tm),	//[0]
				se.getSprite(SpritesHeadsUpDisplay.paneling_bm),	//[1] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_lm),	//[2] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_mr),	//[3]
				
				se.getSprite(SpritesHeadsUpDisplay.paneling_mm),	//[4]
			
				se.getSprite(SpritesHeadsUpDisplay.paneling_tl),	//[5] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_tr),	//[6] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_bl),	//[7] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_br),	//[8]
		};
		
		hotbar = new BoxHUD(HUDGlobals.SPACE_HOTBAR, 10, 10, 80, 3, sprites, 1);
		hotbar.createHotbarMenu(0, 8, se.getSprite(SpritesSpaceHudMenu.menu_bar));
		hotbar.setMenuBarAction(0, new MenuBarAction(){
			public void doAction(){
				System.out.println("well well");
			}
		});
		hud.addBoxHUD(hotbar);
	}
	
	public void renderInterface(SpriteBatch batch){
	}
	
	public void updateInterface(float mouseX, float mouseY, boolean justPressedL){
		
	}
}
