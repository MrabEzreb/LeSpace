package xtre.game.graphics.space.background;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Star {
	
	public int size, width, height;
	float x, y, depth;
	public Sprite sprite;
	private final float clickAreaSize;
	
	public boolean selected = false;
	
	public Star(float x, float y, float depth, int size, Sprite sprite){
		this.size = size;
		this.height = size;
		this.width = size;
		this.x=x;
		this.y=y;
		this.depth=depth;
		this.sprite = sprite;
		clickAreaSize = sprite.getWidth()*3;
		
	}
	
	public void reset(float x, float y, float depth, int size, Sprite sprite){
		this.size = size;
		this.height = size;
		this.width = size;
		this.x=x;
		this.y=y;
		this.depth=depth;
		this.sprite = sprite;

	}
	public boolean clicked(float mx, float my, boolean mouseDown){
		if(areaCheck(mx, my) && mouseDown)
			return true;
		else
			return false;
	}
	
	public void setPosition(float x, float y, float depth){
		sprite.setPosition(x, y);
	}

	public void updatePosition(float px, float py) {
		sprite.setPosition(x-(px/depth), y-(py/depth));
	}

	public boolean hovered(float mx, float my) {
		if(areaCheck(mx, my))
			return true;
		else
			return false;
	}
	
	private boolean areaCheck(float mx, float my){
		if(mx > sprite.getX() && mx < sprite.getX()+clickAreaSize && my > sprite.getY() && my < sprite.getY()+clickAreaSize)
			return true;
		else
			return false;
	}
}
