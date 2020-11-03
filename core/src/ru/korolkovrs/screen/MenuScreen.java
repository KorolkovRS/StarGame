package ru.korolkovrs.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import ru.korolkovrs.sprite.MyPlane;
import ru.korolkovrs.sprite.PlayButton;

public class MenuScreen extends BaseScreen {

    private static final int CLOUD_COUNT = 8;

    private Game game;

    protected TextureAtlas atlas;
    private Texture bg;

    protected Background background;
    protected Ground ground;

<<<<<<< HEAD
    protected Cloud[] clouds;
    protected ExitButton exitButton;
    protected PlayButton playButton;
=======
    private Cloud[] clouds;
    private ExitButton exitButton;
    private PlayButton playButton;
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    private MyPlane myPlane;

    protected Music music;

    public MenuScreen(Game game) {
        this.game = game;
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds\\music.mp3"));
        music.setLooping(true);
    }

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures\\menuAtlas.pack");
        bg = new Texture("textures\\background.png");

        background = new Background(new TextureRegion(bg));
        ground = new Ground(atlas);

        clouds = new Cloud[CLOUD_COUNT];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(atlas);
        }

        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas, game);
        myPlane = new MyPlane(atlas);
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
        myPlane.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
        myPlane.dispose();
        music.dispose();
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

    protected void draw() {
        batch.begin();
        background.draw(batch);
        ground.draw(batch);

        for (Cloud cloud : clouds) {
            cloud.draw(batch);
        }

        exitButton.draw(batch);
        myPlane.draw(batch);
        playButton.draw(batch);
        batch.end();
        music.play();
    }
}
