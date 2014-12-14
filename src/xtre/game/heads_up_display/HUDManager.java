package xtre.game.heads_up_display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public HeadsUpDisplay getHUD(String name){
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).name.equals(name))
				return huds.get(i);
		}
		
		return null;
	}

	Random r = new Random();
	
	public String requestBox(String name, float x, float y, int width, int height) {
		if(name.equals("")) return null;
		String returnedName = name;
		System.out.println("hud request box");
	
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).name.equals(name)){
				System.out.println("Error requesting box HUD [\""+name+"\"] already exists.");
				String s = "abcdefghijklmnopqrstuvwxyz";
				for(int l = 0; l < 26; l++){
					name += s.charAt(r.nextInt(25));
				}
			}
		}
		
		huds.add(new BoxHUD(name, (int)x,(int) y, width, height));
		
		System.out.println(returnedName + "  -]");
		return returnedName;
	}
	
	public void closeHud(String hud_name){
		for(int i = 0; i < huds.size(); i++){
			if(huds.get(i).name.equals(hud_name)){
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

	public boolean hudNameMatches(int i, String name) {
		boolean match = false;
		
		if(name.equals(huds.get(i).name)){
			match = true;
			System.out.println("name matches in test for hudNameMatches");
		}
		
		return match;
	}
}
