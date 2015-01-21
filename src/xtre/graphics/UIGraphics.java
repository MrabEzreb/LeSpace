package xtre.graphics;

import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class UIGraphics {
	
	private UIGraphics(){}
	
	/**
	 * TM, BM, LM, RM, MM, TL, TR, BL, BR;
	 * @return
	 */
	public static final Sprite[] getBoxHUDGraphics() {
		Sprite[] boxHUDGraphics = new Sprite[] {
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tm), //[0]
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bm), //[1] 
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_lm), //[2] 
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_rm), //[3]

				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_mm), //[4] 

				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tl), //[5] 
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tr), //[6] 
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bl), //[7] 
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_br), //[8]
		};
		return boxHUDGraphics;
	}
	
	public static Sprite getTM(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tm);
	}
	public static Sprite getBM(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bm);
	}
	public static Sprite getLM(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_lm);
	}
	public static Sprite getRM(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_rm);
	}
	public static Sprite getMM(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_mm);
	}
	public static Sprite getTL(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tl);
	}
	public static Sprite getTR(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tr);
	}
	public static Sprite getBL(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bl);
	}
	public static Sprite getBR(){
		return GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_br);
	}
}
