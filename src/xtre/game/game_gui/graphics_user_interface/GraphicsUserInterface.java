package xtre.game.game_gui.graphics_user_interface;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.GameInterface;
import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics.UIGraphics;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.sprites.SpriteEntity;

import com.badlogic.gdx.utils.Disposable;

public abstract class GraphicsUserInterface extends GameInterface {
	public final static int TYPE=GlobalsInterface.GUI_TYPE;

	public SpriteEntity se = new SpriteEntity();
	
	protected List<Disposable> disposable = new ArrayList<>();
	
	/**
	 * @param GUI_ID
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public GraphicsUserInterface(GameInterfaceManager gim, int GUI_ID, float x, float y, float width, float height){
		super(gim, GUI_ID, TYPE, x, y, width, height);
	}
	
	public final void close(){
		dispose();
	}
	
	public abstract void dispose();
	
}
