package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import spriteframework.sprite.BadSprite;
import spriteframework.Commons;

public class Monster extends BadSprite {
    private static final int MONSTER_WIDTH = 30;
    private static final int MONSTER_HEIGHT = 30;

    private boolean frozen = false;
    private Random random = new Random();
    private int speed = 1;
    private Image normalImage;
    private Image frozenImage;

    public Monster(int x, int y, int type) {
        this.x = x;
        this.y = y;
        loadImage(type); // Carrega a imagem baseada no tipo
        getImageDimensions();

        dx = random.nextInt(3) - 1;
        dy = random.nextInt(3) - 1;
        if (dx == 0 && dy == 0) dx = 1;
    }

    protected void loadImage(int type) {
        // Carrega a imagem normal
        String normalImagePath = "/images/monster" + type + ".png";
        ImageIcon iiNormal = new ImageIcon(getClass().getResource(normalImagePath));
        this.normalImage = iiNormal.getImage().getScaledInstance(MONSTER_WIDTH, MONSTER_HEIGHT, Image.SCALE_SMOOTH);

        // Carrega a imagem congelada
        String frozenImagePath = "/images/monster" + type + "bg.png";
        ImageIcon iiFrozen = new ImageIcon(getClass().getResource(frozenImagePath));
        this.frozenImage = iiFrozen.getImage().getScaledInstance(MONSTER_WIDTH, MONSTER_HEIGHT, Image.SCALE_SMOOTH);

        // Define a imagem inicial
        setImage(this.normalImage);
    }

    public void act() {
        if (!frozen) {
            x += dx * speed;
            y += dy * speed;

            if (x <= 0 || x >= Commons.BOARD_WIDTH - MONSTER_WIDTH) dx *= -1;
            if (y <= 0 || y >= Commons.GROUND - MONSTER_HEIGHT) dy *= -1;

            if (random.nextInt(100) < 2) { // 2% de chance de mudar direção
                dx = random.nextInt(3) - 1;
                dy = random.nextInt(3) - 1;
                if (dx == 0 && dy == 0) dx = 1;
            }
        }
    }

    public void freeze() {
        this.frozen = true;
        setImage(this.frozenImage); // Apenas troca para a imagem já carregada
    }

    public boolean isFrozen() {
        return frozen;
    }
}