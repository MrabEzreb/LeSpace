package xtre.globals.game_interface.hud;

import com.badlogic.gdx.Gdx;

public abstract class GameInputs {
	
	private static boolean leftButton = false;
	
	public static final boolean mouseJustClicked(int button){
		boolean b = false;
		boolean on = Gdx.input.isButtonPressed(button);
		
		if(on&&!leftButton){
			b=true;
			leftButton=true;
		}
		
		if(!on){
			leftButton = false;
		}
		return b;
	}
	
	public static final boolean mouseHolding(int button){
		return Gdx.input.isButtonPressed(button);
	}
}
