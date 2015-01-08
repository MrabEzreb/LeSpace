package xtre.game.game_gui.graphics;

import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class UIGraphics {

	public static final Sprite[] getBoxHUDGraphics(SpriteEntity se) {
		Sprite[] boxHUDGraphics = new Sprite[] {

		se.getSprite(SpritesHeadsUpDisplay.paneling_tm), //[0]
				se.getSprite(SpritesHeadsUpDisplay.paneling_bm), //[1] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_lm), //[2] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_mr), //[3]

				se.getSprite(SpritesHeadsUpDisplay.paneling_mm), //[4] 

				se.getSprite(SpritesHeadsUpDisplay.paneling_tl), //[5] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_tr), //[6] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_bl), //[7] 
				se.getSprite(SpritesHeadsUpDisplay.paneling_br), //[8]
		};
		return boxHUDGraphics;
	}
}
