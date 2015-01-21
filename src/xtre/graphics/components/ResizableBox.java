package xtre.graphics.components;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ResizableBox {

	public List<Sprite> graphics = new ArrayList<>();
	
	public float x, y, width, height;
	
	public ResizableBox(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setTL(Sprite sprite){
		sprite.setPosition(x, y+height);
		graphics.add(sprite);
	}
	
	public void setTR(Sprite sprite){
		sprite.setPosition(x+width, y+height);
		graphics.add(sprite);
	}
	
	public void setBL(Sprite sprite){
		sprite.setPosition(x, y);
		graphics.add(sprite);
	}
	
	public void setBR(Sprite sprite){
		sprite.setPosition(x+width, y);
		graphics.add(sprite);
	}
	
	public void setM(Sprite sprite){
		float w = sprite.getWidth(), h = sprite.getHeight();
		sprite.setPosition(x+w, y+h);
		sprite.setSize(width-sprite.getWidth(), height-sprite.getHeight());
		graphics.add(sprite);
	}
	
	public void setMT(Sprite sprite){
		sprite.setPosition(x+sprite.getWidth(), y+height);
		sprite.setSize(width-sprite.getWidth(), sprite.getHeight());
		graphics.add(sprite);
	}
	
	public void setMB(Sprite sprite){
		sprite.setPosition(x+sprite.getWidth(), y);
		sprite.setSize(width- sprite.getWidth(), sprite.getHeight());
		graphics.add(sprite);	
	}
	
	public void setML(Sprite sprite){
		sprite.setPosition(x, y+sprite.getHeight());
		sprite.setSize(sprite.getWidth(), height-sprite.getHeight());
		graphics.add(sprite);
	}
	
	public void setMR(Sprite sprite){
		sprite.setPosition(x+width, y+sprite.getHeight());		
		sprite.setSize(sprite.getWidth(), height-sprite.getHeight());
		graphics.add(sprite);
	}
	
	public void render(SpriteBatch batch){
		for(Sprite s:graphics){
			s.draw(batch);
		}
	}
}
