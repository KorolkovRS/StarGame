package ru.korolkovrs.sprite;

import ru.korolkovrs.base.EnemyAircraft;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;
<<<<<<< HEAD
import ru.korolkovrs.pool.ExplosionPool;
=======
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6

public class EnemyHelicopter1 extends EnemyAircraft {

    private static final int BURST_LENGTH = 10;

    private boolean gunBurst;
<<<<<<< HEAD
    private int burstCount;

    public EnemyHelicopter1(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
        super(bulletPool, explosionPool, worldBounds, ground);
=======
    private int gunBurstRateTimer;
    private int burstCount;

    public EnemyHelicopter1(BulletPool bulletPool, Rect worldBounds, Ground ground) {
        super(bulletPool, worldBounds, ground);
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    }

    @Override
    protected void doAction(float delta) {
        bulletPos.set(getLeft(), getBottom() + 0.02f);
        pos.mulAdd(velocity, delta);

        if (getBottom() < (ground.getTop()) || getTop() > worldBounds.getTop()) {
            velocity.scl(-1f);
        }

        if (!gunBurst) {
            if (rateTimer == 0) {
                gunBurst = true;
                shoot();
            } else {
                rateTimer--;
            }
        } else {
            shoot();
        }
    }

    @Override
    protected void shoot() {
            if (gunBurst) {
                if (burstCount <= 40) {
                    if ((burstCount % 4) == 0) {
                        super.shoot();
                    }
                    burstCount++;
                } else {
                    burstCount = 0;
                    rateTimer = rateOfFire;
                    gunBurst = false;
                }
            }
    }
}
