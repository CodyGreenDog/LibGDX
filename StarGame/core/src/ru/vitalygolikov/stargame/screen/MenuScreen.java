package ru.vitalygolikov.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.vitalygolikov.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private SpriteBatch batch;
    private Texture img;
    private Texture background;
    private Vector2 position;
    private Vector2 destination;
    private Vector2 speed;

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        background = new Texture( "dog.jpg");
        speed = new Vector2(0, 0);
        destination = new Vector2();
        position = new Vector2(0, 0);
        destination = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        if( Math.abs(position.x - destination.x) > 1  ||
            Math.abs(position.y - destination.y) > 1 ) {
            position.add(speed);
        }
        else
        {
            speed.set(0, 0 );
        }


        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(img, position.x, position.y);
        batch.end();

        if(position.x != destination.x ) {
            position.add(speed);
        }
        else
        {
            speed.set(0, 0 );
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        destination.set( screenX, Gdx.graphics.getHeight() - screenY);

        Vector2 direction = new Vector2();
        direction.set(destination);
        direction.sub(position);
        direction.nor();
        speed.set(direction);
        return false;
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        img.dispose();
        background.dispose();
    }
}
