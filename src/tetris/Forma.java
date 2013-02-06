package tetris;

import java.awt.Color;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Forma vazia, com atributos e métodos comuns a todas as formas específicas. */
public class Forma {

    /*
     *    . . . . . . . . . .
     *    . . . . . . . . . .
     *    . . . . . . . . . .
     *    . . . . . . . . . .
     *
     */

    /** A matriz onde será desenhada a peça. (matrix[linha][coluna]) */
    protected Color[][] matrix;

    /** A cor de cada peça. A cor da forma vazia é preta. */
    protected Color cor;

    protected Color corNeutra;

    private Som som;

    protected char tipo = ' ';

    /** Número de posições possíveis. Ex: I tem 2 posições. */
    protected int posicoes;

    /** Orientação atual (de 1 a 'posicoes'). */
    protected int orientacao;

    /** Largura da peça. Muda dependendo da orientação atual. */
    protected int largura;

    /** Altura da peça. Muda dependendo da orientação atual. */
    protected int altura;

    /** Onde está lateralmente (de 0 a lateralMax). */
    protected int lateral;

    /** Salva o atributo 'lateral' para casos de reorientação da peça. */
    private int lateralBk;
    
    /** Máximo de posições laterais dependendo da largura. */
    protected int lateralMax;

    /**  */
    protected int linhaFinal;

    /**  */
    protected boolean especial;

    /**  */
    protected int delay;
    
    /** Construtor da forma vazia.
     *  Inicia uma matriz 4x10 com tudo em corNeutra. */
    public Forma(Color corNeutra) {
        matrix = new Color[4][10]; // [linha][coluna]
        this.corNeutra = corNeutra;
        linhaFinal = 0;
        especial = false;
        delay = 30;
    }

    public void limpaCor() {
        int i, j;
        for (i=0; i<4; i++) {
            for (j=0; j<10; j++) {
                matrix[i][j] = corNeutra;
            }
        }
    }

    public Color[][] getMatrix() {
        return matrix;
    }

    public Color getCor() {
        return this.cor;
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public int getLateral() {
        return lateral;
    }

    public int getDelay() {
        return delay;
    }

    public void setCorNeutra(Color corNeutra) {
        this.corNeutra = corNeutra;
    }

    public void setLinhaFinal() {
        linhaFinal = 1;
        posiciona();
    }

    /** Alterna o atributo 'orientacao' entre as orientações possíveis. */
    public void gira() {
        //som.tocaAudio(2);
        if(orientacao == posicoes)
             orientacao = 1;
        else orientacao++;
        lateralBk = lateral;
    }
    
    public void giraAnti() {
        if(orientacao == 1)
             orientacao = posicoes;
        else orientacao--;
        lateral = lateralBk;
    }

    /** Move a peça para a esquerda.
     *  Método genérico que considera apenas o atributo 'lateral'. */
    public void praEsquerda() {
        if(lateral>0)
            lateral--;
    }

    /** Move a peça para a direita.
     *  Método genérico que considera apenas os atributos 'lateral' e 'lateralMax'. */
    public void praDireita() {
        if(lateral<lateralMax)
            lateral++;
    }

    public void praBaixo() {
        delay = 1;
    }

    public void posiciona() {
        //throw new UnsupportedOperationException("Método ainda não implementado.");
    }
}
