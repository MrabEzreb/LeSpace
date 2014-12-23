package xtre.game.game_gui.heads_up_display.hud_interfaces;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUDGuage extends HeadsUpDisplay{
	
	public HUDGuage(String name, int globalID, Sprite[] graphics, int sizeWidth, int sizeHeight) {
		super(name, globalID, graphics[0].getX(), graphics[0].getY(), graphics[0].getX()+graphics[1].getWidth()+graphics[2].getWidth(), graphics[0].getHeight());
		
	}

	@Override
	public void updateInterface(float mouseX, float mouseY, boolean justPressedLeftMouseButton) {
	}

	@Override
	public void renderInterface(SpriteBatch batch) {
	}

}
