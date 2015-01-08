package xtre.graphics.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUDFont {
	
	public final BitmapFont largeFont;
	public final BitmapFont mediumFont;
	public final BitmapFont smallFont;
	
	public final static HUDFont title_font = new HUDFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("font/font_0.png"))));

	public HUDFont(FileHandle file, TextureRegion textureRegion){
		BitmapFont largeFont = new BitmapFont(file, textureRegion);
		largeFont.setScale(0.48f, 0.48f);
		this.largeFont = largeFont;
		
		BitmapFont mediumFont = new BitmapFont(file, textureRegion);
		mediumFont.setScale(0.32f, 0.32f);
		this.mediumFont = mediumFont;

		BitmapFont smallFont = new BitmapFont(file, textureRegion);
		smallFont.setScale(0.28f, 0.28f);
		this.smallFont = smallFont;
	}
}
