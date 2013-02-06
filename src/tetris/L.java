package tetris;

import java.awt.Color;
import java.util.Random;

/** @author UFPel - POO - Grupo 1 - 2011 */
public class L extends Forma {


    public L(Color corNeutra) {
        super(corNeutra);
        cor = Color.BLUE;
        tipo = 'L';
        posicoes = 4;
        lateral = 3;

        /* Sorteia a posição inicial */
        Random aleatorio = new Random();
        orientacao = 1 + aleatorio.nextInt(posicoes); // random de 1 a 4

        /* Veja a representação quando "orientacao==3": lateral==4 */
        if (orientacao==3) lateral++;

        posiciona();
        dimensiona();

    }

    @Override
    public final void posiciona() {
        limpaCor();

        switch(orientacao) {

            case 1:
                /*    0 1 2 3 4 5 6 7 8 9
                 *  0 . . . . . . . . . .
                 *  1 . . . # # . . . . .
                 *  2 . . . . # . . . . .
                 *  3 . . . . # . . . . .
                 */
                matrix[1][lateral+0] = cor;
                matrix[1][lateral+1] = cor;
                matrix[2][lateral+1] = cor;
                matrix[3][lateral+1] = cor;
                especial = false;
                break;

            case 2:
                /*    0 1 2 3 4 5 6 7 8 9
                 *  0 . . . . . . . . . .
                 *  1 . . . . . # . . . .
                 *  2 . . . # # # . . . .
                 *  3 . . . . . . . . . .
                 */
                matrix[1+linhaFinal][lateral+2] = cor;
                matrix[2+linhaFinal][lateral+0] = cor;
                matrix[2+linhaFinal][lateral+1] = cor;
                matrix[2+linhaFinal][lateral+2] = cor;
                especial = true;
                break;

            case 3:
                /*    0 1 2 3 4 5 6 7 8 9
                 *  0 . . . . . . . . . .
                 *  1 . . . . # . . . . .
                 *  2 . . . . # . . . . .
                 *  3 . . . . # # . . . .
                 */
                matrix[1][lateral+0] = cor;
                matrix[2][lateral+0] = cor;
                matrix[3][lateral+0] = cor;
                matrix[3][lateral+1] = cor;
                especial = false;
                break;

            case 4:
                /*    0 1 2 3 4 5 6 7 8 9
                 *  0 . . . . . . . . . .
                 *  1 . . . . . . . . . .
                 *  2 . . . # # # . . . .
                 *  3 . . . # . . . . . .
                 */
                matrix[2][lateral+0] = cor;
                matrix[2][lateral+1] = cor;
                matrix[2][lateral+2] = cor;
                matrix[3][lateral+0] = cor;
                especial = false;
                break;

            default:
                break;
        }
    }

    public final void dimensiona() {
        if((orientacao == 1) || (orientacao == 3)) {
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
        switch(orientacao) {
            case 2:
            /* L terá a orientação 2 (ainda não tem) */
                if(lateral > 7) lateral--;
                break;

            case 3:
            /* L terá a orientação 3 (ainda não tem) */
                lateral++;
                break;

            case 4:
            /* L terá a orientação 4 (ainda não tem) */
                if(lateral > 0) lateral--;
                break;

            default:
                break;
        }
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
