package xtre.ship_forge;

import java.util.ArrayList;
import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.globals.GlobalScreen;
import xtre.ship_forge.components.ShipForgeMenu;
import xtre.ship_forge.components.TileSelectionView;
import xtre.ship_forge.components.button.ShipForgeButton;
import xtre.ship_forge.components.button.ShipForgeButtonAction;
import xtre.ship_forge.graphics.ShipForgeTiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForge {

	private List<ShipGrid> grids = new ArrayList<>();
	private ShipForgeMenu forgeMenu;
	
	private TileSelectionView tileSelectionView;
	
	private float mouseX=0, mouseY=0;
	private boolean mouseLeftPress = false;
	private Sprite selectedTile;
		
	public ShipForge() {
		forgeMenu = new ShipForgeMenu((GlobalScreen.GAME_WIDTH-300), 30, 128, 512);
		setupRightMenu();
		
		int x = (GlobalScreen.LAUNCHER_WIDTH/2);
		int y = 4;
		int w = 512;
		int h = 128;
		tileSelectionView = new TileSelectionView(x-(w/2), y, w, h);
	}
	
	private void setupRightMenu(){
		final ShipForgeButton tiles1 = new ShipForgeButton(forgeMenu, 0, "tiles");
		tiles1.setAction(new ShipForgeButtonAction(){

			public void action(){
				List<Sprite> s = ShipForgeTiles.getShipFloorTiles();
				List<Sprite> ss = new ArrayList<>();
				for(int i = s.size()-1; i > -1; i--)
					ss.add(s.get(i));
				
				tileSelectionView.slide.setTiles(ss);
				forgeMenu.untoggleButtons();
				tiles1.toggle = true;
			}
		});
		forgeMenu.addButton(tiles1);

		final ShipForgeButton tiles2 = new ShipForgeButton(forgeMenu, 1, "tiles");
		tiles2.setAction(new ShipForgeButtonAction(){
			public void action(){
				List<Sprite> s = ShipForgeTiles.getShipFloorTiles();
				tileSelectionView.slide.setTiles(s);
				forgeMenu.untoggleButtons();
				tiles2.toggle = true;
			}
		});
		forgeMenu.addButton(tiles2);
	}
	
	public void checks(float mouseX, float mouseY, boolean mouseLeftPress){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = mouseLeftPress;
		
		tileSelectionView.checks(mouseX, mouseY, mouseLeftPress);
		selectedTile = tileSelectionView.getSelectedSlot();
		forgeMenu.update(mouseX, mouseY, mouseLeftPress);
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		tileSelectionView.update(mouseX, mouseY, mouseLeftPress);
	}

	public void render(SpriteBatch batch) {	
		forgeMenu.render(batch);
		tileSelectionView.render(batch);

		if(selectedTile != null){
			selectedTile.setPosition(mouseX, mouseY);
			selectedTile.draw(batch);
		}
	}

	public void dispose() {
		forgeMenu.dispose();
	}
}
