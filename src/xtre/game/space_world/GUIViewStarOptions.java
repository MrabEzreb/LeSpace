package xtre.game.space_world;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.hud_parts.BackPanel;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButton;
import xtre.game.game_gui.heads_up_display.utils.button_set.game_button.GameButtonAction;
import xtre.game.game_gui.heads_up_display.utils.menu_bar.GameMenu;
import xtre.game.player.ship.scene.inside_ship.ShipScene;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIViewStarOptions extends GraphicsUserInterface {

	private SpriteEntity se = new SpriteEntity();
	
	private GameMenu[] menus;

	private float mouseX, mouseY;
	public boolean active = false;

	public GUIViewStarOptions(final GameInterfaceManager gim, int GI_ID, BackPanel frame) {
		super(gim, GI_ID, frame.y, frame.x, frame.width, frame.height);
		this.frame = frame;
		
		Sprite s = se.getSprite(SpritesSpaceHudMenu.menu_bar);
		
		menus = new GameMenu[]{
				new GameMenu(new Sprite(s)),
				new GameMenu(new Sprite(s)),
				new GameMenu(new Sprite(s)),
		};
		
		menus[0].addButton(new GameButton(se.getSprite(SpritesSpaceHudMenu.menu_bar_button)), 118, 10);
		menus[0].addButton(new GameButton(se.getSprite(SpritesSpaceHudMenu.menu_bar_button)), 118, -22);
		menus[0].addButton(new GameButton(se.getSprite(SpritesSpaceHudMenu.menu_bar_button)), 118, -54);
		
		menus[0].setActionToButton(0, new GameButtonAction(){
			public void doAction(){
				System.out.println("Button view doing its thing");
			}
		});
		menus[0].setActionToButton(1, new GameButtonAction(){
			public void doAction(){
				System.out.println("Changing scene to interiorShip");
				isActive = false;
				gim.getGameManager().setScene().interiorShip();
			}
		});
		menus[0].setActionToButton(2, new GameButtonAction(){
			public void doAction(){
				System.out.println("Button view doing its thing");
			}
		});
		
		
		menus[1].addButton(new GameButton(se.getSprite(SpritesSpaceHudMenu.menu_bar_button)), 118, 10);
		menus[1].setActionToButton(0, new GameButtonAction(){
			public void doAction(){
				System.out.println("Button one doing its thing");
			}
		});
		
		
		menus[2].addButton(new GameButton(se.getSprite(SpritesSpaceHudMenu.menu_bar_button)), 118, 10);
		menus[2].setActionToButton(0, new GameButtonAction(){
			public void doAction(){
				System.out.println("Button two doing its thing");
			}
		});
		
		
		menus[0].setFont(new FontEntity("View", HUDFont.title_font.largeFont), 32, 27);
		menus[1].setFont(new FontEntity("Goto Ship", HUDFont.title_font.largeFont), 19, 27);
		menus[2].setFont(new FontEntity("two", HUDFont.title_font.largeFont), 38, 27);
		
	}

	public void dispose() {
	}
	
	public void updateInterface(float mouseX, float mouseY, boolean leftMousePress) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		if(isActive){
			for(GameMenu gm:menus){
				gm.update(mouseX, mouseY, leftMousePress);
			}
		}
	}

	public void renderInterface(SpriteBatch batch) {
		if(isActive){
			frame.render(batch);
			for(GameMenu gm:menus){
				gm.render(batch);
			}
		}
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		
		frame.setPosition(x, y);
		for(int i = 0; i < menus.length; i++)
			menus[i].setPosition(x, y - (i*42) + 88);
	}

	/**
	 * Checks if there are any menus open. If so DO NOT close the whole thing.
	 * @return
	 */
	public boolean isClosable() {
		for(GameMenu gm:menus)
			if(gm.isMenuBarOpen) return false;

		if(!mouseOutOfBounds(mouseX, mouseY, mouseLeftPress)){
			return false;
		}
		return true;
	}
	
	public void isActive(float mouseX, float mouseY, boolean mouseLeftPress){
		isActive = active;
		this.mouseLeftPress = false;
	}
}
