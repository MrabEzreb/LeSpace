package xtre.game.heads_up_display.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class HeadsUpDisplay {
	public int id = -1;
	
	public Sprite[] paneling;
	public Sprite button;
	public Sprite background;

	public void update(){
		if(id == -1){
			closeHUD();
		}
	}
	
	public void render(SpriteBatch batch){
		if(paneling!=null)
		for(Sprite s : paneling){
			if(s!=null)
			s.draw(batch);
		}
		
		if(background!=null){
			background.draw(batch);
		}
		
		//button.draw(batch);
	}
	
	public void closeHUD(){
		dispose();
	}

	public void requestHud() {
	}
	
	
	public void dispose(){
		if(paneling!=null)
		for(Sprite s : paneling){
			s.getTexture().dispose();
		}
	}
}
