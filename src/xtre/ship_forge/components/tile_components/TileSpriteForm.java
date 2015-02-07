package xtre.ship_forge.components.tile_components;

import java.util.ArrayList;
import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.globals.GlobalSpriteNumbers;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.ship_forge.components.button.ShipForgeButton;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileSpriteForm	{
	/**
	 * Takes ShipTiles and creates their sprites, then stores them for rendering.
	 */
	
	private List<Sprite> displaySprites = new ArrayList<>();
	private List<ShipTile> shipTiles = new ArrayList<>();
	
	private float mouseX=0, mouseY=0;
	
	public void setGrid(ShipGrid grid){
		dispose();
		displaySprites.clear();
		shipTiles.clear();
		
		shipTiles = grid.getTiles();
		for(int i = 0; i < shipTiles.size(); i++){
			addTile(shipTiles.get(i));
		}
	}
	
	public void update(float mouseX, float mouseY){
		this.mouseX = (mouseX -(32/2))/32;
		this.mouseY = (mouseY -(32/2))/32;
	}
	
	public void renderDisplay(SpriteBatch batch){
		for(int i = 0; i < size(); i++){
			batch.draw(displaySprites.get(i), displaySprites.get(i).getX(), displaySprites.get(i).getY());
		}
	}
	
	public void addTile(ShipTile tile){
		System.out.println(displaySprites.size());
		if(tile!=null){
			Sprite s = GlobalSpriteNumbers.getSprite(tile.SHIP_VIEW_SPRITE_ID);
			s.setPosition(tile.x, tile.y);
			displaySprites.add(s);
			shipTiles.add(tile);
		}
	}
	
	public void removeHighlightedTile(float mouseX, float mouseY){
		for(int i = 0; i < displaySprites.size(); i++){
			if(GlobalsInterface.withinSquareBounds(mouseX, mouseY, displaySprites.get(i).getX(), displaySprites.get(i).getY(), displaySprites.get(i).getWidth(), displaySprites.get(i).getHeight())){
				displaySprites.remove(i);
				shipTiles.remove(i);
			}
		}
	}

	public Sprite getSprite(int i) {
		return displaySprites.get(i);
	}

	public ShipTile getTile(int i) {
		return shipTiles.get(i);
	}
	
	public int size(){
		return shipTiles.size();
	}
	
	public void dispose(){
		for(Sprite s:displaySprites)
			s.getTexture().dispose();
	}
}
