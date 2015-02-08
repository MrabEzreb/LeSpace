package xtre.globals.game_interface.hud;

import com.badlogic.gdx.Gdx;

public abstract class GameInputs {
	
	public static boolean keyPressed(int key) {
		return Gdx.input.isKeyJustPressed(key);
	}
	
	private static boolean mousePressed;
	public static final boolean mousePressed(int button){
		boolean b = false;
		boolean on = Gdx.input.isButtonPressed(button);
		
		if(on&&!mousePressed){
			b=true;
			mousePressed=true;
		}
		
		if(!on){
			mousePressed = false;
		}
		return b;
	}
	
	public static final boolean mouseHolding(int button){
		return Gdx.input.isButtonPressed(button);
	}
	
	public static final boolean keyHolding(int key){
		return Gdx.input.isKeyPressed(key);
	}
}
