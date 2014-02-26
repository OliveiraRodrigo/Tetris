package tetris;

import java.awt.Color;
import java.util.Random;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Classe que contém as ações do jogo */
public class Jogo {

    protected Grade fundo;
    protected Forma formaAtual;
    protected Forma formaNext;
    private Color corNeutra;
    protected Som som;
    private Timer timer;
    protected int trunfo;
    protected boolean[][] registro;
    protected boolean pausado;
    protected boolean ajuste;
    protected boolean praLeft;
    protected boolean praRight;
    protected boolean girou;
    protected boolean desenha;
    private boolean especial = false;
    
    public Jogo() {
        corNeutra = Color.BLACK;
        fundo = new Grade(corNeutra);
        formaAtual = new Forma(corNeutra);
        formaNext = new Forma(corNeutra);
        som = new Som();
        trunfo = 3;
        registro = new boolean[4][10];
        praLeft = false;
        praRight = false;
        girou = false;
        desenha = false;
        ajuste = false;
        pausado = false;
    }

    /** Seta a matriz de registro para false */
    public void limpaRegistro() {
        for(int i=3; i>=0; i--) {
            for(int j=9; j>=0; j--) {
                registro[i][j] = false;
            }
        }
    }

    /** Sorteia a próxima peça a cair no jogo */
    public char sorteiaPeca() {
        especial = false;
        
        switch(1 + new Random().nextInt(7)) {
            case 1:
                formaNext = new I(corNeutra);
                break;
            case 2:
                formaNext = new J(corNeutra);
                break;
            case 3:
                formaNext = new L(corNeutra);
                break;
            case 4:
                formaNext = new O(corNeutra);
                break;
            case 5:
                formaNext = new S(corNeutra);
                break;
            case 6:
                formaNext = new T(corNeutra);
                break;
            case 7:
                formaNext = new Z(corNeutra);
                break;
            case 8:
                formaNext = new Troll(corNeutra);
                break;
            default:
                break;
        }
        return formaNext.tipo;
    }

    /** Desenha temporariamente a peça na matriz */
    public boolean desenhaPeca(int x) throws InterruptedException {

        boolean fixar = false;
        ajuste = false;
        desenha = false;
        //Thread.sleep(formaAtual.delay/2);

        int bloco = 4;
        for(int i=3; i>=0; i--) {
            for(int j=9; j>=0; j--) {
                if(formaAtual.matrix[i][j] == formaAtual.cor) {
                    if(fundo.matrix[i+x][j] == corNeutra) {
                        if(desenha) {
                            fundo.matrix[i+x][j] = formaAtual.cor;
                            registro[i][j] = true;
                        }
                        else {
                            bloco--;
                            registro[i][j] = false;
                        }
                    }
                    else {
                        registro[i][j] = false;
                        if(!praLeft && !praRight && !girou) {
                            fixar = true;
                            fixar = fixaPeca(x);
                            return fixar;
                        }
                        else {
                            if(praLeft)
                                formaAtual.praDireita();
                            if(praRight)
                                formaAtual.praEsquerda();
                            if(girou) {
                                formaAtual.giraAnti();
                            }
                            praLeft = false;
                            praRight = false;
                            girou = false;
                            return false;
                        }
                    }
                }
                else {
                    registro[i][j] = false;
                }
                if(bloco == 0) {
                    bloco = 4;
                    i = 3;
                    j = 10;
                    desenha = true;
                    fixar = false;
                    praLeft = false;
                    praRight = false;
                    girou = false;
                }
            }
        }
        praLeft = false;
        praRight = false;
        girou = false;
        return fixar;
    }

    /** Apaga a peça da matriz para redesenhar */
    public int apagaPeca(int x, int repete) throws InterruptedException {

        if(formaAtual.delay < 15) {
            repete = 0;
        }
        if(repete == 2) {
            //Thread.sleep(formaAtual.delay);
            ajuste = false;
        }
        for(int i=3; i>=0; i--) {
            for(int j=9; j>=0; j--) {
                if(registro[i][j] == true) {
                    registro[i][j] = false;
                    if(x < 17) {
                        fundo.matrix[i+x][j] = corNeutra;
                    }
                }
            }
        }
        return repete;
    }
    
    /** Fixa a peça na matriz quando há colisão vertical */
    private boolean fixaPeca(int x) {

        ajuste = false;
        
        for(int i=3; i>=0; i--) {
            for(int j=9; j>=0; j--) {
                if(registro[i][j] == true) {
                    fundo.matrix[i+x][j] = corNeutra;
                    registro[i][j] = false;
                }
            }
        }
        
        for(int i=3; i>=0; i--) {
            for(int j=9; j>=0; j--) {
                if(formaAtual.matrix[i][j] == formaAtual.cor) {
                    if(x >= 1) {
                        if(fundo.matrix[i+x-1][j] == corNeutra) {
                            fundo.matrix[i+x-1][j] = formaAtual.cor;
                        }
                        else {
                            //System.out.println("ERRO -> x: " + x + ", j: " + j);
                        }
                    }
                    else {
                        fundo.matrix[i][j] = formaAtual.cor; // Game Over!
                    }
                }
            }
        }
        //som.tocaAudio(5);
        return true;
    }

    /** Fica a peça na última linha quando não houve colisão vertical */
    public void fixaPecaFundo() throws InterruptedException {
        boolean fixar = false;
        formaAtual.setLinhaFinal();
        ajuste = true;
        //desenha = false;
        int bloco = 4;

        for(int x=19; x>=16; x--) {
            for(int j=9; j>=0; j--) {
                if(formaAtual.matrix[x-16][j] == formaAtual.cor) {
                    if(fundo.matrix[x][j] == corNeutra) {
                        if(desenha) {
                            fundo.matrix[x][j] = formaAtual.cor;
                            registro[x-16][j] = true;
                            fixar = false;
                        }
                        else {
                            bloco--;
                            registro[x-16][j] = false;
                        }
                    }
                    else {
                        registro[x-16][j] = false;
                        if(!praLeft && !praRight && !girou) {
                            j = -1;
                            x = 15;
                            fixar = true;
                        }
                        else {
                            if(praLeft) {
                                formaAtual.praDireita();
                            }
                            if(praRight) {
                                formaAtual.praEsquerda();
                            }
                            if(girou) {
                                formaAtual.giraAnti();
                            }
                            praLeft = false;
                            praRight = false;
                            girou = false;
                            //x = 19;
                            //j = 10;
                            //desenha = true;
                            bloco = 4;
                            //limpaRegistro();
                        }
                    }
                }
                else {
                    registro[x-16][j] = false;
                }
                if(bloco == 0) {
                    bloco = 4;
                    x = 19;
                    j = 10;
                    desenha = true;
                    fixar = false;
                    praLeft = false;
                    praRight = false;
                    girou = false;
                }
            }
        }
        
        if(fixar) {
            for(int x=19; x>=16; x--) {
                for(int j=9; j>=0; j--) {
                    if(registro[x-16][j] == true) {
                        fundo.matrix[x][j] = corNeutra;
                        registro[x-16][j] = false;
                    }
                }
            }
            for(int x=19; x>=16; x--) {
                for(int j=9; j>=0; j--) {
                    if(formaAtual.matrix[x-16][j] == formaAtual.cor) {
                        fundo.matrix[x-1][j] = formaAtual.cor;
                    }
                }
            }
        }
        //som.tocaAudio(5);
    }

    /** Retorna se o jogo está pausado ou não */
    public boolean isPausado() {
        return pausado;
    }

    /** Procura recursivamente por linhas completas para eliminá-las */
    public int linhasCompletas(int linha) {
        if(linha <= 0) {
            return 0;
        }
        boolean linhaCheia = true;
        boolean linhaVazia = true;
        int quantidade = 0;

        for(int j=9; j>=0; j--) {

            if(fundo.matrix[linha-1][j] == corNeutra) {
                linhaCheia = false;
            }
            else {
                linhaVazia = false;
            }
        }

        if(linhaVazia) {
            return 0;
        }

        if(linhaCheia) {
            quantidade = 1 + copiaLinhas(linha);
            som.tocaAudio(1);
        }
        
        return quantidade + linhasCompletas(linha-1);
    }

    /** Método utilizado pelo linhasCompletas()
     * para descer uma linha para cada linha completa */
    private int copiaLinhas(int linha) {
        boolean linhaCheia = true;
        boolean linhaVazia = true;
        int quantidade = 0;

        for(int j=9; j>=0; j--) {
            if(fundo.matrix[linha-1][j] == corNeutra) {
                linhaCheia = false;
            }
            else {
                linhaVazia = false;
            }
        }

        if(linhaCheia) {
            quantidade = linhasCompletas(linha-1);
        }

        if(linhaVazia) {
            return 0;
        }
        else {
            for(int j=9; j>=0; j--) {
                fundo.matrix[linha-1][j] = fundo.matrix[linha-2][j];
            }
        }
        return quantidade + copiaLinhas(linha-1);
    }

    /** Garante que a peça I seja a próxima. Usar até 3 vezes. **/
    public void usaTrunfo() {
        if(!especial) {
            especial = true;
            if(trunfo>0) {
                trunfo--;
                formaNext = new I(corNeutra);
                som.tocaAudio(6);
            }
        }
    }
    
    public void trollar() {
        if(!especial) {
            especial = true;
            formaNext = new Troll(corNeutra);
        }
    }
    
    /** Verifica e retorna o fim ou não do jogo */
    public boolean gameOver() {
        for(int j=9; j>=0; j--) {
            if(fundo.matrix[1][j] != corNeutra) {
                som.tocaAudio(7);
                return true;
            }
        }
        return false;
    }
}