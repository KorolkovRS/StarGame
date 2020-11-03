package ru.korolkovrs.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;
import ru.korolkovrs.pool.ExplosionPool;
import ru.korolkovrs.sprite.Explosion;

public abstract class Aircraft extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
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

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

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

        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    protected abstract void shoot();

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos);
    }

    public void damage(int damage) {
        damageAnimateTimer = 0f;
        hp -= damage;
        System.out.println(getClass().getName() + ": HP = " + hp);
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }
}



