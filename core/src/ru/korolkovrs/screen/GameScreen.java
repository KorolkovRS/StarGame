package ru.korolkovrs.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.BaseScreen;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;
import ru.korolkovrs.pool.EnemyAircraftPool;
import ru.korolkovrs.sprite.Background;
import ru.korolkovrs.sprite.Cloud;
import ru.korolkovrs.sprite.Ground;
import ru.korolkovrs.sprite.Joystick;
import ru.korolkovrs.sprite.MyPlane;
import ru.korolkovrs.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private static final int CLOUD_COUNT = 8;

    private TextureAtlas atlas;
    private Texture bg;
    private Sound enemyBarrelSound;

    private Background background;
    private Ground ground;

    private Cloud[] clouds;
    private MyPlane myPlane;
    private BulletPool bulletPool;
    private Joystick joy;

    private EnemyAircraftPool enemyAircraftPool;
    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures\\mainAtlas.pack");
        bg = new Texture("textures\\background.png");
        enemyBarrelSound = Gdx.audio.newSound(Gdx.files.internal("sounds\\bullet.wav"));

        background = new Background(new TextureRegion(bg));
        ground = new Ground(atlas);

        clouds = new Cloud[CLOUD_COUNT];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(atlas);
        }

        bulletPool = new BulletPool();
        enemyAircraftPool = new EnemyAircraftPool(bulletPool, worldBounds);

        myPlane = new MyPlane(atlas, bulletPool);
        joy = new Joystick(atlas, this.myPlane);

        enemyEmitter = new EnemyEmitter(worldBounds, enemyAircraftPool, enemyBarrelSound, atlas, ground);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        ground.resize(worldBounds);
        for (Cloud cloud : clouds) {
            cloud.resize(worldBounds);
        }
        myPlane.resize(worldBounds);
        joy.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        myPlane.dispose();
        enemyBarrelSound.dispose();
        enemyAircraftPool.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        myPlane.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        myPlane.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        joy.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        joy.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        joy.touchDragged(touch, pointer);
        return false;
    }

    public void checkCollision() {

    }

    private void update(float delta) {
        myPlane.update(delta);

        for (Cloud cloud : clouds) {
            cloud.update(delta);
        }
        ground.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyAircraftPool.updateActiveSprites(delta);
        enemyEmitter.generate(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        ground.draw(batch);

        for (Cloud cloud : clouds) {
            cloud.draw(batch);
        }

        myPlane.draw(batch);
        joy.draw(batch);
        bulletPool.drawActiveSprites(batch);
        enemyAircraftPool.drawActiveSprites(batch);
        batch.end();
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyAircraftPool.freeAllDestroyedActiveSprites();
    }
}
