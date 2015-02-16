package xtre.game.physics_objects.player;


import xtre.game.GameLoop;
import xtre.globals.GlobalScreen;

public class SpaceObject {
	
	protected float x;
	protected float y;
	
	protected float dx;
	protected float dy;
	
	protected float radians;
	protected float speed;
	protected float rotationSpeed;
	
	protected int width;
	protected int height;
	
	protected float[] shapex;
	protected float[] shapey;
	
	protected void wrap() {
		if(x < 0) x = GlobalScreen.GAME_WIDTH;
		if(x > GlobalScreen.GAME_WIDTH) x = 0;
		if(y < 0) y = GlobalScreen.GAME_HEIGHT;
		if(y > GlobalScreen.GAME_HEIGHT) y = 0; 
	}
	
}


















