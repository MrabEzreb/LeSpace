package xtre.game.graphics.space.background;

import java.util.Arrays;
import java.util.Random;

import xtre.globals.ScreenGlobals;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.SpaceBackgroundStarSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stars {
	
	private final int worldSize, density, depth;
	
	private Random r = new Random();
	
	private Star[] stars;
	private Float[] depths;
	
	private float camX, camY;
	
	public int selectedStar = -1;
	
	public Stars(int worldSize, int density, int depth){
		this.worldSize = worldSize;
		this.density = density;
		this.depth = depth;
		
		stars = new Star[(worldSize)];
		createDepth(depth);
		createStars();
		
	}
	
	public void moveStars(float x, float y){
		
	}
	
	
	public Star getSelectedStar(){
		if(selectedStar!=-1)
			return stars[selectedStar];
		else
			return null;
	}
	
	public void update(float camX, float camY){
		this.camX = camX;
		this.camY = camY;
		updateStars();
	}
	
	public void render(SpriteBatch batch, float camX, float camY){
		for(int i = stars.length-1; i > -1; i--){
			if(stars[i].sprite.getX() < ScreenGlobals.WIDTH && stars[i].sprite.getX() > 0 && stars[i].sprite.getY() < ScreenGlobals.HEIGHT && stars[i].sprite.getY() > 0)
					stars[i].sprite.draw(batch);
		}
	}
	
	private void updateStars(){
		for(int i = 0; i < stars.length; i++){
			stars[i].updatePosition(camX, camY);
			starSelection(i);
			
		}
		//if(Gdx.input.isButtonPressed(Buttons.LEFT))

	}

	private void starSelection(int i){
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			if(selectedStar == -1 || stars[i].selected && selectedStar == i){
				if(stars[i].selected){
					stars[i].selected = false;
					selectedStar = -1;
				}
				
				if(stars[i].clicked(Gdx.input.getX(), (int)(-Gdx.input.getY()+ScreenGlobals.HEIGHT), i)){
					stars[i].selected = true;
					selectedStar = i;
				}
			}
		}
	}

	private void createStars(){
		float z = 0;
		int starMaxSize=0;
		SpriteEntity se = new SpriteEntity();
		Sprite[] ss = new Sprite[]{
				se.getSprite(SpaceBackgroundStarSprites.alpha_star),
				se.getSprite(SpaceBackgroundStarSprites.small_star),
				se.getSprite(SpaceBackgroundStarSprites.medium_star),
				se.getSprite(SpaceBackgroundStarSprites.large_star),

		};
		
		for(int i = 0; i < stars.length; i++){
			
			int dark = r.nextInt(2)+1;
			int red =   (255 - r.nextInt(220)) / dark;
			int green = (255 - r.nextInt(120)) / dark;
			int blue =  (255 - r.nextInt(70))  / dark;
			
			int x = (r.nextInt(worldSize)*density);
			int y = (r.nextInt(worldSize)*density);
			
			z = depths[i];
			starMaxSize = 2;
			if(r.nextInt(10)==5) starMaxSize = 10;
			//---
						
			stars[i] = new Star(
					x+(650-worldSize/2),							//x
					y+(400-worldSize/2),							//y
					z,							//z
					r.nextInt(starMaxSize)+1,	//size
					new Sprite(ss[r.nextInt(ss.length-1)])				//star sprite
			);
			
		}
		
		stars[0].x = 0;
		stars[0].y = 0;
	}

	private void createDepth(int depth){
		depths = new Float[stars.length];
		for(int i = 0; i < depths.length; i++){
			depths[i] = (r.nextFloat()*r.nextInt(depth))+3;
		}
		
		Arrays.sort(depths);
	}
	
	public void dispose(){
		for(Star s : stars){
			s.sprite.getTexture().dispose();
		}
	}
	
}
