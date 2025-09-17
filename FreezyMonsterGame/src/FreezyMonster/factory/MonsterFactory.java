package FreezyMonster.factory;

import FreezyMonster.sprite.Monster;

public class MonsterFactory {
    public Monster createMonster(int x, int y, int type) {
        return new Monster(x, y, type);
    }
}
