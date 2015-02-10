package xtre.game.player.ship;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_item.GameItem;
import xtre.game.game_item.utils.ItemEmptySlot;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpriteInventory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Inventory extends GraphicsUserInterface{
	
	private Sprite emptySlot = GameSprite.getSprite(SpriteInventory.empty_slot);
	
	private GameItem[] slots;
	
	private final float inventoryX, inventoryY;
	
	/**
	 * 
	 * @param gim
	 * @param slotsHorizontal
	 * @param slotsVirtical
	 * @param graphics
	 */
	public Inventory(GameInterfaceManager gim, int globalID, int slotsHorizontal, int slotsVirtical, Sprite[] graphics){
		super(gim, globalID, 200, 400, slotsHorizontal*32, slotsVirtical*32);
		inventoryX = 0;
		inventoryY = 0;
		
		System.out.println(x + " pos " + y);
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
	public void updateInterfaces() {
	}
	
	@Override
	public void updateInteractives() {
	}

	@Override
	public void renderInterfaces(SpriteBatch batch) {
		for(int i = 0; i < slots.length; i++){
			slots[i].render(batch);
		}
	}
	
	public void renderInteractives(SpriteBatch batch){
		
	}

	@Override
	public void setPosition(float x, float y) {
		for(GameItem gi: slots){
			gi.sprite.setPosition(inventoryX+x, inventoryY+y);
		}
	}
}