package xtre.launcher.menus.utils;

import java.util.ArrayList;
import java.util.List;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.UIGraphics;
import xtre.graphics.components.ResizableBox;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DialogBox {
	
	private ResizableBox box;
	
	public boolean open = false;
	private boolean start = true;
	
	List<MenuButton> buttons = new ArrayList<>();
	
	public DialogBox(float x, float y, float width, float height){
		
		box = new ResizableBox(x, y, width, height);
		box.setBL(UIGraphics.getBL());
		box.setBR(UIGraphics.getBR());
		box.setM(UIGraphics.getMM());
		box.setMB(UIGraphics.getBM());
		box.setML(UIGraphics.getLM());
		box.setMR(UIGraphics.getRM());
		box.setMT(UIGraphics.getTM());
		box.setTL(UIGraphics.getTL());
		box.setTR(UIGraphics.getTR());
	}
	
	public void update(float mouseX, float mouseY){
		if(!start)
		open = GlobalsInterface.withinSquareBounds(mouseX, mouseY, box.getX(), box.getY(), box.getWidth(), box.getHeight());
		start = false;
	}
	
	public String isPressed(int i){
			if(buttons.get(i).isPressed())
				return buttons.get(i).title;
		
		return "";
	}
	
	public void render(SpriteBatch batch){
		box.render(batch);
		for(MenuButton mb:buttons) 
			mb.render(batch);
	}

	public void addButton(MenuButton button) {
		buttons.add(button);
	}
	
	public void setPosition(float x, float y){
		box.setPosition(x, y);
	}
	
	public void setSize(float width, float height){
		box.setSize(width, height);
	}
	
	public void dispose(){
		box.dispose();
	}

	public float getX(){
		return box.getX();
	}
	
	public float getY(){
		return box.getY();
	}
	
	public float getWidth() {
		return box.getWidth();
	}
	
	public float getHeight(){
		return box.getHeight();
	}
}
