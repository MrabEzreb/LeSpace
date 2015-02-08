package xtre.graphics.components;

import xtre.graphics.UIGraphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ResizableBox {

	private Sprite[] graphics = new Sprite[9];
	
	private Vector2[] position = new Vector2[9];
	
	private final float x, y, width, height;
	
	public ResizableBox(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		for(int i = 0; i < position.length; i++){
			position[i] = new Vector2();
		}
	}
	
	public void setGraphicsTo(int graphicsSetID){
		dispose();
		if(graphicsSetID == 0){
			setBL(UIGraphics.getBL());
			setBR(UIGraphics.getBR());
			setM(UIGraphics.getMM());
			setMB(UIGraphics.getBM());
			setML(UIGraphics.getLM());
			setMR(UIGraphics.getRM());
			setMT(UIGraphics.getTM());
			setTL(UIGraphics.getTL());
			setTR(UIGraphics.getTR());
		}else
			
		if(graphicsSetID == 1){
			
		}
		if(graphicsSetID > 1)
			System.out.println("ResizableBox: graphicsSetID is over the max number for the graphics");
	}
	
	public void setTL(Sprite sprite){
		position[0].x = x; position[0].y = y+height;
		sprite.setPosition(position[0].x, position[0].y);
		graphics[0] = sprite;
	}
	
	public void setTR(Sprite sprite){
		position[1].x = x+width; position[1].y = y+height;
		sprite.setPosition(position[1].x, position[1].y);
		graphics[1] = sprite;
	}
	
	public void setBL(Sprite sprite){
		position[2].x = x; position[2].y = y;
		sprite.setPosition(position[2].x, position[2].y);
		graphics[2] = sprite;
	}
	
	public void setBR(Sprite sprite){
		position[3].x = x+width; position[3].y = y;
		sprite.setPosition(position[3].x, position[3].y);
		graphics[3] = sprite;
	}
	
	public void setM(Sprite sprite){
		position[4].x = x+sprite.getWidth(); position[4].y = y+sprite.getHeight();
		sprite.setPosition(position[4].x, position[4].y);
		sprite.setSize(width-sprite.getWidth(), height-sprite.getHeight());
		sprite.setAlpha(0.8f);
		graphics[4] = sprite;
	}
	
	public void setMT(Sprite sprite){
		position[5].x = x+sprite.getWidth(); position[5].y = y+height;
		sprite.setPosition(position[5].x, position[5].y);
		sprite.setSize(width-sprite.getWidth(), sprite.getHeight());
		graphics[5] = sprite;
	}
	
	public void setMB(Sprite sprite){
		position[6].x = x+sprite.getWidth(); position[6].y = y;
		sprite.setPosition(position[6].x, position[6].y);
		sprite.setSize(width- sprite.getWidth(), sprite.getHeight());
		graphics[6] = sprite;
	}
	
	public void setML(Sprite sprite){
		position[7].x = x; position[7].y = y+sprite.getHeight();
		sprite.setPosition(position[7].x, position[7].y);
		sprite.setSize(sprite.getWidth(), height-sprite.getHeight());
		graphics[7] = sprite;
	}
	
	public void setMR(Sprite sprite){
		position[8].x = x+width; position[8].y = y+sprite.getHeight();
		sprite.setPosition(position[8].x, position[8].y);		
		sprite.setSize(sprite.getWidth(), height-sprite.getHeight());
		graphics[8] = sprite;
	}
	
	public void render(SpriteBatch batch){
		for(Sprite s:graphics){
			s.draw(batch);
		}
	}

	public void tint(float r, float g, float b, float a) {
		for(int i = 0; i < graphics.length; i++){
			graphics[i].setColor(r,g,b,a);
		}
	}
	
	public void setPosition(float x, float y) {
		for(int i = 0; i < graphics.length; i++){
			graphics[i].setPosition(position[i].x + x, position[i].y + y);
		}
	}

	public void dispose() {
		if(graphics!=null)
			for(Sprite s:graphics)
				if(s!=null)
					s.getTexture().dispose();
	}

	public float getX() {
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
}
