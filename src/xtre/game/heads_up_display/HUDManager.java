package xtre.game.heads_up_display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xtre.game.heads_up_display.hud_interfaces.BoxHUD;
import xtre.game.heads_up_display.hud_interfaces.HighlightHUD;
import xtre.game.heads_up_display.utils.HeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class HUDManager {

	private List<HeadsUpDisplay> huds = new ArrayList<>();
	
	public HUDManager(){

	}
	
	public void update(float mouseX, float mouseY, boolean justPressedLeftMouseButton){
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i)!=null){
				huds.get(i).update(mouseX, mouseY, justPressedLeftMouseButton);
				if(!huds.get(i).running){
					huds.remove(i);
				}
			}
		}
	}
	
	public void render(SpriteBatch batch){
		for(HeadsUpDisplay hud:huds){
			if(hud!=null){
				hud.render(batch);
			}		
		}
	}
	
	public HeadsUpDisplay getHUD(int id){
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).id == id)
				return huds.get(i);
		}
		System.out.println("HUDManager.getHUD Error: couldn't get the requested hud " + id);
		return null;
	}

	Random r = new Random();
	
	public int requestBox(int id, float x, float y, int width, int height) {
		if(id==-1) return -1;
		int returnedID = id;
	
		huds.add(new BoxHUD(returnedID, (int)x,(int) y, width, height));
		
		System.out.println("requestBox [" + returnedID + "]");
		return returnedID;
	}
	
	public int requestStarHighlight(int id, float x, float y, int width, int height) {
		if(id == -1) return -1;
		int returnedID = id;

		huds.add(new HighlightHUD(returnedID, (int)x,(int) y, width, height));
		return returnedID;
	}
	
	public void closeHud(int hud_id){
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).id == hud_id){
				huds.get(i).dispose();
				huds.remove(i);
			}
		}
	}
	
	public void dispose(){
		for(HeadsUpDisplay hud:huds){
			hud.dispose();
		}
	}

	public int getHUDSize() {
		return huds.size();
	}

	public boolean hudNamseMatches(int i, int id) {
		boolean match = false;
		
		if(id == huds.get(i).id){
			match = true;
			System.out.println("name matches in test for hudNameMatches");
		}
		
		return match;
	}

	public boolean hudDisplaying(int id){
		boolean hudAlreadyDisplaying = false;
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).id == id){
				hudAlreadyDisplaying = true;
			}
		}
		return hudAlreadyDisplaying;
	}
}
