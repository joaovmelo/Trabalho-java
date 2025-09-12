// FreezyRay.java
package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.sprite.Sprite;

public class FreezyRay extends Sprite {

    public FreezyRay(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        loadImage();
        getImageDimensions();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/ray.png"));
        setImage(ii.getImage());
    }

    public void act() {
        x += dx;
        y += dy;
    }
}