package xtre.game.game_gui.heads_up_display.hud_parts;


import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackPannel {
	
	SpriteEntity se = new SpriteEntity();
	
	private float mouseX = 0, mouseY = 0;
	private boolean leftMousePress = false;

	public int x, y, width, height;
	
	private Sprite[] graphics;
	
	/**
	 * Accounts for width by height, the sides of top and bottom, left and right and then four corners.
	 * The width and height only account for the size of the middle, as to always have the requested space
	 * without going into the side panels.
	 *	 
	 * Add new custom HUDBox.
	 * Sprites must be in an order of {@code TM, BM, LM, RM, M, TL, TR, BR, BL}
	 * 
	 * @param gim
	 * @param x
	 * @param y
	 * @param horizontalPanels
	 * @param verticalPanels
	 * @param panelGraphics
	 */
	
	public BackPannel(GameInterfaceManager gim, int x, int y, int horizontalPanels, int verticalPanels, Sprite[] panelGraphics){
//		super(gim, GUI_ID, x, y, panelGraphics[0].getWidth()*horizontalPanels, panelGraphics[0].getHeight()*verticalPanels);

		this.x = x;
		this.y = y;
		this.width = horizontalPanels*16;
		this.height = verticalPanels*16;
		
		graphics = new Sprite[(horizontalPanels*verticalPanels) + (horizontalPanels*2) + (verticalPanels*2)+4];
		
		setupTM(x, y, horizontalPanels, verticalPanels, panelGraphics[0]);
		setupBM(x, y, horizontalPanels, verticalPanels, panelGraphics[1]);
		setupLM(x, y, horizontalPanels, verticalPanels, panelGraphics[2]);
		setupRM(x, y, horizontalPanels, verticalPanels, panelGraphics[3]);
		setupCenter(x, y, horizontalPanels, verticalPanels, panelGraphics[4]);

		Sprite[] s = new Sprite[4];
		for(int i = 0; i < s.length; i++)
			s[i] = panelGraphics[i+5];
		setupCorners(x, y, horizontalPanels, verticalPanels, s);
		
	}

	public void update(float mouseX, float mouseY, boolean leftMousePress) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.leftMousePress = leftMousePress;
	}

	public void render(SpriteBatch batch) {
		for(Sprite g:graphics){
			g.draw(batch);
		}
	}
	
	public boolean checkIfShouldClose() {
		if(GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height)){
			return true;
		}
		else
			return false;
	}
	
	public void dispose() {
		for(int i = 0; i < graphics.length; i++){
			graphics[i].getTexture().dispose();
			
		}
	}
	
	////// Box creation below only
	
	private void setupRM(int x, int y, int width, int height, Sprite sprite){
		Sprite[] mrPaneling = new Sprite[height];

		for(int i = 0; i < mrPaneling.length; i++){
			mrPaneling[i] = new Sprite(sprite);
			mrPaneling[i].setPosition(x+(width*16), y+(i*16));
		}
		
		for(int i = 0; i < mrPaneling.length; i++){
			graphics[i+step] = mrPaneling[i];
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
			graphics[i+step] = lmPaneling[i];
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
			graphics[i+step] = tmPaneling[i];
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
			graphics[i+step] = bmPaneling[i];
		}
		step();
	}
	
	private void setupCorners(int x, int y, int width, int height, Sprite[] sprite){
		graphics[0+step] = new Sprite(sprite[0]);
		graphics[0+step].setPosition(x-16, y+(height*16));

		graphics[1+step] = new Sprite(sprite[1]);
		graphics[1+step].setPosition(x+(width*16), y+(height*16));

		graphics[2+step] = new Sprite(sprite[2]);
		graphics[2+step].setPosition(x-16, y-16);

		graphics[3+step] = new Sprite(sprite[3]);
		graphics[3+step].setPosition(x+(width*16), y-16);
		
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
			graphics[i+step] = centerPaneling[i];
		}
		step();
	}
	
	public int step = 0;
	public void step(){
		for(int i = 0; i < graphics.length; i++){
			if(graphics[i] == null){
				step = i;
				return;
			}
		}
	}
}
