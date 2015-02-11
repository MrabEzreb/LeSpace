package xtre.graphics.sprites;

import java.util.ArrayList;
import java.util.List;

import xtre.graphics.sprites.utils.SpriteDetails;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameSprite {
	
	public static List<Sprite> sprites = new ArrayList<>();
	
	private static AssetManager assets = new AssetManager();
	private static TextureAtlas atlas;
	
	public static Sprite getSprite(SpriteDetails id){
		assets.load(id.path, TextureAtlas.class);
		assets.finishLoading();
		
		atlas = assets.get(id.path);
		Sprite s = new Sprite(atlas.findRegion(id.name));
		sprites.add(s);
		return s;
	}
}
