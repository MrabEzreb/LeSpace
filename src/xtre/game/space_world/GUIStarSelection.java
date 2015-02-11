package xtre.game.space_world;

import xtre.game.game_gui.GameInterfaceManager;
import xtre.game.game_gui.graphics_user_interface.GraphicsUserInterface;
import xtre.globals.game_interface.GlobalsInterface;
import xtre.globals.game_interface.hud.GameInputs;
import xtre.graphics.components.ResizableBox;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GUIStarSelection extends GraphicsUserInterface {
	
	private Sprite highlight;
	private Stars stars;
	private Star selectedStar;
	
	private GUIViewStarOptions menu;
	
	public GUIStarSelection(GameInterfaceManager gim, int GI_ID, Sprite highlight, Stars stars) {
		super(gim, GI_ID, 0, 0, 1300, 800);
		
		this.highlight = highlight;
		this.stars = stars;		
		
		ResizableBox box = new ResizableBox(0, 0, 256, 136);
		box.setGraphicsTo(0);
		menu = new GUIViewStarOptions(gim, GI_ID, box);
	}

	@Override
	public void updateInterfaces(){
		
	}
	
	@Override
	public void updateInteractives(){
//		if(GameInputs.keyPressed(Buttons.LEFT)){
//			if(!menu.active)
//				for(int i = 0; i < stars.length; i++){
//					if(stars.getStar(i).onScreen){
//						if(GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), stars.getStar(i).getX()-16, stars.getStar(i).getY()-16, 32, 32)){
//							menu.setPosition(stars.getStar(i).getX(), stars.getStar(i).getY());
//							menu.active = true;
//							break;
//						}
//					}
//				}
//			if(menu.mouseOutOfBounds()){
//				if(menu.isClosable()){
//					menu.active = false;
//				}
//			}
//		}
//		
//		selectedStar = null;
//		for(int i = 0; i < stars.length; i++){
//			if(GlobalsInterface.withinSquareBounds(GameInputs.getX(), GameInputs.getY(), stars.getStar(i).getX()-16, stars.getStar(i).getY()-16, 32, 32)){
//				highlight.setPosition(stars.getStar(i).getX(), stars.getStar(i).getY());
//				selectedStar = stars.getStar(i);
//				break;
//			}
//		}
//		
//		if(selectedStar!=null){
//			highlight.setPosition(selectedStar.getX()-(highlight.getWidth()/2)+(selectedStar.sprite.getWidth()/2), selectedStar.getY()-(highlight.getHeight()/2)+(selectedStar.sprite.getHeight()/2));
//		}
	}
	
	@Override
	public void renderInterfaces(SpriteBatch batch) {
	}
	
	@Override
	public void renderInteractives(SpriteBatch batch){
//		if(selectedStar!=null)
//			highlight.draw(batch);
	}

	@Override
	public void setPosition(float x, float y) {
		menu.setPosition(x, y);
	}

	@Override
	public void dispose() {
	}
}

