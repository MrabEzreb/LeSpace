package xtre.game.heads_up_display.utils;

import xtre.game.heads_up_display.button_interface.button_set.space.GameButton;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class HeadsUpDisplay {
	public final String name;
	public final int id;
	public float x, y, width, height;
	public float mouseX, mouseY;
	
	protected boolean justPressedLeftMouse = false;
	
	public Sprite[] paneling;
	public Sprite background;
	
	public boolean running = true;
	
	protected GameMenu[] gameMenus = new GameMenu[0];
	
	public HeadsUpDisplay(String name, int id, float x, float y, float width, float height){
		this.name = name;
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	public HeadsUpDisplay(){
		id = -1;
		name = "hud doesnt have a name";
	}

	public final void update(float mouseX, float mouseY, boolean justPressedLeftMouseButton){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.justPressedLeftMouse = justPressedLeftMouseButton;

		updateInterface(mouseX, mouseY, justPressedLeftMouseButton);
		
	}

	public final void render(SpriteBatch batch){
		if(paneling!=null)
		for(Sprite s : paneling){
			if(s!=null)
			s.draw(batch);
		}
		
		if(background!=null){
			background.draw(batch);
		}

		renderInterface(batch);
	}
	
	public void updateInterface(float mouseX, float mouseY, boolean justPressedLeftMouseButton){}
	public void renderInterface(SpriteBatch batch){}

	public void closeHUD(){
		dispose();
	}

	public final void dispose(){
		if(paneling!=null)
		for(Sprite s : paneling){
			s.getTexture().dispose();
		}
	}
	
	public boolean mouseOutOfBounds(){
		if(mouseX > x && mouseX < x+(width) && mouseY > y && mouseY < y+(height))
			return false;
		else
			return true;
	}
	public boolean isClicked() {
		if(mouseOutOfBounds() && justPressedLeftMouse){
			return true;
		}
		else
			return false;
	}

	public void addMenu(GameMenu menu) {
		GameMenu[] l = this.gameMenus;
		this.gameMenus = new GameMenu[l.length+1];
		
		//Rebuild menu with new menu
		this.gameMenus[this.gameMenus.length-1] = menu;
		for(int i = 0; i < this.gameMenus.length-1; i++){
			this.gameMenus[i] = l[i];
		}
	}
	
	public boolean status(){
		boolean status = false;
		if(mouseOutOfBounds()){
			status = true;
			for(GameMenu gm:gameMenus)
				if(gm.isMenuBarOpen) 
					for(GameButton gb:gm.getButtons())
						if(!gb.isOutOfBounds(mouseX, mouseY, justPressedLeftMouse)) 
							status = false;
		}		
		
		return status;
	}
	
}
