package xtre.game.game_gui.space_gui;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;
import xtre.game.game_gui.heads_up_display.hud_interfaces.BoxHUD;
import xtre.globals.ScreenGlobals;
import xtre.globals.hud.HUDGlobals;
import xtre.graphics.sprites.sprite_types.SpritesTextArea;
import xtre.graphics.sprites.sprite_types.space_hud.HeadsUpDisplaySprites;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerInterface extends HeadsUpDisplay{
	
	BoxHUD hotBar;
	
	public PlayerInterface(){
//		background = new Sprite(se.getSprite(SpritesTextArea.gui_box_mid));
//		background.setColor(1.4f,1,1,.5f);
//		background.setPosition((ScreenGlobals.WIDTH/2)-(background.getWidth()/2), 7+(background.getHeight()/2));
//		background.setScale(40, 1.5f);

		Sprite[] sprites = new Sprite[]{
				
				se.getSprite(HeadsUpDisplaySprites.paneling_tm),	//[0]
				se.getSprite(HeadsUpDisplaySprites.paneling_bm),	//[1] 
				se.getSprite(HeadsUpDisplaySprites.paneling_lm),	//[2] 
				se.getSprite(HeadsUpDisplaySprites.paneling_mr),	//[3]
				
				se.getSprite(HeadsUpDisplaySprites.paneling_mm),	//[4] 
			
				se.getSprite(HeadsUpDisplaySprites.paneling_tl),	//[5] 
				se.getSprite(HeadsUpDisplaySprites.paneling_tr),	//[6] 
				se.getSprite(HeadsUpDisplaySprites.paneling_bl),	//[7] 
				se.getSprite(HeadsUpDisplaySprites.paneling_br),	//[8]
		};
		
		hotBar = new BoxHUD(HUDGlobals.SPACE_HOTBAR, 10, 10, 80, 3, sprites, 1);
		
		
	}
	
	public void renderInterface(SpriteBatch batch){
		hotBar.render(batch);
	}
	
	public void updateInterface(float mouseX, float mouseY, boolean justPressedL){
		hotBar.updateInterface(mouseX, mouseY, justPressedL);
		
	}
	
}
