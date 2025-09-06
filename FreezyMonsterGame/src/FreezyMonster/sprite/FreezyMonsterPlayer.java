package FreezyMonster.sprite;

import spriteframework.sprite.Player;
import spriteframework.sprite.Sprite;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FreezyMonsterPlayer extends Player {
    public FreezyMonsterPlayer() {
        loadImage();
        getImageDimensions();
    }
    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            dy = -2;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = 2;
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
    }

    @Override
    public void loadImage(){
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/woody.png"));
        setWidth(ii.getImage().getWidth(null));
        setImage(ii.getImage());
    }


}
