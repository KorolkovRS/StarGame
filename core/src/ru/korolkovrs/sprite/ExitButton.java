package ru.korolkovrs.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.korolkovrs.base.BaseButton;
import ru.korolkovrs.math.Rect;

public class ExitButton extends BaseButton {
    private static final float MARGIN = 0.025f;

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
        setRight(worldBounds.getRight() - MARGIN);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public void touchUpAction() {
        System.out.println("exit");
        Gdx.app.exit();
    }
}
