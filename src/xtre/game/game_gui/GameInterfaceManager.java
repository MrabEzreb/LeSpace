package xtre.game.game_gui;

import java.util.LinkedList;

import xtre.game.scene_manager.GameManager;
import xtre.game.scene_manager.GameScene;
import xtre.globals.game_interface.GlobalsInterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameInterfaceManager {

	private GameManager gm;
	
	private LinkedList<GameInterfaceComponent> gi = new LinkedList<>();

	 /**
	  * GameInterfaceManager manages every GUI component on the screen currently.
	  * This is a more dynamic way of processing interfaces. By adding interfaces to a list
	  * and then iterating over it to update each component. When done the list can be disposed of
	  * then renewed for the next batch of interface components.
	  * 
	  * To create components, use {@code add(gameInterfaceComponent);}
	  * @param gm
	  */
	
	public GameInterfaceManager(GameManager gm){
		this.gm = gm;
	}
	
	public void updateInteractives(){
		for(int i = gi.size()-1; i >= 0; i--){
			if(gi.get(i).bringToFront){
				gi.get(i).bringToFront = false;
				gi.push(gi.get(i));
				gi.remove(i);
			}
		}

		if(!gi.isEmpty())
			gi.get(0).updateInteractives();
	}
	
	public void updateInterfaces(){
		for(int i = 0; i < gi.size(); i++){
			gi.get(i).updateInterfaces();
		}
	}
	
	public void renderInteractives(SpriteBatch batch){
		for(GameInterfaceComponent gi:gi){
			gi.renderInteractives(batch);
		}
	}
	
	public void renderInterfaces(SpriteBatch batch){
		for(GameInterfaceComponent gi:gi){
			gi.renderInterfaces(batch);
		}
	}

	public void setScene(GameScene scene) {
		gm.setScene(scene);
	}
	
	public void closeFrames() {
		for(GameInterfaceComponent gi:gi){
			gi.dispose();
		}
	}
	
	public GameInterfaceComponent getHUD(int id){
		System.out.println("Getting HUD " + id);
		for(int i = 0; i < gi.size(); i++){
			if(gi.get(i).GI_ID == id && gi.get(i).TYPE == GlobalsInterface.HUD_TYPE)
				return gi.get(i);
		}
		System.out.println("HUDManager.getHUD Error: couldn't get the requested hud " + id);
		return null;
	}
	
	public GameInterfaceComponent getGUI(int id){
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
	
	public int add(GameInterfaceComponent gi){
		if(gi.GI_ID == -1) {
			System.out.println("GameInterfaceManager: add(gameInterfaceComponent) returned invalid. GI_ID == " + gi.GI_ID + " for " + gi.getClass().getSimpleName());
			return -1;
		}
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

	public void resize(int width, int height) {
		for(GameInterfaceComponent gic:gi)
			gic.updateScale(width, height);
	}
}
