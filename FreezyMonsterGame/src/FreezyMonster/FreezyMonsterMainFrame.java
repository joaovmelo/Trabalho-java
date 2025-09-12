package FreezyMonster;

import spriteframework.*;
import spriteframework.sprite.*;
public class FreezyMonsterMainFrame extends MainFrame {

    public FreezyMonsterMainFrame() {
        super("Freezy Monster");
    }

    @Override
    protected AbstractBoard createBoard() {
        return new FreezyMonsterBoard();
    }

    public static void main(String[] args) {
        new FreezyMonsterMainFrame();
    }
}
