package xtre.ship_forge.components;

import xtre.launcher.menus.utils.DialogBox;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileSelectionView extends ShipForgeComponent{

	private DialogBox box;
	public TileSlide slide;
	
	public TileSelectionView(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		box = new DialogBox(x, y, width, height);
		slide = new TileSlide(this, x, y);
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		slide.update(mouseX, mouseY, mouseLeftPress);
		processSliderButtons();
	}
	
	private void processSliderButtons(){
		if(slide.left){
			slide.left = false;
			slide.slideLeft(1);
		}else if(slide.right){
			slide.right = false;
			slide.slideRight(1);
		}
	}
	
	@Override
	public void renderComponent(SpriteBatch batch) {
		box.render(batch);
		slide.render(batch);
	}
	
	public void dispose(){
		box.dispose();
	}

	public float getWidth() {
		return box.width;
	}
	
	public float getHeight(){
		return box.height;
	}

	public void checks(float mouseX, float mouseY, boolean mouseLeftPress) {
		slide.checks(mouseX, mouseY, mouseLeftPress);
	}

	public Sprite getSelectedSlot() {
		return slide.selectedTile;
	}
}
