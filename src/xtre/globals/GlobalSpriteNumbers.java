package xtre.globals;

import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpriteInsideShip;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GlobalSpriteNumbers {

	/**
	 * Used for comparing whether a listed id here is matching the same sprite id.
	 * Used for getting the correct sprite out of the main sprite storage array.
	 */
	
	public static final int SPRITE_INSIDE_SHIP_white_panel_edge = 0;
	
	public static Sprite getSprite(int id){
		if(id == 0)return GameSprite.getSprite(SpriteInsideShip.white_panel_edge);
		return null;
	}
}
