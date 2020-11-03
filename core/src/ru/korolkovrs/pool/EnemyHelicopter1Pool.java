package ru.korolkovrs.pool;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.EnemyHelicopter1;
import ru.korolkovrs.sprite.Ground;

public class EnemyHelicopter1Pool extends SpritesPool<EnemyHelicopter1> {
    private BulletPool bulletPool;
    private Rect worldBounds;
    private Ground ground;
<<<<<<< HEAD
    private ExplosionPool explosionPool;

    public EnemyHelicopter1Pool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.ground = ground;
        this.explosionPool = explosionPool;
=======

    public EnemyHelicopter1Pool(BulletPool bulletPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.ground = ground;
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    }

    @Override
    protected EnemyHelicopter1 newObject() {
<<<<<<< HEAD
        return new EnemyHelicopter1(bulletPool, explosionPool, worldBounds, ground);
=======
        return new EnemyHelicopter1(bulletPool, worldBounds, ground);
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    }
}
