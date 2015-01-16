package xtre.game.player.ship.scene.inside_ship.graphics;

import java.util.ArrayList;
import java.util.List;

import xtre.globals.GlobalScreen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipGrid {

	public int gridSize;
	public float gridHeight, gridWidth;
	List<ShipTile> tiles = new ArrayList<>();
	
	public ShipGrid(int gridSize){
		this.gridSize = gridSize;
		gridWidth = GlobalScreen.WIDTH/gridSize;
		gridHeight = GlobalScreen.HEIGHT/gridSize;
	}
	
	public void setPosition(int x, int y){
		
	}
	
	public void addTile(ShipTile tile){
		tiles.add(tile); 
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
	}

	public void render(SpriteBatch batch) {
	}
}
