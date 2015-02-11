package xtre.ship_forge.components.button;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.font.FontEntity;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.ship_forge.components.ShipForgeMenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForgeButton {

	protected FontEntity font;
	
	public float x, y, width, height;
	public FontEntity title;
	public Sprite sprite = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar);
	public Sprite overlay;
	private Color col = new Color(1,1,1,1);
	private Action action;
	
	public boolean hover = false;
	public boolean toggle = false;
	public boolean pressed = false;
	
	public ShipForgeButton(){}
	public ShipForgeButton(ShipForgeMenu menu, float row, FontEntity font) {
		title = font;
		if(!menu.horizontal){
			width = menu.width;
			height = menu.height;
			x = menu.x;
			y = (menu.y+(menu.height-height)) - row*height;
		}else{
			width = menu.width;
			height = menu.height;
			x = (menu.x+(menu.width-width)) - row*width;
			y = menu.y;
		}
		sprite.setPosition(x, y);
		sprite.setSize(width, height);
		
	}
	public ShipForgeButton(ShipForgeMenu menu, float row, String title) {
		this.title = new FontEntity(null, title, menu.x, menu.y);
		if(!menu.horizontal){
			width = menu.width;
			height = menu.height;
			x = menu.x;
			y = (menu.y+(menu.height-height)) - row*height;
		}else{
			width = menu.width;
			height = menu.height;
			x = (menu.x+(menu.width-width)) - row*width;
			y = menu.y;
		}
		sprite.setPosition(x, y);
		sprite.setSize(width, height);
	}
	public ShipForgeButton(float x, float y, float width, float height, String title){
		this.title = new FontEntity(null, title, x, y);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		sprite.setPosition(x, y);
		sprite.setSize(width, height);
	}
	
	public void setOverlay(Sprite overlay){
		overlay.setPosition(x, y);
		this.overlay = overlay;
	}
	
	public void action(){
		action.action();
	}
	
	public void render(SpriteBatch batch){
		if(hover || toggle){
			sprite.setColor(.7f,.9f,.9f,.8f);
		}
		else if(!hover){
			sprite.setColor(col);
		}
		sprite.draw(batch);
		
		if(overlay!=null)
			overlay.draw(batch);
		
		if(font!=null)
			font.font.draw(batch, title.text, title.x, title.y);
	}
	public void click(float mouseX, float mouseY){
		if(hover) {
			action();
		}
	}
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		hover = GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height);
		if(hover)
			pressed = true;
		else
			pressed = false;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	public void dispose() {
		sprite.getTexture().dispose();
		font.font.dispose();
	}
	public void setColor(float f, float g, float h, float i) {
		col = new Color(f,g,h,i);
	}
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		sprite.setPosition(x, y);
	}
}
