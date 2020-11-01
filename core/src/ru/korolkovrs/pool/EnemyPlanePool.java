package ru.korolkovrs.pool;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.EnemyPlane;
import ru.korolkovrs.sprite.Ground;

public class EnemyPlanePool extends SpritesPool<EnemyPlane> {

    private BulletPool bulletPool;
    private Rect worldBounds;
    private Ground ground;

    public EnemyPlanePool(BulletPool bulletPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.ground = ground;
    }

    @Override
    protected EnemyPlane newObject() {
        return new EnemyPlane(bulletPool, worldBounds, ground);
    }
}
