package xtre.game.in_space.physics_objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class PhysicsEntity {

	public float x, y;
	public Sprite sprite;

	public Body body;
	public BodyDef bodyDef;
	public FixtureDef fixtureDef;
	
	public void render(SpriteBatch batch){
		sprite.draw(batch);
	}
	
	public void update(){
		
	}
}
