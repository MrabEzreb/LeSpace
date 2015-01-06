package xtre.game.game_gui.player;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.gui_types.GUIHotBar;
import xtre.game.game_gui.heads_up_display.hud_parts.HUDMetre;
import xtre.game.player.Player;
import xtre.globals.game_interface.gui.GlobalsGUI;
import xtre.globals.game_interface.hud.GlobalsHUD;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceGUI;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerInterface {
	
	private SpriteEntity se = new SpriteEntity();
	
	private final float fuelAmount;
	
	private final Player player;
	private HUDMetre metre;
	
	private GUIHotBar hotbar;
	private GameInterfaceManager gim;
	
	public PlayerInterface(GameInterfaceManager gim, Player player){
		this.gim = gim;
		this.player = player;
		fuelAmount = player.getFuelAmount();
//		background = new Sprite(se.getSprite(SpritesTextArea.gui_box_mid));
//		background.setColor(1.4f,1,1,.5f);
//		background.setPosition((ScreenGlobals.WIDTH/2)-(background.getWidth()/2), 7+(background.getHeight()/2));
//		background.setScale(40, 1.5f);

		createFuelMetre();
		hotbar = new GUIHotBar(GlobalsGUI.HOTBAR, 8, 1, 3, se.getSprite(SpritesSpaceGUI.hotbar_unselected));
	}
	
	public void update(float mouseX, float mouseY, boolean justPressedL){
		hotbar.update(mouseX, mouseY, justPressedL);
	}
	
	public void render(SpriteBatch batch){
		hotbar.render(batch);
	}
	
	public boolean setMetreLevel(float level){
		return metre.setLevel(level);
	}
	
	//Creation below only
	
	private void createFuelMetre(){
		Sprite[] graphics = new Sprite[]{
			se.getSprite(SpritesSpaceGUI.fuel_bar_full_l),
			se.getSprite(SpritesSpaceGUI.fuel_bar_full_m),
			se.getSprite(SpritesSpaceGUI.fuel_bar_full_r),
			
			se.getSprite(SpritesSpaceGUI.fuel_bar_empty_l),
			se.getSprite(SpritesSpaceGUI.fuel_bar_empty_m),
			se.getSprite(SpritesSpaceGUI.fuel_bar_empty_r)
		};
		
		metre = new HUDMetre(GlobalsHUD.SPACE_FUEL_METRE, 1, graphics, (int)fuelAmount);
		//metre.setPosition(880, 80);
		gim.addHUD(metre);
	}
}
