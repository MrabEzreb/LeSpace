package xtre.ship_forge.menus;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import xtre.launcher.menus.utils.TitleString;

public class ShipForgeMenu {

	public float x, y, width, height;
	public TitleString title;
	private List<ShipForgeButton> buttons = new ArrayList<>();
	
	public ShipForgeMenu(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void addButton(ShipForgeButton button){
		buttons.add(button);
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		for(ShipForgeButton b:buttons){
			b.update(mouseX, mouseY, mouseLeftPress);
		}
	}

	public void render(SpriteBatch batch) {
		for(ShipForgeButton b:buttons){
			b.render(batch);
		}
	}
	
	public void dispose(){
		for(ShipForgeButton b:buttons){
			b.dispose();
		}
	}
}
