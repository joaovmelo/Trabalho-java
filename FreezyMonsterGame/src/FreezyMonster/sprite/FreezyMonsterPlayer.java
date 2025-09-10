package FreezyMonster.sprite;

import FreezyMonster.Commons;
import spriteframework.sprite.Player;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Image;

public class FreezyMonsterPlayer extends Player {
    private int lastDx = 1;
    private int lastDy = 0;
    public FreezyMonsterPlayer() {
        loadImage();
        getImageDimensions();
    }

    public int getLastDx() {
        return lastDx;
    }

    public int getLastDy() {
        return lastDy;
    }

    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            dy = -2;
            lastDy = -2;
            lastDx = 0;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = 2;
            lastDy = 2;
            lastDx = 0;
        }
        if(key == KeyEvent.VK_LEFT){
            dx = -2;
            lastDx = -2;
            lastDy = 0;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 2;
            lastDx = 2;
            lastDy = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        super.keyReleased(e);
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            dy = 0;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = 0;
        }
        if(key == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }

    @Override
    public void loadImage(){
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/woody.png"));

        // --- CORREÇÃO: Redimensiona a imagem para o tamanho definido em Commons ---
        Image scaledImage = ii.getImage().getScaledInstance(Commons.PLAYER_WIDTH, Commons.PLAYER_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        setImage(scaledIcon.getImage());
        setWidth(scaledIcon.getImage().getWidth(null));
    }

    @Override
    public void act() {
        super.act();
        y += dy;
        if (y <= 2) {
            y = 2;
        }
        if (y >= spriteframework.Commons.BOARD_HEIGHT - imageHeight) {
            y = spriteframework.Commons.BOARD_HEIGHT - imageHeight;
        }
    }
}