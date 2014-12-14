package xtre.game.heads_up_display.utils;

import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.HeadsUpDisplaySprites;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class BoxHUD extends HeadsUpDisplay {
	
	private int step = 0;
	
	SpriteEntity se = new SpriteEntity();
	
	public float x=0, y=0, width=0, height=0;

	public BoxHUD(int id, int x, int y, int width, int height){
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		paneling = new Sprite[(width*height) + (width*2) + (height*2)+4];
		System.out.println(paneling.length+" paneling length");

		button = se.getSprite(HeadsUpDisplaySprites.paneling_bl);
		button.setPosition(x, y);

		setupCenter(x, y, width, height);
		setupCorners(x, y, width, height);
		setupTM(x, y, width, height);
		setupBM(x, y, width, height);
		setupLM(x, y, width, height);
		setupRM(x, y, width, height);

//		for(int i = 0; i < paneling.length; i++){
//			System.out.println(paneling[i] + " " + i);
//		}
	}
	
	public BoxHUD(){
		
	}
	
	public void updatePos(float x, float y){
		this.x = x;
		this.y = y;
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
	
	private void createBox(int x, int y, int width, int height){
			
	}

}
