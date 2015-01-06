package xtre.game.game_gui.graphics_user_interface.gui_types;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.hud_parts.HUDBox;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuArchive extends GraphicsUserInterface {

	private final GameInterfaceManager gim;
	
	private final Sprite mainButton;
	
	private List<GameButton> subButtons = new ArrayList<>();
	
	public MenuArchive(int GUI_ID, GameInterfaceManager gim, Sprite mainButton) {
		super(GUI_ID, mainButton.getX(), mainButton.getY(), mainButton.getWidth(), mainButton.getHeight());
		
		this.gim = gim;
		this.mainButton = mainButton;
		
		HUDBox[] subMenus = new HUDBox[]{
			new HUDBox(GUI_ID, GUI_ID, GUI_ID, GUI_ID, GUI_ID, null, GUI_ID),
		};
	}

	@Override
	public void updateInterface(float mouseX, float mouseY, boolean justPressedL) {
		for(int i = 0; i < subButtons.size(); i++){
			if(subButtons.get(i).isClicked(mouseX, mouseY, justPressedL)){
				subButtons.get(i).doAction();
			}
		}
	}

	@Override
	public void renderInterface(SpriteBatch batch) {
		mainButton.draw(batch);
		
		for(int i = 0; i < subButtons.size(); i++){
			subButtons.get(i).render(batch);
		}
	}
	
	@Override
	public void dispose() {
		for(int i = 0; i < subButtons.size(); i++){
			subButtons.get(i).dispose();
		}
	}
	
	public void addButton(GameButton button){
		subButtons.add(button);
	}
}
