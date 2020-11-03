package ru.korolkovrs.base;

import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;
<<<<<<< HEAD
import ru.korolkovrs.pool.ExplosionPool;
import ru.korolkovrs.sprite.Bullet;
import ru.korolkovrs.sprite.Explosion;
=======
import ru.korolkovrs.sprite.Bullet;
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
import ru.korolkovrs.sprite.Ground;

public abstract class EnemyAircraft extends Aircraft {
    private final static float V_RIDE_OUT = -0.5f;
    protected Ground ground;

    protected Vector2 rideOutVelocity;

<<<<<<< HEAD
    public EnemyAircraft(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
=======
    public EnemyAircraft(BulletPool bulletPool, Rect worldBounds, Ground ground) {
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        rideOutVelocity = new Vector2(V_RIDE_OUT, 0);
        this.ground = ground;
<<<<<<< HEAD
        this.explosionPool = explosionPool;
=======
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    }

    @Override
    public void update(float delta) {
        if (worldBounds.getRight() < getRight()) {
            rideOut(delta);
        } else {
            doAction(delta);
        }
<<<<<<< HEAD

        if(getRight() < worldBounds.getLeft()) {
            destroy();
        }
=======
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
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
<<<<<<< HEAD
        this.setHeightProportion(settings.getHeight());
=======
        setHeightProportion(settings.getHeight());
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
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
<<<<<<< HEAD

    public boolean isBulletCollision(Rect bullet) {
        return !(
                bullet.getRight() < getLeft()
                        || bullet.getLeft() > getRight()
                        || bullet.getBottom() > getTop()
                        || bullet.getTop() < pos.y
        );
    }
=======
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
}
