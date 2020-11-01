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
            if (type < 0.1f) {
                enemyAircraft = enemyPlanePool.obtain();
                enemyAircraft.set(enemyPlaneSettingsDto);
//            } else if (type < 0.8f) {
//                ene
//                enemyAircraft.set(enemyHelicopter1SettingDto);
            } else {
                enemyAircraft = enemyHelicopter1Pool.obtain();
                enemyAircraft.set(enemyHelicopter1SettingDto);
            }

            enemyAircraft.pos.y = Rnd.nextFloat(worldBounds.getBottom() + enemyAircraft.getHalfHeight() + ground.getHeight(),
                    worldBounds.getTop() - enemyAircraft.getHalfHeight());
            enemyAircraft.setLeft(worldBounds.getRight());
        }
    }
}
