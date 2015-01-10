package xtre.game.game_gui;

import xtre.game.game_gui.heads_up_display.hud_parts.BackPannel;
import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameInterface {
	
	public float mouseX, mouseY;
	public boolean mouseLeftPress = false;
	
	public final float x, y, width, height;
	
	public final int GI_ID;
	public final int TYPE;
	public boolean selected = false;
	
	public final GameInterfaceManager gim;
	public boolean closed = false;
	
	public BackPannel frame;
	
	public GameInterface(GameInterfaceManager gim, int GI_ID, int GAME_INTERFACE_TYPE, float x, float y, float width, float height){
		this.gim = gim;
		this.GI_ID = GI_ID;
		this.TYPE = GAME_INTERFACE_TYPE;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		System.out.println(width + " size " + height + " " + this.getClass().getSimpleName());
		gim.add(this);
	}
	
	public final void render(SpriteBatch batch){
		if(frame!=null)frame.render(batch);
		renderInterface(batch);
	}
	
	public final void update(float mouseX, float mouseY, boolean mouseLeftPress){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = mouseLeftPress;

		if(frame!=null)frame.update(mouseX, mouseY, mouseLeftPress);
		
		updateInterface(mouseX, mouseY, mouseLeftPress);
	}
	
	public boolean isActive(float mouseX, float mouseY, boolean mouseLeftPress){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		if(!mouseOutOfBounds()){
			return true;
		}
		else
			return false;
	}
	
	public boolean mouseOutOfBounds(){
		if(GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height))
			return false;
		else
			return true;
	}
	
	public abstract void updateInterface(float mouseX, float mouseY, boolean mouseLeftClicked);
	public abstract void renderInterface(SpriteBatch batch);
	public abstract void dispose();
}
