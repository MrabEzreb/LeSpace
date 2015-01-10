package xtre.game.space_world;

import java.util.ArrayList;
import java.util.List;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics.UIGraphics;
import xtre.game.game_gui.heads_up_display.hud_parts.BackPannel;
import xtre.game.game_gui.heads_up_display.hud_parts.HUDHighlight;
import xtre.game.physics_objects.player.SpaceRock;
import xtre.game.player.Player;
import xtre.globals.GlobalScreen;
import xtre.globals.game_interface.gui.GlobalsGUI;
import xtre.globals.game_interface.hud.GlobalsHUD;
import xtre.graphics.sprites.SpriteEntity;
import xtre.graphics.sprites.sprite_types.space.SpritesSpaceGame;
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
	
	private GameInterfaceManager gim;
		
	SpriteEntity se = new SpriteEntity();
	
	public Player[] player;
	public SpaceRock[] rock;
	public Stars stars;
	
	private float camX=0, camY=0;
	private boolean justPressedLeftMouseButton = false;

	public final float timeStep = 1f/30f;
	public final int velocityIterations = 80, positionIterations = 30;
	
	public SpaceScene(GameInterfaceManager gim){
		this.gim = gim;
		world = new World(new Vector2(0, 0f), true);
		
		stars = new Stars(gim, 15000, 1, 10);
		
		Sprite playerSprite = new Sprite(se.getSprite(SpritesSpaceGame.player_ship));
		player = new Player[1];
		
		float playerwp = (0), playerhp = (0);
		for(int i = 0; i < player.length; i++){
			player[i] = new Player(playerwp, playerhp, playerSprite, world, gim);
		}

		rock = new SpaceRock[4];		
		rock[0] = new SpaceRock((GlobalScreen.MPP(300)), (GlobalScreen.MPP(-300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[1] = new SpaceRock((GlobalScreen.MPP(300)), (GlobalScreen.MPP(300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[2] = new SpaceRock((GlobalScreen.MPP(-300)), (GlobalScreen.MPP(-300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);
		rock[3] = new SpaceRock((GlobalScreen.MPP(-300)), (GlobalScreen.MPP(300)), new Sprite(se.getSprite(SpritesSpaceGame.space_rock)), world);

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
		
		for(int i = 0; i < rock.length; i++){
			rock[i].render(batch);
		}

		for(int i = 0; i < player.length; i++)
			player[i].render(batch);
	}
	
	private void starHighlight(){
		if(!gim.gUIDisplaying(GlobalsGUI.SPACE_INSPECT_STAR_OPTION) && stars.hoveredStar != -1 && !gim.hUDDisplaying(GlobalsHUD.STAR_HIGHLIGHT)){
			Sprite highlight = stars.getStar(stars.hoveredStar).sprite;
			new HUDHighlight(gim, GlobalsHUD.STAR_HIGHLIGHT, (int)((highlight.getX()-(48/2))+(highlight.getWidth()/2)), (int)((highlight.getY()-(48/2))+(highlight.getWidth()/2)), 1, 1);
		}else if(gim.hUDDisplaying(GlobalsHUD.STAR_HIGHLIGHT) && stars.hoveredStar == -1){
			gim.closeHUD(GlobalsHUD.STAR_HIGHLIGHT);
		}
	}
	
	private void starOptions() {
		if(justPressedLeftMouseButton) {
			if (stars.selectedStar != -1 && !gim.gUIDisplaying(GlobalsGUI.SPACE_INSPECT_STAR_OPTION)) {
				
				new BackPannel(gim, (int)(stars.getSelectedStar().sprite.getX()), (int)(stars.getSelectedStar().sprite.getY()), 16, 8, UIGraphics.getBoxHUDGraphics(se));
//	TODO			for(int i = 0; i < 3; i++)
//					((HUDBox)gim.getGUI(GlobalsGUI.SPACE_INSPECT_STAR)).createDropDownMenu(20, (i*40)+6, se.getSprite(SpritesSpaceHudMenu.menu_bar));
				
				player[0].ship.slowToStop(true);
				return;
			}

			if (gim.gUIDisplaying(GlobalsGUI.SPACE_INSPECT_STAR_OPTION)) {
				gim.closeGUI(GlobalsGUI.SPACE_INSPECT_STAR_OPTION);
				player[0].ship.slowToStop(false);
				return;
			}
		}
	}

	private void setBounds(float x, float y, float w, float h){
		x=GlobalScreen.MPP(x);
		y=GlobalScreen.MPP(y);
		w=GlobalScreen.MPP(w);
		h=GlobalScreen.MPP(h);
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
		player[0].ship.dispose();
	}
	
}
