package ru.korolkovrs.pool;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
