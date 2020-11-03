package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;

public class GameOver extends Sprite {
    public GameOver(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setWidthProportion(0.9f);
        pos.set(worldBounds.pos);
    }
}
