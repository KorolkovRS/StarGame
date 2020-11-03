package ru.korolkovrs.dto;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.EnemySettingsDto;
import ru.korolkovrs.utils.Regions;

public class EnemyHelicopter2SettingDto extends EnemySettingsDto {
    private static final float ENEMY_BIG_HEIGHT = 0.1f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final int ENEMY_BIG_DAMAGE = 10;
    private static final int ENEMY_BIG_RATE_OF_FIRE = 60;
    private static final int ENEMY_BIG_HP = 3;

    public EnemyHelicopter2SettingDto(TextureAtlas atlas, Sound bulletSound) {
        TextureRegion enemy0 = atlas.findRegion("helicopter2");
        setRegions(Regions.split(enemy0, 1, 1, 2));
        setV0(new Vector2(-0.1f, 0));
        setBulletRegion(atlas.findRegion("myBullet"));
        setBulletHeight(ENEMY_BIG_BULLET_HEIGHT);
        setBulletV(new Vector2(-0.6f, 0));
        setBulletSound(bulletSound);
        setDamage(ENEMY_BIG_DAMAGE);
        setReloadInterval(ENEMY_BIG_RATE_OF_FIRE);
        setHeight(ENEMY_BIG_HEIGHT);
        setHp(ENEMY_BIG_HP);
    }
}
