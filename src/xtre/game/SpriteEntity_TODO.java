package xtre.game;

import xtre.graphics.sprites.utils.SpriteData;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpriteEntity_TODO {
	
	private static AssetManager assets;
	private static TextureAtlas atlas;
	
	private SpriteEntity_TODO(){
		assets = new AssetManager();

	}	
	
	public static Sprite getSprite(SpriteData data){
		assets.load(data.location, TextureAtlas.class);
		assets.finishLoading();
		
		atlas = assets.get(data.location);
		
		Sprite sprite = new Sprite(atlas.findRegion(data.name));
		sprite.setPosition(0,0);
		
		return new Sprite(sprite);
	}
}
