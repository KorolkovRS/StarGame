package ru.korolkovrs.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private int VELOCITY = 10;

    private Texture img;
    private Texture background;
    private Texture ground;

    private Vector2 planePosition;
    private Vector2 targetPosition;
    private Vector2 planeDirectional;
    private Vector2 planeVelocity;
    private Vector2 distance;

    @Override
    public void show() {
        super.show();
        img = new Texture("yellowPlane.png");
        background = new Texture("background.png");
        ground = new Texture("groundDirt.png");
        planePosition = new Vector2(100, 100);
        targetPosition = new Vector2(planePosition);
        planeDirectional = new Vector2();
        planeVelocity = new Vector2();
        distance = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        drawBackground();
        drawGround();
        drawPlane();
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        targetPosition.set(screenX, Gdx.graphics.getHeight() - screenY);
        reset();
        calculatePlaneMotion();
        return super.touchDown(screenX, screenY, pointer, button);
    }

    private void drawBackground() {
        batch.draw(background, 0,0);
    }

    private void drawGround() {
        batch.draw(ground, 0,0);
    }

    private void drawPlane() {
        if (!planePosition.equals(targetPosition)) {
            System.out.println("pp = " + planePosition + " tp = " + targetPosition);
            if (distance.set(targetPosition).sub(planePosition).len() >= VELOCITY) {
                planePosition = planePosition.add(planeVelocity);
                System.out.println("apd = " + planePosition);
            } else {
                System.out.println("trigger!");
                planePosition.set(targetPosition);
                reset();
            }
        }
        batch.draw(img, planePosition.x, planePosition.y);
    }

    private void calculatePlaneMotion() {
        System.out.println("tp = " + targetPosition);
        planeDirectional.set(targetPosition).sub(planePosition).nor();
        planeVelocity.add(planeDirectional).scl(VELOCITY);
        System.out.println("v= " + planeVelocity);
        System.out.println("Plane dir = " + planeDirectional);
    }

    private void reset() {
        planeDirectional.set(0, 0);
        planeVelocity.set(0,0);
        distance.set(0,0);
    }
}
