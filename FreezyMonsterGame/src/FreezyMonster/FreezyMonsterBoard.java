package FreezyMonster;

import FreezyMonster.sprite.PlayerFreezy;
import spriteframework.sprite.*;
import spriteframework.*;
import FreezyMonster.sprite.FreezyRay;
import FreezyMonster.sprite.Goop;
import FreezyMonster.sprite.Monster;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class FreezyMonsterBoard extends AbstractBoard {

    // Agora podemos inicializar as variáveis na declaração, que é a forma mais limpa.
    private LinkedList<FreezyRay> freezyRays = new LinkedList<>();
    private LinkedList<Goop> goops = new LinkedList<>();
    private final int numberOfMonsters = 9;
    private Random random = new Random();

    @Override
    protected Player createPlayer() {
        return new PlayerFreezy();
    }

    @Override
    protected void createBadSprites() {
        for (int i = 0; i < numberOfMonsters; i++) {
            int x = random.nextInt(Commons.BOARD_WIDTH - 50) + 20;
            int y = random.nextInt(Commons.GROUND - 100) + 20;
            badSprites.add(new Monster(x, y, i + 1));
        }
    }

    @Override
    protected void createOtherSprites() {
        // Este método precisa existir, mas pode ficar vazio se as listas já foram criadas.
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        for (FreezyRay ray : freezyRays) {
            if (ray.isVisible()) {
                g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
            }
        }
        for (Goop goop : goops) {
            if (goop.isVisible()) {
                g.drawImage(goop.getImage(), goop.getX(), goop.getY(), this);
            }
        }
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shootFreezeRay((PlayerFreezy) player);
        }
    }

    private void shootFreezeRay(PlayerFreezy player) {
        int speed = 5;
        int dx = player.getDx();
        int dy = player.getDy();

        if (dx == 0 && dy == 0) {
            dy = -speed;
        } else {
            dx = (int) (Math.signum(dx) * speed);
            dy = (int) (Math.signum(dy) * speed);
        }

        freezyRays.add(new FreezyRay(player.getX(), player.getY(), dx, dy));
    }

    @Override
    protected void update() {
        if (!inGame) return;

        getPlayer(0).act();
        updateMonstersAndGoops();
        updateProjectiles();
        checkCollisions();
        checkWinCondition();
    }

    private void updateMonstersAndGoops() {
        for (BadSprite bad : badSprites) {
            Monster monster = (Monster) bad;
            monster.act();

            if (!monster.isFrozen() && monster.isVisible() && random.nextInt(700) < 1) { // 0.5% chance
                int dirX = random.nextInt(3) - 1;
                int dirY = random.nextInt(3) - 1;
                if (dirX == 0 && dirY == 0) dirX = 1;
                goops.add(new Goop(monster.getX(), monster.getY(), dirX, dirY));
            }
        }
    }

    private void updateProjectiles() {
        LinkedList<FreezyRay> raysToRemove = new LinkedList<>();
        for (FreezyRay ray : freezyRays) {
            ray.act();
            if (ray.getX() < 0 || ray.getX() > Commons.BOARD_WIDTH || ray.getY() < 0 || ray.getY() > Commons.BOARD_HEIGHT) {
                raysToRemove.add(ray);
            }
        }
        freezyRays.removeAll(raysToRemove);

        LinkedList<Goop> goopsToRemove = new LinkedList<>();
        for (Goop goop : goops) {
            goop.act();
            if (goop.getX() < 0 || goop.getX() > Commons.BOARD_WIDTH || goop.getY() < 0 || goop.getY() > Commons.BOARD_HEIGHT) {
                goopsToRemove.add(goop);
            }
        }
        goops.removeAll(goopsToRemove);
    }

    private void checkCollisions() {
        LinkedList<FreezyRay> raysToRemove = new LinkedList<>();
        LinkedList<Goop> goopsToRemove = new LinkedList<>();

        for (FreezyRay ray : freezyRays) {
            for (BadSprite bad : badSprites) {
                Monster monster = (Monster) bad;
                if (monster.isVisible() && !monster.isFrozen() && ray.getRect().intersects(monster.getRect())) {
                    monster.freeze();
                    raysToRemove.add(ray);
                }
            }
            for (Goop goop : goops) {
                if (goop.isVisible() && ray.getRect().intersects(goop.getRect())) {
                    goopsToRemove.add(goop);
                    raysToRemove.add(ray);
                }
            }
        }

        for (Goop goop : goops) {
            if (getPlayer(0).isVisible() && goop.getRect().intersects(getPlayer(0).getRect())) {
                getPlayer(0).setDying(true);
                message = "Game Over - A gosma te pegou!";
                inGame = false;
            }
        }

        freezyRays.removeAll(raysToRemove);
        goops.removeAll(goopsToRemove);
    }

    private void checkWinCondition() {
        int frozenCount = 0;
        for (BadSprite bad : badSprites) {
            if (((Monster) bad).isFrozen()) {
                frozenCount++;
            }
        }
        if (frozenCount == numberOfMonsters) {
            message = "Você Venceu!";
            inGame = false;
        }
    }
}