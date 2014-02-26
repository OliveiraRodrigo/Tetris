package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Classe que exibe a tela executa as ações do jogo */
public class Executar1 implements Runnable {

    protected Tela tela;
    //protected Timer timer;
    private Som som;
    protected char pecaAtual;
    protected char pecaNext;
    protected boolean fixar = false;
    private boolean inicio;

    @Override
    public void run() {
        
        try {
            while(true) {
                tela = new Tela();
                som = new Som();
                try {
                    tela.pontuacao = new Pontuacao();
                    tela.pontuacao.recuperaPontuacao();
                }
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(Executar1.class.getName()).log(Level.SEVERE, null, ex);
                }
                tela.novo = false;
                inicio = true;
                tela.timer.comecar();
                while(!tela.novo) {
                    tela.jogo1.limpaRegistro();
                    
                    if(inicio) {
                        pecaNext = tela.jogo1.sorteiaPeca();
                        inicio = false;
                    }
                    
                    tela.jogo1.formaAtual = tela.jogo1.formaNext;
                    pecaAtual = pecaNext;
                    pecaNext = tela.jogo1.sorteiaPeca();
                    
                    int i, j, x, repete = 0;
                    for(x=0; x<=16; x++) { // (16 = 20 linhas tabuleiro - 4 linhas peça)
                        repete = tela.jogo1.formaAtual.delay*10;
                        while(repete > 0) {
                            
                            fixar = tela.jogo1.desenhaPeca(x);
                            
                            while(tela.jogo1.pausado){
                                Thread.sleep(100);
                                tela.render();
                            }
                            
                            if(tela.novo) {
                                repete = 0;
                            }
                            
                            if(!fixar) {
                                tela.render();
                                repete = tela.jogo1.apagaPeca(x, repete);
                            }
                            else {
                                repete = 0;
                                x = 17;
                            }
                            repete--;
                        }
                        
                        /** Se o jogo acabou, solicita o nome
                         * do jogador para o ranking */
                        if(tela.jogo1.gameOver() || tela.novo) {
                            tela.pontuacao.tempo = tela.timer.contar(true);
                            tela.pontuacao.insereJogador(tela.pegaNome());
                            tela.pontuacao.salvaPontuacao();
                            tela.pontuacao.exportaRanking();
                            x = 16;
                            if(!tela.novo) {
                                System.exit(0);
                            }
                        }
                        if(tela.jogo1.formaAtual.delay >= 15){
                            som.tocaAudio(0);
                        }
                    }
                    
                    if(!fixar) {
                        repete = tela.jogo1.formaAtual.delay/2;
                        
                        while(repete > 0) {
                            tela.jogo1.fixaPecaFundo();
                            if(tela.jogo1.formaAtual.especial && tela.jogo1.ajuste) {
                                tela.render();
                                Thread.sleep(tela.jogo1.formaAtual.delay);
                            }
                            repete = tela.jogo1.apagaPeca(16, repete);
                            repete--;
                        }
                        tela.jogo1.fixaPecaFundo();
                    }
                    
                    tela.pontuacao.contaPeca(pecaAtual);
                    tela.pontuacao.somaLinhas(tela.jogo1.linhasCompletas(20));
                    
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Tetris.class.getName()).log(Level.SEVERE, null, ex);
        } throw new UnsupportedOperationException("Not supported yet.");
    }
}
