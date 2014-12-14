package xtre.game.heads_up_display.types;

import xtre.game.heads_up_display.utils.HeadsUpDisplay;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.HeadsUpDisplaySprites;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class HighlightHUD extends HeadsUpDisplay {

	SpriteEntity se = new SpriteEntity();
	
	public HighlightHUD(int id, int x, int y, int width, int height){
		this.id = id;
		
		background = se.getSprite(HeadsUpDisplaySprites.star_highlight);
		
		background.setPosition(x, y);
		background.setScale(width, height);
		
	}
}
