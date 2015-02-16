package xtre.game.game_gui.graphics_user_interface.gui_parts;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.button.GameButton;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.ship_forge.components.button.Action;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SelectionBar extends GraphicsUserInterface{
	
	private List<GameButton> buttonArray = new ArrayList<>();
	public static int selected=0;
	
	public SelectionBar(GameInterfaceManager gim,int GUI_ID, int width, int height, int spacing, Sprite sprite){
		super(gim, GUI_ID, sprite.getX(), sprite.getY(), sprite.getWidth()*width, sprite.getHeight()*height);
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				Sprite s = new Sprite(sprite);
				s.setPosition((x*s.getWidth()), (y*s.getHeight()));
				buttonArray.add(new GameButton(s, new FontEntity(HUDFont.title_font.smallFont, new Color(1,1,1,1), "SelectionBar: x[" + x +"], y[" + y + "]", x, y)));
			}
		}
	}
	
	public void setButtonAction(int i, Action buttonAction){
		 buttonArray.get(i).setAction(buttonAction);
	}

	@Override
	public void updateInterfaces() {
		System.out.println("selectionBar");
	}
	
	@Override
	public void updateInteractives() {
		for(int i = 0; i < buttonArray.size(); i++){
			if(buttonArray.get(i).isClicked()){
				selected = i;
			}
		}
	}

	@Override
	public void renderInterfaces(SpriteBatch batch) {
		for(int i = 0; i < buttonArray.size(); i++){
			buttonArray.get(i).render(batch);
		}
	}
	
	public void renderInteractives(SpriteBatch batch){
	}
	
	@Override
	public void dispose() {
		for(GameButton b:buttonArray){
			b.dispose();
		}
	}

	@Override
	public void setPosition(float x, float y) {
	}
}
