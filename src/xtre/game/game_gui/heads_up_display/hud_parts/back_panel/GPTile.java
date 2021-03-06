package xtre.game.game_gui.heads_up_display.hud_parts.back_panel;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GPTile {
	
	public Sprite tile;
	public int x, y;
	
	public GPTile(int x, int y, Sprite tile){
		this.x = x;
		this.y = y;
		this.tile = tile;
	}

	public void setOffset(float xOffset, float yOffset) {
		tile.setPosition(xOffset + x, yOffset + y);
	}
	
	public void tint(float r, float g, float b, float a){
		tile.setColor(r,g,b,a);
	}
}
