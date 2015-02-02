package xtre.ship_forge.components.tile_components;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.ship_forge.components.ShipForgeComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileEdit extends ShipForgeComponent{
	
	private TileSlide slide;
	
	public ShipGrid grid;
	
	public TileEdit(TileSlide slide){
		this.slide = slide;
	}
	
	public void updateData(ShipGrid grid){
		this.grid = grid;
	}
	
	private void applyTile(int toWhichTile){
		grid.setHighlightedTile(toWhichTile);
	}
	
	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		if(grid!=null && mouseLeftPress){
			for(int i = 0; i < grid.gridSize; i++){
				applyTile(grid.getHighlightedTileID());
			}
		}else if(grid!=null){
			grid.update(mouseX, mouseY, mouseLeftPress);
		}
	}

	@Override
	public void renderComponent(SpriteBatch batch) {
	}
}
