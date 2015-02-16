package xtre.ship_forge.components.tile_components;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.game.player.ship.scene.inside_ship.graphics.ShipTile;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.ship_forge.components.ShipForgeComponent;
import xtre.ship_forge.components.ShipForgeGridLayerView;
import xtre.ship_forge.components.button.Action;
import xtre.ship_forge.components.button.ShipForgeButton;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForgeTools extends ShipForgeComponent{
	private ShipTile selectedTile;
	
	public TileSpriteForm gridForm = new TileSpriteForm();
	
	private TileSlide slide;
	private ShipForgeGridLayerView gridLayerMenu;
	
	private float mouseX, mouseY;
	
	public boolean shouldUse = true, hasTool;
	
	//Tools
	private Action toolNone = new Action(){
		public void action(){
			hasTool = false;
		}
	};
	private Action toolSelected = toolNone;
	private Action toolPlaceTile = new Action(){
		public void action(){
			if(selectedTile!=null){
				gridForm.removeHighlightedTile(selectedTile.x+1, selectedTile.y+1);
				gridForm.addTile(selectedTile);
				hasTool = true;
			}
		}
	};
	private Action toolRemoveTile = new Action(){
		public void action(){
			gridForm.removeHighlightedTile(mouseX, mouseY);
			hasTool = true;
		}
	};
	//End of Tools
	
	public ShipForgeTools(TileSlide slide, ShipForgeGridLayerView gridLayerMenu) {
		this.slide = slide;
		this.gridLayerMenu = gridLayerMenu;
		
		setupMenu((GlobalScreen.LAUNCHER_WIDTH)-((menu.buttonAmount()*32)+32*7), GlobalScreen.LAUNCHER_HEIGHT - (34*2), 40, 40);
		setupButtons();
	}

	@Override
	public void updateComponent(float mouseX, float mouseY, boolean mouseLeftPress) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		if(shouldUse && !press && GameInputs.mouseHolding(Buttons.LEFT)){
			toolSelected.action();
		}
	}
	
	@Override
	public void mouseClick(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		gridForm.update(mouseX, mouseY);
	}

	@Override
	public void renderComponent(SpriteBatch batch) {
		gridForm.renderDisplay(batch);
	}

	public void setToolUse(boolean b){
		
	}
	
	/** This menu lists the tools for tile manipulation */
	private void setupButtons(){
		final ShipForgeButton addGridLayer = new ShipForgeButton(menu, 0, "addGridLayer");
		addGridLayer.setOverlay(GameSprite.getSprite(SpritesSpaceHudMenu.tool_new_layer));
		addGridLayer.setAction(new Action(){
			public void action(){
				gridLayerMenu.addLayer(new ShipGrid(32));
				press = true;
			}
		});
		menu.addButton(addGridLayer);
		
		final ShipForgeButton toolTileRemove = new ShipForgeButton(menu, 1, "toolTileRemove");
		toolTileRemove.setOverlay(GameSprite.getSprite(SpritesSpaceHudMenu.tool_remove_tile));
		toolTileRemove.setAction(new Action(){
			public void action(){
				toolSelected = toolRemoveTile;
				press = true;
			}
		});
		menu.addButton(toolTileRemove);
		
		final ShipForgeButton toolTilePlace = new ShipForgeButton(menu, 2, "toolTilePlace");
		toolTilePlace.setOverlay(GameSprite.getSprite(SpritesSpaceHudMenu.tool_place_tile));
		toolTilePlace.setAction(new Action(){
			public void action(){
				toolSelected = toolPlaceTile;
				press = true;
			}
		});
		menu.addButton(toolTilePlace);
		
		final ShipForgeButton test2 = new ShipForgeButton(menu, 3, "test1");
		test2.setAction(new Action(){
			public void action(){
				press = true;
			}
		});
		menu.addButton(test2);
		
		final ShipForgeButton toolFree = new ShipForgeButton(menu, 4, "toolFree");
		toolFree.setOverlay(GameSprite.getSprite(SpritesSpaceHudMenu.tool_free));
		toolFree.setAction(new Action(){
			public void action(){
				toolSelected = toolNone;
				press = true;
			}
		});
		menu.addButton(toolFree);
	}

	@Override
	public void setupMenu(float x, float y, float width, float height) {
		menu.setup(x, y, width, height);
		menu.horizontal = true;
	}
	
	public void updateSelectedTile(ShipTile tile){
		selectedTile = tile;
	}

	@Override
	public void dispose() {
	}
}
