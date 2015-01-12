package xtre.game.game_gui.player;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.gui_parts.DropMenu;
import xtre.game.game_gui.graphics_user_interface.gui_parts.GUIHotBar;
import xtre.game.game_gui.heads_up_display.hud_parts.HUDMetre;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButtonAction;
import xtre.game.game_gui.heads_up_display.utils.menu_bar.GameMenu;
import xtre.game.player.Player;
import xtre.game.player.ship.Inventory;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.gui.GlobalsGUI;
import xtre.globals.game_interface.hud.GlobalsHUD;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceGUI;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerInterface {
	
	private SpriteEntity se = new SpriteEntity();
	
	private final float fuelAmount;
	
	private final Player player;
	private HUDMetre metre;
	
	private GUIHotBar hotbar;
	private DropMenu spaceOptions;
	private Inventory inventory;
	
	private GameInterfaceManager gim;
	
	public PlayerInterface(GameInterfaceManager gim, Player player){
		this.gim = gim;
		this.player = player;
		fuelAmount = player.ship.getFuelAmount();
//		background = new Sprite(se.getSprite(SpritesTextArea.gui_box_mid));
//		background.setColor(1.4f,1,1,.5f);
//		background.setPosition((ScreenGlobals.WIDTH/2)-(background.getWidth()/2), 7+(background.getHeight()/2));

		createFuelMetre();
		createHotBar();
		createSpaceOptionsMenu();
	}
	
	public void update(float mouseX, float mouseY, boolean leftMousePress){
		//spaceOptions.updateInterface(mouseX, mouseY, leftMousePress);
	}
	
	public void render(SpriteBatch batch){
		//spaceOptions.renderInterface(batch);
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
		
		metre = new HUDMetre(gim, GlobalsHUD.SPACE_FUEL_METRE, 1, graphics, (int)fuelAmount);
		//metre.setPosition(880, 80);
	}
	
	private void createHotBar(){
		hotbar = new GUIHotBar(gim, GlobalsGUI.HOTBAR, 8, 1, 3, se.getSprite(SpritesSpaceGUI.hotbar_unselected));
	}
	
	private void createSpaceOptionsMenu(){
		float sOX = 130, sOY = GlobalScreen.HEIGHT-65;
		Sprite dropMenuSprite = se.getSprite(SpritesSpaceHudMenu.menu_bar);
		BitmapFont spaceOptionsSupraMenuFont = HUDFont.title_font.mediumFont;
		BitmapFont spaceOptionsSubMenuFont = HUDFont.title_font.smallFont;
		
//		GameMenu spaceOptionsMenu = new GameMenu();
//			spaceOptionsMenu.setBitmapFont(spaceOptionsSubMenuFont, spaceOptionsMenu.sprite.getX()+24, spaceOptionsMenu.sprite.getY() +19, "Goto Ship");
//		GameMenu spaceOptionsMenu1 = new GameMenu();
//		GameMenu spaceOptionsMenu2 = new GameMenu();
//		GameMenu spaceOptionsMenu3 = new GameMenu();
//		GameMenu spaceOptionsMenu4 = new GameMenu();
		
//		spaceOptions = new DropMenu(sOX, sOY, dropMenuSprite, new FontEntity("Menu", spaceOptionsSupraMenuFont));
//
//		spaceOptions.addSubMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight()), 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button), new FontEntity("", null));
//		spaceOptions.setSubMenuButtonAction(0, 0, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("player interface actions");
//			}
//		});
//		spaceOptions.setSubMenuButtonAction(0, 1, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("player interface actions1");
//			}
//		});
//		
//		spaceOptions.addSubMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight())-30, 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button), new FontEntity("", null));
//		spaceOptions.setSubMenuButtonAction(1, 0, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("player interface actions2");
//			}
//		});
//		spaceOptions.setSubMenuButtonAction(1, 1, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("player interface actions3");
//			}
//		});
//		
//		spaceOptions.addSubMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight())-120, 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button), new FontEntity("", null));
//		spaceOptions.setSubMenuButtonAction(2, 0, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("player interface actions4");
//			}
//		});
//		spaceOptions.setSubMenuButtonAction(2, 1, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("player interface actions4");
//			}
//		});
	}
}
