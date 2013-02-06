package tetris;

import java.awt.Color;


/** @author UFPel - POO - Grupo 1 - 2011 */
public class O extends Forma {

    public O(Color corNeutra) {
        super(corNeutra);
        cor = Color.RED;
        tipo = 'O';
        posicoes = 1;
        lateral = 4;
        largura = 2;
        altura = 2;
        orientacao = 1;

        posiciona();
        lateralMax = 10 - largura;

    }

    @Override
    public final void posiciona() {
        limpaCor();
        /*    0 1 2 3 4 5 6 7 8 9
         *  0 . . . . . . . . . .
         *  1 . . . . # # . . . .
         *  2 . . . . # # . . . .
         *  3 . . . . . . . . . .
         */
        matrix[1+linhaFinal][lateral+0] = cor;
        matrix[1+linhaFinal][lateral+1] = cor;
        matrix[2+linhaFinal][lateral+0] = cor;
        matrix[2+linhaFinal][lateral+1] = cor;
        especial = true;
    }

    @Override
    public void praEsquerda() {
        super.praEsquerda();
        posiciona();
    }

    @Override
    public void praDireita() {
        super.praDireita();
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
