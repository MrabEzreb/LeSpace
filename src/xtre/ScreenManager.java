package xtre;

import com.badlogic.gdx.Screen;

public class ScreenManager {
	
	Screen screen;
	
	public ScreenManager(Screen screen){
		this.screen = screen;	
	}
	
	public Screen getScreen(){
		return screen;
	}
}
