package xtre.game.in_space.physics_objects.player;


import xtre.game.GameLoop;
import xtre.globals.ScreenGlobals;

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
		if(x < 0) x = ScreenGlobals.WIDTH;
		if(x > ScreenGlobals.WIDTH) x = 0;
		if(y < 0) y = ScreenGlobals.HEIGHT;
		if(y > ScreenGlobals.HEIGHT) y = 0; 
	}
	
}


















