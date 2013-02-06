package tetris;

import java.awt.Color;
import java.util.Random;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Peça 'I'. 
 * (as demais peças possuem atributos e métodos equivalentes) */
public class I extends Forma {

    public I(Color corNeutra) {
        super(corNeutra);
        cor = Color.PINK;
        tipo = 'I';
        posicoes = 2;
        lateral = 4;

        /* Sorteia a orientação inicial */
        Random aleatorio = new Random();
        orientacao = 1 + aleatorio.nextInt(posicoes); // random de 1 a 2

        /* Particularmente, quando orientacao==2, lateral==3.
         * Ver na representação. */
        if (orientacao==2) lateral--;

        posiciona();
        dimensiona();

    }

    @Override
    public final void posiciona() {
        limpaCor();
        if(orientacao == 1) {
            /*    0 1 2 3 4 5 6 7 8 9
             *  0 . . . . # . . . . .
             *  1 . . . . # . . . . .
             *  2 . . . . # . . . . .
             *  3 . . . . # . . . . .
             */
            matrix[0][lateral] = cor;
            matrix[1][lateral] = cor;
            matrix[2][lateral] = cor;
            matrix[3][lateral] = cor;
            especial = false;
        }
        else { /* orientacao==2 */
            /*    0 1 2 3 4 5 6 7 8 9
             *  0 . . . . . . . . . .
             *  1 . . . . . . . . . .
             *  2 . . . # # # # . . .
             *  3 . . . . . . . . . .
             */
            matrix[2+linhaFinal][lateral+0] = cor;
            matrix[2+linhaFinal][lateral+1] = cor;
            matrix[2+linhaFinal][lateral+2] = cor;
            matrix[2+linhaFinal][lateral+3] = cor;
            especial = true;
        }
    }

    public final void dimensiona() {
        if(orientacao == 1) {
            largura = 1;
            altura = 4;
        }
        else {
            largura = 4;
            altura = 1;
        }
        lateralMax = 10 - largura;
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
        super.gira();

        /* Ajusta no espaço para poder girar na posição correta */
        if(orientacao == 2) {
        /* I será horizontal (ainda não é) */
            if(lateral > 0) lateral--;
            if(lateral > 6) lateral = 6;
        }
        else
        /* I será vertical (ainda não é) */
            lateral++;

        posiciona();
        dimensiona();
    }
    
    @Override
    public void giraAnti() {
        super.giraAnti();
        posiciona();
        dimensiona();
    }
}
