package ru.korolkovrs.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.BaseButton;
import ru.korolkovrs.math.Rect;

public class Joystick extends BaseButton {
    private static final float MARGIN = 0.2f;
    private static final float V_LEN = 3f;
    private static final float MAX_LEN = 1f;

    private Plane plane;

    private Vector2 planeVelocity;
    private Vector2 temp;

    public Joystick(TextureAtlas atlas, Plane plane) {
        super(atlas.findRegion("joy"));
        this.plane = plane;
        planeVelocity = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.25f);
        setLeft(worldBounds.getLeft() + MARGIN);
        setBottom(worldBounds.getBottom() + MARGIN);
        System.out.println(this.pos);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (this.getPointer() != pointer || !this.isPressed()) {
            return false;
        }
        temp.set(touch);
        if(touch.sub(this.pos).scl(V_LEN).len() > MAX_LEN) {
            touch.setLength(MAX_LEN);
        }
        planeVelocity.set(touch);
        plane.setVelocity(planeVelocity);
        System.out.println("Vel = " + planeVelocity.len());
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.getPointer() != pointer || !isPressed()) {
            return false;
        }
        setPressed(false);
        scale = 1f;
        touchUpAction();
        return false;
    }

    @Override
    public void touchUpAction() {
        planeVelocity.setLength(0);
        plane.setVelocity(planeVelocity);
    }
}



