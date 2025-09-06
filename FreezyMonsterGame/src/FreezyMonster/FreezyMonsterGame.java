package FreezyMonster;

import spriteframework.MainFrame;
import spriteframework.AbstractBoard;

import java.awt.EventQueue;

public class FreezyMonsterGame extends MainFrame {
    public FreezyMonsterGame() {
        super("Freezy Monster");
    }
    protected  AbstractBoard createBoard() {
        return new FreezyMonsterBoard();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FreezyMonsterGame();
        });
    }
}
