package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.sprite.Sprite;
import FreezyMonster.strategy.MoveStrategy;
import FreezyMonster.strategy.LinearMoveStrategy;

import java.awt.*;

public class Goop extends Sprite {
    private final int speed = 1;
    private MoveStrategy moveStrategy;

    public Goop(int x, int y, int directionX, int directionY) {
        this.x = x;
        this.y = y;
        this.dx = directionX * speed;
        this.dy = directionY * speed;
        this.moveStrategy = new LinearMoveStrategy();
        loadImage();
        getImageDimensions();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/gosma.png"));
        Image scaledImage = ii.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImage).getImage());
    }

    public void act() {
        moveStrategy.move(this);
    }
}