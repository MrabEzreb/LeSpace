package xtre.game.game_item.utils;

import xtre.game.game_item.GameItem;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ItemEmptySlot extends GameItem{

	public ItemEmptySlot(Sprite s){
		super(s);
	}
	
	public void render(SpriteBatch batch){
		sprite.draw(batch);
	}
}
