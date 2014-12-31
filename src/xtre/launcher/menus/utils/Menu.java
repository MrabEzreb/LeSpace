package xtre.launcher.menus.utils;

import java.util.ArrayList;
import java.util.List;

import xtre.graphics.sprites.SpriteEntity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Menu {
	/*
	 * 		//Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/button_press_edited.ogg")).play();
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/bedip_button.ogg"));
		//Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/button_press.wav")).play();
	 */
	protected Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/launcher/click_menu/bedip_button.ogg"));
	protected BitmapFont font = new BitmapFont(Gdx.files.internal("font/default_font.fnt"));
	//TODO remove all sound and font creations from sub classes as this makes them obsolete. (Unless custom)
	
	protected SpriteEntity se = new SpriteEntity();
	
	protected final MenuManager manager;
	
	public List<MenuButton> buttons = new ArrayList<>();
	private boolean mouseReleased = true, activateChecks = false;
	
	public Menu(MenuManager manager){
		this.manager = manager;
	}
	
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
			buttons.get(i).render(batch);
		}
		renderScreen(batch);
	}
	
	public void renderScreen(SpriteBatch batch){
		
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
