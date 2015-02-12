package xtre.graphics.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class HUDFont {
	
	public final BitmapFont largeFont;
	public final BitmapFont mediumFont;
	public final BitmapFont smallFont;
	
	public final static HUDFont title_font = new HUDFont(Gdx.files.internal("font/Exo-Light.otf"));

	public HUDFont(FileHandle file){
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(file);
		FreeTypeFontParameter par = new FreeTypeFontParameter();

		
		par.size = 18;
		largeFont = gen.generateFont(par);
		
		par.size = 16;
		mediumFont = gen.generateFont(par);
		
		par.size = 14;
		smallFont = gen.generateFont(par);
		
		gen.dispose();
	}
}
