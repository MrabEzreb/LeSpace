package xtre.ship_forge.components;

import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.globals.GlobalScreen;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.ship_forge.components.button.ShipForgeButton;
import xtre.ship_forge.components.button.Action;
import xtre.ship_forge.components.tile_components.TileSelectionView;
import xtre.ship_forge.graphics.ShipForgeSprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForgeTileListsMenu extends ShipForgeComponent{
	
	private TileSelectionView tileSelectionView;
	
	public ShipForgeTileListsMenu(TileSelectionView tileSelectionView){
		this.tileSelectionView = tileSelectionView;
		
		setupMenu();
	}
	
	/** This menu lists the different sets of tiles */
	private void setupMenu(){
		menu.setup((GlobalScreen.GAME_WIDTH-300), 512, 128, 18);
		final ShipForgeButton tiles1 = new ShipForgeButton(menu, 0, new FontEntity("tiles", HUDFont.title_font.smallFont));
		tiles1.setAction(new Action(){
			public void action(){
				List<ShipTile> s = ShipForgeSprites.getShipFloorTiles();
				tileSelectionView.slide.setTiles(s);
				menu.untoggleButtons();
				tiles1.toggle = true;
			}
		});
		menu.addButton(tiles1);
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
	}

	@Override
	public void mouseClick(float mouseX, float mouseY) {
	}

	@Override
	public void renderComponent(SpriteBatch batch) {
	}

	@Override
	public void setupMenu(float x, float y, float width, float height) {
	}

	@Override
	public void dispose() {
		menu.dispose();
		for(ShipForgeButton b:buttons)
			b.dispose();
	}
}
