package xtre.game.space_world;

import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Star {
	
	public int size, width, height;
	private final float placeX, placeY, depth;
	public Sprite sprite;
	public boolean onScreen = false;
//	private final float clickAreaSize = 32;
	
//	public boolean selected = false;
	
	public Star(float placeX, float placeY, float depth, int size, Sprite sprite){
		this.size = size;
		this.height = size;
		this.width = size;
		this.placeX=placeX-(sprite.getWidth()/2);
		this.placeY=placeY-(sprite.getHeight()/2);
		this.depth=depth;
		this.sprite = sprite;
		
	}
//
//	public boolean clicked(float mx, float my, boolean mouseDown){
//		if(mouseDown){
//			boolean b = areaCheck(mx, my);
//			if(b){
//				selected = true;
//			}
//			else if(!b)
//				selected = false;
//		}
//		return selected;
//	}
//	
	public void setPosition(float x, float y, float depth){
		sprite.setPosition(x, y);
	}

	public void updatePosition(float px, float py) {
		sprite.setPosition((placeX-(px/depth)), placeY-(py/depth));
	}
	public float getX() {
		return sprite.getX();
	}
	
	public float getY(){
		return sprite.getY();
	}

//	public boolean hovered(float mx, float my) {
//		if(areaCheck(mx, my))
//			return true;
//		else
//			return false;
//	}
//	
//	private boolean areaCheck(float mx, float my){
//		if(GlobalsInterface.isWithin(mx, my, sprite.getX(), sprite.getY(), clickAreaSize))
//			return true;
//		else
//			return false;
//	}
	
}
