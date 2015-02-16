package xtre.globals;

public class GlobalScreen {
	private final static float PPM = 32f;
	public static float GAME_WIDTH = 1300, GAME_HEIGHT = 800;
	public static int LAUNCHER_WIDTH = 1152, LAUNCHER_HEIGHT = 648;

	public static float PPM(float n){
		return n*PPM;
	}
	
	public static float MPP(float n){
		return n/PPM;
	}
}
