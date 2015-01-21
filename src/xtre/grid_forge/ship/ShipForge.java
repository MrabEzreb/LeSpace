package xtre.grid_forge.ship;

import java.util.ArrayList;
import java.util.List;

import xtre.game.player.Player;
import xtre.game.player.ship.scene.inside_ship.graphics.ShipGrid;
import xtre.game.scene_manager.GameManager;
import xtre.game.scene_manager.GameScene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class ShipForge extends GameScene{

	private List<ShipGrid> grids = new ArrayList<>();
		
	public ShipForge(World world, Player player) {
		super(world, player);
	}

	@Override
	public void update(float camX, float camY, float mouseX, float mouseY, boolean mouseLeftPress) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
	}

	@Override
	public void dispose() {
	}

	
	
}
