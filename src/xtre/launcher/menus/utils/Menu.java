package xtre.launcher.menus.utils;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
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
	
	protected final MenuManager manager;
	
	public List<MenuButton> buttons = new ArrayList<>();
	public String buttonPressed = "";
	
	private DialogBox dialogBox;
	protected boolean isDialogBoxOpen = false;
	private boolean mouseReleased = true, activateChecks = false;
	
	public float mouseX=0, mouseY=0;
	public boolean mouseLeftPress=false;
	
	public Menu(MenuManager manager){
		this.manager = manager;
	}
	
	public abstract void checks(float mouseX, float mouseY, boolean mouseLeftPress);
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseLeftPress = mouseLeftPress;
		
		if(mouseLeftPress) {
			mouseReleased = false;
			activateChecks = true;
		}else
			mouseReleased = true;
		
		if(!mouseLeftPress && mouseReleased && activateChecks){
			checkAndDoButton();
			clicked();
			if(isDialogBoxOpen){
				updateDialogBox(mouseX, mouseY);
			}
			mouseReleased = false;
			activateChecks = false;
		}
		checks(mouseX, mouseY, mouseLeftPress);
	}
	public void render(SpriteBatch batch){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).render(batch);
		}
		if(isDialogBoxOpen){
			dialogBox.render(batch);
		}
		renderScreen(batch);
	}
	
	public abstract void renderScreen(SpriteBatch batch);
	
	public void addButton(MenuButton btn){
		buttons.add(btn);
	}
	
	public void updateDialogBox(float mouseX, float mouseY){
		dialogBox.update(mouseX, mouseY);
		isDialogBoxOpen = dialogBox.open;
	}
	
	public void openDialogBox(float x, float y, float width, float height){
		if(!isDialogBoxOpen){
			dialogBox = new DialogBox(x, y, width, height);
		}
		isDialogBoxOpen = true; dialogBox.open = true;
	}
	
	public void addDialogButton(MenuButton button){
		dialogBox.addButton(button);
	}

	public MenuButton getButton(int i) {
		return buttons.get(i);
	}
	
	public void checkAndDoButton(){
		buttonPressed = "";
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).isPressed() &! buttonPressed.equals(buttons.get(i).title)){
				System.out.println("(Menu.java:105): "+buttons.get(i).title);
				buttons.get(i).btnSound.play();

				buttonPressed = buttons.get(i).title;
			}
		}

		for(int i = 0; isDialogBoxOpen && i < dialogBox.buttons.size(); i++){
			if(!dialogBox.isPressed(i).equals("")){
				buttonPressed = dialogBox.isPressed(i);
				System.out.println(" :: " + buttonPressed);
			}
		}
	}
	
	public abstract void clicked();
	
	public void dispose(){
		for(MenuButton b : buttons){
			b.font.dispose();
		}
	}
}
