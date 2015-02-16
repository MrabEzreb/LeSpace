package xtre.ship_forge.components.tile_components;

import java.util.LinkedList;
import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.ship_forge.components.ShipForgeComponent;
import xtre.ship_forge.components.button.Action;
import xtre.ship_forge.components.button.ShipForgeButton;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileSlide extends ShipForgeComponent {
	
	private TileSpriteForm tiles = new TileSpriteForm();
	private LinkedList<Integer> slots = new LinkedList<>();
	
	float xx = 88, yy = 0;
	public int tileAmount, maxSlotSize=0, middleSlot, spriteScale;
	public Sprite selectedSprite;
	public ShipTile selectedTile;
	
	public boolean empty = true;

	/**
	 * @param x
	 * @param y
	 * @param maxSlotSize
	 * @param spriteScale
	 */
	public TileSlide(float x, float y, int maxSlotSize, int spriteScale){
		this.x = x;
		this.y = y;
		this.maxSlotSize = maxSlotSize;
		this.spriteScale = spriteScale;
		xx += x;
		yy += y;

		int xx = (int)x+16, yy = (int)y+16;
		
		ShipForgeButton leftButton = new ShipForgeButton(xx, yy, 72, 96, "leftSlider");
		leftButton.setAction(new Action(){
			public void action(){
				slideLeft();
			}
		});
		leftButton.setColor(1,1,1,1);
		buttons.add(leftButton);
		
		ShipForgeButton rightButton = new ShipForgeButton(xx+424, yy, 72, 96, "rightSlider");
		rightButton.setAction(new Action(){
			public void action(){
				slideRight();
			}
		});
		buttons.add(rightButton);
	}
	
	public void setTiles(List<ShipTile> tiles){
		for(int i = 0; i < tiles.size(); i++){
			this.tiles.addTile(tiles.get(i));
		}
		slots.clear();
		
		//Specify slot amount
		if(tiles.size() > 4)
		middleSlot = ((maxSlotSize)/2);
		
		//Add the specified amount of slots.
		for(int i = 0; i < maxSlotSize; i++){
			slots.add(i);
		}
		empty = slots.isEmpty() && tiles.isEmpty();
		for(int i = 0; i < tiles.size(); i++){
			
		}
	}
	
	@Override
	public void mouseClick(float mouseX, float mouseY) {}

	@Override
	public void renderComponent(SpriteBatch batch) {
		if(!empty){
			for(int i = 0; i < tiles.size() && i < slots.size(); i++){
				if(i < middleSlot)
					batch.draw(tiles.getSprite(slots.get(i)), xx+((tiles.getSprite(slots.get(i)).getWidth()*spriteScale)*i), yy+((tiles.getSprite(slots.get(i)).getHeight()*spriteScale)/2), 32*spriteScale, 32*spriteScale);
				else if(i == middleSlot)
						batch.draw(tiles.getSprite(slots.get(i)), xx+((tiles.getSprite(slots.get(i)).getWidth()*spriteScale)*i), yy+((tiles.getSprite(slots.get(i)).getHeight()*spriteScale)/2)-16, (32*spriteScale)+32, (32*spriteScale)+32);
				else if(i > middleSlot)
					batch.draw(tiles.getSprite(slots.get(i)), xx+((tiles.getSprite(slots.get(i)).getWidth()*spriteScale)*i)+32, yy+((tiles.getSprite(slots.get(i)).getHeight()*spriteScale)/2), (32*spriteScale), (32*spriteScale));
			}
		}
	}
	
	public void slideLeft(){
		//Shifts each slots number to the positive if it is not more than, else
		//move the slots number to the beginning of the displaySprites array.
		
		for(int i = 0; i < slots.size(); i++){
			if(slots.get(i) < tiles.size()-1)
				slots.set(i, slots.get(i)+1);
			else{
				slots.set(i, 0);
			}
		}
	}
	
	public void slideRight(){
		//Shifts each slots number to the negative if it is not less than the amount
		//of loaded tiles, else shifts the slots number to the end of the displaySprites array
		
		for(int i = 0; i < slots.size(); i++){
			if(slots.get(i) > 0){
				slots.set(i, slots.get(i)-1);
			}else{
				slots.set(i, tiles.size()-1);
			}
		}
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		if(!empty){
			selectedSprite = tiles.getSprite(slots.get(middleSlot));
			selectedTile = tiles.getTile(slots.get(middleSlot));
		}
	}

	@Override
	public void setupMenu(float x, float y, float width, float height) {
	}
	
	public void dispose(){
		for(int i = 0; i < tileAmount; i++)
			tiles.dispose();
		
		menu.dispose();
		for(ShipForgeButton b:buttons)
			b.dispose();
	}
}