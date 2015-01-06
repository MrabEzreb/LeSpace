package xtre.game.game_gui.graphics_user_interface;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.GameInterface;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.sprites.SpriteEntity;

import com.badlogic.gdx.utils.Disposable;

public abstract class GraphicsUserInterface extends GameInterface {
	public final static int TYPE=GlobalsInterface.GUI_TYPE;

	public SpriteEntity se = new SpriteEntity();
	
	protected List<Disposable> disposable = new ArrayList<>();
	
	public GraphicsUserInterface(int GUI_ID, float x, float y, float width, float height){
		super(GUI_ID, TYPE, x, y, width, height);
	}
	
	public final void close(){
		dispose();
	}
	
	public abstract void dispose();
	
}
