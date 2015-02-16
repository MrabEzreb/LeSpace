package xtre.game.game_gui.graphics_user_interface;

import xtre.game.game_gui.GameInterfaceComponent;
import xtre.game.game_gui.GameInterfaceManager;
import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class GraphicsUserInterface extends GameInterfaceComponent {
	public final static int TYPE=GlobalsInterface.GUI_TYPE;
	
	public Sprite[] graphics;
	
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
