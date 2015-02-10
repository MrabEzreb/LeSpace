package xtre.game.player.ship.scene.inside_ship;

import java.util.HashMap;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.player.Player;
import xtre.game.player.ship.Ship;
import xtre.game.player.ship.scene.inside_ship.graphics.InsideShip;
import xtre.game.scene_manager.GameScene;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpriteInsideShip;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class ShipScene extends GameScene {
	
	private GameInterfaceManager gim;
	private Ship ship;
	
	private InsideShip insideShip;

	private HashMap<Integer, Sprite> sprites = new HashMap<>();
	
	public ShipScene(GameInterfaceManager gim, World world, Player player){
		super(world, player);
		sprites.get(1);
		loadSprites();
		
		insideShip = new InsideShip();
	}

	public void setGameInterfaceManager(GameInterfaceManager gim){
		this.gim = gim;
	}
	
	public void setShip(Ship ship){
		this.ship = ship;
	}
	
	private void loadSprites(){
//		public final static SpriteID white_panel_edge = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "white_panel_edge");
//
//		public final static SpriteID panel_panel_cross_junction = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_cross_junction");
//		public final static SpriteID panel_c_br = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_br");
//		public final static SpriteID panel_c_lb = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_lb");
//		public final static SpriteID panel_c_lt = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_lt");
//		public final static SpriteID panel_c_rt = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_rt");
//		public final static SpriteID panel_l = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_rt");
//		public final static SpriteID panel_lr = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_rt");
//		public final static SpriteID panel_m = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_rt");
//		public final static SpriteID panel_r = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_rt");
//		public final static SpriteID panel_t = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_rt");
//		public final static SpriteID panel_tb = new SpriteID("output/game_sprites/player_ship/inside_ship/paneling/paneling.pack", "panel_c_rt");

		sprites.put(0, GameSprite.getSprite(SpriteInsideShip.white_panel_edge));
		sprites.put(1, GameSprite.getSprite(SpriteInsideShip.panel_panel_cross_junction));
		sprites.put(2, GameSprite.getSprite(SpriteInsideShip.panel_c_br));
		sprites.put(3, GameSprite.getSprite(SpriteInsideShip.panel_c_lb));
		sprites.put(4, GameSprite.getSprite(SpriteInsideShip.panel_c_lt));
		sprites.put(5, GameSprite.getSprite(SpriteInsideShip.panel_c_rt));
		sprites.put(6, GameSprite.getSprite(SpriteInsideShip.panel_l));
		sprites.put(7, GameSprite.getSprite(SpriteInsideShip.panel_lr));
		sprites.put(8, GameSprite.getSprite(SpriteInsideShip.panel_m));
		sprites.put(9, GameSprite.getSprite(SpriteInsideShip.panel_r));
		sprites.put(10, GameSprite.getSprite(SpriteInsideShip.panel_t));
		sprites.put(11, GameSprite.getSprite(SpriteInsideShip.panel_tb));
		
	}
	
	@Override
	public void update(float camX, float camY) {
		insideShip.update();
	}
	
	@Override
	public void render(SpriteBatch batch){
		insideShip.render(batch);
	}

	@Override
	public void dispose() {
		insideShip.dispose();
	}
}