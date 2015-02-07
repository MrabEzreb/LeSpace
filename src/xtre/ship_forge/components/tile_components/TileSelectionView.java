package xtre.ship_forge.components.tile_components;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.launcher.menus.utils.DialogBox;
import xtre.ship_forge.components.ShipForgeComponent;
import xtre.ship_forge.components.button.ShipForgeButton;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileSelectionView extends ShipForgeComponent{

	private DialogBox box;
	public TileSlide slide;
	private Sprite selectedSprite;
	public boolean renderSelectedSprite = true;
	
	public TileSelectionView(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new DialogBox(x, y, width, height);
		slide = new TileSlide(x, y, 5, 2);
	}

	@Override
	public void mouseClick(float mouseX, float mouseY) {
		press = slide.press;
	}
	
	@Override
	public void renderComponent(SpriteBatch batch) {
		box.render(batch);
		slide.render(batch);
		if(selectedSprite != null && renderSelectedSprite)
			selectedSprite.draw(batch);
	}

	public float getWidth() {
		return box.width;
	}
	
	public float getHeight(){
		return box.height;
	}

	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		slide.update(mouseX, mouseY, mouseLeftPress);
		selectedSprite = slide.selectedSprite;
		mouseX = (mouseX -(32/2))/32;
		mouseY = (mouseY -(32/2))/32;
		if(selectedSprite != null){
			selectedSprite.setPosition(Math.round(mouseX)*32, Math.round(mouseY)*32);
		}
	}

	public Sprite getSelectedSlot() {
		return slide.selectedSprite;
	}

	@Override
	public void setupMenu(float x, float y, float width, float height) {
	}
	
	public ShipTile getSelectedTile(){
		ShipTile tile = slide.selectedTile;
		if(tile!=null){
			tile.x = selectedSprite.getX();
			tile.y = selectedSprite.getY();
		}
		return tile;
	}
	
	public void dispose(){
		box.dispose();
		slide.dispose();
		menu.dispose();
		for(ShipForgeButton b:buttons)
			b.dispose();
	}
}
