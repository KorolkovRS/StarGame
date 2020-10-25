package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.math.Rnd;

public class Cloud extends Sprite {
    private final Vector2 v;
    private Rect worldBounds;

    public Cloud(TextureAtlas atlas) {
        super(atlas.findRegion("cloud1"));
        setHeightProportion(Rnd.nextFloat(0.05f, 0.15f));
        v = new Vector2(getHeight() * -4, 0);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom() / 2, worldBounds.getTop());
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
    }
}
