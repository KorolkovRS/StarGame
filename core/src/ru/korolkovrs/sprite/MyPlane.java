package ru.korolkovrs.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.Aircraft;
import ru.korolkovrs.base.BaseScreen;
import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;

public class MyPlane extends Aircraft {

    private static final int SCALE = 10;
    private static final int HP = 100;

    private boolean shootPressed;

    public MyPlane(TextureAtlas atlas) {
        super(atlas.findRegion("yellowPlane"));
    }

    public MyPlane(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("yellowPlane"));
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("myBullet");
        this.barrelSound = Gdx.audio.newSound(Gdx.files.internal("sounds\\bullet.wav"));
        this.bulletHeight = 0.05f;
        this.damage = 1;
        this.velocity = new Vector2();
        this.bulletV.set(2f, 0);
        this.hp = HP;
        this.rateOfFire = 5;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(worldBounds.getHeight() / SCALE);
        pos.set(worldBounds.pos);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(velocity, delta);
        if (getLeft() <= worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        }
        if (getRight() >= worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        }
        if (getTop() >= worldBounds.getTop()) {
            setTop(worldBounds.getTop());
        }
        if (getBottom() <= worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
        }

        if (shootPressed) {
            if(rateTimer == 0) {
                shoot();
                rateTimer = rateOfFire;
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


    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletPos.set(getRight(), pos.y);
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, 1, 0.05f);
        barrelSound.play(1.0f);
    }

    public void dispose() {
        if (barrelSound != null) {
            barrelSound.dispose();
        }
    }
}
