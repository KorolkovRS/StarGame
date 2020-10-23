package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.Sprite;
import ru.korolkovrs.math.Rect;

public class Plane extends Sprite {

    private int SCALE = 10;
    private float V_LEN = 0.01f;
    private Vector2 touch;
    private Vector2 velocity;
    private Vector2 temp;


    public Plane(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / SCALE);
        pos.set(worldBounds.pos);
        velocity = new Vector2();
        temp = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void draw(SpriteBatch batch) {
        temp = pos.cpy().sub(touch);
        if (temp.len() >= velocity.len()) {
            pos.add(velocity);
        } else {
            pos.set(touch);
        }
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        velocity = touch.cpy().sub(pos).setLength(V_LEN);
        return super.touchDown(touch, pointer, button);
    }
}
