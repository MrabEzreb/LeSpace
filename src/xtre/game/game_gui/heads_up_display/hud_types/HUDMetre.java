package xtre.game.game_gui.heads_up_display.hud_types;

import xtre.game.game_gui.heads_up_display.HeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUDMetre extends HeadsUpDisplay{
	
	private final Sprite levelSprite;
	public int level;
	
	/**
	 * A Metre bar is a bar that has 4 sprites. {@code [0] = left side, [1] middle, [2] right side, [3] level indicator}
	 * 
	 * @param name
	 * @param globalID
	 * @param graphics
	 * @param metreWidth
	 */
	
	public HUDMetre(String name, int globalID, int metreWidth, Sprite[] graphics) {
		super(name, globalID, 0, 0, metreWidth*graphics[0].getWidth(), graphics[0].getHeight());
		

		//Add the middle(metreWidth) and both sides(2)
		this.graphics = new Sprite[(metreWidth+2)];
		createBar(x, y, (int)metreWidth, graphics);
		levelSprite = graphics[3];
	}

	public void updateInterface(float mouseX, float mouseY, boolean justPressed){}
	
	public void renderInterface(SpriteBatch batch){
		for(Sprite g:graphics){
			g.draw(batch);
		}
	}

	public void setLevel(int level){
		this.level = level;
	}
	
	//Creation below only
	
	private void createBar(float x, float y, int width, Sprite[] graphics){
		createLeftSide(x, y, width, graphics[0]);
		createMiddle(x, y, width, graphics[1]);
		createRightSide(x, y, width, graphics[2]);
	}
	
	private void createLeftSide(float x, float y, int width, Sprite spriteLeftSide){
		graphics[step] = new Sprite(spriteLeftSide);
		graphics[step].setPosition(x-graphics[0].getWidth(), y);
		step();
	}
	
	public void createRightSide(float x, float y, int width, Sprite spriteRightSide){		
		graphics[step] = new Sprite(spriteRightSide);
		graphics[step].setPosition(x+(width*graphics[1].getWidth()), y);
		step();
	}
	
	public void createMiddle(float x, float y, int width, Sprite spriteMiddleSide){
		Sprite[] s = new Sprite[width];
		
		for(int i = 0; i < s.length; i++){
			s[i] = new Sprite(spriteMiddleSide);
			s[i].setPosition(x+(i*spriteMiddleSide.getWidth()), y);
		}
		
		for(int i = 0; i < s.length; i++){
			graphics[i+step] = s[i];
		}
		step();
	}

	public void setPosition(int x, int y) {
		for(int i = 0; i < graphics.length; i++){
			System.out.println(graphics[i] + "[" + i + "]");
			graphics[i].setPosition(graphics[i].getX()+x, graphics[i].getY()+y);
		}
	}
}
