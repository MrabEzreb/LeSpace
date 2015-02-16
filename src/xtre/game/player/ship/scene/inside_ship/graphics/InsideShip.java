package xtre.game.player.ship.scene.inside_ship.graphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InsideShip {
	//TODO all it
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

	public void update() {
		for(ShipGrid sg:grids)
			sg.update();
	}

	public void render(SpriteBatch batch) {

	}
	
	public void loadShip(ShipGrid ship){
		
	}
}
