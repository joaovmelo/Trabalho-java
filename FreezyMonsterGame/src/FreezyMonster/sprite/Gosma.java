package FreezyMonster.sprite;

import spriteframework.sprite.Sprite;
import javax.swing.ImageIcon;

public class Gosma extends Sprite {

    private boolean destroyed;

    public Gosma(int x, int y) {
        initGosma(x, y);
    }

    private void initGosma(int x, int y) {
        setDestroyed(true);
        this.x = x;
        this.y = y;
        String gosmaImage = "/images/gosma.png";
        ImageIcon ii = new ImageIcon(getClass().getResource(gosmaImage));
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void act() {
        this.x += dx;
        this.y += dy;
    }
}