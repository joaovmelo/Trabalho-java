package FreezyMonster.strategy;

import spriteframework.sprite.Sprite;

public class LinearMoveStrategy implements MoveStrategy {
    @Override
    public void move(Sprite sprite) {
        sprite.setX(sprite.getX() + sprite.getDx());
        sprite.setY(sprite.getY() + sprite.getDy());
    }
}