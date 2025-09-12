// Player.java
package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.Commons;
import spriteframework.sprite.*;

import java.awt.event.KeyEvent;

public class PlayerFreezy extends Player {
    private int width;
    private int height;

    public PlayerFreezy() {
        loadImage();
        getImageDimensions();
        resetState();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/woody.png"));
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
    }

    public void act() {
        x += dx;
        y += dy;

        // Manter dentro dos limites
        if (x <= 2) {
            x = 2;
        }
        if (x >= Commons.BOARD_WIDTH - 2 * width) {
            x = Commons.BOARD_WIDTH - 2 * width;
        }
        if (y <= 2) {
            y = 2;
        }
        if (y >= Commons.GROUND - height) {
            y = Commons.GROUND - height;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    private void resetState() {
        setX(Commons.INIT_PLAYER_X);
        setY(Commons.INIT_PLAYER_Y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}