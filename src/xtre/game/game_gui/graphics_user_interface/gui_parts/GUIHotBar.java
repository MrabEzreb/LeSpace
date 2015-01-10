package xtre.game.game_gui.graphics_user_interface.gui_parts;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButtonAction;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIHotBar extends GraphicsUserInterface{
	
	private List<GameButton> buttonArray = new ArrayList<>();
	public static int selected=0;
	
	public GUIHotBar(GameInterfaceManager gim,int GUI_ID, int width, int height, int spacing, Sprite sprite){
		super(gim, GUI_ID, sprite.getX(), sprite.getY(), sprite.getWidth()*width, sprite.getHeight()*height);
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				Sprite s = new Sprite(sprite);
				s.setPosition((x*s.getWidth()), (y*s.getHeight()));
				buttonArray.add(new GameButton(new Sprite(s)));
			}
		}
	}
	
	public void setButtonAction(int i, GameButtonAction buttonAction){
		 buttonArray.get(i).setAction(buttonAction);
	}

	@Override
	public void updateInterface(float mouseX, float mouseY, boolean justPressedL) {
		for(int i = 0; i < buttonArray.size(); i++){
			if(buttonArray.get(i).isClicked(mouseX, mouseY, justPressedL)){
				selected = i;
			}
		}
	}

	@Override
	public void renderInterface(SpriteBatch batch) {
		for(int i = 0; i < buttonArray.size(); i++){
			buttonArray.get(i).render(batch);
		}
	}
	
	@Override
	public void dispose() {
		for(GameButton b:buttonArray){
			b.dispose();
		}
	}
}
