package xtre.graphics.sprites;

import xtre.graphics.sprites.sprite_types.utils.SpriteData;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpriteEntity {	
	
	private AssetManager assets;
	private TextureAtlas atlas;
	
	public SpriteEntity(){
		assets = new AssetManager();

	}	
	
	public Sprite getSprite(SpriteData data){
		assets.load(data.location, TextureAtlas.class);
		assets.finishLoading();
		
		atlas = assets.get(data.location);
		
		Sprite sprite = new Sprite(atlas.findRegion(data.name));
		sprite.setPosition(0,0);
		
		return new Sprite(sprite);
	}
}
