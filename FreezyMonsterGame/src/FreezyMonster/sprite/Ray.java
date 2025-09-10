package FreezyMonster.sprite;

import spriteframework.sprite.Sprite;
import javax.swing.ImageIcon;

public class Ray extends Sprite {
    public Ray(int x, int y, int lastDx, int lastDy) {
        initRay(x, y, lastDx, lastDy);
    }

    private void initRay(int x, int y, int lastDx, int lastDy) {
        String rayImage = "/images/ray.png";
        ImageIcon ii = new ImageIcon(getClass().getResource(rayImage));
        setImage(ii.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);

        this.dx = lastDx;
        this.dy = lastDy;
    }

    public void act() {
        this.x += dx;
        this.y += dy;
    }
}