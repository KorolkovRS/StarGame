package ru.korolkovrs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture background;
	Texture ground;

	int counter = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		background = new Texture("background.png");
		ground = new Texture("groundDirt.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		drawBackground();
		drawGround();
		drawMobilObject();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	private void drawBackground() {
		batch.draw(background, 0,0);
	}

	private void drawGround() {
		batch.draw(ground, 0,0);
	}

	private void drawMobilObject() {
		if (counter >= Gdx.graphics.getWidth()) {
			counter = 0;
		}
		batch.draw(img, ++counter, 100);
	}
}
