package ru.korolkovrs.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;

public abstract class Aircraft extends Sprite {

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Sound barrelSound;
    protected float bulletHeight;
    protected int damage;

    protected Vector2 velocity;
    protected final Vector2 bulletV;
    protected final Vector2 bulletPos;

    protected int rateTimer;
    protected int rateOfFire;

    protected int hp;

    public Aircraft() {
        velocity = new Vector2();
        bulletV = new Vector2();
        bulletPos = new Vector2();
    }

    public Aircraft(TextureRegion region) {
        super(region);
        velocity = new Vector2();
        bulletV = new Vector2();
        bulletPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(velocity, delta);
    }

    protected abstract void shoot();
}



