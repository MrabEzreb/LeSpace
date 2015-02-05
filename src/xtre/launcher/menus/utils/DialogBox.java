package xtre.launcher.menus.utils;

import java.util.ArrayList;
import java.util.List;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.UIGraphics;
import xtre.graphics.components.ResizableBox;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DialogBox {

	public float x, y, width, height;
	
	private ResizableBox box;
	
	public boolean open = false, start = true;
	
	List<MenuButton> buttons = new ArrayList<>();
	
	public DialogBox(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
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
		open = GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height);
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
	
	public void dispose(){
		for(Sprite s:box.graphics){
			s.getTexture().dispose();
		}
	}

	public void addButton(MenuButton button) {
		buttons.add(button);
	}
}
