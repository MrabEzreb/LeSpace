package xtre.game.space_world;

import java.util.Arrays;
import java.util.Random;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.gui.GlobalsGUI;
import xtre.globals.game_interface.hud.GlobalsHUD;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space.SpritesSpaceBackgroundStar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stars {
	
	public final int length;

	private final int worldSize, density, depth;
	
	private GameInterfaceManager gim;
	
	private Random r = new Random();
	
	private Star[] stars;
	private Float[] depths;
	
	private float camX, camY, mouseX, mouseY;
//	private boolean starHighlightDisplaying = false, starOptionsDisplaying = false;
	private boolean justPressedLeftMouseButton = false;
	
//	public int selectedStar = -1, hoveredStar = -1;
	
	public Stars(GameInterfaceManager gim, int worldSize, int density, int depth){
		this.gim = gim;
		this.worldSize = worldSize;
		this.density = density;
		this.depth = depth;
		
		stars = new Star[(worldSize)];
		createDepth(depth);
		createStars();
		length = stars.length;
	}
	
	public void moveStars(float x, float y){
		
	}
	
//	public Star getSelectedStar(){
//		if(selectedStar!=-1)
//			return stars[selectedStar];
//		else
//			return null;
//	}
	
	public void update(float camX, float camY, float mouseX, float mouseY, boolean justPressedLeftMouseButton){
		this.camX = camX;
		this.camY = camY;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.justPressedLeftMouseButton = justPressedLeftMouseButton;
		
//		starHighlightDisplaying = gim.hUDDisplaying(GlobalsHUD.STAR_HIGHLIGHT);
//		starOptionsDisplaying = gim.gUIDisplaying(GlobalsGUI.SPACE_INSPECT_STAR_OPTION);

		updateStars();
	}
	public void render(SpriteBatch batch, float camX, float camY){
		for(int i = stars.length-1; i > -1; i--){
			if(stars[i].sprite.getX() < GlobalScreen.WIDTH && stars[i].sprite.getX() > 0 && stars[i].sprite.getY() < GlobalScreen.HEIGHT && stars[i].sprite.getY() > 0)
					stars[i].sprite.draw(batch);
		}
	}
	
	private void updateStars(){
		
//		for(int i = 0; i < stars.length; i++){
//			stars[i].updatePosition(camX, camY);	
//			updateHighlightedStar(i);
//			starOptionsInteraction(i);
//		}
		//if(selectedStar!=-1 && stars[selectedStar].clicked(mouseX, mouseY, mouseButtonLeft)) selectedStar = -1;
		
		for(int i = 0; i < stars.length; i++){
			stars[i].updatePosition(camX, camY);
			if(stars[i].getX() > 0 && stars[i].getX() < GlobalScreen.WIDTH && stars[i].getY() > 0 && stars[i].getY() < GlobalScreen.HEIGHT) 
				stars[i].onScreen = true;
			else
				stars[i].onScreen = false;
		}
		
//		updateHighlightedStar();
//		
//		if(justPressedLeftMouseButton)
//		//select a star
//		for(int i = 0; i < stars.length; i++){
//		boolean b = stars[i].clicked(mouseX, mouseY, justPressedLeftMouseButton);
//			
//			if(b && !gim.gUIDisplaying(GlobalsGUI.SPACE_INSPECT_STAR_OPTION)){
//				selectedStar = i;
//			}
//			
//			if(!b && selectedStar == i) selectedStar =-1;
//			
//		}
		
		///
	}
	
//	private void starOptionsInteraction(int i){
//		
//	}
//	
//	private void updateHighlightedStar() {
//		for (int i = 0; i < stars.length; i++) {
//			boolean hovering = stars[i].hovered(mouseX, mouseY);
//
//			if (hovering) {
//				hoveredStar = i;
//			} else if (hoveredStar == i) {
//				hoveredStar = -1;
//			}
//		}
//	}

	private void createStars(){
		float z = 0;
		int starMaxSize=0;
		SpriteEntity se = new SpriteEntity();
		Sprite[] ss = new Sprite[]{
				se.getSprite(SpritesSpaceBackgroundStar.alpha_star),
				se.getSprite(SpritesSpaceBackgroundStar.small_star),
				se.getSprite(SpritesSpaceBackgroundStar.medium_star),
				se.getSprite(SpritesSpaceBackgroundStar.large_star),

		};
		
		for(int i = 0; i < stars.length; i++){
			
//			int dark = r.nextInt(2)+1;
//			int red =   (255 - r.nextInt(220)) / dark;
//			int green = (255 - r.nextInt(120)) / dark;
//			int blue =  (255 - r.nextInt(70))  / dark;
			
			float dark = r.nextFloat();
			if(dark > .3f)dark-=.3f;
			
			float red = r.nextFloat();
			float green = r.nextFloat();
			float blue = r.nextFloat();
			
			int x = (r.nextInt(worldSize));
			int y = (r.nextInt(worldSize));
			Color c = new Color();
			z = depths[i];
			starMaxSize = 2;
			if(r.nextInt(10)==5) starMaxSize = 10;
			//---
			
			Sprite s = new Sprite(ss[r.nextInt(ss.length-1)]);
			s.setColor(red, green, blue, .5f);
			stars[i] = new Star(
					x+(650-worldSize/2),							//x
					y+(400-worldSize/2),							//y
					z,							//z
					r.nextInt(starMaxSize)+1,	//size
					s                           //star sprite
			);
		}
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

	public Star getStar(int i) {
		return stars[i];
	}

//	public void unselectStar(int i){
//		getStar(selectedStar).selected = false;
//		selectedStar = -1;
//	}
//	
}
