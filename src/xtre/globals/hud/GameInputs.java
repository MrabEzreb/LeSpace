package xtre.globals.hud;

import com.badlogic.gdx.Gdx;

public class GameInputs {
	private boolean leftButton = false;
	public boolean mouseJustClicked(int button){
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

	
	

	
}
