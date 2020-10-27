package ru.korolkovrs.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.BaseScreen;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.Background;
import ru.korolkovrs.sprite.Cloud;
import ru.korolkovrs.sprite.ExitButton;
import ru.korolkovrs.sprite.Ground;
import ru.korolkovrs.sprite.Plane;
import ru.korolkovrs.sprite.PlayButton;

public class MenuScreen extends BaseScreen {

    private static final int CLOUD_COUNT = 8;

    private Game game;

    private TextureAtlas atlas;
    private Texture bg;
    private Texture gr;

    private Background background;
    private Ground ground;

    private Cloud[] clouds;
    private ExitButton exitButton;
    private PlayButton playButton;
    private Plane plane;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures\\menuAtlas.atlas");
        bg = new Texture("textures\\background.png");
        gr = new Texture("textures\\groundDirt.png");

        background = new Background(new TextureRegion(bg));
        ground = new Ground(new TextureRegion(gr));

        clouds = new Cloud[CLOUD_COUNT];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(atlas);
        }

        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas, game);
        plane = new Plane(atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        ground.resize(worldBounds);

        for (Cloud cloud : clouds) {
            cloud.resize(worldBounds);
        }

        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);
        plane.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        gr.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exitButton.touchDown(touch, pointer, button);
        playButton.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exitButton.touchUp(touch, pointer, button);
        playButton.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Cloud cloud : clouds) {
            cloud.update(delta);
        }

        ground.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        ground.draw(batch);

        for (Cloud cloud : clouds) {
            cloud.draw(batch);
        }

        exitButton.draw(batch);
        plane.draw(batch);
        playButton.draw(batch);
        batch.end();
    }
}
