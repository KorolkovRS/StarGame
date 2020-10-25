package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.BaseScreen;
import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;

public class Plane extends Sprite {

    private static final int SCALE = 10;
    private Vector2 velocity;

    public Plane(TextureAtlas atlas) {
        super(atlas.findRegion("yellowPlane"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / SCALE);
        pos.set(worldBounds.pos);
        velocity = new Vector2();
    }

    @Override
    public void draw(SpriteBatch batch) {
        pos.add(velocity);
        super.draw(batch);
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
