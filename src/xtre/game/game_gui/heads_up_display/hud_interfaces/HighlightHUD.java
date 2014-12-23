package xtre.game.game_gui.heads_up_display.hud_interfaces;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space_hud.HeadsUpDisplaySprites;

public class HighlightHUD extends HeadsUpDisplay {

	SpriteEntity se = new SpriteEntity();
	
	public HighlightHUD(int id, int x, int y, int width, int height){
		super("i am a HighlightHUD", id, x, y, width, height);
		
		background = se.getSprite(HeadsUpDisplaySprites.star_highlight);
		
		background.setPosition(x, y);
		background.setScale(width, height);
	}
}
