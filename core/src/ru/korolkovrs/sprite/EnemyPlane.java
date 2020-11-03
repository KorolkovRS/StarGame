package ru.korolkovrs.sprite;

import ru.korolkovrs.base.EnemyAircraft;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;
<<<<<<< HEAD
import ru.korolkovrs.pool.ExplosionPool;

public class EnemyPlane extends EnemyAircraft {
    public EnemyPlane(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
        super(bulletPool, explosionPool, worldBounds, ground);
=======

public class EnemyPlane extends EnemyAircraft {
    public EnemyPlane(BulletPool bulletPool, Rect worldBounds, Ground ground) {
        super(bulletPool, worldBounds, ground);
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    }

    @Override
    protected void doAction(float delta) {
        bulletPos.set(getLeft(), pos.y);
        pos.mulAdd(velocity, delta);

        if (rateTimer == 0) {
            shoot();
            rateTimer = rateOfFire;
        } else {
            rateTimer--;
        }
    }
}
