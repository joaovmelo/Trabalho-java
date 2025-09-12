// Goop.java
package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.sprite.Sprite;

public class Goop extends Sprite {
    private int speed = 3;

    public Goop(int x, int y, int directionX, int directionY) {
        this.x = x;
        this.y = y;
        this.dx = directionX * speed;
        this.dy = directionY * speed;
        loadImage();
        getImageDimensions();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/goop.png"));
        setImage(ii.getImage());
    }

    public void act() {
        x += dx;
        y += dy;
    }
}