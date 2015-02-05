package xtre.ship_forge.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ShipForgeComponent {

	public float x, y, width, height;
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		updateComponent(mouseX, mouseY, mouseLeftPress);
	}
	
	public void render(SpriteBatch batch){
		renderComponent(batch);
	}
	
	public abstract void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress);
	public abstract void renderComponent(SpriteBatch batch);
	
}
