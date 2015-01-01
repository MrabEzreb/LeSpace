package xtre.game.game_gui.heads_up_display.hud_parts;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUDMetre extends HeadsUpDisplay{
	
	public float levelL, levelM, levelR;
	private final float maxLevel;
	
	/**
	 * A Metre bar is a bar that has 6 sprites.
	 * {@code [0] = left side, [1] = middle, [2] = right side, [3] = overlay left, [4] = overlay right, [5] = overlay middle}
	 * 
	 * @param name
	 * @param globalID
	 * @param graphics
	 * @param metreWidth
	 */
	
	public HUDMetre(String name, int globalID, int metreWidth, Sprite[] graphics, float maxLevel) {
		super(name, globalID, 0, 0, metreWidth*graphics[0].getWidth(), graphics[0].getHeight());
		
		this.graphics = graphics;
		this.maxLevel = maxLevel;
		levelL = graphics[0].getWidth();
		levelM = maxLevel;
		levelR = graphics[2].getWidth();
		
//		Sprite[] full_fuel_bar = new Sprite[]{
//				graphics[0],
//				graphics[1],
//				graphics[2],
//		};
//		
//		Sprite[] empty_fuel_bar = new Sprite[]{
//				graphics[3],
//				graphics[4],
//				graphics[5],
//		};
	}
	
	public void updateInterface(float mouseX, float mouseY, boolean justPressed){}
	
	public void renderInterface(SpriteBatch batch){
		System.out.println(levelM);
		batch.draw(graphics[3], 100, 100, graphics[0].getWidth(), graphics[0].getHeight());
		batch.draw(graphics[4], 100+graphics[0].getWidth(), 100, maxLevel, graphics[1].getHeight());
		batch.draw(graphics[5], 100+graphics[0].getWidth()+maxLevel, 100, graphics[2].getWidth(), graphics[2].getHeight());
	
		batch.draw(graphics[0], 100, 100, levelL, graphics[0].getHeight());
		batch.draw(graphics[1], 100+levelL, 100, levelM, graphics[1].getHeight());
		batch.draw(graphics[2], 100+levelM+levelL, 100, levelR, graphics[2].getHeight());
	}

	public boolean setLevel(float level){
		if(levelM>0){
			this.levelM = level;
			return true;
		}
		else
			return false;
	}
	
	public void setPosition(int x, int y) {
		for(int i = 0; i < graphics.length; i++){
			System.out.println(graphics[i] + "[" + i + "]");
			graphics[i].setPosition(graphics[i].getX()+x, graphics[i].getY()+y);
		}
	}
}
