package ru.korolkovrs.pool;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.EnemyPlane;
<<<<<<< HEAD
import ru.korolkovrs.sprite.Explosion;
=======
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
import ru.korolkovrs.sprite.Ground;

public class EnemyPlanePool extends SpritesPool<EnemyPlane> {

    private BulletPool bulletPool;
    private Rect worldBounds;
    private Ground ground;
<<<<<<< HEAD
    private ExplosionPool explosionPool;

    public EnemyPlanePool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.ground = ground;
        this.explosionPool = explosionPool;
=======

    public EnemyPlanePool(BulletPool bulletPool, Rect worldBounds, Ground ground) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.ground = ground;
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    }

    @Override
    protected EnemyPlane newObject() {
<<<<<<< HEAD
        return new EnemyPlane(bulletPool, explosionPool, worldBounds, ground);
=======
        return new EnemyPlane(bulletPool, worldBounds, ground);
>>>>>>> 31b1b2f... Merge pull request #8 from KorolkovRS/lesson6
    }
}
