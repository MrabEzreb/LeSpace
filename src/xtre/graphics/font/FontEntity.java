package xtre.graphics.font;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontEntity {
	
	public String text;
	public BitmapFont font;
	public float x, y;
	
	public FontEntity(BitmapFont font, Color col, String text, float x, float y){
		this.text = text; 
		if(font!=null)font.setColor(col);
		this.font = font;
		this.x = x;
		this.y = y;
	}
	public FontEntity(BitmapFont font, Color col, String text){
		font.setColor(col);
		this.font = font;
		this.text = text;
	}
	
	public void dispose(){
		if(font!=null)font.dispose();
	}
}
