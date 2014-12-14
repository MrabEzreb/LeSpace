package xtre.game.heads_up_display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xtre.game.heads_up_display.types.HighlightHUD;
import xtre.game.heads_up_display.utils.BoxHUD;
import xtre.game.heads_up_display.utils.HeadsUpDisplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class HUDManager {

	private List<HeadsUpDisplay> huds = new ArrayList<>();
	
	public HUDManager(){
		
	}
	
	public void update(){
		
		for(HeadsUpDisplay hud:huds){
			if(hud!=null){
				hud.update();
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
		
		return null;
	}

	Random r = new Random();
	
	public int requestBox(int id, float x, float y, int width, int height) {
		if(id==-1) return -1;
		int returnedID = id;
		System.out.println("hud request box");
	
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).id == returnedID){
				System.out.println("Error requesting box HUD [\""+returnedID+"\"] already exists.");
				String s = "abcdefghijklmnopqrstuvwxyz";
				for(int l = 0; l < 26; l++){
					returnedID += s.charAt(r.nextInt(25));
				}
			}
		}
		
		huds.add(new BoxHUD(returnedID, (int)x,(int) y, width, height));
		
		System.out.println(returnedID + "  -]");
		return returnedID;
	}
	
	public void closeHud(int hud_id){
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).id == hud_id){
				huds.get(i).dispose();
				huds.remove(i);
				System.out.println("removing hud from list");
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

	public int requestStarHighlight(int id, float x, float y, int width, int height) {
		if(id == -1) return -1;
		int returnedID = id;
		System.out.println("hud request highlight");
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).id == returnedID){
				System.out.println("Error requesting box HUD [\""+returnedID+"\"] already exists.");
				String s = "abcdefghijklmnopqrstuvwxyz";
				for(int l = 0; l < 26; l++){
					returnedID += r.nextInt(25);
				}
			}
		}
		
		huds.add(new HighlightHUD(returnedID, (int)x,(int) y, width, height));
		
		System.out.println(returnedID + "  -]");
		return returnedID;
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
