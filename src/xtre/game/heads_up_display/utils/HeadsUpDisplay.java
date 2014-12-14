package xtre.game.heads_up_display.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class HeadsUpDisplay {
	public String name = "";
	
	public Sprite[] paneling;
	public Sprite button;
		
	public void update(){
		if(name == ""){
			this.closeHUD();
		}
		
	}
	
	public void render(SpriteBatch batch){
		for(Sprite s : paneling){
			if(s!=null)
			s.draw(batch);
		}
		
		//button.draw(batch);
	}
	
	public void closeHUD(){
	}

	public void requestHud() {
	}
	
	
	public void dispose(){
		for(Sprite s : paneling){
			s.getTexture().dispose();
		}
	}
}
