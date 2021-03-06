package xtre.game.world.space_world;

import java.util.ArrayList;
import java.util.List;

import xtre.game.graphics.space.background.Stars;
import xtre.game.heads_up_display.HUDManager;
import xtre.game.physics_objects.player.SpaceRock;
import xtre.game.player.Player;
import xtre.globals.ScreenGlobals;
import xtre.globals.hud.HUDGlobals;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.SpritesSpaceGame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class SpaceWorld {

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
	
	public SpaceWorld(HUDManager hud){
		this.hud = hud;
		world = new World(new Vector2(0, 0f), true);
		
		stars = new Stars(hud, 15000, 1, 10);
		
		//Player
		Sprite playerSprite = new Sprite(se.getSprite(SpritesSpaceGame.player_ship));
		player = new Player[1];
		
		float playerwp = (0), playerhp = (0);
		for(int i = 0; i < player.length; i++){
			player[i] = new Player(playerwp, playerhp, playerSprite, world);
		}
		//

		//Rocks
		rock = new SpaceRock[4];		
		rock[0] = new SpaceRock((ScreenGlobals.MPP(300)), (ScreenGlobals.MPP(-300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[1] = new SpaceRock((ScreenGlobals.MPP(300)), (ScreenGlobals.MPP(300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[2] = new SpaceRock((ScreenGlobals.MPP(-300)), (ScreenGlobals.MPP(-300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[3] = new SpaceRock((ScreenGlobals.MPP(-300)), (ScreenGlobals.MPP(300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);

//		 Bounds
		
//		setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		setBounds((16f * 1f)/4, (9f * 2f)/4, 2f/4f, 2f/4f);
//		setBounds(5, 5, 5, 5);
	

	}
	
	List<String> starOptions = new ArrayList<>();
	
	public void update(float camX, float camY, float mouseX, float mouseY, boolean justPressedLeftMouseButton) {
		this.camX = camX;
		this.camY = camY;
		this.justPressedLeftMouseButton = justPressedLeftMouseButton;
		
		stars.update(camX, camY, mouseX, mouseY, justPressedLeftMouseButton);
		
		for(int i = 0; i < player.length; i++)
			player[0].update(camX, camY, mouseX, mouseY);
		
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
		if(!hud.hudDisplaying(HUDGlobals.STAR_OPTIONS) && stars.hoveredStar != -1 && !hud.hudDisplaying(HUDGlobals.STAR_HIGHLIGHT)){
			Sprite highlight = stars.getStar(stars.hoveredStar).sprite;
			hud.requestStarHighlight(HUDGlobals.STAR_HIGHLIGHT, (highlight.getX()-(48/2))+(highlight.getWidth()/2), (highlight.getY()-(48/2))+(highlight.getWidth()/2), 1, 1);
		}else if(stars.hoveredStar == -1){
			hud.closeHud(HUDGlobals.STAR_HIGHLIGHT);
		}
	}
	
	private void starOptions() {
		if(justPressedLeftMouseButton) {
			if (stars.selectedStar != -1 && !hud.hudDisplaying(HUDGlobals.STAR_OPTIONS)) {
				hud.requestBox(HUDGlobals.STAR_OPTIONS,	stars.getSelectedStar().sprite.getX(), stars.getSelectedStar().sprite.getY(), 16, 8);
				player[0].slowToStop(true);
				return;
			}

			if (hud.hudDisplaying(HUDGlobals.STAR_OPTIONS) && hud.getHUD(HUDGlobals.STAR_OPTIONS).status()) {
				hud.closeHud(HUDGlobals.STAR_OPTIONS);
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
