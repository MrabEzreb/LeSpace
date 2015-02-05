package xtre.ship_forge;

import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.globals.GlobalScreen;
import xtre.ship_forge.components.ShipForgeGridLayerView;
import xtre.ship_forge.components.ShipForgeMenu;
import xtre.ship_forge.components.button.ShipForgeButton;
import xtre.ship_forge.components.button.ShipForgeButtonAction;
import xtre.ship_forge.components.tile_components.TileEdit;
import xtre.ship_forge.components.tile_components.TileSelectionView;
import xtre.ship_forge.graphics.ShipForgeSprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForge {

	private ShipForgeMenu forgeMenu;
	private ShipForgeMenu toolMenu;
	private ShipForgeGridLayerView gridLayerMenu;
	private TileEdit tileEditor;
	
	private TileSelectionView tileSelectionView;
	private float mouseX=0, mouseY=0;
	private boolean mouseLeftPress = false;
		
	public ShipForge() {
		forgeMenu = new ShipForgeMenu((GlobalScreen.GAME_WIDTH-300), 512, 128, 16);
		setupRightMenu();
		
		toolMenu = new ShipForgeMenu((GlobalScreen.GAME_WIDTH-400), 584, 40, 40);
		toolMenu.horizontal = true;
		setupGridToolMenu();
		
		gridLayerMenu = new ShipForgeGridLayerView(64, 128, 128, 32);
		setupGridLayerMenu();
		
		{
			int x = (GlobalScreen.LAUNCHER_WIDTH/2);
			int y = 4;
			int w = 512;
			int h = 112;
			tileSelectionView = new TileSelectionView(x-(w/2), y, w, h);
		}
	}
	
	private void setupGridLayerMenu(){
	}
	
	/** This menu lists the tools for tile manipulation */
	private void setupGridToolMenu(){
		final ShipForgeButton newShipGrid = new ShipForgeButton(toolMenu, 0, "newShipGrid");
		newShipGrid.setAction(new ShipForgeButtonAction(){
			public void action(){
				ShipGrid sg = new ShipGrid(32);
				gridLayerMenu.addLayer(sg);
			}
		});
		toolMenu.addButton(newShipGrid);
		
		final ShipForgeButton test = new ShipForgeButton(toolMenu, 1, "test");
		test.setAction(new ShipForgeButtonAction(){
			public void action(){
			}
		});
		toolMenu.addButton(test);
		final ShipForgeButton test1 = new ShipForgeButton(toolMenu, 2, "test");
		test1.setAction(new ShipForgeButtonAction(){
			public void action(){
			}
		});
		toolMenu.addButton(test1);
		final ShipForgeButton test2 = new ShipForgeButton(toolMenu, 3, "test");
		test2.setAction(new ShipForgeButtonAction(){
			public void action(){
			}
		});
		toolMenu.addButton(test2);
		final ShipForgeButton test3 = new ShipForgeButton(toolMenu, 4, "test");
		test3.setAction(new ShipForgeButtonAction(){
			public void action(){
			}
		});
		toolMenu.addButton(test3);
	}
	
	/** This menu lists the different sets of tiles */
	private void setupRightMenu(){
		final ShipForgeButton tiles1 = new ShipForgeButton(forgeMenu, 0, "tiles");
		tiles1.setAction(new ShipForgeButtonAction(){
			public void action(){
				System.out.println("new tiles");
				List<Sprite> s = ShipForgeSprites.getShipFloorTiles();
				System.out.println(s.size()+" ::");
				tileSelectionView.slide.setTiles(s);
				forgeMenu.untoggleButtons();
				tiles1.toggle = true;
			}
		});
		forgeMenu.addButton(tiles1);
	}
	
	public void checks(float mouseX, float mouseY, boolean mouseLeftPress){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = mouseLeftPress;
		
		forgeMenu.update(mouseX, mouseY, mouseLeftPress);
		toolMenu.update(mouseX, mouseY, mouseLeftPress);
		gridLayerMenu.update(mouseX, mouseY, mouseLeftPress);
		tileSelectionView.update(mouseX, mouseY, mouseLeftPress);
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		//tileEditor.update(mouseX, mouseY, mouseLeftPress);
		//tileEditor.updateData(gridLayerMenu.getGrid(gridLayerMenu.size));
	}

	public void render(SpriteBatch batch) {	
		forgeMenu.render(batch);
		toolMenu.render(batch);
		gridLayerMenu.render(batch);
		tileSelectionView.render(batch);
	//	tileEditor.render(batch);
	}

	public void dispose() {
		forgeMenu.dispose();
	}
}
