package xtre.globals;

import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpriteInsideShip;
import xtre.graphics.sprites.utils.SpriteDetails;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GlobalSpriteNumbers {

	/**
	 * Used for comparing whether a listed id here is matching the same sprite id.
	 * Used for getting the correct sprite out of the main sprite storage array.
	 */
	
	public static final int SPRITE_INSIDE_SHIP_white_panel_edge = 0;
	public static final int SPRITE_INSIDE_SHIP_dark_tile_floor = 1;
	public static final int SPRITE_INSIDE_SHIP_x_tile_floor = 2;
	public static final int SPRITE_INSIDE_SHIP_x_tile_floor_mk2 = 3;
	public static final int SPRITE_INSIDE_SHIP_cream_tile_floor = 4;
	public static final int SPRITE_INSIDE_SHIP_cream_tile_floor_v2 = 5;
	public static final int SPRITE_INSIDE_SHIP_panel_panel_cross_junction = 6;
	public static final int SPRITE_INSIDE_SHIP_panel_c_br = 7;
	public static final int SPRITE_INSIDE_SHIP_panel_c_lb = 8;
	public static final int SPRITE_INSIDE_SHIP_panel_c_lt = 9;
	public static final int SPRITE_INSIDE_SHIP_panel_c_rt = 10;
	public static final int SPRITE_INSIDE_SHIP_panel_l = 11;
	public static final int SPRITE_INSIDE_SHIP_panel_lr = 12;
	public static final int SPRITE_INSIDE_SHIP_panel_m = 13;
	public static final int SPRITE_INSIDE_SHIP_panel_r = 14;
	public static final int SPRITE_INSIDE_SHIP_panel_t = 15;
	public static final int SPRITE_INSIDE_SHIP_panel_tb = 16;
	public static final int SPRITE_INSIDE_SHIP_graytiled_floor = 17;
	
	public static Sprite getSprite(int id){
		if(id == 0)return GameSprite.getSprite(SpriteInsideShip.white_panel_edge);
		if(id == 1)return GameSprite.getSprite(SpriteInsideShip.dark_tile_floor);
		if(id == 2)return GameSprite.getSprite(SpriteInsideShip.x_tile_floor);
		if(id == 3)return GameSprite.getSprite(SpriteInsideShip.x_tile_floor_mk2);
		if(id == 4)return GameSprite.getSprite(SpriteInsideShip.cream_tile_floor);
		if(id == 5)return GameSprite.getSprite(SpriteInsideShip.cream_tile_floor_v2);
		if(id == 6)return GameSprite.getSprite(SpriteInsideShip.panel_panel_cross_junction);
		if(id == 7)return GameSprite.getSprite(SpriteInsideShip.panel_c_br);
		if(id == 8)return GameSprite.getSprite(SpriteInsideShip.panel_c_lb);
		if(id == 9)return GameSprite.getSprite(SpriteInsideShip.panel_c_lt);
		if(id == 10)return GameSprite.getSprite(SpriteInsideShip.panel_c_rt);
		if(id == 11)return GameSprite.getSprite(SpriteInsideShip.panel_l);
		if(id == 12)return GameSprite.getSprite(SpriteInsideShip.panel_lr);
		if(id == 13)return GameSprite.getSprite(SpriteInsideShip.panel_m);
		if(id == 14)return GameSprite.getSprite(SpriteInsideShip.panel_r);
		if(id == 15)return GameSprite.getSprite(SpriteInsideShip.panel_t);
		if(id == 16)return GameSprite.getSprite(SpriteInsideShip.panel_tb);
		if(id == 17)return GameSprite.getSprite(SpriteInsideShip.graytiled_floor);
		return null;
	}
}
