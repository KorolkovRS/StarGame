package ru.korolkovrs.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.korolkovrs.base.EnemyAircraft;
import ru.korolkovrs.base.EnemySettingsDto;
import ru.korolkovrs.dto.EnemyHelicopter1SettingDto;
import ru.korolkovrs.dto.EnemyHelicopter2SettingDto;
import ru.korolkovrs.dto.EnemyPlaneSettingsDto;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.math.Rnd;
import ru.korolkovrs.pool.EnemyHelicopter1Pool;
import ru.korolkovrs.pool.EnemyPlanePool;
import ru.korolkovrs.sprite.EnemyHelicopter1;
import ru.korolkovrs.sprite.EnemyPlane;
import ru.korolkovrs.sprite.Ground;

public class EnemyEmitter {

    private static final float GENERATE_INTERVAL = 4f;

    private Rect worldBounds;
    private EnemyPlanePool enemyPlanePool;
    private EnemyHelicopter1Pool enemyHelicopter1Pool;
    private Ground ground;
    private float generateTimer;

    private EnemySettingsDto enemyHelicopter2SettingDto;
    private EnemySettingsDto enemyHelicopter1SettingDto;
    private EnemySettingsDto enemyPlaneSettingsDto;

    public EnemyEmitter(Rect worldBounds, EnemyPlanePool enemyPlanePool,EnemyHelicopter1Pool enemyHelicopter1Pool,
                        Sound bulletSound, TextureAtlas atlas, Ground ground) {
        this.worldBounds = worldBounds;
        this.enemyPlanePool = enemyPlanePool;
        this.enemyHelicopter1Pool = enemyHelicopter1Pool;
        enemyHelicopter1SettingDto = new EnemyHelicopter1SettingDto(atlas, bulletSound);
        enemyHelicopter2SettingDto = new  EnemyHelicopter2SettingDto(atlas, bulletSound);
        enemyPlaneSettingsDto = new EnemyPlaneSettingsDto(atlas, bulletSound);
        this.ground = ground;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            EnemyAircraft enemyAircraft;
            float type = (float) Math.random();
            if (type < 0.5f) {
                enemyAircraft = getEnemyPlane();
            } else {
                if (enemyHelicopter1Pool.getActiveObjects().size() < 2) {
                    enemyAircraft = getEnemyHelicopter1();
                } else {
                    enemyAircraft = getEnemyPlane();
                }
            }

            enemyAircraft.pos.y = Rnd.nextFloat(worldBounds.getBottom() + enemyAircraft.getHalfHeight() + ground.getHeight(),
                    worldBounds.getTop() - enemyAircraft.getHalfHeight());
            enemyAircraft.setLeft(worldBounds.getRight());
        }
    }

    private EnemyPlane getEnemyPlane() {
        EnemyPlane enemyPlane = enemyPlanePool.obtain();
        enemyPlane.set(enemyPlaneSettingsDto);
        return enemyPlane;
    }

    private EnemyHelicopter1 getEnemyHelicopter1() {
        EnemyHelicopter1 enemyHelicopter1 = enemyHelicopter1Pool.obtain();
        enemyHelicopter1.set(enemyHelicopter1SettingDto);
        return enemyHelicopter1;
    }
}
