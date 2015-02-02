package xtre.ship_forge.components.tile_components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.ship_forge.components.ShipForgeComponent;
import xtre.ship_forge.components.button.ShipForgeButton;
import xtre.ship_forge.components.button.ShipForgeButtonAction;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileSlide extends ShipForgeComponent {
	
	private ShipForgeButton leftButton, rightButton;
	
	private List<ShipTile> tiles = new ArrayList<>();
	private List<Sprite> displaySprites = new ArrayList<>();
	private LinkedList<Integer> slots = new LinkedList<>();
	
	float xx = 88, yy = 8;
	public int tileAmount, maxSlotSize=0, middleSlot, spriteScale;
	public Sprite selectedTile;
	
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
		leftButton = new ShipForgeButton(xx, yy, 72, 112, "leftSlider");
		leftButton.setAction(new ShipForgeButtonAction(){
			public void action(){
				slideLeft();
			}
		});
		leftButton.setColor(1,1,1,1);
		
		rightButton = new ShipForgeButton(xx+424, yy, 72, 112, "rightSlider");
		rightButton.setAction(new ShipForgeButtonAction(){
			public void action(){
				slideRight();
			}
		});
	}
	
	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		if(!empty){
			selectedTile = displaySprites.get(slots.get(middleSlot));
	
			leftButton.update(mouseX, mouseY, mouseLeftPress);
			rightButton.update(mouseX, mouseY, mouseLeftPress);
		}
	}

	@Override
	public void renderComponent(SpriteBatch batch) {
		if(!empty){
			for(int i = 0; i < displaySprites.size() && i < slots.size(); i++){
				if(i < middleSlot)
					batch.draw(displaySprites.get(slots.get(i)), xx+((displaySprites.get(slots.get(i)).getWidth()*spriteScale)*i), yy+((displaySprites.get(slots.get(i)).getHeight()*spriteScale)/2), 32*spriteScale, 32*spriteScale);
				else if(i == middleSlot)
						batch.draw(displaySprites.get(slots.get(i)), xx+((displaySprites.get(slots.get(i)).getWidth()*spriteScale)*i), yy+((displaySprites.get(slots.get(i)).getHeight()*spriteScale)/2)-16, (32*spriteScale)+32, (32*spriteScale)+32);
				else if(i > middleSlot)
					batch.draw(displaySprites.get(slots.get(i)), xx+((displaySprites.get(slots.get(i)).getWidth()*spriteScale)*i)+32, yy+((displaySprites.get(slots.get(i)).getHeight()*spriteScale)/2), (32*spriteScale), (32*spriteScale));
					
			}
		
			leftButton.render(batch);
			rightButton.render(batch);
		}
	}
	
	public void setTiles(List<Sprite> displaySprites){
		this.displaySprites = displaySprites;
		
		//Set amount of slots to the amount of displaySprites there are if there are more slots than displaySprites.
		slots.clear();
		
		if(displaySprites.size() > 4)
		middleSlot = ((maxSlotSize)/2);
		
		//Add the specified amount of slots.
		for(int i = 0; i < maxSlotSize; i++){
			slots.add(i);
		}
		empty = slots.isEmpty() && displaySprites.isEmpty();
	}
	
	public void slideRight(){
		//Shifts each slots number to the positive if it is not more than, else
		//move the slots number to zero/beginning of the displaySprites array.
		
		for(int i = 0; i < slots.size(); i++){
			if(slots.get(i) < displaySprites.size()-1)
				slots.set(i, slots.get(i)+1);
			else{
				slots.set(i, 0);
			}
		}
		for(Integer i:slots)
			System.out.println(" :: :: " + i);
		System.out.println(" # ");
	}
	
	public void slideLeft(){
		//Shifts each slots number to the negative if it is not less than the amount
		//of loaded tiles, else shifts the slots number to the end of the displaySprites array
		
		for(int i = 0; i < slots.size(); i++){
			if(slots.get(i) > 0){
				slots.set(i, slots.get(i)-1);
			}else{
				slots.set(i, displaySprites.size()-1);
			}
		}
		for(Integer i:slots)
			System.out.println(" :: :: " + i);
		System.out.println(" # ");
	}
	
	public void dispose(){
		for(int i = 0; i < tileAmount; i++)
			displaySprites.get(i).getTexture().dispose();
	}
}
