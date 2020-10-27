package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;

public class Ground extends Sprite {
    private float VELOCITY = 0.4f;
    private Rect worldBounds;

    private float yDistance;
    private Vector2 x_velocity;

    public Ground(TextureRegion region) {
        super(region);
        x_velocity = new Vector2(-1f, 0).scl(VELOCITY);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setWidthProportion(worldBounds.getWidth() * 2);
        yDistance = this.getHalfHeight() - 0.5f;
        pos.set(0, yDistance);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(x_velocity, delta);
        if (getRight() < worldBounds.getRight()) {
            setLeft(worldBounds.getLeft());
        }
    }
}
