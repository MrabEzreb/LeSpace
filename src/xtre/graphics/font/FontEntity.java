package xtre.graphics.font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontEntity {
	
	public String text;
	public BitmapFont font;
	public float x, y;
	
	public FontEntity(BitmapFont font, String text, float x, float y){
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
	}
	
	public void dispose(){
		if(font!=null)font.dispose();
	}
}
