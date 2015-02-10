package xtre.game.game_gui;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.components.ResizableBox;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameInterfaceComponent{
		
	public float x, y, width, height;
	
	public final int GI_ID;
	public final int TYPE;
	
	public final GameInterfaceManager gim;
	public boolean closed = false;
	
	public ResizableBox frame;
	public int priority=0;
	
	public boolean bringToFront = false;
	
	/**
	 * @param gim
	 * @param GI_COMPONENT_ID
	 * @param GAME_INTERFACE_TYPE
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	
	public GameInterfaceComponent(GameInterfaceManager gim, int GI_COMPONENT_ID, int GAME_INTERFACE_TYPE, float x, float y, float width, float height){
		this.gim = gim;
		this.GI_ID = GI_COMPONENT_ID;
		this.TYPE = GAME_INTERFACE_TYPE;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		System.out.println("GameInterfaceComponent: " + width + " size " + height + " " + this.getClass().getSimpleName());
		gim.add(this);
	}
	
	
	public boolean mouseOutOfBounds(){
		if(GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), x, y, width, height))
			return false;
		else
			return true;
	}

	public abstract void updateInterfaces();
	public abstract void updateInteractives();

	public abstract void renderInterfaces(SpriteBatch batch);
	public abstract void renderInteractives(SpriteBatch batch);
	
	public abstract void setPosition(float x, float y);
	public abstract void dispose();

	public void tintColor(float r, float g, float b, float a) {
		if(frame!=null)frame.tint(r,g,b,a);
	}
}
