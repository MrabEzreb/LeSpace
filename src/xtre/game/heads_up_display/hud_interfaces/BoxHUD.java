package xtre.game.heads_up_display.hud_interfaces;

import xtre.game.heads_up_display.button_interface.button_set.space.GameButton;
import xtre.game.heads_up_display.button_interface.button_set.space.GameButtonAction;
import xtre.game.heads_up_display.utils.HeadsUpDisplay;
import xtre.game.heads_up_display.utils.GameMenu;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space_hud.HeadsUpDisplaySprites;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoxHUD extends HeadsUpDisplay {
	
	private int step = 0;
	
	SpriteEntity se = new SpriteEntity();
		
	public BoxHUD(int id, int x, int y, int width, int height){
		super("I am a BoxHUD", id, x, y, width*16, height*16);

		paneling = new Sprite[(width*height) + (width*2) + (height*2)+4];
		
		setupCenter(x, y, width, height);
		setupCorners(x, y, width, height);
		setupTM(x, y, width, height);
		setupBM(x, y, width, height);
		setupLM(x, y, width, height);
		setupRM(x, y, width, height);
		Sprite menuSprite = se.getSprite(SpritesSpaceHudMenu.menu_bar);

		for(int i = 0; i < 3; i++)
			createMenuLists(x+12, (y+(i*40)+6), menuSprite.getWidth(), 40, new Sprite(menuSprite));
		
	}
	
	//
	public void updateInterface(float mousex, float mouseY, boolean justPressed){
		for(int i = 0; i < gameMenus.length; i++){
			gameMenus[i].update(mousex, mouseY, justPressed);
		}
	}
	
	public void renderInterface(SpriteBatch batch){
		for(int i = 0; i < gameMenus.length; i++){
			gameMenus[i].render(batch);
		}
	}	
	//
	
	private void createMenuLists(float menuBarX, float menuBarY, float openMenuButtonX, float openMenuButtonY, Sprite menuBarSprite){
		GameMenu menu = new GameMenu(menuBarX, menuBarY, menuBarSprite.getWidth(), menuBarSprite.getHeight(), menuBarSprite);
		BitmapFont bm = new BitmapFont(Gdx.files.internal("font/default.fnt"));
		bm.scale(-0.2f);
		//menu.setBitmapFont(bm, menuBarX+20, menuBarY+26, "Inspect");
		
		Sprite buttonSprite = se.getSprite(SpritesSpaceHudMenu.menu_bar_button);
		buttonSprite.setScale(1.1f);
		menu.addButton(
			new GameButton(0, 0, this, new Sprite(buttonSprite), new GameButtonAction(){
				public void doAction(){
					System.out.println("inspect it");
				}
			}));
		
		buttonSprite.setScale(1.1f);
		menu.addButton(
			new GameButton(0, 0, this, new Sprite(buttonSprite), new GameButtonAction(){
				public void doAction(){
					System.out.println("inspect it");
				}
			}));
		
		
		addMenu(menu);
		
	}

	public void setHUDPosition(float x, float y){
		this.x = x;
		this.y = y;
	}

	////// Box creation below only
	
	public void recreateBox(int x, int y, int width, int height){
		super.x = x;
		super.y = y;
		super.width = width*16;
		super.height = height*16;
		paneling = new Sprite[(width*height) + (width*2) + (height*2)+4];
		
		setupCenter(x, y, width, height);
		setupCorners(x, y, width, height);
		setupTM(x, y, width, height);
		setupBM(x, y, width, height);
		setupLM(x, y, width, height);
		setupRM(x, y, width, height);

	}
	
	private void step(){
		// ;)
		for(int i = 0; i < paneling.length; i++){
			if(paneling[i] == null){
				step = i;
				return;
			}
		}
	}
	
	private void setupRM(int x, int y, int width, int height){
		Sprite[] mrPaneling = new Sprite[height];
		
		for(int i = 0; i < mrPaneling.length; i++){
			mrPaneling[i] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_mr));
			mrPaneling[i].setPosition(x+(width*16), y+(i*16));
		}
		
		for(int i = 0; i < mrPaneling.length; i++){
			paneling[i+step] = mrPaneling[i];
		}
		step();
	}
	
	private void setupLM(int x, int y, int width, int height){
		Sprite[] lmPaneling = new Sprite[height];
		
		for(int i = 0; i < lmPaneling.length; i++){
			lmPaneling[i] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_lm));
			lmPaneling[i].setPosition(x-16, y+(i*16));
		}
		
		for(int i = 0; i < lmPaneling.length; i++){
			paneling[i+step] = lmPaneling[i];
		}
		step();
	}
	
	private void setupTM(int x, int y, int width, int height){
		Sprite[] tmPaneling = new Sprite[width];
		se.getSprite(HeadsUpDisplaySprites.paneling_tm);
		
		for(int i = 0; i < tmPaneling.length; i++){
			tmPaneling[i] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_tm));
			tmPaneling[i].setPosition(x+(i*16), y+(height*16));
			
		}
		
		for(int i = 0; i < tmPaneling.length; i++){
			paneling[i+step] = tmPaneling[i];
		}
		step();
	}
	
	private void setupBM(int x, int y, int width, int height){
		Sprite[] bmPaneling = new Sprite[width];
		
		for(int i = 0; i < bmPaneling.length; i++){
			bmPaneling[i] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_bm));
			bmPaneling[i].setPosition(x+(i*16), y-16);
		}
		
		for(int i = 0; i < bmPaneling.length; i++){
			paneling[i+step] = bmPaneling[i];
		}
		step();
	}
	
	private void setupCorners(int x, int y, int width, int height){
			paneling[0+step] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_tl));
			paneling[0+step].setPosition(x-16, y+(height*16));
	
			paneling[1+step] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_tr));
			paneling[1+step].setPosition(x+(width*16), y+(height*16));
	
			paneling[2+step] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_bl));
			paneling[2+step].setPosition(x-16, y-16);
	
			paneling[3+step] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_br));
			paneling[3+step].setPosition(x+(width*16), y-16);
			
			step();
		
	}
	
	private void setupCenter(int x, int y, int width, int height){
		Sprite[] centerPaneling = new Sprite[width*height];
		
		for(int i = 0; i < centerPaneling.length; i++){
			centerPaneling[i] = new Sprite(se.getSprite(HeadsUpDisplaySprites.paneling_mm));
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
