package tetris;

import java.awt.Color;
import java.util.Random;

/** @author UFPel - POO - Grupo 1 - 2011 */
public class Z extends Forma {

    public Z(Color corNeutra) {
        super(corNeutra);
        cor = Color.GREEN;
        tipo = 'Z';
        posicoes = 2;
        lateral = 4;

        /* Sorteia a posição inicial */
        Random aleatorio = new Random();
        orientacao = 1 + aleatorio.nextInt(posicoes); // random de 1 a 2

        /* Veja a representação quando "orientacao==2": lateral==3 */
        if (orientacao==2) lateral--;

        posiciona();
        dimensiona();

    }

    @Override
    public final void posiciona() {
        limpaCor();
        if(orientacao == 1) {
            largura = 2;
            altura = 3;
            /*    0 1 2 3 4 5 6 7 8 9
             *  0 . . . . . . . . . .
             *  1 . . . . . # . . . .
             *  2 . . . . # # . . . .
             *  3 . . . . # . . . . .
             */
            matrix[1][lateral+1] = cor;
            matrix[2][lateral+0] = cor;
            matrix[2][lateral+1] = cor;
            matrix[3][lateral+0] = cor;
            especial = false;
        }
        else { /* orientacao==2 */
            largura = 3;
            altura = 2;
            /*    0 1 2 3 4 5 6 7 8 9
             *  0 . . . . . . . . . .
             *  1 . . . # # . . . . .
             *  2 . . . . # # . . . .
             *  3 . . . . . . . . . .
             */
            matrix[1+linhaFinal][lateral+0] = cor;
            matrix[1+linhaFinal][lateral+1] = cor;
            matrix[2+linhaFinal][lateral+1] = cor;
            matrix[2+linhaFinal][lateral+2] = cor;
            especial = true;
        }
    }

    public final void dimensiona() {
        if(orientacao == 1) {
            largura = 2;
            altura = 3;
        }
        else {
            largura = 3;
            altura = 2;
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
        /* Z será horizontal (ainda não é) */
            if(lateral > 0) lateral--;
        }
        else
        /* Z será vertical (ainda não é) */
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
