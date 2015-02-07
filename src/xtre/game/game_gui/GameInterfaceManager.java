package xtre.game.game_gui;

import java.util.ArrayList;
import java.util.List;

import xtre.game.scene_manager.GameManager;
import xtre.game.scene_manager.GameScene;
import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameInterfaceManager {

	private GameManager gm;
	
	private List<GameInterface> gi = new ArrayList<>();

	public GameInterfaceManager(GameManager gm){
		this.gm = gm;
	}
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		for(int i = 0; i < gi.size(); i++){
			gi.get(i).update(mouseX, mouseY, mouseLeftPress);
		}
	}
	
	public void render(SpriteBatch batch){
		for(GameInterface gi:gi){
			gi.render(batch);
		}
	}

	public void setScene(GameScene scene) {
		gm.setScene(scene);
	}
	
	public void closeFrames() {
		for(GameInterface gi:gi){
			gi.dispose();
		}
	}
	
	public GameInterface getHUD(int id){
		System.out.println("Getting HUD " + id);
		for(int i = 0; i < gi.size(); i++){
			if(gi.get(i).GI_ID == id && gi.get(i).TYPE == GlobalsInterface.HUD_TYPE)
				return gi.get(i);
		}
		System.out.println("HUDManager.getHUD Error: couldn't get the requested hud " + id);
		return null;
	}
	
	public GameInterface getGUI(int id){
		System.out.println("Getting GUI " + id);
		for(int i = 0; i < gi.size(); i++){
			if(gi.get(i).GI_ID == id && gi.get(i).TYPE == GlobalsInterface.GUI_TYPE)
				return gi.get(i);
		}
		System.out.println("HUDManager.getHUD Error: couldn't get the requested hud " + id);
		return null;
	}

	/**
	 * Requests a new BoxHUD.
 	 * Sprites must be in an order of {@code TM, BM, LM, RM, M, TL, TR, BR, BL}
	 * 
	 * @param int globalID
	 * @param int boxX
	 * @param int boxY
	 * @param int boxWidth
	 * @param int boxHeight
	 * @param Sprite[] panelGraphics 
	 * @param int amountOfMenus 
	 */
//	public int requestBox(int id, float x, float y, int width, int height, Sprite[] panelGraphics) {
//		if(id==-1) return -1;
//		int returnedID = id;
//	
//		gi.add(new HUDBox(this, returnedID, (int)x,(int) y, width, height, panelGraphics));
//		
//		System.out.println("requestBox [" + returnedID + "]");
//		return returnedID;
//	}
	
	/**
	 * Adds an already created HUD-type to the current HUD being displayed.
 	 *
	 * @param hudType
	 */
	
	public int add(GameInterface gi){
		if(gi.GI_ID == -1) return -1;
		int returnedID = gi.GI_ID;
		
		this.gi.add(gi);
		
		System.out.println("added [" + returnedID + "]");
		return returnedID;
	}
	
//	public int requestStarHighlight(int id, float x, float y, int width, int height) {
//		if(id == -1) return -1;
//		int returnedID = id;
//
//		gi.add(new HUDHighlight(this, returnedID, (int)x,(int) y, width, height));
//		return returnedID;
//	}
	
	public void closeHUD(int HUD_ID){
		System.out.println("closing hud " + HUD_ID);
		for(int i = 0; i < gi.size(); i++){
			if(gi.get(i).GI_ID == HUD_ID && gi.get(i).TYPE == GlobalsInterface.HUD_TYPE){
				gi.get(i).closed = true;
			}
		}
	}
	
	public void closeGUI(int GUI_ID){
		System.out.println("closing gui " + GUI_ID);
		for(int i = 0; i < gi.size(); i++){
			if(gi.get(i).GI_ID == GUI_ID && gi.get(i).TYPE == GlobalsInterface.GUI_TYPE){
				gi.get(i).closed = true;
			}
		}
	}
	
	public void dispose(){
		for(int i = 0; i < gi.size(); i++){
			gi.get(i).dispose();
			gi.remove(i);
		}
	}

	public int getHUDSize() {
		return gi.size();
	}

	public boolean hudNameMatches(int i, int id) {
		boolean match = false;
		
		if(id == gi.get(i).GI_ID){
			match = true;
			System.out.println("name matches in test for hudNameMatches");
		}
		
		return match;
	}

	private boolean hUDDisplaying(int id){
		boolean hUDAlreadyDisplaying = false;
		for(int i = 0; i < gi.size(); i++){
			if(gi.get(i).GI_ID == id && gi.get(i).TYPE == GlobalsInterface.HUD_TYPE){
				hUDAlreadyDisplaying = true;
			}
		}
		return hUDAlreadyDisplaying;
	}
	
	private boolean gUIDisplaying(int id){
		boolean gUIAlreadyDisplaying = false;
		for(int i = 0; i < gi.size(); i++){
			if(gi.get(i).GI_ID == id && gi.get(i).TYPE == GlobalsInterface.GUI_TYPE){
				gUIAlreadyDisplaying = true;
			}
		}
		return gUIAlreadyDisplaying;
	}

	public GameManager getGameManager() {
		return gm;
	}

	public void reset() {
		dispose();
	}
}
