package FreezyMonster.factory;

import FreezyMonster.sprite.FreezyRay;
import FreezyMonster.sprite.Goop;
import spriteframework.sprite.Sprite;

public class ProjectileFactory {
    public Sprite createFreezyRay(int x, int y, int dx, int dy) {
        return new FreezyRay(x, y, dx, dy);
    }

    public Sprite createGoop(int x, int y, int dx, int dy) {
        return new Goop(x, y, dx, dy);
    }
}
