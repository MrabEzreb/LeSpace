package xtre.game.player.ship.scene.inside_ship.graphics;

import java.util.ArrayList;
import java.util.List;

import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipGrid {

	public int gridSize;
	public float gridHeight, gridWidth;
	List<ShipTile> tiles = new ArrayList<>();
	
	float mouseX, mouseY;
	boolean mouseLeftPress;
	
	/**
	 * Grid size is how big each tile is.
	 * @param gridSize
	 */
	public ShipGrid(int gridSize){
		this.gridSize = gridSize;
		gridWidth = GlobalScreen.GAME_WIDTH/gridSize;
		gridHeight = GlobalScreen.GAME_HEIGHT/gridSize;
	}
	
	public void setPosition(int xx, int yy){
		for(int x = 0; x < gridWidth; x++){
			for(int y = 0; y < gridHeight; y++){
				tiles.get((int)(x+y*gridWidth)).setPosition(x, y);
			}
		}
	}
	
	public void addTile(ShipTile tile){
		tiles.add(tile);
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		System.out.println(mouseX + " " + mouseY);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = mouseLeftPress;
	}
	
	public int getHighlightedTileID(){
		for(int i = 0; i < tiles.size(); i++){
			if(GlobalsInterface.withinSquareBounds(mouseX, mouseY, tiles.get(i).x, tiles.get(i).y, tiles.get(i).gridSize, tiles.get(i).gridSize))
				return i;
		}
		return -1;
	}
	
	public ShipTile getHighlightedTile(){
		int tile = getHighlightedTileID();
		if(tile>-1 && tile < tiles.size())
			return tiles.get(tile);
		else
			return null;
	}

	public void render(SpriteBatch batch) {
	}

	public void setHighlightedTile(int i) {
		tiles.set(i, new ShipTile(tiles.get(i).SHIP_VIEW_SPRITE_ID, tiles.get(i).gridLocationX, tiles.get(i).gridLocationY, tiles.get(i).gridSize));
	}
}
