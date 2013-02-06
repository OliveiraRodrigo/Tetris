package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.JFrame;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Classe que exibe a tela executa as ações do jogo 2 */
public class Executar2 extends Executar1 {

    @Override
    public void run() {

        try {
            while(true) {
                tela = new Tela();
                try {
                    tela.pontuacao2 = new Pontuacao();
                    tela.pontuacao2.recuperaPontuacao();
                }
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(Executar1.class.getName()).log(Level.SEVERE, null, ex);
                }
                tela.novo = false;
                    while(!tela.novo) {
                    tela.jogo2.limpaRegistro();
                    pecaAtual = tela.jogo2.sorteiaPeca();
                    int i, j, x, repete = 0;
                    for(x=0; x<=16; x++) { // (16 = 20 linhas tabuleiro - 4 linhas peça)
                        repete = tela.jogo2.formaAtual.delay;
                        while(repete > 0) {
                            fixar = tela.jogo2.desenhaPeca(x);
                            
                            while(tela.jogo2.pausado){
                                Thread.sleep(1);
                            }
                            
                            if(tela.novo) {
                                repete = 0;
                            }
                            
                            if(!fixar) {
                                tela.render();
                                repete = tela.jogo2.apagaPeca(x, repete);
                            }
                            else {
                                repete = 0;
                                x = 17;
                            }
                            repete--;
                        }
                        
                        /** Se o jogo acabou, solicita o nome
                         * do jogador para o ranking */
                        if(tela.jogo2.gameOver() || tela.novo) {
                            tela.pontuacao2.insereJogador(tela.pegaNome());
                            tela.pontuacao2.salvaPontuacao();
                            tela.pontuacao2.exportaRanking();
                            x = 16;
                            if(!tela.novo) {
                                System.exit(0);
                            }
                        }
                    }
                    
                    if(!fixar) {
                        tela.jogo2.fixaPecaFundo();
                        if(tela.jogo2.formaAtual.especial && tela.jogo2.ajuste) {
                            tela.render();
                            Thread.sleep(tela.jogo2.formaAtual.delay*20);
                        }
                    }
                    
                    tela.pontuacao2.contaPeca(pecaAtual);
                    tela.pontuacao2.somaLinhas(tela.jogo2.linhasCompletas(20));
                    
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Tetris.class.getName()).log(Level.SEVERE, null, ex);
        } throw new UnsupportedOperationException("Not supported yet.");
    }
}
