// Monster.java
package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import java.util.Random;
import spriteframework.sprite.BadSprite;
import spriteframework.Commons;

public class Monster extends BadSprite {
    private boolean frozen = false;
    private Random random = new Random();
    private int directionX;
    private int directionY;
    private int speed = 2;

    public Monster(int x, int y) {
        this.x = x;
        this.y = y;
        loadImage();
        getImageDimensions();
        chooseRandomDirection();
    }

    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/monster.png"));
        setImage(ii.getImage());
    }

    public void loadFrozenImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/monster_frozen.png"));
        setImage(ii.getImage());
    }

    public void act() {
        if (!frozen) {
            // Mudar direção aleatoriamente
            if (random.nextInt(100) < 5) { // 5% de chance de mudar direção
                chooseRandomDirection();
            }

            x += directionX * speed;
            y += directionY * speed;

            // Manter dentro dos limites
            if (x <= 2) {
                x = 2;
                directionX = 1;
            }
            if (x >= Commons.BOARD_WIDTH - 2 * getImageWidth()) {
                x = Commons.BOARD_WIDTH - 2 * getImageWidth();
                directionX = -1;
            }
            if (y <= 2) {
                y = 2;
                directionY = 1;
            }
            if (y >= Commons.GROUND - getImageHeight()) {
                y = Commons.GROUND - getImageHeight();
                directionY = -1;
            }
        }
    }

    private void chooseRandomDirection() {
        directionX = random.nextInt(3) - 1; // -1, 0, ou 1
        directionY = random.nextInt(3) - 1; // -1, 0, ou 1

        // Garantir que se mova em pelo menos uma direção
        if (directionX == 0 && directionY == 0) {
            directionX = 1;
        }
    }

    public void freeze() {
        frozen = true;
        loadFrozenImage();
    }

    public boolean isFrozen() {
        return frozen;
    }

    public boolean isDestroyed() {
        return frozen;
    }
}