package FreezyMonster;

import spriteframework.*;

public class FreezyMonsterMainFrame extends MainFrame {

    public FreezyMonsterMainFrame() {
        super("Freezy Monster");
    }

    @Override
    protected AbstractBoard createBoard() {
        return new FreezyMonsterBoard();
    }

    public static void main(String[] args) {
        // Invoca o construtor da superclasse (JFrame) na thread de eventos da UI
        java.awt.EventQueue.invokeLater(() -> {
            new FreezyMonsterMainFrame();
        });
    }
}