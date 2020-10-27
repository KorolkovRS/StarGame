package ru.korolkovrs.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.BaseScreen;
import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;

public class Plane extends Sprite {

    private static final int SCALE = 10;
    private static final int RATE_OF_FIRE = 10;

    private Rect worldBounds;
    private Vector2 velocity;

    private BulletPool bulletPool;
    private TextureRegion bulletRegion;

    private final Vector2 bulletV = new Vector2(2f, 0);
    private final Vector2 bulletPos = new Vector2();

    private boolean shootPressed;
    private int rateTimer;

    public Plane(TextureAtlas atlas) {
        super(atlas.findRegion("yellowPlane"));
    }

    public Plane(TextureAtlas atlas, BulletPool bulletPool) {
        this(atlas);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("myBullet");
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(worldBounds.getHeight() / SCALE);
        pos.set(worldBounds.pos);
        velocity = new Vector2();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(velocity, delta);
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        } else if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        } else if (getTop() > worldBounds.getTop()) {
            setTop(worldBounds.getTop());
        } else if (getBottom() < worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
        }

        if (shootPressed) {
            if(rateTimer == 0) {
                shoot();
                rateTimer = RATE_OF_FIRE;
            } else {
                rateTimer--;
            }
        }
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            shootPressed = true;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        shootPressed = false;
        rateTimer = 0;
        return false;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletPos.set(getRight(), pos.y);
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, 1, 0.05f);
    }
}
