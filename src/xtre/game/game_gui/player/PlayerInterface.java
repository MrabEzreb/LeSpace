package xtre.game.game_gui.player;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.gui_types.DropMenu;
import xtre.game.game_gui.graphics_user_interface.gui_types.GUIHotBar;
import xtre.game.game_gui.heads_up_display.hud_parts.HUDMetre;
import xtre.game.game_gui.heads_up_display.utils.menu_bar.GameMenu;
import xtre.game.game_gui.heads_up_display.utils.menu_bar.MenuBarAction;
import xtre.game.player.Player;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.gui.GlobalsGUI;
import xtre.globals.game_interface.hud.GlobalsHUD;
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
	private GameInterfaceManager gim;
	
	public PlayerInterface(GameInterfaceManager gim, Player player){
		this.gim = gim;
		this.player = player;
		fuelAmount = player.getFuelAmount();
//		background = new Sprite(se.getSprite(SpritesTextArea.gui_box_mid));
//		background.setColor(1.4f,1,1,.5f);
//		background.setPosition((ScreenGlobals.WIDTH/2)-(background.getWidth()/2), 7+(background.getHeight()/2));

		createFuelMetre();
		hotbar = new GUIHotBar(GlobalsGUI.HOTBAR, 8, 1, 3, se.getSprite(SpritesSpaceGUI.hotbar_unselected));
		//spaceOptions = new MenuArchive(0, gim, new GameMenu(0, 0, 0, 0, se.getSprite(SpritesSpaceHudMenu.menu_bar)));
		
		float sOX = 130, sOY = GlobalScreen.HEIGHT-65;
		Sprite dropMenuSprite = se.getSprite(SpritesSpaceHudMenu.menu_bar);
		BitmapFont spaceOptionsSupraMenuFont = HUDFont.title_font.mediumFont;
		BitmapFont spaceOptionsSubMenuFont = HUDFont.title_font.smallFont;
		
		GameMenu spaceOptionsMenu = new GameMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight()), 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button));
			spaceOptionsMenu.setBitmapFont(spaceOptionsSubMenuFont, sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight()), "Goto Ship");
		GameMenu spaceOptionsMenu1 = new GameMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight())-30, 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button));
		GameMenu spaceOptionsMenu2 = new GameMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight())-60, 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button));
		GameMenu spaceOptionsMenu3 = new GameMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight())-90, 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button));
		GameMenu spaceOptionsMenu4 = new GameMenu(sOX+(dropMenuSprite.getWidth()/2)+(dropMenuSprite.getWidth()/4), sOY-(dropMenuSprite.getHeight())-120, 200, 100, se.getSprite(SpritesSpaceHudMenu.menu_bar_button));
		
		spaceOptionsMenu.setAction(new MenuBarAction(){
			public void action(){
				System.out.println("player interface actions");
			}
		});
		
		spaceOptionsMenu1.setAction(new MenuBarAction(){
			public void action(){
				System.out.println("player interface actions1");
			}
		});
		
		spaceOptionsMenu2.setAction(new MenuBarAction(){
			public void action(){
				System.out.println("player interface actions2");
			}
		});
		
		spaceOptionsMenu3.setAction(new MenuBarAction(){
			public void action(){
				System.out.println("player interface actions3");
			}
		});
		
		spaceOptionsMenu4.setAction(new MenuBarAction(){
			public void action(){
				System.out.println("player interface actions4");
			}
		});
		
		spaceOptions = new DropMenu(GlobalsGUI.SPACE_DROP_MENU, sOX, sOY, dropMenuSprite, "Menu", spaceOptionsSupraMenuFont);
		spaceOptions.addSubMenu(spaceOptionsMenu);
		spaceOptions.addSubMenu(spaceOptionsMenu1);
		spaceOptions.addSubMenu(spaceOptionsMenu2);
		spaceOptions.addSubMenu(spaceOptionsMenu3);
		spaceOptions.addSubMenu(spaceOptionsMenu4);
		
	}
	
	public void update(float mouseX, float mouseY, boolean justPressedL){
		hotbar.update(mouseX, mouseY, justPressedL);
		spaceOptions.update(mouseX, mouseY, justPressedL);
	}
	
	public void render(SpriteBatch batch){
		hotbar.render(batch);
		spaceOptions.render(batch);
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
