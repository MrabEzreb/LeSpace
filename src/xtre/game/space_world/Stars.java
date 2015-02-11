package xtre.game.space_world;

import java.util.Arrays;
import java.util.Random;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.globals.GlobalScreen;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space.SpriteSpacePlanets;
import xtre.graphics.sprites.sprite_types.space.SpritesSpaceBackgroundStar;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stars {
	
	public final int length;

	private final int worldSize, depth;
	
	private GameInterfaceManager gim;
	
	private Random r = new Random();
	
	private Star[] stars;
	private Float[] depths;
//	private List<Star> warpStars = new ArrayList<>();
	
	private float camX, camY;
//	private boolean starHighlightDisplaying = false, starOptionsDisplaying = false;
	
	private boolean warping;
	
//	public int selectedStar = -1, hoveredStar = -1;
	
	public Stars(int worldSize, int density, int depth){
		this.worldSize = worldSize;
		this.depth = depth;
		
		stars = new Star[(worldSize)];
		createDepth(depth);
		createStars();
		length = stars.length;
	}
	public Stars(GameInterfaceManager gim, int worldSize, int density, int depth){
		this.gim = gim;
		this.worldSize = worldSize/density;
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
	
	public void update(float camX, float camY){
		this.camX = camX;
		this.camY = camY;
		
//		starHighlightDisplaying = gim.hUDDisplaying(GlobalsHUD.STAR_HIGHLIGHT);
//		starOptionsDisplaying = gim.gUIDisplaying(GlobalsGUI.SPACE_INSPECT_STAR_OPTION);

		updateStars();
	}
	public void render(SpriteBatch batch, float camX, float camY){
//		if(!warping)
			for(int i = stars.length-1; i > -1; i--){
				if(stars[i].sprite.getX() < GlobalScreen.GAME_WIDTH && stars[i].sprite.getX() > 0 && stars[i].sprite.getY() < GlobalScreen.GAME_HEIGHT && stars[i].sprite.getY() > 0)
					stars[i].sprite.draw(batch);
			}
//		else
//			for(int i = 0; i < warpStars.size(); i++){
//				warpStars.get(i).sprite.draw(batch);
//			}
	}
	
	private void updateStars(){
//		for(int i = 0; i < stars.length; i++){
//			stars[i].updatePosition(camX, camY);	
//			updateHighlightedStar(i);
//			starOptionsInteraction(i);
//		}
		//if(selectedStar!=-1 && stars[selectedStar].clicked(mouseX, mouseY, mouseButtonLeft)) selectedStar = -1;
		
//		if(!warping)
		for(int i = 0; i < stars.length; i++){
			stars[i].updatePosition(camX, camY);
			if(stars[i].getX() > 0 && stars[i].getX() < GlobalScreen.GAME_WIDTH && stars[i].getY() > 0 && stars[i].getY() < GlobalScreen.GAME_HEIGHT){ 
				stars[i].onScreen = true;
			}
			else{
				stars[i].onScreen = false;
			}
		}

//		if(!warping)
//		if(GameInputs.keyHolding(Keys.SHIFT_LEFT)){
//			for(int i = 0; i < stars.length; i++){
//				if(warping)
//					if(stars[i].onScreen)
//						warpStars.add(stars[i]);
//			}
//			warping = true;
//		}
//		if(warping){
//			for(int i = 0; i < warpStars.size(); i++){
//				warpStars.get(i).updatePosition(100, 100);
//			}
//		}

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
		float starMaxSize=0;
		Sprite[] ss = new Sprite[]{
				GameSprite.getSprite(SpritesSpaceBackgroundStar.alpha_star),
				GameSprite.getSprite(SpritesSpaceBackgroundStar.small_star),
				GameSprite.getSprite(SpritesSpaceBackgroundStar.medium_star),
				GameSprite.getSprite(SpritesSpaceBackgroundStar.large_star),
		};
		
		for(int i = 1; i < stars.length; i++){	
//			float dark = r.nextFloat();
//			if(dark > .3f)dark-=.3f;
//			float red = 1 - r.nextFloat()/3;
//			float green = 1 - r.nextFloat()/3;
//			float blue = 1 - r.nextFloat()/3;
//			
			int x = (r.nextInt(worldSize));
			int y = (r.nextInt(worldSize));
			z = depths[i];
			starMaxSize = r.nextFloat();
		//	if(r.nextInt(10)==5) starMaxSize = 10;
			//---
			
			float size = r.nextFloat()*depth;
			Sprite s = new Sprite(ss[r.nextInt(ss.length-1)]);
			
			s.setColor(1, 1, 1, 1.5f);
			s.setSize(size+(size/2.5f), size+(size/2.5f));
			stars[i] = new Star(
					(x+(650-worldSize/2)),		//x
					(y+(400-worldSize/2)),    	//y
					z,							//z
					starMaxSize+1,	//size
					s                           //star sprite
			);
		}
		
		stars[0] = new Star(1000, 1000, 1, 0, GameSprite.getSprite(SpriteSpacePlanets.earth_like));
	}

	private void createDepth(int depth){
		depths = new Float[stars.length];
		for(int i = 0; i < depths.length; i++){
			depths[i] = (float) (r.nextFloat()*depth);
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
