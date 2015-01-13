package xtre.game.game_gui.heads_up_display;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.GameInterface;
import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;
import xtre.game.game_gui.heads_up_display.utils.menu_bar.GameMenu;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.sprites.SpriteEntity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class HeadsUpDisplay extends GameInterface{
	public static final int TYPE = GlobalsInterface.HUD_TYPE;
	
	public Sprite[] graphics;
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
	
	public HeadsUpDisplay(GameInterfaceManager gim, int HUD_ID, float x, float y, float width, float height){
		super(gim, HUD_ID, TYPE, x, y, width, height);

	}

	public abstract void updateInterface(float mouseX, float mouseY, boolean justPressedLeftMouseButton);
	public abstract void renderInterface(SpriteBatch batch);

	public void closeHUD(){
		dispose();
	}

	public void dispose(){
		if(graphics!=null)
		for(Sprite s : graphics){
			s.getTexture().dispose();
		}
		
		for(Disposable d:disposable)
			d.dispose();
		System.out.println("disposed of hud ");
	}
	
	public final boolean status(){
		boolean status = false;
		if(mouseOutOfBounds(mouseX, mouseY, mouseLeftPress)){
			status = true;
			for(GameMenu gm:gameMenus)
				if(gm.isMenuBarOpen) 
					for(GameButton gb:gm.getButtons())
						if(gb.isClicked(mouseX, mouseY, mouseLeftPress))
							status = false;
		}		
		
		return status;
	}
}
