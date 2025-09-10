package FreezyMonster;

public interface Commons extends spriteframework.Commons{
    int MONSTER_WIDTH = 30;
    int MONSTER_HEIGHT = 30;

    // Define o posicionamento e espaçamento dos monstros
    int MONSTER_SPACING_X = 35;
    int MONSTER_SPACING_Y = 35;
    int MONSTER_INIT_X = 100;
    int MONSTER_INIT_Y = 5;

    // Define o tamanho do jogador
    int PLAYER_WIDTH = 35;
    int PLAYER_HEIGHT = 45;

    // Define a condição de vitória
    int NUMBER_OF_MONSTERS_TO_DESTROY = 15;
}
