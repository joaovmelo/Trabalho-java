package FreezyMonster.sprite;

import javax.swing.ImageIcon;
import spriteframework.Commons;
import spriteframework.sprite.*;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class PlayerFreezy extends Player {
    private static final int PLAYER_WIDTH = 35;
    private static final int PLAYER_HEIGHT = 55;

    public PlayerFreezy() {
        loadImage();
        getImageDimensions();
        resetState();
    }

    @Override
    protected void loadImage() {
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/woody.png"));
        // Redimensiona a imagem
        Image scaledImage = ii.getImage().getScaledInstance(PLAYER_WIDTH, PLAYER_HEIGHT, Image.SCALE_SMOOTH);
        setImage(new ImageIcon(scaledImage).getImage());
    }

    public void act() {
        x += dx;
        y += dy;

        // Manter dentro dos limites da tela
        if (x <= 2) x = 2;
        if (x >= Commons.BOARD_WIDTH - PLAYER_WIDTH) x = Commons.BOARD_WIDTH - PLAYER_WIDTH;
        if (y <= 2) y = 2;
        if (y >= Commons.GROUND - PLAYER_HEIGHT) y = Commons.GROUND - PLAYER_HEIGHT;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int speed = 2;

        if (key == KeyEvent.VK_LEFT) dx = -speed;
        if (key == KeyEvent.VK_RIGHT) dx = speed;
        if (key == KeyEvent.VK_UP) dy = -speed;
        if (key == KeyEvent.VK_DOWN) dy = speed;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) dx = 0;
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) dy = 0;
    }

    private void resetState() {
        setX(Commons.INIT_PLAYER_X);
        setY(Commons.INIT_PLAYER_Y);
    }
}