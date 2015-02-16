package xtre.ship_forge.components;

import java.util.ArrayList;
import java.util.List;

import xtre.graphics.font.FontEntity;
import xtre.ship_forge.components.button.ShipForgeButton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForgeMenu {
	public boolean setup = false;

	public boolean horizontal = false;
	
	public float x, y, width, height;
	public FontEntity title;
	private List<ShipForgeButton> buttons = new ArrayList<>();

	public boolean pressed = false;
	
	public void setup(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setup = true;
	}

	public void mouseClick(float mouseX, float mouseY, boolean mouseLeftPress) {
		for(ShipForgeButton b:buttons){
			b.click(mouseX, mouseY);
		}
	}

	public void render(SpriteBatch batch) {
		for(ShipForgeButton b:buttons){
			b.render(batch);
		}
	}
	
	public void addButton(ShipForgeButton button){
		buttons.add(button);
	}

	public void untoggleButtons() {
		for(ShipForgeButton fb:buttons) {
			fb.toggle = false;
		}
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		pressed = false;
		for(ShipForgeButton b:buttons){
			b.update(mouseX, mouseY, mouseLeftPress);
			if(b.hover)
				pressed = true;
		}
	}
	
	public void dispose(){
		for(ShipForgeButton b:buttons){
			b.dispose();
		}
	}

	public int buttonAmount() {
		return buttons.size();
	}
}