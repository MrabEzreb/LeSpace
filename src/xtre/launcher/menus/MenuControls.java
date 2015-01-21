package xtre.launcher.menus;

import xtre.graphics.sprites.GameSprite;
import xtre.launcher.menus.utils.Menu;
import xtre.launcher.menus.utils.MenuButton;
import xtre.launcher.menus.utils.MenuManager;
import xtre.launcher.menus.utils.TitleString;
import xtre.launcher.sprites.sprite_types.SpritesControlsScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MenuControls extends Menu{
	private MenuManager manager;
	
	private final KeyIcon[] keyIcons;
	
	public MenuControls(MenuManager manager) {
		super(manager);
		this.manager = manager;

		buttons.add(new MenuButton(125, (Gdx.graphics.getHeight()/2), 100, 32, "Back", font, sound));

		//Graphics Keys
			Sprite[] graphics_keys = new Sprite[]{
					GameSprite.getSprite(SpritesControlsScreen.key_icon_w),
					GameSprite.getSprite(SpritesControlsScreen.key_icon_a),
					GameSprite.getSprite(SpritesControlsScreen.key_icon_s),
					GameSprite.getSprite(SpritesControlsScreen.key_icon_d),
					GameSprite.getSprite(SpritesControlsScreen.key_icon_shift)
			};

			graphics_keys[0].setPosition((Gdx.graphics.getWidth()/4)+90, (Gdx.graphics.getHeight()/4)+55);
			graphics_keys[1].setPosition((Gdx.graphics.getWidth()/4)+55, Gdx.graphics.getHeight()/4);
			graphics_keys[2].setPosition((Gdx.graphics.getWidth()/4)+(55*2), Gdx.graphics.getHeight()/4);
			graphics_keys[3].setPosition((Gdx.graphics.getWidth()/4)+(55*3), Gdx.graphics.getHeight()/4);
			graphics_keys[4].setPosition((Gdx.graphics.getWidth()/4)-(32), (Gdx.graphics.getHeight()/4)-(55));
			
			TitleString[] descriptions = new TitleString[]{
					new TitleString(graphics_keys[0].getX(), graphics_keys[0].getY()+77, "Accelerate"),
					new TitleString(graphics_keys[1].getX()-100, graphics_keys[1].getY()+64, "Turn Left"),
					new TitleString(graphics_keys[2].getX()-27, graphics_keys[2].getY()-7, "Dampers"),
					new TitleString(graphics_keys[3].getX()+50, graphics_keys[3].getY()+64, "Turn Right"),
					new TitleString(graphics_keys[4].getX(), graphics_keys[4].getY()-7, "Boosters")
			};
			
			keyIcons = new KeyIcon[5];
			for(int i = 0; i < keyIcons.length; i++){
				keyIcons[i] = new KeyIcon(graphics_keys[i], descriptions[i], new BitmapFont(Gdx.files.internal("font/default_font.fnt")));
			}
			
		//
		
		//Graphics Icon
//			graphics_icons = new Sprite[]{
//					se.getSprite(SpritesControlsScreen.key_icon)
//			};
//			
//			for(int i = 0; i < graphics_icons.length; i++){
//				graphics_icons[i].setPosition(256, (Gdx.graphics.getHeight())/4+(i*64));
//			}
		//
			
	}
	
	public void renderScreen(SpriteBatch batch){
		for(KeyIcon ki:keyIcons)
			drawControl(batch, ki);
		
	}
	
	private void drawControl(SpriteBatch batch, KeyIcon ki){
		int mx = Gdx.input.getX(), my = -Gdx.input.getY() + Gdx.graphics.getHeight();

		if(mx > ki.sprite.getX() && mx < ki.sprite.getX()+ki.sprite.getWidth() && my > ki.sprite.getY() && my < ki.sprite.getY()+ki.sprite.getHeight()){
			ki.sprite.setColor(1, 1, 1, 1f);
			ki.sprite.draw(batch);

			((BitmapFont) ki.bitmapFont).draw(batch, ki.title.title, ki.title.x, ki.title.y);
			((BitmapFont) ki.bitmapFont).setColor(1, 1, 1, 1f);
		}else{
			ki.sprite.setColor(1, 1, 1, .5f);
			ki.sprite.draw(batch);

			((BitmapFont) ki.bitmapFont).setColor(1, 1, 1, .5f);
			((BitmapFont) ki.bitmapFont).draw(batch, ki.title.title, ki.title.x, ki.title.y);
		}
	}
	
	@Override
	public void process(){
		if(buttonPressed.equals("Back")){
			manager.setMenu(new MenuOptions(manager, font));
		}
	}
	
	class KeyIcon{
		public final Sprite sprite;
		public final TitleString title;
		public final Object bitmapFont;
		
		public KeyIcon(Sprite sprite, TitleString title, Object bitmapFont){
			this.sprite=sprite;
			this.title=title;
			this.bitmapFont=bitmapFont;
		}
		
	}
}
