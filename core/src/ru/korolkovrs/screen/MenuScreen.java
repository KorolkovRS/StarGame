package ru.korolkovrs.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.korolkovrs.base.BaseScreen;
import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.Background;
import ru.korolkovrs.sprite.Ground;
import ru.korolkovrs.sprite.Plane;

public class MenuScreen extends BaseScreen {
    private Texture bg;
    private Texture gr;
    private Texture pl;
    private Background background;
    private Ground ground;
    private Plane plane;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures\\background.png");
        gr = new Texture("textures\\groundDirt.png");
        pl = new Texture("textures\\yellowPlane.png");
        background = new Background(new TextureRegion(bg));
        ground = new Ground(new TextureRegion(gr));
        plane = new Plane(new TextureRegion(pl));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        ground.draw(batch);
        plane.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        ground.resize(worldBounds);
        plane.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        gr.dispose();
        pl.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        plane.touchDown(touch, pointer, button);
        return false;
    }
}
