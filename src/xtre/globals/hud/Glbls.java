package xtre.globals.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Glbls {
		
	public static final boolean withinSquareBounds(Vector2 mousePosition, Vector2 squarePosition, Vector2 squareSize){
		if(mousePosition.x > squarePosition.x && mousePosition.x < squarePosition.x + squareSize.x
		&& mousePosition.y > squarePosition.y && mousePosition.y < squarePosition.y + squareSize.y)
			return true;
		else
			return false;
	}
	
	public static final boolean isWithin(Vector2 mousePosition, Vector2 circlePosition, double radius){
		double n = sq(mousePosition.x - circlePosition.x) + sq(mousePosition.x - circlePosition.y);
		if(n < radius)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @param circleX
	 * @param circleY
	 * @param radius
	 * @return
	 */
	public static final boolean isWithin(float mouseX, float mouseY, float circleX, float circleY, double radius){
		double n = sq(mouseX - circleX) + sq(mouseY - circleY);
		if(n < radius)
			return true;
		else
			return false;
	}
	
	private final static double sq(double n){
		return Math.sqrt(n*n);
	}

	public static boolean withinSquareBounds(float mouseX, float mouseY,  float squareX, float squareY, float squareWidth, float squareHeight) {
		if(mouseX > squareX && mouseX < squareX+squareWidth && mouseY > squareY && mouseY < squareY+squareHeight)
			return true;
		else
			return false;
	}

}
