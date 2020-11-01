package ru.korolkovrs.sprite;

import ru.korolkovrs.base.EnemyAircraft;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;

public class EnemyPlane extends EnemyAircraft {
    public EnemyPlane(BulletPool bulletPool, Rect worldBounds, Ground ground) {
        super(bulletPool, worldBounds, ground);
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
