package ru.korolkovrs.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseButton extends Sprite {
    private final static float PRESS_SCALE = 0.9f;

    private int pointer;
    private boolean pressed;

    public BaseButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (pressed || !isMe(touch)) {
            System.out.println("not me");
            return false;
        }
        System.out.println("Base touch down");
        this.pointer = pointer;
        pressed = true;
        scale = PRESS_SCALE;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !pressed) {
            return false;
        }
        pressed = false;
        scale = 1f;
        if (isMe(touch)) {
            System.out.println("is me");
            touchUpAction();
            return false;
        }
        return false;
    }

    public abstract void touchUpAction();

    public int getPointer() {
        return pointer;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
