package xtre.game.heads_up_display.types;

import xtre.game.heads_up_display.utils.BoxHUD;
import xtre.game.heads_up_display.utils.HeadsUpDisplay;

public class SpaceHuds {
	public HeadsUpDisplay[] spaceHuds;

	
	public HeadsUpDisplay[] loadSpaceHuds(){
		spaceHuds = new HeadsUpDisplay[]{
				new BoxHUD(),
		};
		
		return spaceHuds;
	}
	
}
