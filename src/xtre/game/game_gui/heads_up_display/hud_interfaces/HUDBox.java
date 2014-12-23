package xtre.game.game_gui.heads_up_display.hud_interfaces;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;
import xtre.game.game_gui.heads_up_display.button_interface.button_set.space.GameButton;
import xtre.game.game_gui.heads_up_display.button_interface.button_set.space.GameButtonAction;
import xtre.game.game_gui.heads_up_display.menu_bar.GameMenu;
import xtre.game.game_gui.heads_up_display.menu_bar.MenuBarAction;
import xtre.graphics.font.HUDFont;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUDBox extends HeadsUpDisplay {
	
	/**
	 * Add new custom BoxHUD.
	 * Sprites must be in an order of {@code TM, BM, LM, RM, M, TL, TR, BR, BL}
	 *  
	 * @param int globalID
	 * @param int boxX
	 * @param int boxY
	 * @param int boxWidth
	 * @param int boxHeight
	 * @param Sprite[] panelGraphics 
	 * @param int amountOfMenus 
	 */
	
	public HUDBox(int globalID, int boxX, int boxY, int boxWidth, int boxHeight, Sprite[] panelGraphics, int amountOfMenus){
		super("I am a BoxHUD", globalID, boxX, boxY, boxWidth*16, boxHeight*16);
		
		paneling = new Sprite[(boxWidth*boxHeight) + (boxWidth*2) + (boxHeight*2)+4];
		
		setupTM(boxX, boxY, boxWidth, boxHeight, panelGraphics[0]);
		setupBM(boxX, boxY, boxWidth, boxHeight, panelGraphics[1]);
		setupLM(boxX, boxY, boxWidth, boxHeight, panelGraphics[2]);
		setupRM(boxX, boxY, boxWidth, boxHeight, panelGraphics[3]);
		setupCenter(boxX, boxY, boxWidth, boxHeight, panelGraphics[4]);

		Sprite[] s = new Sprite[4];
		for(int i = 0; i < s.length; i++)
			s[i] = panelGraphics[i+5];
		setupCorners(boxX, boxY, boxWidth, boxHeight, s);
	}

	public void updateInterface(float mouseX, float mouseY, boolean justPressed){
		for(int i = 0; i < gameMenus.length; i++){
			gameMenus[i].update(mouseX, mouseY, justPressed);
		}
	}
	
	public void renderInterface(SpriteBatch batch){
		for(int i = 0; i < gameMenus.length; i++){
			gameMenus[i].render(batch);
		}
	}
	
	public void createDropDownMenu(float menuX, float menuY, Sprite menuBarSprite){
		BitmapFont menuFont = HUDFont.title_font;
		menuFont.setScale(0.48f, 0.48f);
		menuFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		BitmapFont buttonFont = HUDFont.description_font;
		buttonFont.setScale(0.34f, 0.34f);
		buttonFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		Sprite buttonSprite = se.getSprite(SpritesSpaceHudMenu.menu_bar_button);
		buttonSprite.setScale(1.1f);
		
		GameMenu menu = new GameMenu(x+menuX, y+menuY, menuBarSprite.getWidth(), menuBarSprite.getHeight(), menuBarSprite);
		menu.setBitmapFont(menuFont, x+menuX+20, y+menuY+26, "Inspect");
	
		GameButton menuButtonOne = new GameButton(0, 0, this, new Sprite(buttonSprite), new GameButtonAction(){
			public void doAction(){
				System.out.println("menuButtonOne was pressed");
			}
		});
		menuButtonOne.setLabel(x+menuX+menuBarSprite.getWidth()+46, y+menuY+21, "Ok", buttonFont);
		menu.addButton(menuButtonOne, x+menuX+(menuBarSprite.getWidth()+(menuBarSprite.getWidth()-buttonSprite.getWidth())), y+menuY);

		GameButton menuButtonTwo = new GameButton(0, 0, this, new Sprite(buttonSprite), new GameButtonAction(){
			public void doAction(){
				System.out.println("menuButtonTwo was pressed");
			}
		});
		menuButtonTwo.setLabel(0, 0, "Label", buttonFont);
		menu.addButton(menuButtonTwo, x+menuX+(menuBarSprite.getWidth()+(menuBarSprite.getWidth()-buttonSprite.getWidth())), y+menuY);
		
		addMenu(menu);
	}

	public void setMenuBarAction(int whichMenu, MenuBarAction menuBarAction) {
		gameMenus[whichMenu].addAction(menuBarAction);
	}
	
	
//		Sprite menuSprite = se.getSprite(SpritesSpaceHudMenu.menu_bar);
//
//		for(int i = 0; i < amountOfMenus; i++)
//			addMenu(createMenu(boxX+12, (boxY+(i*40)+6), new Sprite(menuSprite), menuSprite.getWidth(), menuSprite.getHeight()+10));
//		
	/**
	 * @param {@code float menuBarX}
	 * @param {@code float menuBarY}
	 * @param {@code Sprite menuBarSprite}
	 */
	public void createHotbarMenu(float menuBarX, float menuBarY, Sprite menuBarSprite){
		BitmapFont menuFont = HUDFont.title_font;
		menuFont.setScale(0.48f, 0.48f);
		menuFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		GameMenu menu = new GameMenu(menuBarX, menuBarY, menuBarSprite.getWidth(), menuBarSprite.getHeight(), menuBarSprite);
		menu.setBitmapFont(menuFont, menuBarX+20, menuBarY+26, "Inspect");
		
		addMenu(menu);
	}

	public void setHUDPosition(float x, float y){
		this.x = x;
		this.y = y;
	}

	
	
	////// Box creation below only
	
	private int step = 0;
	private void step(){
		// ;)
		for(int i = 0; i < paneling.length; i++){
			if(paneling[i] == null){
				step = i;
				return;
			}
		}
	}
	
	private void setupRM(int x, int y, int width, int height, Sprite sprite){
		Sprite[] mrPaneling = new Sprite[height];
		
		for(int i = 0; i < mrPaneling.length; i++){
			mrPaneling[i] = new Sprite(sprite);
			mrPaneling[i].setPosition(x+(width*16), y+(i*16));
		}
		
		for(int i = 0; i < mrPaneling.length; i++){
			paneling[i+step] = mrPaneling[i];
		}
		step();
	}
	
	private void setupLM(int x, int y, int width, int height, Sprite sprite){
		Sprite[] lmPaneling = new Sprite[height];
		
		for(int i = 0; i < lmPaneling.length; i++){
			lmPaneling[i] = new Sprite(sprite);
			lmPaneling[i].setPosition(x-16, y+(i*16));
		}
		
		for(int i = 0; i < lmPaneling.length; i++){
			paneling[i+step] = lmPaneling[i];
		}
		step();
	}
	
	private void setupTM(int x, int y, int width, int height, Sprite sprite){
		Sprite[] tmPaneling = new Sprite[width];
		se.getSprite(SpritesHeadsUpDisplay.paneling_tm);
		
		for(int i = 0; i < tmPaneling.length; i++){
			tmPaneling[i] = new Sprite(sprite);
			tmPaneling[i].setPosition(x+(i*16), y+(height*16));
			
		}
		
		for(int i = 0; i < tmPaneling.length; i++){
			paneling[i+step] = tmPaneling[i];
		}
		step();
	}
	
	private void setupBM(int x, int y, int width, int height, Sprite sprite){
		Sprite[] bmPaneling = new Sprite[width];
		
		for(int i = 0; i < bmPaneling.length; i++){
			bmPaneling[i] = new Sprite(sprite);
			bmPaneling[i].setPosition(x+(i*16), y-16);
		}
		
		for(int i = 0; i < bmPaneling.length; i++){
			paneling[i+step] = bmPaneling[i];
		}
		step();
	}
	
	private void setupCorners(int x, int y, int width, int height, Sprite[] sprite){
			paneling[0+step] = new Sprite(sprite[0]);
			paneling[0+step].setPosition(x-16, y+(height*16));
	
			paneling[1+step] = new Sprite(sprite[1]);
			paneling[1+step].setPosition(x+(width*16), y+(height*16));
	
			paneling[2+step] = new Sprite(sprite[2]);
			paneling[2+step].setPosition(x-16, y-16);
	
			paneling[3+step] = new Sprite(sprite[3]);
			paneling[3+step].setPosition(x+(width*16), y-16);
			
			step();
		
	}
	
	private void setupCenter(int x, int y, int width, int height, Sprite sprite){
		Sprite[] centerPaneling = new Sprite[width*height];
		
		for(int i = 0; i < centerPaneling.length; i++){
			centerPaneling[i] = new Sprite(sprite);
		}
		
		for(int xx = 0; xx < width; xx++){
			for(int yy = 0; yy < height; yy++){
				centerPaneling[xx+yy*width].setPosition(x+(xx*16), y+(yy*16));
			}
		}
		
		for(int i = 0; i < centerPaneling.length; i++){
			paneling[i+step] = centerPaneling[i];
		}
		step();
	}
}
