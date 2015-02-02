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
	
//	
//	private ShipForgeButton leftButton, rightButton;
//	private List<ShipTile> tiles = new ArrayList<>();
////	private List<TileSlot> slots = new ArrayList<>();
//	private List<Sprite> displaySprites = new ArrayList<>();
//	public ShipTile selectedTile;
//	
//	public boolean left=false, right=false;
//	
//	private TileSelectionView view;
//		
//	public TileSlide(TileSelectionView view, float x, float y) {
//		this.view = view;
//		this.x = x;
//		this.y = y;
//
//		int xx = (int)x+16, yy = (int)y+16;
//		leftButton = new ShipForgeButton(xx, yy, 96, 112, "leftSlider");
//		leftButton.setAction(new ShipForgeButtonAction(){
//			public void action(){
//				slideLeft(1);
//			}
//		});
//		leftButton.setColor(1,1,1,1);
//		
//		rightButton = new ShipForgeButton(xx+400, yy, 96, 112, "rightSlider");
//		rightButton.setAction(new ShipForgeButtonAction(){
//			public void action(){
//				slideRight(1);
//			}
//		});
//	}
//	
//	public void setTiles(List<ShipTile> tiles) {
//		this.tiles = tiles;
//
//		slots = new ArrayList<>();
//		for(int i = 0; i < tiles.size() && i < 5; i++){
//			slots.add(new TileSlot(tiles.get(i).SHIP_VIEW_SPRITE_ID, i));
//		}
//		for(int i = 0; i < slots.size(); i++){
//			float tileWidth = tiles.get(slots.get(i).tile).gridSize*tiles.get(slots.get(i).tile).gridSize;
//			slots.get(i).updateSlider(view.x + (tileWidth-(tileWidth/3) - (tileWidth/12)) + (i*tileWidth), view.y + 56);
//		}
//		System.out.println("tiles loaded into TileSlide");
//	}
//
//	public void slideRight(int n){
//		for(int i = 0; i < slots.size(); i++){
//			if(slots.get(i).slot > 0)
//				slots.get(i).slot--;
//			else{
//				slots.get(i).slot = tiles.size()-1;
//			}
//		}
//		setSlotChanges();
//	}
//	public void slideLeft(int n){
//		for(int i = 0; i < slots.size(); i++){
//			if(slots.get(i).slot < tiles.size()-1)
//				slots.get(i).slot++;
//			else{
//				slots.get(i).slot = 0;
//			}
//		}
//		setSlotChanges();
//	}
//	
//	private void setSlotChanges(){
//		for(int i = 0; i < slots.size(); i++){
//			slots.get(i).tile = tiles.get(slots.get(i).tile);
//		}
//		for(int i = 0; i < displaySprites.size(); i++){
//			
//		}
//	}
//
//	@Override
//	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
//		if(slots.size() < 1){
//			try{
//				selectedTile.changeTo(tiles.get(slots.get(0).slot));			
//			}catch(IndexOutOfBoundsException e){
//				selectedTile = null;
//			}
//		}else if(slots.size() < 2){
//			selectedTile = tiles.get(slots.get(0).slot);
//		}else if(slots.size() < 3){
//			selectedTile = tiles.get(slots.get(1).slot);
//		}else if(slots.size() < 4){
//			selectedTile = tiles.get(slots.get(1).slot);
//		}else if(slots.size() < 5){
//			selectedTile = tiles.get(slots.get(2).slot);
//		}else if(slots.size() < 6){
//			selectedTile = tiles.get(slots.get(2).slot);
//		}
//	}
//
//	@Override
//	public void renderComponent(SpriteBatch batch) {
//		for(int i = 0; i < slots.size(); i++){
//			displaySprites.get(i).draw(batch);
//		}
//		leftButton.render(batch);
//		rightButton.render(batch);
//	}
//
//	public void checks(float mouseX, float mouseY, boolean mouseLeftPress) {
//		leftButton.update(mouseX, mouseY, mouseLeftPress);
//		rightButton.update(mouseX, mouseY, mouseLeftPress);
//	}
//}

