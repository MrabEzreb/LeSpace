package xtre.game.space_world;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.game.game_gui.heads_up_display.button.GameButtonAction;
import xtre.game.game_gui.heads_up_display.menu.GameMenu;
import xtre.graphics.components.ResizableBox;
import xtre.graphics.font.FontEntity;
import xtre.graphics.font.HUDFont;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIViewStarOptions extends GraphicsUserInterface {

	//private GameMenu[] menus;

	public boolean active = false;

	public GUIViewStarOptions(final GameInterfaceManager gim, int GI_ID, ResizableBox frame) {
		super(gim, GI_ID, frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
		this.frame = frame;
		
//		Sprite s = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar);
//		
//		menus = new GameMenu[]{
//				new GameMenu(new Sprite(s), 16),
//				new GameMenu(new Sprite(s), 16),
//				new GameMenu(new Sprite(s), 16),
//		};
//		
//		Sprite viewSubButtonSprite = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button);
//		menus[0].addButton(new Sprite(viewSubButtonSprite));
//		menus[0].addButton(new Sprite(viewSubButtonSprite));
//		menus[0].addButton(new Sprite(viewSubButtonSprite));
//		
////		menus[0].addButton(new Sprite(viewSubButtonSprite), 118, (-viewSubButtonSprite.getHeight()*1)+32);
////		menus[0].addButton(new Sprite(viewSubButtonSprite), 118, (-viewSubButtonSprite.getHeight()*2)+32);
////		menus[0].addButton(new Sprite(viewSubButtonSprite), 118, (-viewSubButtonSprite.getHeight()*3)+32);
//
//		menus[0].setActionToButton(0, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("Button view doing its thing");
//			}
//		});
//		menus[0].setActionToButton(1, new GameButtonAction(){
//			public void doAction(){
//			}
//		});
//		menus[0].setActionToButton(2, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("Button view doing its thing");
//			}
//		});
//		
//		menus[1].addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button));
//		menus[1].setActionToButton(0, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("Changing scene to interiorShip");
//				gim.getGameManager().setScene().interiorShip();
//			}
//		});
//		
//		menus[2].addButton(GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar_button));
//		menus[2].setActionToButton(0, new GameButtonAction(){
//			public void doAction(){
//				System.out.println("Button two");
//			}
//		});
//		
//		menus[0].setFont(new FontEntity("View", HUDFont.title_font.largeFont), 32, 28);
//		menus[0].getButtons().get(0).setLabel(-74, -12, "ok", HUDFont.title_font.mediumFont);
//		
//		menus[1].setFont(new FontEntity("Goto Ship", HUDFont.title_font.largeFont), 8, 28);
//		menus[1].getButtons().get(0).setLabel(-74, -12, "ok", HUDFont.title_font.mediumFont);
//		
//		menus[2].setFont(new FontEntity("twop", HUDFont.title_font.largeFont), 38, 28);
//		menus[2].getButtons().get(0).setLabel(-74, -12, "ok", HUDFont.title_font.mediumFont);
//		
	}
	
	public void updateInterfaces() {}
	
	@Override
	public void updateInteractives() {
//		if(active){
//			for(GameMenu gm:menus){
//				gm.update();
//			}
//		}
	}

	@Override
	public void renderInterfaces(SpriteBatch batch) {}
	
	@Override
	public void renderInteractives(SpriteBatch batch){
//		if(active){
//			frame.render(batch);
//			for(GameMenu gm:menus){
//				gm.render(batch);
//			}
//		}
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		
		frame.setPosition(x, y);
	//	for(int i = 0; i < menus.length; i++)
	//		menus[i].setPosition(x+16, y - (i*40) + 98);
	}

	/**
	 * Checks if there are any menus open. If so DO NOT close the whole thing.
	 * @return
	 */
	public boolean isClosable() {
	//	for(GameMenu gm:menus)
	//		if(gm.isMenuBarOpen) return false;

		if(!mouseOutOfBounds()){
			return false;
		}
		return true;
	}
	
	public void dispose() {
	//	for(GameMenu gm:menus){
	//		gm.dispose();
	//	}
	}
}
