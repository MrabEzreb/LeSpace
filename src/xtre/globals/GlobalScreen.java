package xtre.globals;

public class GlobalScreen {
	private final static float PPM = 32f;
	public static final float WIDTH = 1300, HEIGHT = 800;
	
	public static float PPM(float n){
		return n*PPM;
	}
	
	public static float MPP(float n){
		return n/PPM;
	}
}
