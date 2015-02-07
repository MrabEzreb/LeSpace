package xtre.game.player.ship.scene.inside_ship.graphics;

import java.util.ArrayList;
import java.util.List;

import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipGrid {

	public int gridSize;
	private List<ShipTile> tiles = new ArrayList<>();
	
	float mouseX, mouseY;
	boolean mouseLeftPress;
	
	/**
	 * Grid size is how big each tile is.
	 * @param gridSize
	 */
	public ShipGrid(int gridSize){
		this.gridSize = gridSize;
	}
	
	public void setPosition(int xx, int yy){
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

	public List<ShipTile> getTiles(){
		return tiles;
	}
	
}
