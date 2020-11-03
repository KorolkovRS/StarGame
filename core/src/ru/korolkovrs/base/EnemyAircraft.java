package ru.korolkovrs.base;

import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;
import ru.korolkovrs.pool.ExplosionPool;
import ru.korolkovrs.sprite.Bullet;
import ru.korolkovrs.sprite.Explosion;
import ru.korolkovrs.sprite.Ground;

public abstract class EnemyAircraft extends Aircraft {
    private final static float V_RIDE_OUT = -0.5f;
    protected Ground ground;

    protected Vector2 rideOutVelocity;

    public EnemyAircraft(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        rideOutVelocity = new Vector2(V_RIDE_OUT, 0);
        this.ground = ground;
        this.explosionPool = explosionPool;
    }

    @Override
    public void update(float delta) {
        if (worldBounds.getRight() < getRight()) {
            rideOut(delta);
        } else {
            doAction(delta);
        }

        if(getRight() < worldBounds.getLeft()) {
            destroy();
        }
    }

    public void set(EnemySettingsDto settings) {
        this.regions = settings.getRegions();
        this.velocity.set(settings.getV0());
        this.bulletRegion = settings.getBulletRegion();
        this.bulletHeight = settings.getBulletHeight();
        this.bulletV.set(settings.getBulletV());
        this.barrelSound = settings.getBulletSound();
        this.damage = settings.getDamage();
        this.rateOfFire = settings.getReloadInterval();
        this.setHeightProportion(settings.getHeight());
        this.hp = settings.getHp();
    }

    @Override
    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, damage, bulletHeight);
        barrelSound.play();
    }

    private void rideOut(float delta) {
        pos.mulAdd(rideOutVelocity, delta);
    }

    protected abstract void doAction(float delta);

    public boolean isBulletCollision(Rect bullet) {
        return !(
                bullet.getRight() < getLeft()
                        || bullet.getLeft() > getRight()
                        || bullet.getBottom() > getTop()
                        || bullet.getTop() < pos.y
        );
    }
}
