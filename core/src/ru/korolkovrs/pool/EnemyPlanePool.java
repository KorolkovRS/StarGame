package ru.korolkovrs.pool;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.EnemyPlane;
import ru.korolkovrs.sprite.Explosion;
import ru.korolkovrs.sprite.Ground;

public class EnemyPlanePool extends SpritesPool<EnemyPlane> {

    private BulletPool bulletPool;
    private Rect worldBounds;
    private Ground ground;
    private ExplosionPool explosionPool;

    public EnemyPlanePool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.ground = ground;
        this.explosionPool = explosionPool;
    }

    @Override
    protected EnemyPlane newObject() {
        return new EnemyPlane(bulletPool, explosionPool, worldBounds, ground);
    }
}
