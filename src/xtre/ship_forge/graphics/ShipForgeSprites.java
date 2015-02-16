package xtre.ship_forge.graphics;

import java.util.ArrayList;
import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.globals.GlobalSpriteNumbers;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpriteInsideShip;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ShipForgeSprites {

	public static List<ShipTile> getShipFloorTiles(){
		List<ShipTile> tiles = new ArrayList<>();
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_white_panel_edge));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_dark_tile_floor));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_x_tile_floor));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_x_tile_floor_mk2));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_cream_tile_floor));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_cream_tile_floor_v2));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_panel_cross_junction));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_c_br));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_c_lb));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_c_lt));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_c_rt));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_l));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_lr));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_m));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_r));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_t));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_panel_tb));
		tiles.add(new ShipTile(GlobalSpriteNumbers.SPRITE_INSIDE_SHIP_graytiled_floor));
		
		return tiles;
	}
}



//		Sprite s1 = GameSprite.getSprite(SpriteInsideShip.dark_tile_floor);
//		sprites.add(s1);
//		
//		Sprite s2 = GameSprite.getSprite(SpriteInsideShip.cream_tile_floor);
//		sprites.add(s2);
//		
//		Sprite s3 = GameSprite.getSprite(SpriteInsideShip.cream_tile_floor_v2);
//		sprites.add(s3);
//		
//		Sprite s4 = GameSprite.getSprite(SpriteInsideShip.white_panel_edge);
//		sprites.add(s4);
//		
//		Sprite s5 = GameSprite.getSprite(SpriteInsideShip.x_tile_floor);
//		sprites.add(s5);
//		
//		Sprite s6 = GameSprite.getSprite(SpriteInsideShip.x_tile_floor_mk2);
//		sprites.add(s6);