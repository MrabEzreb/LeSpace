package xtre.ship_forge.components;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xtre.launcher.menus.utils.TitleString;
import xtre.ship_forge.components.button.ShipForgeButton;

public class ShipForgeMenu extends ShipForgeComponent{

	public boolean horizontal = false;
	
	public float x, y, width, height;
	public TitleString title;
	private List<ShipForgeButton> buttons = new ArrayList<>();
	
	public ShipForgeMenu(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		for(ShipForgeButton b:buttons){
			b.update(mouseX, mouseY, mouseLeftPress);
		}
	}

	@Override
	public void renderComponent(SpriteBatch batch) {
		for(ShipForgeButton b:buttons){
			b.render(batch);
		}
	}
	
	public void addButton(ShipForgeButton button){
		buttons.add(button);
	}
	
	public void dispose(){
		for(ShipForgeButton b:buttons){
			b.dispose();
		}
	}

	public void untoggleButtons() {
		for(ShipForgeButton fb:buttons) fb.toggle = false; 
	}
}