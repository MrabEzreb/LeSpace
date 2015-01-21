package xtre.game.game_gui.heads_up_display.hud_parts;


import xtre.game.game_gui.heads_up_display.hud_parts.back_panel.GPTile;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackPanel {
	
	private float mouseX = 0, mouseY = 0;
	private boolean mouseLeftPress = false;

	public float x, y, width, height;
	
	private GPTile[] graphics;
	
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
	
	public BackPanel(int x, int y, int horizontalPanels, int verticalPanels, Sprite[] panelGraphics){
		this.x = x;
		this.y = y;
		this.width = horizontalPanels*16;
		this.height = verticalPanels*16;
		
		graphics = new GPTile[(horizontalPanels*verticalPanels) + (horizontalPanels*2) + (verticalPanels*2)+4];
		
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

	public void update(float mouseX, float mouseY, boolean mouseLeftPress) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = mouseLeftPress;
	}

	public void render(SpriteBatch batch) {
		for(GPTile g:graphics){
			g.tile.draw(batch);
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
			graphics[i].tile.getTexture().dispose();
		}
	}
	
	public boolean dragging(){
		if(mouseLeftPress && GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height)){
			this.x = mouseX-x;
			this.y = mouseY-y;
			return true;
		}
		else
			return false;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		for(int i = 0; i < graphics.length; i++){
			graphics[i].setOffset(x, y);
		}
	}
	
	public void setOffset(int x, int y){
		for(int i = 0; i < graphics.length; i++){
			graphics[i].setOffset(x, y);
		}
	}
	
	public void tint(float r, float g, float b, float a) {
		for(int i = 0; i < graphics.length; i++){
			graphics[i].tint(r,g,b,a);
		}
	}
	
	////// Box creation below only
	
	private void setupRM(int x, int y, int width, int height, Sprite sprite){
		GPTile[] mrPaneling = new GPTile[height];

		for(int i = 0; i < mrPaneling.length; i++){
			mrPaneling[i] = new GPTile(x+(width*16), y+(i*16), new Sprite(sprite));
		}
		
		for(int i = 0; i < mrPaneling.length; i++){
			graphics[i+step] = mrPaneling[i];
		}
		step();
	}
	
	private void setupLM(int x, int y, int width, int height, Sprite sprite){
		GPTile[] lmPaneling = new GPTile[height];
		
		for(int i = 0; i < lmPaneling.length; i++){
			lmPaneling[i] = new GPTile(x-16, y+(i*16), new Sprite(sprite));
		}
		
		for(int i = 0; i < lmPaneling.length; i++){
			graphics[i+step] = lmPaneling[i];
		}
		step();
	}
	
	private void setupTM(int x, int y, int width, int height, Sprite sprite){
		GPTile[] tmPaneling = new GPTile[width];
		GameSprite.getSprite(SpritesHeadsUpDisplay.paneling_tm);
		
		for(int i = 0; i < tmPaneling.length; i++){
			tmPaneling[i] = new GPTile(x+(i*16), y+(height*16), new Sprite(sprite));
		}
		
		for(int i = 0; i < tmPaneling.length; i++){
			graphics[i+step] = tmPaneling[i];
		}
		step();
	}
	
	private void setupBM(int x, int y, int width, int height, Sprite sprite){
		GPTile[] bmPaneling = new GPTile[width];
		
		for(int i = 0; i < bmPaneling.length; i++){
			bmPaneling[i] = new GPTile(x+(i*16), y-16, new Sprite(sprite));
		}
		
		for(int i = 0; i < bmPaneling.length; i++){
			graphics[i+step] = bmPaneling[i];
		}
		step();
	}
	
	private void setupCorners(int x, int y, int width, int height, Sprite[] sprite){
		graphics[0+step] = new GPTile(x-16, y+(height*16), new Sprite(sprite[0]));

		graphics[1+step] = new GPTile(x+(width*16), y+(height*16), new Sprite(sprite[1]));

		graphics[2+step] = new GPTile(x-16, y-16, new Sprite(sprite[2]));

		graphics[3+step] = new GPTile(x+(width*16), y-16, new Sprite(sprite[3]));
		
		step();
	}
	
	private void setupCenter(int x, int y, int width, int height, Sprite sprite){
		GPTile[] centerPaneling = new GPTile[width*height];

		for(int xx = 0; xx < width; xx++){
			for(int yy = 0; yy < height; yy++){
				centerPaneling[xx+yy*width] = new GPTile(x+(xx*16), y+(yy*16), new Sprite(sprite));
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
