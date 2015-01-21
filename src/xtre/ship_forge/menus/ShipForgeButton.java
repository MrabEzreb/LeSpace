package xtre.ship_forge.menus;

import xtre.globals.game_interface.GlobalsInterface;
import xtre.graphics.sprites.GameSprite;
import xtre.graphics.sprites.sprite_types.space_hud.SpritesSpaceHudMenu;
import xtre.launcher.menus.utils.TitleString;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipForgeButton {

	public float x, y, width, height;
	public TitleString title;
	public Sprite sprite = GameSprite.getSprite(SpritesSpaceHudMenu.menu_bar);
	private ShipForgeButtonAction action;
	
	private boolean hover = false;
	
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
	
	public void action(){
		action.action();
	}
	
	public void render(SpriteBatch batch){
		if(hover){
			sprite.setColor(.7f,.9f,.9f,.8f);
		}else if(!hover){
			sprite.setColor(1,1,1,1);
		}
		sprite.draw(batch);
	}
	
	public void update(float mouseX, float mouseY, boolean mouseLeftPress){
		hover = GlobalsInterface.withinSquareBounds(mouseX, mouseY, x, y, width, height);
		System.out.println(hover + " " + mouseX + " " + x + " " + mouseY + " " + y);
		
		if(mouseLeftPress && hover){
			action();
		}
	}
	
	public void setAction(ShipForgeButtonAction action) {
		this.action = action;
	}
	public void dispose() {
		sprite.getTexture().dispose();
	}
}
