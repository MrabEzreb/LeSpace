package xtre.ship_forge.graphics;

import java.util.ArrayList;
import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpriteInsideShip;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ShipForgeSprites {

	public static List<Sprite> getShipFloorTiles(){
		List<Sprite> sprites = new ArrayList<>();
		
		Sprite s1 = GameSprite.getSprite(SpriteInsideShip.dark_tile_floor);
		sprites.add(s1);
		
		Sprite s2 = GameSprite.getSprite(SpriteInsideShip.cream_tile_floor);
		sprites.add(s2);
		
		Sprite s3 = GameSprite.getSprite(SpriteInsideShip.cream_tile_floor_v2);
		sprites.add(s3);
		
		Sprite s4 = GameSprite.getSprite(SpriteInsideShip.white_panel_edge);
		sprites.add(s4);
		
		Sprite s5 = GameSprite.getSprite(SpriteInsideShip.x_tile_floor);
		sprites.add(s5);
		
		Sprite s6 = GameSprite.getSprite(SpriteInsideShip.x_tile_floor_mk2);
		sprites.add(s6);
		
		return sprites;
	}
}
