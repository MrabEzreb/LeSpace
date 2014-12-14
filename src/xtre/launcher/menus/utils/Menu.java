package xtre.launcher.menus.utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Menu {
	
	public List<MenuButton> buttons = new ArrayList<>();
	private boolean mouseReleased = true, activateChecks = false;
	
	public void update(){
		boolean mousePressed = Gdx.input.isButtonPressed(Buttons.LEFT);
		
		if(mousePressed) {
			mouseReleased = false;
			activateChecks = true;
		}else
			mouseReleased = true;
		
		if(!mousePressed && mouseReleased && activateChecks){
			checkAndDoButton();
			processButtons();
			mouseReleased = false;
			activateChecks = false;
		}
	}
	
	public void render(SpriteBatch batch){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).font.draw(batch, buttons.get(i).title, buttons.get(i).x, buttons.get(i).y);
		}
	}
	
	public void addButton(MenuButton btn){
		buttons.add(btn);
	}

	public MenuButton getButton(int i) {
		return buttons.get(i);
	}
	
	public String buttonPressed = "";
	
	public String checkAndDoButton(){
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).isPressed() && !buttonPressed.equals(buttons.get(i).title)){
				System.out.println(buttons.get(i).title);
				buttons.get(i).btnSound.play();

				buttonPressed = buttons.get(i).title;
				return buttons.get(i).title;
			}
		}
		return "";
	}	
	
	public abstract void processButtons();
	
	public void dispose(){
		for(MenuButton b : buttons){
			b.font.dispose();
		}
	}
}
