package xtre.ship_forge;

import java.util.ArrayList;
import java.util.List;

import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.globals.GlobalScreen;
import xtre.ship_forge.menus.ShipForgeButton;
import xtre.ship_forge.menus.ShipForgeButtonAction;
import xtre.ship_forge.menus.ShipForgeMenu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForge{

	private List<ShipGrid> grids = new ArrayList<>();
	private List<ShipForgeMenu> menus = new ArrayList<>();
		
	public ShipForge() {
		ShipForgeMenu main = new ShipForgeMenu((GlobalScreen.GAME_WIDTH-300), 30, 128, 512);
		menus.add(main);
		setupRightMenu();
		setupShipTileSelection();
	}
	
	private void setupRightMenu(){
		ShipForgeButton tiles1 = new ShipForgeButton(menus.get(0), 0, "tiles");
		tiles1.setAction(new ShipForgeButtonAction(){
			public void action(){
				System.out.println("selected: floor tiles");
			}
		});
		menus.get(0).addButton(tiles1);
	
		ShipForgeButton tiles2 = new ShipForgeButton(menus.get(0), 1, "tiles");
		tiles2.setAction(new ShipForgeButtonAction(){
			public void action(){
				System.out.println("selected: floor tiles");
			}
		});
		menus.get(0).addButton(tiles2);
		
		for(int i = 2; i < 16; i++){
			menus.get(0).addButton(new ShipForgeButton(menus.get(0), i, "tiles"));
		}
	}

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		for(ShipForgeMenu m:menus){
			m.update(mouseX, mouseY, mouseLeftPress);
		}
	}

	public void render(SpriteBatch batch) {
		for(ShipForgeMenu m:menus){
			m.render(batch);
		}
	}

	public void dispose() {
		for(ShipForgeMenu m:menus){
			m.dispose();
		}
	}
}
