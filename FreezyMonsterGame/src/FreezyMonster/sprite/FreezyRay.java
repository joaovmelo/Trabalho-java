package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.sprite.Sprite;
import FreezyMonster.strategy.MoveStrategy;
import FreezyMonster.strategy.LinearMoveStrategy;

import java.awt.*;

public class FreezyRay extends Sprite {
    private MoveStrategy moveStrategy;

    public FreezyRay(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.moveStrategy = new LinearMoveStrategy();
        loadImage();
        getImageDimensions();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/ray.png"));
        Image scaledImage = ii.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImage).getImage());
    }

    public void act() {
        moveStrategy.move(this);
    }
}