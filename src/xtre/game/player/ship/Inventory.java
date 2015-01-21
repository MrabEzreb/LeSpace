package xtre.game.player.ship;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.hud_parts.BackPanel;
import xtre.game.game_item.GameItem;
import xtre.game.game_item.utils.ItemEmptySlot;
import xtre.globals.game_interface.gui.GlobalsGUI;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpriteInventory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Inventory extends GraphicsUserInterface{
	
	private Sprite emptySlot = GameSprite.getSprite(SpriteInventory.empty_slot);
	
	private GameItem[] slots;
	
	/**
	 * @param slotsHorizontal
	 * @param slotsVirtical
	 * @param graphics
	 */
	public Inventory(GameInterfaceManager gim, int slotsHorizontal, int slotsVirtical, Sprite[] graphics){
		super(gim, GlobalsGUI.SHIP_INVENTORY, 200, 400, slotsHorizontal*32, slotsVirtical*32);
		
		slots = new GameItem[slotsHorizontal*slotsVirtical];
		int xx=0, yy=0;
		for(int i = 0; i < slots.length; i++){
			Sprite s = new Sprite(emptySlot);
			s.setPosition(x + (xx*32), y + (yy*32));
			
			slots[i] = new ItemEmptySlot(s);
			
			if(xx<2)xx++;
			else{
				yy++;
				xx=0;
			}
		}
		
		System.out.println("\n length:" + slots.length);
		
		frame = new BackPanel(200-8, 400-8, 7, 7, graphics);
	}
	
	public void put(GameItem item, int slot){
		slots[slot] = item;
	}
	
	public GameItem pull(int slot){
		return slots[slot];
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void updateInterface(float mouseX, float mouseY, boolean mouseLeftPress) {
	}

	@Override
	public void renderInterface(SpriteBatch batch) {
		for(int i = 0; i < slots.length; i++){
			slots[i].render(batch);
		}
	}

	@Override
	public void setPosition(float x, float y) {
	}
}