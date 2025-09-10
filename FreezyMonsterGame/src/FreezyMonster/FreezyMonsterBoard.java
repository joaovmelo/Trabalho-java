package FreezyMonster;

import FreezyMonster.sprite.FreezyMonsterPlayer;
import FreezyMonster.sprite.Gosma;
import FreezyMonster.sprite.MonsterSprite;
import FreezyMonster.sprite.Ray;
import spriteframework.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

public class FreezyMonsterBoard extends AbstractBoard {
    private Ray ray;
    private int monstersKilled = 0;

    @Override
    protected Player createPlayer() {
        return new FreezyMonsterPlayer();
    }

    @Override
    protected void createBadSprites() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                int monsterNumber = (i * 5 + j) % 9 + 1;
                MonsterSprite monster = new MonsterSprite(
                        Commons.MONSTER_INIT_X + Commons.MONSTER_SPACING_X * j,
                        Commons.MONSTER_INIT_Y + Commons.MONSTER_SPACING_Y * i,
                        monsterNumber);
                badSprites.add(monster);
            }
        }
    }

    @Override
    protected void createOtherSprites() {
        ray = new Ray(0, 0, 0, 0);
        ray.die();
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            if (inGame && !ray.isVisible()) {
                FreezyMonsterPlayer p = (FreezyMonsterPlayer) player;
                ray = new Ray(p.getX(), p.getY(), p.getLastDx(), p.getLastDy());
            }
        }
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        if (ray.isVisible()) {
            g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
        }

        for (BadSprite monster : badSprites) {
            MonsterSprite ms = (MonsterSprite) monster;
            Gosma gosma = ms.getGosma();
            if (!gosma.isDestroyed()) {
                g.drawImage(gosma.getImage(), gosma.getX(), gosma.getY(), this);
            }
        }
    }

    @Override
    protected void update() {
        if (monstersKilled == Commons.NUMBER_OF_MONSTERS_TO_DESTROY) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        for (Player player : players) {
            player.act();
        }

        if (ray.isVisible()) {
            ray.act();

            if (ray.getX() < 0 || ray.getX() > spriteframework.Commons.BOARD_WIDTH || ray.getY() < 0 || ray.getY() > spriteframework.Commons.BOARD_HEIGHT) {
                ray.die();
            }

            for (BadSprite monster : badSprites) {
                if (monster.isVisible() && ray.isVisible()) {
                    if (ray.getRect().intersects(monster.getRect())) {
                        MonsterSprite ms = (MonsterSprite) monster;
                        if (!ms.isFrozen()) {
                            ms.freeze();
                        } else {
                            ms.die();
                            monstersKilled++;
                        }
                        ray.die();
                    }
                }
            }
        }

        for (BadSprite monster : badSprites) {
            if (monster.isVisible()) {
                monster.act();
            }
        }

        Random generator = new Random();
        for (BadSprite monster : badSprites) {
            int shot = generator.nextInt(150);
            MonsterSprite ms = (MonsterSprite) monster;
            Gosma gosma = ms.getGosma();

            if (shot == 5 && ms.isVisible() && gosma.isDestroyed()) {
                gosma.setDestroyed(false);
                gosma.setX(ms.getX());
                gosma.setY(ms.getY());
                gosma.setDx(generator.nextInt(3) - 1);
                gosma.setDy(generator.nextInt(3) - 1);
            }

            Player player = players.get(0);
            if (player.isVisible() && !gosma.isDestroyed()) {
                if (gosma.getRect().intersects(player.getRect())) {
                    player.setDying(true);
                    gosma.setDestroyed(true);
                    inGame = false;
                    message = "Game Over";
                }
            }

            if (!gosma.isDestroyed()) {
                gosma.act();
                if (gosma.getX() < 0 || gosma.getX() > spriteframework.Commons.BOARD_WIDTH || gosma.getY() < 0 || gosma.getY() > spriteframework.Commons.BOARD_HEIGHT) {
                    gosma.setDestroyed(true);
                }
                if(ray.isVisible() && ray.getRect().intersects(gosma.getRect())){
                    ray.die();
                    gosma.setDestroyed(true);
                }
            }
        }
    }
}