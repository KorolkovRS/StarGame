package ru.korolkovrs.pool;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.EnemyHelicopter1;
import ru.korolkovrs.sprite.Ground;

public class EnemyHelicopter1Pool extends SpritesPool<EnemyHelicopter1> {
    private BulletPool bulletPool;
    private Rect worldBounds;
    private Ground ground;
    private ExplosionPool explosionPool;

    public EnemyHelicopter1Pool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.ground = ground;
        this.explosionPool = explosionPool;
    }

    @Override
    protected EnemyHelicopter1 newObject() {
        return new EnemyHelicopter1(bulletPool, explosionPool, worldBounds, ground);
    }
}
