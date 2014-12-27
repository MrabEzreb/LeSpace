package xtre;


import xtre.launcher.LauncherLoop;

import com.badlogic.gdx.Game;

public class Main extends Game {
	
	LauncherLoop launcherGameLoop;
	
	public void create () {
		launcherGameLoop = new LauncherLoop(this);
		setScreen(launcherGameLoop);
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause () {
		super.pause();
	}

	@Override
	public void resume () {
		super.resume();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize (int width, int height) {
		super.resize(width, height);
	}

}
