package ru.korolkovrs.sprite;

import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.EnemySettingsDto;
import ru.korolkovrs.base.Aircraft;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;

public class EnemyAircraft extends Aircraft {
    private final static float V_RIDE_OUT = -0.5f;

    protected Vector2 rideOutVelocity;

    public EnemyAircraft(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        rideOutVelocity = new Vector2(V_RIDE_OUT, 0);
    }

    @Override
    public void update(float delta) {
        if (worldBounds.getRight() < getRight()) {
            rideOut(delta);
        } else {
            doAction(delta);
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
        setHeightProportion(settings.getHeight());
        this.hp = settings.getHp();
    }

    @Override
    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, damage, bulletHeight);
        barrelSound.play();
    }

    private void rideOut(float delta) {
        super.update(delta);
        pos.mulAdd(rideOutVelocity, delta);
    }

    private void doAction(float delta) {
        bulletPos.set(getLeft(), pos.y);
        super.update(delta);
        pos.mulAdd(velocity, delta);

        if (rateTimer == 0) {
            shoot();
            rateTimer = rateOfFire;
        } else {
            rateTimer--;
        }
    }
}
