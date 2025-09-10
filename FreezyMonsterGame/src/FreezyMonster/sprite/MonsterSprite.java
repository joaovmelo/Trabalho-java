package FreezyMonster.sprite;

import FreezyMonster.Commons;
import spriteframework.sprite.BadSprite;
import javax.swing.ImageIcon;
import java.awt.Image; // Importe a classe Image

public class MonsterSprite extends BadSprite {

    private boolean frozen = false;
    private Gosma gosma;
    private String normalImage;
    private String frozenImage;


    public MonsterSprite(int x, int y, int monsterNumber) {
        this.normalImage = "/images/monster" + monsterNumber + ".png";
        this.frozenImage = "/images/monster" + monsterNumber + "bg.png";
        initMonster(x, y);
    }

    private void initMonster(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = new java.util.Random().nextInt(3) - 1;
        this.dy = new java.util.Random().nextInt(3) - 1;
        gosma = new Gosma(x, y);

        ImageIcon ii = new ImageIcon(getClass().getResource(normalImage));
        Image scaledImage = ii.getImage().getScaledInstance(Commons.MONSTER_WIDTH, Commons.MONSTER_HEIGHT, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImage).getImage());

        getImageDimensions();
    }

    @Override
    public void act() {
        if (frozen) return;

        this.x += dx;
        this.y += dy;

        if (x < 0 || x > spriteframework.Commons.BOARD_WIDTH - imageWidth) {
            dx = -dx;
        }
        if (y < 0 || y > spriteframework.Commons.BOARD_HEIGHT - imageHeight) {
            dy = -dy;
        }
    }

    public void freeze() {
        this.frozen = true;

        ImageIcon ii = new ImageIcon(getClass().getResource(frozenImage));
        Image scaledImage = ii.getImage().getScaledInstance(Commons.MONSTER_WIDTH, Commons.MONSTER_HEIGHT, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImage).getImage());

        getImageDimensions();
    }

    public boolean isFrozen() {
        return frozen;
    }

    public Gosma getGosma() {
        return gosma;
    }
}