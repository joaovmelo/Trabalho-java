package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.sprite.Sprite;

import java.awt.*;

public class Goop extends Sprite {
    private final int speed = 1;

    public Goop(int x, int y, int directionX, int directionY) {
        this.x = x;
        this.y = y;
        this.dx = directionX * speed;
        this.dy = directionY * speed;
        loadImage();
        getImageDimensions();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/gosma.png"));
        Image scaledImage = ii.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImage).getImage());
    }

    public void act() {
        // Regra 6: Gosmas vão em linha reta
        x += dx;
        y += dy;
    }
}