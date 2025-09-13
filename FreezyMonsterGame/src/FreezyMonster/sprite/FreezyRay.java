package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.sprite.Sprite;

import java.awt.*;

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
        Image scaledImage = ii.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImage).getImage());
    }

    public void act() {
        // Regra 3: Raio congelante vai em linha reta
        x += dx;
        y += dy;
    }
}