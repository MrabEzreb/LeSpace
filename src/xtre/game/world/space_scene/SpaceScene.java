package xtre.game.world.space_scene;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.heads_up_display.hud_interfaces.BoxHUD;
import xtre.game.game_gui.heads_up_display.utils.HUDManager;
import xtre.game.in_space.graphics.space.background.Stars;
import xtre.game.in_space.physics_objects.player.SpaceRock;
import xtre.game.in_space.player.Player;
import xtre.globals.ScreenGlobals;
import xtre.globals.hud.HUDGlobals;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.SpritesSpaceGame;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesHeadsUpDisplay;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class SpaceScene {

	public World world;
	
	private HUDManager hud;
		
	SpriteEntity se = new SpriteEntity();
	
	public Player[] player;
	public SpaceRock[] rock;
	public Stars stars;
	
	private float camX=0, camY=0;
	private boolean justPressedLeftMouseButton = false;

	public final float timeStep = 1f/30f;
	public final int velocityIterations = 80, positionIterations = 30;
	
	public SpaceScene(HUDManager hud){
		this.hud = hud;
		world = new World(new Vector2(0, 0f), true);
		
		stars = new Stars(hud, 15000, 1, 10);
		
		Sprite playerSprite = new Sprite(se.getSprite(SpritesSpaceGame.player_ship));
		player = new Player[1];
		
		float playerwp = (0), playerhp = (0);
		for(int i = 0; i < player.length; i++){
			player[i] = new Player(playerwp, playerhp, playerSprite, world, hud);
		}

		rock = new SpaceRock[4];		
		rock[0] = new SpaceRock((ScreenGlobals.MPP(300)), (ScreenGlobals.MPP(-300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[1] = new SpaceRock((ScreenGlobals.MPP(300)), (ScreenGlobals.MPP(300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[2] = new SpaceRock((ScreenGlobals.MPP(-300)), (ScreenGlobals.MPP(-300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[3] = new SpaceRock((ScreenGlobals.MPP(-300)), (ScreenGlobals.MPP(300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);

	}

	List<String> starOptions = new ArrayList<>();
	
	public void update(float camX, float camY, float mouseX, float mouseY, boolean justPressedLeftMouseButton) {
		this.camX = camX;
		this.camY = camY;
		this.justPressedLeftMouseButton = justPressedLeftMouseButton;
		
		stars.update(camX, camY, mouseX, mouseY, justPressedLeftMouseButton);
		
		for(int i = 0; i < player.length; i++)
			player[0].update(camX, camY, mouseX, mouseY, justPressedLeftMouseButton);
		
		for(int i = 0; i < rock.length; i++){
			rock[i].update(camX, camY);
		}
		
		starHighlight();
		starOptions();

		world.step(timeStep, velocityIterations, positionIterations);
	}
	
	public void render(SpriteBatch batch){
		stars.render(batch, camX, camY);
		
		for(int i = 0; i < player.length; i++)
			player[i].render(batch);
		
		for(int i = 0; i < rock.length; i++){
			rock[i].render(batch);
		}
	}
	
	private void starHighlight(){
		if(!hud.hudDisplaying(HUDGlobals.SPACE_INSPECT_STAR) && stars.hoveredStar != -1 && !hud.hudDisplaying(HUDGlobals.STAR_HIGHLIGHT)){
			Sprite highlight = stars.getStar(stars.hoveredStar).sprite;
			hud.requestStarHighlight(HUDGlobals.STAR_HIGHLIGHT, (highlight.getX()-(48/2))+(highlight.getWidth()/2), (highlight.getY()-(48/2))+(highlight.getWidth()/2), 1, 1);
		}else if(hud.hudDisplaying(HUDGlobals.STAR_HIGHLIGHT) && stars.hoveredStar == -1){
			hud.closeHud(HUDGlobals.STAR_HIGHLIGHT);
		}
	}
	
	private void starOptions() {
		if(justPressedLeftMouseButton) {
			if (stars.selectedStar != -1 && !hud.hudDisplaying(HUDGlobals.SPACE_INSPECT_STAR)) {
				Sprite[] boxHUDGraphics = new Sprite[]{
						
						se.getSprite(SpritesHeadsUpDisplay.paneling_tm),	//[0]
						se.getSprite(SpritesHeadsUpDisplay.paneling_bm),	//[1] 
						se.getSprite(SpritesHeadsUpDisplay.paneling_lm),	//[2] 
						se.getSprite(SpritesHeadsUpDisplay.paneling_mr),	//[3]
						
						se.getSprite(SpritesHeadsUpDisplay.paneling_mm),	//[4] 
					
						se.getSprite(SpritesHeadsUpDisplay.paneling_tl),	//[5] 
						se.getSprite(SpritesHeadsUpDisplay.paneling_tr),	//[6] 
						se.getSprite(SpritesHeadsUpDisplay.paneling_bl),	//[7] 
						se.getSprite(SpritesHeadsUpDisplay.paneling_br),	//[8]
				};
				
				hud.requestBox(HUDGlobals.SPACE_INSPECT_STAR,	stars.getSelectedStar().sprite.getX(), stars.getSelectedStar().sprite.getY(), 16, 8, boxHUDGraphics);
				for(int i = 0; i < 3; i++)
					((BoxHUD)hud.getHUD(HUDGlobals.SPACE_INSPECT_STAR)).createDropDownMenu(0, (i*40)+6, se.getSprite(SpritesSpaceHudMenu.menu_bar));
				
				player[0].slowToStop(true);
				return;
			}

			if (hud.hudDisplaying(HUDGlobals.SPACE_INSPECT_STAR) && hud.getHUD(HUDGlobals.SPACE_INSPECT_STAR).status()) {
				hud.closeHud(HUDGlobals.SPACE_INSPECT_STAR);
				player[0].slowToStop(false);
				return;
			}
		}
	}

	private void setBounds(float x, float y, float w, float h){
		x=ScreenGlobals.MPP(x);
		y=ScreenGlobals.MPP(y);
		w=ScreenGlobals.MPP(w);
		h=ScreenGlobals.MPP(h);
		BodyDef areaDef = new BodyDef();
		areaDef.type = BodyType.StaticBody;
		areaDef.position.set(0, 0);
		
		ChainShape shape = new ChainShape();
		//System.out.println("--------\nshape\nscale["+scale+"], x["+x+"], y["+y+"]\n--------");
	
		shape.createChain(new Vector2[]{
				
//				new Vector2(x, y),  //Bottom left
//				new Vector2(x+w, y),	  //Bottom right
//				new Vector2(x+w, y+h),	  //Top right
//				new Vector2(x, y+h),	  //Top left
//				new Vector2(x, y),  //Bottom left
//				
				new Vector2(x, y), 
				new Vector2(x+w, y), 
				new Vector2(x+w, y+h),
				new Vector2(x, y+h),
				new Vector2(x, y),
		});
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		fixtureDef.restitution = 0.1f;
		
		world.createBody(areaDef).createFixture(fixtureDef);
		shape.dispose();
	}
	
	public void dispose(){
		world.dispose();
		player[0].dispose();
	}
	
}
