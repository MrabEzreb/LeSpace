package xtre.game.player.space.gui;
//package xtre.game.game_gui.heads_up_display.hud_parts;
//
//import xtre.game.game_gui.GameInterfaceManager;
//import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;
//import xtre.graphics.sprites.SpriteEntity;
//import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//public class HUDHighlight extends HeadsUpDisplay {
//
//	SpriteEntity se = new SpriteEntity();
//	
//	public HUDHighlight(GameInterfaceManager gim, int id, int x, int y, int width, int height){
//		super(gim, id, x, y, width, height);
//		
//		background = se.getSprite(SpritesHeadsUpDisplay.star_highlight);
//		
//		background.setPosition(x, y);
//		background.setScale(width, height);
//	}
//
//	@Override
//	public void updateInterface(float mouseX, float mouseY,	boolean mouseLeftPress) {
//	}
//	
//	public void renderInterface(SpriteBatch batch){
//		background.draw(batch);
//	}
//	
//	public boolean isActive(float mouseX, float mouseY, boolean mouseLeftPress){
//		return true;
//	}
//	
//}
