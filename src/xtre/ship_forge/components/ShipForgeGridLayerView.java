package xtre.ship_forge.components;

import java.util.LinkedList;
import java.util.Random;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.ship_forge.components.button.ShipForgeButton;
import xtre.ship_forge.components.button.ShipForgeButtonAction;

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
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).bringToFront){
				layers.get(i).bringToFront = false;
				GridLayer gl = layers.get(i);
				layers.remove(i);
				layers.addLast(gl);
				break;
			}
		}
		
		for(int i = layers.size()-1; i >= 0; i--)
			layers.get(i).update(mouseX, mouseY, mouseLeftPress, i);
		size = layers.size()-1;
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
		
//		GridLayer[] gl = new GridLayer[layers.length+1];
//		for(int i = 0; i < layers.length; i++){
//			gl[i] = layers[i];
//		}
//		
//		System.out.println(" :: :: " + layers.length + " " + gl.length);
//		gl[gl.length-1] = new GridLayer(x, y, width+(layers.length*4), 16, layers.length, sg);
//		layers = gl;
	
		layers.push(new GridLayer(x, y, width+(layers.size()*4), height, layers.size(), sg));
	}
	
	public void dispose(){
		for(GridLayer gl:layers)
			gl.dispose();
	}
}

class GridLayer{
	public ShipGrid shipGrid;
	public float x, y, width, height;
	public int slot;
	public boolean bringToFront = false;
	private ShipForgeButton button = new ShipForgeButton();
	public GridLayer(float x, float y, float width, float height, int slot_, ShipGrid shipGrid){
	 	slot = slot_;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.shipGrid = shipGrid;
	
		button.x = this.x;
		button.y = this.y;
		button.width = this.width;
		button.height = this.height;
		button.sprite.setPosition(this.x, this.y);
		button.sprite.setSize(this.width, this.height);
		ShipForgeButtonAction action = new ShipForgeButtonAction(){
			public void action(){
				bringToFront = true;
			}
		};
		Random r = new Random();
		button.setColor(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1f);
		this.button.setAction(action);
	}
	public void updateStats(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		button.x = this.x;
		button.y = this.y;
		button.width = this.width;
		button.height = this.height;
		button.sprite.setPosition(this.x, this.y);
		button.sprite.setSize(this.width, this.height);
	}
	public void update(float mouseX, float mouseY, boolean mouseLeftPress, int slot) {
		button.setPosition(x, y+(slot*height));
		button.update(mouseX, mouseY, mouseLeftPress);
		this.slot = slot;
	}
	public void render(SpriteBatch batch){
		button.render(batch);
	}
	public void dispose(){
		button.dispose();
	}
}