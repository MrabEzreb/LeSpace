package xtre.game.game_gui.heads_up_display;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.button_interface.button_set.space.GameButton;
import xtre.game.game_gui.heads_up_display.menu_bar.GameMenu;
import xtre.graphics.sprites.SpriteEntity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class HeadsUpDisplay {
	public final String name;
	public final int id;
	public float x, y, width, height;
	public float mouseX, mouseY;
	
	protected boolean justPressedLeftMouse = false;
	
	public Sprite[] paneling;
	public Sprite background;
	
	public final SpriteEntity se = new SpriteEntity();
	
	public boolean running = true;
	
	protected GameMenu[] gameMenus = new GameMenu[0];
	
	protected List<Disposable> disposable = new ArrayList<>();
	
	/**
	 * @param name
	 * @param globalID
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	
	public HeadsUpDisplay(String name, int globalID, float x, float y, float width, float height){
		this.name = name;
		this.id = globalID;
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
		try{
			if(paneling!=null){
				for(Sprite s : paneling){
					if(s!=null)
					s.draw(batch);
				}
			}
			
			if(background!=null){
				background.draw(batch);
			}
			
		}catch(NullPointerException e){
			System.out.println("ERROR: (HeadsUpDisplay.java:) "+this.getClass().getName() + " " + e.getCause());
		}
		
		renderInterface(batch);
	}
	
	public abstract void updateInterface(float mouseX, float mouseY, boolean justPressedLeftMouseButton);
	public abstract void renderInterface(SpriteBatch batch);

	public void closeHUD(){
		dispose();
	}

	public final void dispose(){
		if(paneling!=null)
		for(Sprite s : paneling){
			s.getTexture().dispose();
		}
		
		for(Disposable d:disposable)
			d.dispose();
		System.out.println("disposed of hud ");
	}
	
	public void addMenu(GameMenu menu) {
		disposable.add(menu.menuBarSprite.getTexture());
		disposable.add(menu.font);
		for(GameButton b:menu.getButtons())
			disposable.add(b.sprite.getTexture());
		
		GameMenu[] l = gameMenus;
		gameMenus = new GameMenu[l.length+1];
		
		//Rebuild menu with new menu
		gameMenus[gameMenus.length-1] = menu;
		for(int i = 0; i < gameMenus.length-1; i++){
			gameMenus[i] = l[i];
		}
	}
	
	public boolean mouseOutOfBounds(){
		if(mouseX > x && mouseX < x+(width) && mouseY > y && mouseY < y+(height))
			return false;
		else
			return true;
	}

	public boolean status(){
		boolean status = false;
		if(mouseOutOfBounds()){
			status = true;
			for(GameMenu gm:gameMenus)
				if(gm.isMenuBarOpen) 
					for(GameButton gb:gm.getButtons())
						if(gb.isClicked(mouseX, mouseY, justPressedLeftMouse))
							status = false;
		}		
		
		return status;
	}
}
