package ru.korolkovrs.dto;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.EnemySettingsDto;
import ru.korolkovrs.utils.Regions;

public class EnemyHelicopter1SettingDto extends EnemySettingsDto {
    private static final float ENEMY_BIG_HEIGHT = 0.1f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final int ENEMY_BIG_DAMAGE = 10;
    private static final int ENEMY_BIG_RATE_OF_FIRE = 240;
    private static final int ENEMY_BIG_HP = 10;

    public EnemyHelicopter1SettingDto(TextureAtlas atlas, Sound bulletSound) {
        TextureRegion enemy0 = atlas.findRegion("helicopter1");
        setRegions(Regions.split(enemy0, 1, 1, 2));
        setV0(new Vector2(0, -0.1f));
        setBulletRegion(atlas.findRegion("myBullet"));
        setBulletHeight(ENEMY_BIG_BULLET_HEIGHT);
        setBulletV(new Vector2(-1.4f, 0));
        setBulletSound(bulletSound);
        setDamage(ENEMY_BIG_DAMAGE);
        setReloadInterval(ENEMY_BIG_RATE_OF_FIRE);
        setHeight(ENEMY_BIG_HEIGHT);
        setHp(ENEMY_BIG_HP);
    }
}
