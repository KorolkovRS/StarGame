package ru.korolkovrs.dto;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.EnemySettingsDto;
import ru.korolkovrs.utils.Regions;

public class EnemyPlaneSettingsDto extends EnemySettingsDto {

    private static final float ENEMY_BIG_HEIGHT = 0.1f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static final int ENEMY_BIG_DAMAGE = 5;
    private static final int ENEMY_BIG_RATE_OF_FIRE = 90;
    private static final int ENEMY_BIG_HP = 10;

    public EnemyPlaneSettingsDto(TextureAtlas atlas, Sound bulletSound) {
        TextureRegion enemy0 = atlas.findRegion("enemyPlane");
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
