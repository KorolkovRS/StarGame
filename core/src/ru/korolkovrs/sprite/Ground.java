package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;

public class Ground extends Sprite {
    private float VELOCITY = 0.01f;
    private float yDistance;
    private Vector2 x_velocity;
    private Vector2 temp;

    private Texture gr;

    public Ground(TextureRegion region) {
        super(region);
        x_velocity = new Vector2(1f, 0).scl(VELOCITY);
    }

    @Override
    public void resize(Rect worldBounds) {
        setWidthProportion(worldBounds.getWidth() * 2);
        yDistance = this.getHalfHeight() - 0.5f;
        pos.set(0, yDistance);
        temp = this.pos.cpy();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if ((this.pos.x + this.getHalfWidth() / 2) >= VELOCITY) {
            pos.sub(x_velocity);
        } else {
            this.pos.set(this.getHalfWidth() / 2, yDistance);
        }
        super.draw(batch);
    }
}
