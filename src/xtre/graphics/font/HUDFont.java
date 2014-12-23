package xtre.graphics.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUDFont {

	public final static BitmapFont title_font = new BitmapFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("font/font_0.png"))));
	public final static BitmapFont description_font = new BitmapFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(new Texture(Gdx.files.internal("font/font_0.png"))));

}
