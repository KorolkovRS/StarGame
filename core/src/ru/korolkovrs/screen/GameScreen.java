package ru.korolkovrs.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.korolkovrs.base.BaseScreen;
import ru.korolkovrs.base.EnemyAircraft;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;
import ru.korolkovrs.pool.EnemyHelicopter1Pool;
import ru.korolkovrs.pool.EnemyPlanePool;
import ru.korolkovrs.pool.ExplosionPool;
import ru.korolkovrs.sprite.Background;
import ru.korolkovrs.sprite.Bullet;
import ru.korolkovrs.sprite.Cloud;
import ru.korolkovrs.sprite.EnemyHelicopter1;
import ru.korolkovrs.sprite.EnemyPlane;
import ru.korolkovrs.sprite.Ground;
import ru.korolkovrs.sprite.Joystick;
import ru.korolkovrs.sprite.MyPlane;
import ru.korolkovrs.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private Game game;

    private static final int CLOUD_COUNT = 8;

    private TextureAtlas atlas;
    private Texture bg;
    private Sound enemyBarrelSound;
    private Sound explosionSound;

    private Background background;
    private Ground ground;

    private Cloud[] clouds;
    private MyPlane myPlane;
    private BulletPool bulletPool;
    private Joystick joy;

    private EnemyPlanePool enemyPlanePool;
    private EnemyHelicopter1Pool enemyHelicopter1Pool;
    private ExplosionPool explosionPool;

    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();
        atlas = new TextureAtlas("textures\\mainAtlas.pack");
        bg = new Texture("textures\\background.png");
        enemyBarrelSound = Gdx.audio.newSound(Gdx.files.internal("sounds\\bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds\\explosion.wav"));
        explosionPool = new ExplosionPool(atlas, explosionSound);

        background = new Background(new TextureRegion(bg));
        ground = new Ground(atlas);

        clouds = new Cloud[CLOUD_COUNT];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(atlas);
        }

        bulletPool = new BulletPool();
        enemyPlanePool = new EnemyPlanePool(bulletPool, explosionPool, worldBounds, ground);
        enemyHelicopter1Pool = new EnemyHelicopter1Pool(bulletPool, explosionPool, worldBounds, ground);

        myPlane = new MyPlane(atlas, bulletPool);
        joy = new Joystick(atlas, this.myPlane);

        enemyEmitter = new EnemyEmitter(worldBounds, enemyPlanePool, enemyHelicopter1Pool, enemyBarrelSound, atlas, ground);
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
        enemyPlanePool.dispose();
        enemyHelicopter1Pool.dispose();
        explosionPool.dispose();
        explosionSound.dispose();
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
        List<EnemyPlane> enemyPlaneList = enemyPlanePool.getActiveObjects();
        List<EnemyHelicopter1> enemyHelicopter1List = enemyHelicopter1Pool.getActiveObjects();
        float minDist;

        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.isDestroyed()) {
                continue;
            }

            minDist = myPlane.getHalfWidth() + enemyPlane.getHalfWidth();
            if (enemyPlane.pos.dst(myPlane.pos) < minDist) {
                enemyPlane.destroy();
                myPlane.damage(enemyPlane.getDamage());
                return;
            }
        }

        for (EnemyHelicopter1 enemyHelicopter1 : enemyHelicopter1List) {
            if (enemyHelicopter1.isDestroyed()) {
                continue;
            }

            minDist = myPlane.getHalfWidth() + enemyHelicopter1.getHalfWidth();
            if (enemyHelicopter1.pos.dst(myPlane.pos) < minDist) {
                enemyHelicopter1.destroy();
                myPlane.damage(enemyHelicopter1.getDamage());
                return;
            }
        }

        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()) {
                continue;
            }
            if (bullet.getOwner() != myPlane) {
                if (myPlane.isBulletCollision(bullet)) {
                    myPlane.damage(bullet.getDamage());
                    bullet.destroy();
                    return;
                }
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                if (bullet.getOwner() == myPlane) {
                    if (enemyPlane.isBulletCollision(bullet)) {
                        enemyPlane.damage(bullet.getDamage());
                        bullet.destroy();
                        return;
                    }
                }
            }

            for (EnemyHelicopter1 enemyHelicopter1 : enemyHelicopter1List) {
                if (bullet.getOwner() == myPlane) {
                    if (enemyHelicopter1.isBulletCollision(bullet)) {
                        enemyHelicopter1.damage(bullet.getDamage());
                        bullet.destroy();
                        return;
                    }
                }
            }
        }
    }

    private void update(float delta) {
        if (myPlane.isDestroyed()) {
            game.setScreen(new GameOverScreen(game));
        }

        myPlane.update(delta);

        for (Cloud cloud : clouds) {
            cloud.update(delta);
        }
        ground.update(delta);
        bulletPool.updateActiveSprites(delta);
        enemyPlanePool.updateActiveSprites(delta);
        enemyHelicopter1Pool.updateActiveSprites(delta);
        enemyEmitter.generate(delta);
        explosionPool.updateActiveSprites(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        ground.draw(batch);

        for (Cloud cloud : clouds) {
            cloud.draw(batch);
        }

        joy.draw(batch);
        bulletPool.drawActiveSprites(batch);
        myPlane.draw(batch);
        enemyPlanePool.drawActiveSprites(batch);
        enemyHelicopter1Pool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        batch.end();
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyPlanePool.freeAllDestroyedActiveSprites();
        enemyHelicopter1Pool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
