package xtre.game.game_gui;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.components.ResizableBox;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameInterface{
	
	public float mouseX, mouseY;
	public boolean mouseLeftPress = false;
	
	public float x, y, width, height;
	
	public final int GI_ID;
	public final int TYPE;
	public boolean isActive = false, isAlwaysActive = false;
	
	public final GameInterfaceManager gim;
	public boolean closed = false;
	
	public ResizableBox frame;
	public int priority=0;
	
	public GameInterface(GameInterfaceManager gim, int GI_COMPONENT_ID, int GAME_INTERFACE_TYPE, float x, float y, float width, float height){
		this.gim = gim;
		this.GI_ID = GI_COMPONENT_ID;
		this.TYPE = GAME_INTERFACE_TYPE;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		System.out.println(width + " size " + height + " " + this.getClass().getSimpleName());
		gim.add(this);
	}
	
	public final void render(SpriteBatch batch){
		renderInterface(batch);
	}
	
	public final void update(float mouseX, float mouseY, boolean mouseLeftPress){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = mouseLeftPress;
		
		if(isActive || isAlwaysActive){
			updateInterface(mouseX, mouseY, mouseLeftPress);
			//dragging();
		}
		isActive(mouseX, mouseY, mouseLeftPress);

	}
	
	public void isActive(float mouseX, float mouseY, boolean mouseLeftPress){
		if(!mouseOutOfBounds(mouseX, mouseY, mouseLeftPress))
			isActive = true;
		else
			isActive = false;
	}
	
	public boolean mouseOutOfBounds(float mouseX, float mouseY, boolean mouseLeftPress){
		if(GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height))
			return false;
		else
			return true;
	}

//	public boolean dragging(){
//		if(frame != null && frame.dragging()){
//			System.out.println(this.getClass().getSimpleName());
//			this.x = mouseX-x;
//			this.y = mouseY-y;
//			//setPosition(x, y);
//			return true;
//		}
//		else
//			return false;
//	}
	
	public abstract void updateInterface(float mouseX, float mouseY, boolean mouseLeftClicked);
	public abstract void renderInterface(SpriteBatch batch);
	public abstract void setPosition(float x, float y);
	public abstract void dispose();

	public void tintColor(float r, float g, float b, float a) {
		if(frame!=null)frame.tint(r,g,b,a);
	}
}
