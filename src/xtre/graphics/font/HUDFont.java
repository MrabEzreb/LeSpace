package xtre.graphics.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUDFont {
	
	public final BitmapFont font;

	public final static HUDFont title_font = new HUDFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("font/font_0.png"))));
	public final static HUDFont description_font = new HUDFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("font/font_0.png"))));

	public HUDFont(FileHandle file, TextureRegion textureRegion){
		BitmapFont font = new BitmapFont(file, textureRegion);
		this.font = font;
	}
}
