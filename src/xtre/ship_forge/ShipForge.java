package xtre.ship_forge;

import xtre.globals.GlobalScreen;
import xtre.ship_forge.components.ShipForgeGridLayerView;
import xtre.ship_forge.components.ShipForgeTileListsMenu;
import xtre.ship_forge.components.tile_components.ShipForgeTools;
import xtre.ship_forge.components.tile_components.TileSelectionView;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForge {

	private ShipForgeGridLayerView gridLayerMenu;
	private TileSelectionView tileSelectionView;
	private ShipForgeTools shipForgeTools;
	private ShipForgeTileListsMenu forgeMenu;
	
	public ShipForge() {
		gridLayerMenu = new ShipForgeGridLayerView(64, 128, 128, 32);
		setupGridLayerMenu();
		
		{
			int x = (GlobalScreen.LAUNCHER_WIDTH/2)-8;
			int y = 4;
			int w = 512;
			int h = 112;
			tileSelectionView = new TileSelectionView(x-(w/2), y, w, h);
		}
		
		forgeMenu = new ShipForgeTileListsMenu(tileSelectionView);
		shipForgeTools = new ShipForgeTools(tileSelectionView.slide, gridLayerMenu);
	}
	
	private void setupGridLayerMenu(){
	}
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		if(gridLayerMenu.press || tileSelectionView.press || forgeMenu.press || tileSelectionView.slide.press)
			shipForgeTools.shouldUse = false;
		else
			shipForgeTools.shouldUse = true;
		shipForgeTools.updateSelectedTile(tileSelectionView.getSelectedTile());
		shipForgeTools.update(mouseX, mouseY, mouseLeftPress);

		gridLayerMenu.update(mouseX, mouseY, mouseLeftPress);
		tileSelectionView.update(mouseX, mouseY, mouseLeftPress);
		forgeMenu.update(mouseX, mouseY, mouseLeftPress);
	}

	public void mouseClick(float mouseX, float mouseY, boolean mouseLeftPress) {

	}

	public void render(SpriteBatch batch) {	
		if(!shipForgeTools.hasTool)
			tileSelectionView.renderSelectedSprite = false;
		else
			tileSelectionView.renderSelectedSprite = true;
		shipForgeTools.render(batch);
		forgeMenu.render(batch);
		gridLayerMenu.render(batch);
		tileSelectionView.render(batch);
	}

	public void dispose() {
		gridLayerMenu.dispose();
		tileSelectionView.dispose();
		shipForgeTools.dispose();
		forgeMenu.dispose();
	}
}
