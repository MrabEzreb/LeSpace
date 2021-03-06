package xtre.game.player.ship.scene.inside_ship.graphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InsideShip {
	
	private List<ShipGrid> grids = new ArrayList<>();
	private List<Sprite> sprites = new ArrayList<>();

	public InsideShip(){
		ShipGrid hull = new ShipGrid(32);
		
		//SpriteData sd = se.getData(SpriteInsideShip.white_panel_edge);
		
		//hull.addTile(new ShipTile(sd.s, sd. 0, 0));
		grids.add(hull);
		
	}
	
	public void addGrid(ShipGrid grid){
		grids.add(grid);
	}
	
	public void setSprite(){
		
	}

	public void dispose() {
		for(Sprite s:sprites)
			s.getTexture().dispose();
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		for(ShipGrid sg:grids)
			sg.update(mouseX, mouseY, mouseLeftPress);
	}

	public void render(SpriteBatch batch) {
		for(ShipGrid sg:grids)
			sg.render(batch);
	}
	
	public void loadShip(ShipGrid ship){
		
	}
}
