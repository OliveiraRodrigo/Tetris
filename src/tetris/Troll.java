package tetris;

import java.awt.Color;


/** @author UFPel - POO - Grupo 1 - 2011 */
public class Troll extends Forma {

    public Troll(Color corNeutra) {
        super(corNeutra);
        cor = Color.LIGHT_GRAY;
        tipo = 'R';
        posicoes = 1;
        lateral = 3;
        largura = 3;
        altura = 3;
        orientacao = 1;

        posiciona();
        lateralMax = 10 - largura;

    }

    @Override
    public final void posiciona() {
        limpaCor();
        /*    0 1 2 3 4 5 6 7 8 9
         *  0 . . . . . . . . . .
         *  1 . . . . # . . . . .
         *  2 . . . # . # . . . .
         *  3 . . . . # . . . . .
         */
        matrix[1][lateral+1] = cor;
        matrix[2][lateral+0] = cor;
        matrix[2][lateral+2] = cor;
        matrix[3][lateral+1] = cor;
        especial = false;
    }

    @Override
    public void praEsquerda() {
        super.praDireita();
        posiciona();
    }

    @Override
    public void praDireita() {
        super.praEsquerda();
        posiciona();
    }

    @Override
    public void gira() {
        /* Não faz nada */
    }

    @Override
    public void giraAnti() {
        /* Não faz nada */
    }
}
