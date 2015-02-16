package xtre.graphics.components;

import xtre.graphics.UIGraphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ResizableBox {

	private Sprite tl;
	private Sprite tr;
	private Sprite bl;
	private Sprite br;
	
	private Sprite mm;
	
	private Sprite ml;
	private Sprite mr;
	private Sprite mt;
	private Sprite mb;
	
	private final float x, y;
	private float width;
	private float height;
	
	public ResizableBox(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
		tl = sprite;
	}
	
	public void setTR(Sprite sprite){
		tr = sprite;
	}
	
	public void setBL(Sprite sprite){
		bl = sprite;
	}
	
	public void setBR(Sprite sprite){
		br = sprite;
	}
	
	public void setM(Sprite sprite){
		mm = sprite;
	}
	
	public void setMT(Sprite sprite){
		mt = sprite;
	}
	
	public void setMB(Sprite sprite){
		mb = sprite;
	}
	
	public void setML(Sprite sprite){
		ml = sprite;
	}
	
	public void setMR(Sprite sprite){
		mr = sprite;
	}
	
	public void render(SpriteBatch batch){
		tl.draw(batch);
		tr.draw(batch);
		bl.draw(batch);
		br.draw(batch);
		mm.draw(batch);
		mt.draw(batch);
		mb.draw(batch);
		ml.draw(batch);
		mr.draw(batch);
	}

	public void tint(float r, float g, float b, float a) {
		tl.setColor(r,g,b,a);
		tr.setColor(r,g,b,a);
		bl.setColor(r,g,b,a);
		br.setColor(r,g,b,a);
		mm.setColor(r,g,b,a);
		mt.setColor(r,g,b,a);
		mb.setColor(r,g,b,a);
		ml.setColor(r,g,b,a);
		mr.setColor(r,g,b,a);
	}

	public void setPosition(float x, float y) {
		tl.setPosition(x, y+height+bl.getHeight());
		tr.setPosition(x+ml.getWidth()+width, y+br.getHeight()+height);
		bl.setPosition(x, y);
		br.setPosition(x+bl.getWidth()+width, y);
		mm.setPosition(x+ml.getWidth(), y+mb.getHeight());
		mt.setPosition(x+tl.getWidth(), y+mb.getHeight()+mm.getHeight());
		mb.setPosition(x+bl.getWidth(), y);
		ml.setPosition(x, y+bl.getHeight());
		mr.setPosition(x+ml.getWidth()+mm.getWidth(), y+br.getHeight());
	}

	public void dispose() {
		if(tl!=null) tl.getTexture().dispose();
		if(tr!=null) tr.getTexture().dispose();
		if(bl!=null) bl.getTexture().dispose();
		if(br!=null) br.getTexture().dispose();
		if(mm!=null) mm.getTexture().dispose();
		if(mt!=null) mt.getTexture().dispose();
		if(mb!=null) mb.getTexture().dispose();
		if(ml!=null) ml.getTexture().dispose();
		if(mr!=null) mr.getTexture().dispose();
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

	public void setSize(float width, float height){
		this.width = width;
		this.height = height;

		mt.setSize(width, mt.getHeight());
		mb.setSize(width, mb.getHeight());
		mm.setSize(width, height);
		ml.setSize(ml.getWidth(), height);
		mr.setSize(mr.getWidth(), height);
	}
}
