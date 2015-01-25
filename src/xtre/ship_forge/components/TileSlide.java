package xtre.ship_forge.components;

import java.util.ArrayList;
import java.util.List;

import xtre.ship_forge.components.button.ShipForgeButton;
import xtre.ship_forge.components.button.ShipForgeButtonAction;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileSlide extends ShipForgeComponent{
	
	private float x, y;
	
	private ShipForgeButton leftButton, rightButton;
	private List<Sprite> tiles = new ArrayList<>();
	private List<Slot> slots = new ArrayList<>();
	public Sprite selectedTile;
	
	public boolean left=false, right=false;
	
	private TileSelectionView view;
		
	public TileSlide(TileSelectionView view, float x, float y) {
		this.view = view;
		this.x = x;
		this.y = y;

		int xx = (int)x+16, yy = (int)y+16;
		leftButton = new ShipForgeButton(xx, yy, 96, 112, "leftSlider");
		leftButton.setAction(new ShipForgeButtonAction(){
			public void action(){
				slideLeft(1);
			}
		});
		leftButton.setColor(1,1,1,1);
		
		rightButton = new ShipForgeButton(xx+400, yy, 96, 112, "rightSlider");
		rightButton.setAction(new ShipForgeButtonAction(){
			public void action(){
				slideRight(1);
			}
		});
	}
	
	public void setTiles(List<Sprite> tiles) {
		this.tiles = tiles;

		slots = new ArrayList<>();
		for(int i = 0; i < tiles.size() && i < 5; i++){
			slots.add(new Slot(tiles.get(i), i));
		}
		for(int i = 0; i < slots.size(); i++){
			float tileWidth = slots.get(i).tile.getWidth()*slots.get(i).tile.getScaleX();
			slots.get(i).updateSlider(view.x + (tileWidth-(tileWidth/3) - (tileWidth/12)) + (i*tileWidth), view.y + 56);
		}
		System.out.println("tiles loaded into TileSlide");
	}

	public void slideRight(int n){
		for(int i = 0; i < slots.size(); i++){
			if(slots.get(i).slot > 0)
				slots.get(i).slot--;
			else{
				slots.get(i).slot = tiles.size()-1;
			}
		}
		setSlotChanges();
	}
	public void slideLeft(int n){
		for(int i = 0; i < slots.size(); i++){
			if(slots.get(i).slot < tiles.size()-1)
				slots.get(i).slot++;
			else{
				slots.get(i).slot = 0;
			}
		}
		setSlotChanges();
	}
	
	private void setSlotChanges(){
		for(int i = 0; i < slots.size(); i++){
			slots.get(i).tile = tiles.get(slots.get(i).slot);
		}
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		if(slots.size() < 1){
			try{
				selectedTile = tiles.get(slots.get(0).slot);			
			}catch(IndexOutOfBoundsException e){
				selectedTile = null;
			}
		}else if(slots.size() < 2){
			selectedTile = tiles.get(slots.get(0).slot);
		}else if(slots.size() < 3){
			selectedTile = tiles.get(slots.get(1).slot);
		}else if(slots.size() < 4){
			selectedTile = tiles.get(slots.get(1).slot);
		}else if(slots.size() < 5){
			selectedTile = tiles.get(slots.get(2).slot);
		}else if(slots.size() < 6){
			selectedTile = tiles.get(slots.get(2).slot);
		}
	}

	@Override
	public void renderComponent(SpriteBatch batch) {
		for(int i = 0; i < slots.size(); i++){
			slots.get(i).getTile().draw(batch);
		}
		leftButton.render(batch);
		rightButton.render(batch);
	}

	public void checks(float mouseX, float mouseY, boolean mouseLeftPress) {
		leftButton.update(mouseX, mouseY, mouseLeftPress);
		rightButton.update(mouseX, mouseY, mouseLeftPress);
	}
}

class Slot{
	public Sprite tile;
	public int slot;
	public float x, y, width, height;
	
	public Slot(Sprite tile, int slot){
		this.tile = tile;
		this.slot = slot;
	}
	
	public Sprite getTile(){
		tile.setPosition(x, y);
		return tile;
	}
	
	public void updateSlider(float x, float y){
		this.x = x;
		this.y = y;
	}
}

