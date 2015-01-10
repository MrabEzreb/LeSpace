package xtre.game.game_item;

import xtre.graphics.sprites.SpriteEntity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameItem {
	
	public SpriteEntity se = new SpriteEntity();
	
	public Sprite sprite;
	public float x, y;
	
	/**
	 * The sprites x and y values are used to specify this objects x and y values. For usual use, these need to be set;
	 * @param sprite
	 */
	public GameItem(Sprite sprite){
		this.sprite = sprite;
		this.x = sprite.getX();
		this.y = sprite.getY();
	}
	
	public abstract void render(SpriteBatch batch);
	
}
