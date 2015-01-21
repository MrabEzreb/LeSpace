package xtre.game.player.ship.scene.inside_ship.graphics;

import java.io.Serializable;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ShipTile implements Serializable{
	private static final long serialVersionUID = 9011373355602576321L;
	
	public final int SHIP_VIEW_SPRITE_ID;
	public float width, height, x, y;
	
	public int gridLocationX, gridLocationY;

	/**
	 * The sprite is not saved here, only the position and size of it is.
	 * The grid locations are for knowing which grid the tile is on, while the x and y positions are the positions on the tile
	 * This class is for sending over a network.
	 * This class has a {@code SHIP_VIEW_SPRITE_ID} for getting the correct sprite on the clients machine.
	 * 
	 * @param sprite
	 * @param spriteID
	 * @param gridLocationX
	 * @param gridLocationY
	 */
	public ShipTile(Sprite sprite, int SHIP_VIEW_SPRITE_ID, int gridLocationX, int gridLocationY){
		this.SHIP_VIEW_SPRITE_ID = SHIP_VIEW_SPRITE_ID;
		this.gridLocationX = gridLocationX;
		this.gridLocationY = gridLocationY;
		
		this.x = sprite.getX();
		this.y = sprite.getY();
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}
}