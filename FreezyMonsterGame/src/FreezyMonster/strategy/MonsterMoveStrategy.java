package FreezyMonster.strategy;

import FreezyMonster.sprite.Monster;
import spriteframework.Commons;
import spriteframework.sprite.Sprite;

import java.util.Random;

public class MonsterMoveStrategy implements MoveStrategy {
    private Random random = new Random();

    @Override
    public void move(Sprite sprite) {
        Monster monster = (Monster) sprite;
        monster.setX(monster.getX() + monster.getDx() * monster.getSpeed());
        monster.setY(monster.getY() + monster.getDy() * monster.getSpeed());

        if (monster.getX() <= 0 || monster.getX() >= Commons.BOARD_WIDTH - monster.getDimensions().width) {
            monster.setDx(-monster.getDx());
        }
        if (monster.getY() <= 0 || monster.getY() >= Commons.GROUND - monster.getDimensions().height) {
            monster.setDy(-monster.getDy());
        }

        if (random.nextInt(100) < 2) { // 2% de chance de mudar direção
            monster.setDx(random.nextInt(3) - 1);
            monster.setDy(random.nextInt(3) - 1);
            if (monster.getDx() == 0 && monster.getDy() == 0) {
                monster.setDx(1);
            }
        }
    }
}
