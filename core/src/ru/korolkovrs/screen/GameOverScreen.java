package ru.korolkovrs.screen;

import com.badlogic.gdx.Game;

import ru.korolkovrs.math.Rect;
import ru.korolkovrs.sprite.Cloud;
import ru.korolkovrs.sprite.GameOver;

public class GameOverScreen extends MenuScreen {

    private GameOver gameOver;

    public GameOverScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        gameOver = new GameOver(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        gameOver.resize(worldBounds);
    }

    @Override
    protected void draw() {
        batch.begin();
        background.draw(batch);
        ground.draw(batch);

        for (Cloud cloud : clouds) {
            cloud.draw(batch);
        }
        exitButton.draw(batch);
        playButton.draw(batch);
        gameOver.draw(batch);
        batch.end();
        music.play();
    }
}
