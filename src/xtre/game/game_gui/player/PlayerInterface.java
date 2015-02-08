package xtre.game.game_gui.player;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.gui_parts.SelectionBar;
import xtre.game.player.Player;
import xtre.game.player.ship.Inventory;
import xtre.game.player.space.gui.HUDMetre;
import xtre.globals.game_interface.gui.GlobalsGUI;
import xtre.globals.game_interface.hud.GlobalsHUD;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceGUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerInterface {
	
	private GameInterfaceManager gim;
	
	private HUDMetre metre;
	private SelectionBar hotbar;
	private Inventory inventory;
	
	public PlayerInterface(GameInterfaceManager gim, Player player){
		Sprite[] metreGraphics = new Sprite[]{
			GameSprite.getSprite(SpritesSpaceGUI.fuel_bar_full_l),
			GameSprite.getSprite(SpritesSpaceGUI.fuel_bar_full_m),
			GameSprite.getSprite(SpritesSpaceGUI.fuel_bar_full_r),
			
			GameSprite.getSprite(SpritesSpaceGUI.fuel_bar_empty_l),
			GameSprite.getSprite(SpritesSpaceGUI.fuel_bar_empty_m),
			GameSprite.getSprite(SpritesSpaceGUI.fuel_bar_empty_r)
		};
		metre = new HUDMetre(gim, GlobalsHUD.SPACE_FUEL_METRE, 1, metreGraphics, (int)100);
			
		hotbar = new SelectionBar(gim, GlobalsGUI.HOTBAR, 8, 1, 3, GameSprite.getSprite(SpritesSpaceGUI.hotbar_unselected));

		Sprite[] inventoryGraphics = new Sprite[]{
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tm),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bm),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_lm),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_rm),
				
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_mm),
				
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tl),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tr),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_bl),
				GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_br),
		};
		inventory = new Inventory(gim, GlobalsGUI.SHIP_INVENTORY, 3, 3, inventoryGraphics);
	}
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		metre.update(mouseX, mouseY, mouseLeftPress);
		hotbar.update(mouseX, mouseY, mouseLeftPress);
		inventory.update(mouseX, mouseY, mouseLeftPress);
	}
	
	public void render(SpriteBatch batch){
		metre.render(batch);
		hotbar.render(batch);
		inventory.render(batch);
	}
	
	public boolean setMetreLevel(float level){
		return metre.setLevel(level);
	}
}
