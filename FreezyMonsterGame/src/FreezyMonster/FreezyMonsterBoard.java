// FreezyMonsterBoard.java
package FreezyMonster;

import FreezyMonster.sprite.PlayerFreezy;
import spriteframework.sprite.*;
import spriteframework.*;
import FreezyMonster.sprite.FreezyRay;
import FreezyMonster.sprite.Goop;
import FreezyMonster.sprite.Monster;
import spriteframework.sprite.Player;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class FreezyMonsterBoard extends AbstractBoard {

    private LinkedList<FreezyRay> freezyRays;
    private LinkedList<Goop> goops;
    private int monstersFrozen = 0;
    private int totalMonsters = 0;
    private Random random = new Random();

    @Override
    protected void createBadSprites() {
        // Criar monstros - exemplo com 4 monstros
        totalMonsters = 4;
        for (int i = 0; i < totalMonsters; i++) {
            Monster monster = new Monster(50 + i * 70, 50);
            badSprites.add(monster);
        }
    }

    @Override
    protected void createOtherSprites() {
        freezyRays = new LinkedList<>();
        goops = new LinkedList<>();
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        // Desenhar raios congelantes
        for (FreezyRay ray : freezyRays) {
            if (ray.isVisible()) {
                g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
            }
        }

        // Desenhar gosmas
        for (Goop goop : goops) {
            if (goop.isVisible()) {
                g.drawImage(goop.getImage(), goop.getX(), goop.getY(), this);
            }
        }
    }

    @Override
    protected void update() {
        if (!inGame) return;

        Player player = getPlayer(0);
        player.act();

        // Atualizar monstros
        for (BadSprite bad : badSprites) {
            if (bad instanceof Monster) {
                Monster monster = (Monster) bad;
                if (monster.isVisible() && !monster.isFrozen()) {
                    monster.act();

                    // Chance de soltar gosma
                    if (random.nextInt(100) < 2) { // 2% de chance por ciclo
                        releaseGoop(monster);
                    }
                }
            }
        }

        // Atualizar raios congelantes
        updateFreezeRays();

        // Atualizar gosmas
        updateGoops();

        // Verificar vitória
        if (monstersFrozen >= totalMonsters) {
            message = "You Win!";
            inGame = false;
        }
    }

    private void updateFreezeRays() {
        LinkedList<FreezyRay> raysToRemove = new LinkedList<>();

        for (FreezyRay ray : freezyRays) {
            if (ray.isVisible()) {
                ray.act();

                // Verificar se saiu da tela
                if (ray.getX() < 0 || ray.getX() > Commons.BOARD_WIDTH ||
                        ray.getY() < 0 || ray.getY() > Commons.BOARD_HEIGHT) {
                    raysToRemove.add(ray);
                    continue;
                }

                // Verificar colisão com monstros
                for (BadSprite bad : badSprites) {
                    if (bad instanceof Monster && bad.isVisible() &&
                            ray.getRect().intersects(bad.getRect())) {
                        Monster monster = (Monster) bad;
                        if (!monster.isFrozen()) {
                            monster.freeze();
                            monstersFrozen++;
                        }
                        raysToRemove.add(ray);
                        break;
                    }
                }

                // Verificar colisão com gosmas
                for (Goop goop : goops) {
                    if (goop.isVisible() && ray.getRect().intersects(goop.getRect())) {
                        goop.die();
                        raysToRemove.add(ray);
                        break;
                    }
                }
            } else {
                raysToRemove.add(ray);
            }
        }

        freezyRays.removeAll(raysToRemove);
    }

    private void updateGoops() {
        LinkedList<Goop> goopsToRemove = new LinkedList<>();

        for (Goop goop : goops) {
            if (goop.isVisible()) {
                goop.act();

                // Verificar se saiu da tela
                if (goop.getX() < 0 || goop.getX() > Commons.BOARD_WIDTH ||
                        goop.getY() < 0 || goop.getY() > Commons.BOARD_HEIGHT) {
                    goopsToRemove.add(goop);
                    continue;
                }

                // Verificar colisão com player
                Player player = getPlayer(0);
                if (player.isVisible() && goop.getRect().intersects(player.getRect())) {
                    player.setDying(true);
                    inGame = false;
                    break;
                }
            } else {
                goopsToRemove.add(goop);
            }
        }

        goops.removeAll(goopsToRemove);
    }

    private void releaseGoop(Monster monster) {
        int directionX = random.nextInt(3) - 1; // -1, 0, ou 1
        int directionY = random.nextInt(3) - 1; // -1, 0, ou 1

        // Garantir que a gosma se mova em pelo menos uma direção
        if (directionX == 0 && directionY == 0) {
            directionX = 1;
        }

        Goop goop = new Goop(monster.getX(), monster.getY(), directionX, directionY);
        goops.add(goop);
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shootFreezeRay(player);
        }
    }

    /*private void shootFreezeRay(PlayerFreezy player) {
        int dx = player.getDx();
        int dy = player.getDy();

        // Determinar direção baseada no movimento do player
        if (dx == 0 && dy == 0) {
            // Se não está se movendo, atira para cima
            dy = -5;
        } else {
            // Normaliza a direção
            if (dx != 0) dx = dx > 0 ? 5 : -5;
            if (dy != 0) dy = dy > 0 ? 5 : -5;
        }

        FreezyRay ray = new FreezyRay(player.getX() + player.getImageWidth() / 2,
                player.getY() + player.getImageHeight() / 2, dx, dy);
        freezyRays.add(ray);
    }*/
    private void shootFreezeRay(Player player) {
        int dx = player.getDx();
        int dy = player.getDy();

        // Determinar direção baseada no movimento do player
        if (dx == 0 && dy == 0) {
            // Se não está se movendo, atira para cima
            dy = -5;
        } else {
            // Normaliza a direção
            if (dx != 0) dx = dx > 0 ? 5 : -5;
            if (dy != 0) dy = dy > 0 ? 5 : -5;
        }

        FreezyRay ray = new FreezyRay(player.getX() + player.getImageWidth() / 2,
                player.getY() + player.getImageHeight() / 2, dx, dy);
        freezyRays.add(ray);
    }

}