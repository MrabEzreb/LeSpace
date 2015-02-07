package xtre.ship_forge.components;

import java.util.LinkedList;
import java.util.Random;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.launcher.menus.utils.TitleString;
import xtre.ship_forge.components.button.ShipForgeButton;
import xtre.ship_forge.components.button.Action;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForgeGridLayerView extends ShipForgeComponent {

	private LinkedList<GridLayer> layers = new LinkedList<>();
	public int size;
	
	public ShipForgeGridLayerView(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void mouseClick(float mouseX, float mouseY) {
		for(GridLayer gl:layers)
			gl.click(mouseX, mouseY, gl.slot);
	}

	@Override
	public void renderComponent(SpriteBatch batch) {
		for(GridLayer gl:layers)
			gl.render(batch);
	}

	public ShipGrid getGrid(int i) {
		if(i<layers.size() && i > -1)
			return layers.get(i).shipGrid;
		else
			return null;
	}

	public void addLayer(ShipGrid sg) {
		layers.push(new GridLayer(x, y, width, height, layers.size(), sg));
	}
	
	public ShipGrid getTopLayer() {
		if(!layers.isEmpty())
			return layers.get(0).shipGrid;
		else
			return null;
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).button.hover)
				this.press = true;
				if(layers.get(i).pressed){
				layers.get(i).pressed = false;
				GridLayer gl = layers.get(i);
				layers.remove(i);
				layers.addLast(gl);
				break;
			}
		}
		
		for(int i = layers.size()-1; i >= 0; i--)
			layers.get(i).update(mouseX, mouseY, mouseLeftPress);
		
		size = layers.size()-1;
	}

	@Override
	public void setupMenu(float x, float y, float width, float height) {
	}
	
	@Override
	public void dispose(){
		for(GridLayer gl:layers)
			gl.dispose();
		menu.dispose();
		for(ShipForgeButton b:buttons)
			b.dispose();
	}
}

class GridLayer{
	public ShipGrid shipGrid;
	public float x, y, width, height;
	public int slot;
	public boolean pressed = false;
	public ShipForgeButton button = new ShipForgeButton();
	public GridLayer(float x, float y, float width, float height, int slot, ShipGrid shipGrid){
	 	this.slot = slot;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.shipGrid = shipGrid;
	
		button.x = x;
		button.y = y;
		button.width = width;
		button.height = height;
		button.setPosition(x, y+(slot*height));
		button.sprite.setSize(width, height);
		Action action = new Action(){
			public void action(){
				pressed = true;
			}
		};

		float r=1,g=1,b=1,s=slot+7; s = s/15;
		r=0.3f; g=s/1.7f; b=s/3.4f;
		g=g+g/2; b=b+b/2;
		System.out.println(s + " " + r + " " + g + " " + b);
		button.setColor(r, g, b, 1);
		button.setAction(action);
		button.title = new TitleString(x, y, "gridLayer " + slot);
	}
	public void click(float mouseX, float mouseY, int slot){
		this.slot = slot;
		button.setPosition(x, y+(slot*height));
		button.click(mouseX, mouseY);
		button.title.title = "gridLayer " + slot;
	}
	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		button.update(mouseX, mouseY, mouseLeftPress);
	}
	public void render(SpriteBatch batch){
		button.render(batch);
	}
	public void dispose(){
		button.dispose();
	}
}