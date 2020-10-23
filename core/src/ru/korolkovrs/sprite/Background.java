package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
