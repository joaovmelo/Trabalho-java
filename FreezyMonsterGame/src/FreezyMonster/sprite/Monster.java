package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import spriteframework.sprite.BadSprite;
import spriteframework.Commons;
import FreezyMonster.strategy.MoveStrategy;
import FreezyMonster.strategy.MonsterMoveStrategy;

public class Monster extends BadSprite {
    private static final int MONSTER_WIDTH = 30;
    private static final int MONSTER_HEIGHT = 30;

    private boolean frozen = false;
    private int speed = 1;
    private Image normalImage;
    private Image frozenImage;
    private MoveStrategy moveStrategy;

    public Monster(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.moveStrategy = new MonsterMoveStrategy();
        loadImage(type);
        getImageDimensions();

        Random random = new Random();
        dx = random.nextInt(3) - 1;
        dy = random.nextInt(3) - 1;
        if (dx == 0 && dy == 0) dx = 1;
    }

    protected void loadImage(int type) {
        String normalImagePath = "/images/monster" + type + ".png";
        ImageIcon iiNormal = new ImageIcon(getClass().getResource(normalImagePath));
        this.normalImage = iiNormal.getImage().getScaledInstance(MONSTER_WIDTH, MONSTER_HEIGHT, Image.SCALE_SMOOTH);

        String frozenImagePath = "/images/monster" + type + "bg.png";
        ImageIcon iiFrozen = new ImageIcon(getClass().getResource(frozenImagePath));
        this.frozenImage = iiFrozen.getImage().getScaledInstance(MONSTER_WIDTH, MONSTER_HEIGHT, Image.SCALE_SMOOTH);

        setImage(this.normalImage);
    }

    @Override
    public void act() {
        if (!frozen) {
            moveStrategy.move(this);
        }
    }

    public void freeze() {
        this.frozen = true;
        setImage(this.frozenImage);
    }

    public boolean isFrozen() {
        return frozen;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}