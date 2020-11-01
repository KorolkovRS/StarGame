package ru.korolkovrs.pool;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.EnemyAircraft;

public class EnemyAircraftPool extends SpritesPool<EnemyAircraft> {

    private BulletPool bulletPool;
    private Rect worldBounds;

    public EnemyAircraftPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyAircraft newObject() {
        return new EnemyAircraft(bulletPool, worldBounds);
    }
}
