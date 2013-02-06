package tetris;

import java.awt.Color;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Grade de fundo onde as peças se movem e se encaixam. */
public class Grade {
    
    /** A matriz onde será desenhada a grade de fundo. */
    protected Color[][] matrix;
    
    /** A cor da grade de fundo. Começa com preto. */
    protected Color corNeutra;
    
    /** Construtor da grade de fundo. Inicia uma matriz 20x10 com tudo em preto. */
    public Grade(Color corNeutra) {
        this.corNeutra = corNeutra;
        matrix = new Color[20][10];
        int i, j;
        for (i=0; i<20; i++) {
            for (j=0; j<10; j++) {
                matrix[i][j] = this.corNeutra;
            }
        }
    }

    public Color getCor() {
        return corNeutra;
    }

    public Color[][] getMatrix() {
        return matrix;
    }

    public void setCorNeutra(Color corNeutra) {
        this.corNeutra = corNeutra;
    }
    
    public void desenhaTudo() {
        System.out.print("\n");
        int i, j;
        for (i=0; i<20; i++) {
            for (j=0; j<10; j++) {
                if(matrix[i][j] == corNeutra) {
                    System.out.print(". ");
                }
                else {
                    System.out.print("# ");
                }
                if(j==9) {
                    System.out.print("\n");
                }
            }
        }
    } 





}
