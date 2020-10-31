package ru.korolkovrs.sprite;

import ru.korolkovrs.base.EnemySettingsDto;
import ru.korolkovrs.base.Aircraft;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.pool.BulletPool;

public class EnemyAircraft extends Aircraft {
    public EnemyAircraft(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos.x, getBottom());
        super.update(delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(EnemySettingsDto settings) {
        this.regions = settings.getRegions();
        this.velocity.set(settings.getV0());
        this.bulletRegion = settings.getBulletRegion();
        this.bulletHeight = settings.getBulletHeight();
        this.bulletV.set(settings.getBulletV());
        this.barrelSound = settings.getBulletSound();
        this.damage = settings.getDamage();
        this.rateTimer = settings.getReloadInterval();
        setHeightProportion(settings.getHeight());
        this.hp = settings.getHp();
    }

    @Override
    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, damage, bulletHeight);
        barrelSound.play();
    }
}
