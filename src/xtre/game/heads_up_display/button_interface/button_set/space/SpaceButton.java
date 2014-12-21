//package xtre.game.heads_up_display.button_interface.button_set.space;
//
//import xtre.game.heads_up_display.utils.HeadsUpDisplay;
//import xtre.globals.hud.Glbls;
//
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//public abstract class SpaceButton {
//
//	public Sprite sprite;
//	public float x, y, width, height;
//	private float mouseX, mouseY;
//	
//	protected HeadsUpDisplay hud;
//	protected GameButtonAction buttonAction;
//	
//	private boolean justPressedLeftMouseButton = false;
//	
//	public SpaceButton(float x, float y, float width, float height, HeadsUpDisplay hud, Sprite sprite, GameButtonAction buttonAction) {
//		this.x = x;
//		this.y = y;
//		this.width = width;
//		this.height = height;
//		this.hud = hud;
//		this.sprite = sprite;
//		this.sprite.setPosition(x, y);
//		System.out.println(x + "button sprite " + y);
//		this.buttonAction = buttonAction;
//	}
//	
//	public SpaceButton(){}
//	
//	public final void update(float mouseX, float mouseY, boolean justPressedLeftMouseButton){
//		this.mouseX = mouseX;
//		this.mouseY = mouseY;
//		this.justPressedLeftMouseButton = justPressedLeftMouseButton;
//		
//		update();
//		checkBounds();
//	}
//	
//	public final void render(SpriteBatch batch){
//		sprite.draw(batch);
//	}
//	
//	public void setPosition(float x, float y) {
//		sprite.setPosition(x, y);
//		this.x = x;
//		this.y = y;
//	}
//	
//	public final boolean checkBounds(){
//		if(justPressedLeftMouseButton && Glbls.withinSquareBounds(mouseX, mouseY, x, y, width, height)){
//			doAction();
//			return true;
//		}
//		else
//			return false;
//	}
//	
//	public abstract void doAction();
//	public void update(){}
//
//}
