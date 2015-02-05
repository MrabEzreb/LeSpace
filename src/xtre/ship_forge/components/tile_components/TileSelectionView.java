package xtre.ship_forge.components.tile_components;

import xtre.launcher.menus.utils.DialogBox;
import xtre.ship_forge.components.ShipForgeComponent;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileSelectionView extends ShipForgeComponent{

	private DialogBox box;
	public TileSlide slide;
	private Sprite selectedTile;
	
	public TileSelectionView(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new DialogBox(x, y, width, height);
		slide = new TileSlide(x, y, 5, 2);
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		slide.update(mouseX, mouseY, mouseLeftPress);
		selectedTile = slide.selectedTile;
		if(selectedTile != null)
			selectedTile.setPosition(mouseX, mouseY);
		
	}
	
	@Override
	public void renderComponent(SpriteBatch batch) {
		box.render(batch);
		slide.render(batch);
		if(selectedTile != null)
			selectedTile.draw(batch);
	}
	
	public void dispose(){
		box.dispose();
		slide.dispose();
	}

	public float getWidth() {
		return box.width;
	}
	
	public float getHeight(){
		return box.height;
	}

	public void checks(float mouseX, float mouseY, boolean mouseLeftPress) {
	}

	public Sprite getSelectedSlot() {
		return slide.selectedTile;
	}
}
