package xtre.game.game_gui;

import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameInterface {
	
	public float mouseX, mouseY;
	public boolean mouseLeftClicked = false;
	
	public final float x, y, width, height;
	
	public final int GI_ID;
	public final int TYPE;
	
	public GameInterface(int GI_ID, int GAME_INTERFACE_TYPE, float x, float y, float width, float height){
		this.GI_ID = GI_ID;
		this.TYPE = GAME_INTERFACE_TYPE;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public final void render(SpriteBatch batch){
		renderInterface(batch);
	}
	
	public final void update(float mouseX, float mouseY, boolean mouseLeftClicked){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftClicked = mouseLeftClicked;
		
		updateInterface(mouseX, mouseY, mouseLeftClicked);
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
