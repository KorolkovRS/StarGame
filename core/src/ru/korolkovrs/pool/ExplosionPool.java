package ru.korolkovrs.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.korolkovrs.base.SpritesPool;
import ru.korolkovrs.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion>
{
    private TextureAtlas textureAtlas;
    private Sound explosionSound;

    public ExplosionPool(TextureAtlas textureAtlas, Sound explosionSound) {
        this.textureAtlas = textureAtlas;
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(textureAtlas.findRegion("explosion"), 9, 9, 74, explosionSound);
    }
}
