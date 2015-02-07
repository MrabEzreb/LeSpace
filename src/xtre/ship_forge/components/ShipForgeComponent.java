package xtre.ship_forge.components;

import java.util.ArrayList;
import java.util.List;

import xtre.ship_forge.components.button.ShipForgeButton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ShipForgeComponent {
	
	// The menu is very separate from buttons. Buttons are independent, menu buttons are stored inside the menu class.
	public ShipForgeMenu menu = new ShipForgeMenu();
	public List<ShipForgeButton> buttons = new ArrayList<>();
	
	public float x, y, width, height;
	public boolean press = false;
	
	public abstract void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress);
	
	public final void update(float mouseX, float mouseY, boolean mouseLeftPress){
		press = false;
		for(ShipForgeButton b:buttons){ // buttons
			if(b.pressed) press = true;
			System.out.println(b.title.title + " : : " + b.pressed);
		}
		if(menu.pressed)press = true;
		
		//Update
		for(ShipForgeButton b:buttons){ // buttons
			b.update(mouseX, mouseY, mouseLeftPress);
		}
		
		if(menu.setup) menu.update(mouseX, mouseY, mouseLeftPress);
		updateComponent(mouseX, mouseY, mouseLeftPress);
		
		//Click
		if(mouseLeftPress){
			for(ShipForgeButton b:buttons){ // button clicks
				b.click(mouseX, mouseY);
			}
			menu.mouseClick(mouseX, mouseY, mouseLeftPress); // menu
			mouseClick(mouseX, mouseY);
		}
	}
	
	public void render(SpriteBatch batch){
		renderComponent(batch);
		if(menu.setup)
			menu.render(batch);
		for(ShipForgeButton b:buttons)
			b.render(batch);
	}
	
	protected abstract void mouseClick(float mouseX, float mouseY);
	public abstract void renderComponent(SpriteBatch batch);
	public abstract void setupMenu(float x, float y, float width, float height);
	public abstract void dispose();
	
}
