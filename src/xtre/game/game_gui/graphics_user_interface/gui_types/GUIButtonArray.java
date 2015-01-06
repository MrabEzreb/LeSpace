package xtre.game.game_gui.graphics_user_interface.gui_types;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIButtonArray extends GraphicsUserInterface{
	
	private List<GameButton> buttonArray = new ArrayList<>();
	
	public GUIButtonArray(int width, int height, int spacing){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				
			}
		}
	}

	@Override
	public void updateInterface(float mouseX, float mouseY, boolean justPressedL) {
		
	}

	@Override
	public void renderInterface(SpriteBatch batch) {
		
	}
	
	@Override
	public void dispose() {
		for(GameButton b:buttonArray){
			b.dispose();
		}
	}
}
