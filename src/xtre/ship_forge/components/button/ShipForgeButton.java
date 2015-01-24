package xtre.ship_forge.components.button;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.launcher.menus.utils.TitleString;
import xtre.ship_forge.components.ShipForgeMenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForgeButton {

	public float x, y, width, height;
	public TitleString title;
	public Sprite sprite = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar);
	private Color col = new Color(1,1,1,1);
	private ShipForgeButtonAction action;
	
	private boolean hover = false;
	public boolean toggle = false;
	
	public ShipForgeButton(){}
	public ShipForgeButton(ShipForgeMenu menu, float row, String title) {
		this.title = new TitleString(menu.x, menu.y, title);
		width = menu.width;
		height = 18;
		x = menu.x;
		y = (menu.y+(menu.height-height)) - row*height;
		
		sprite.setPosition(x, y);
		sprite.setSize(width, height);
	}
	public ShipForgeButton(float x, float y, float width, float height, String title){
		this.title = new TitleString(x, y, title);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		sprite.setPosition(x, y);
		sprite.setSize(width, height);
	}
	
	public void action(){
		action.action();
	}
	
	public void render(SpriteBatch batch){
		if(toggle){
			sprite.setColor(.7f,.9f,.9f,.8f);
		}
		else if(hover){
			sprite.setColor(.7f,.9f,.9f,.8f);
		}
		else if(!hover){
			sprite.setColor(col);
		}
		sprite.draw(batch);
	}
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		hover = GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height);
		if(hover) action();
	}
	
	public void setAction(ShipForgeButtonAction action) {
		this.action = action;
	}
	public void dispose() {
		sprite.getTexture().dispose();
	}
	public void setColor(int r, int g, int b, int a) {
		col = new Color(r,g,b,a);
	}
}
