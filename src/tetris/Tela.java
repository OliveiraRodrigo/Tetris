package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
//import javax.swing.JInternalFrame;


/** @author UFPel - POO - Grupo 1 - 2011 */

/** Classe que representa os elementos gráficos do jogo
 * e monitora as teclas de ação */
public class Tela extends JFrame implements KeyListener {
    protected Jogo jogo1;
    protected Jogo2 jogo2;
    protected Timer timer;
    private BufferStrategy bs;
    protected boolean visual;
    protected boolean desenhaGrade;
    //protected JFrame tela2;
    //protected JInternalFrame tela2;
    protected Pontuacao pontuacao;
    protected Pontuacao pontuacao2;
    protected boolean novo;
    protected long inicio;
    
    public Tela() {
        jogo1 = new Jogo();
        jogo2 = new Jogo2();
        timer = new Timer();
        //tela2 = new JInternalFrame();
        //tela2 = new JFrame();
        //tela2 = new JDesktopPane();
        //this.add(tela2);
        //tela2.setLocation(40,22);
        pontuacao = new Pontuacao();
        pontuacao2 = new Pontuacao();
        novo = false;
        addKeyListener(this);
        //tela2.addKeyListener(this);
        this.setTitle("Tetris");
        //tela2.setTitle("Jogador 2");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //tela2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setExtendedState(Jogo.MAXIMIZED_BOTH); //frame maximizado
        this.setSize(533,735);
        //tela2.setSize(533,735);
        this.setLocation(440, 0);
        this.setMaximumSize(new Dimension(533,735));
        this.setMinimumSize(new Dimension(371,735));
        //tela2.setVisible(true);
        this.setVisible(true);
        this.initialize();
        setIgnoreRepaint(true);
        visual = true;
        desenhaGrade = true;
        
    }

    /** Inicializa a tela */
    public final void initialize() {
        createBufferStrategy(2);
        bs = getBufferStrategy();
        render();
    }

    /** Configura e atualiza o desenho dos objetos na tela */
    public void render() {
        int a=54;//54

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.lightGray);
        g.fill3DRect(366, 502, 152, 182, false);          // Fundo Legenda
        
        g.setColor(Color.black);
        g.fill3DRect(366, 38+a, 152, 292, false);         // Fundo Peças
        g.fill3DRect(366, 37, 152, 48, false);            // Fundo Pontos
        g.fill3DRect(366, 392, 152, 47, false);           // Fundo Trunfos
        g.fill3DRect(366, 447, 152, 47, false);           // Fundo High Score
        g.fill3DRect(366, 688, 152, 31, false);           // Fundo Timer

                                                          /* Texto Legenda */
        g.drawString("Ctrl", 378, 527);
        g.drawString("Pausa", 453, 527);
        
        g.drawString("Esc", 378, 562);
        g.drawString("Sai", 453, 562);
        
        g.drawString("Espaço", 378, 597);
        g.drawString("Trunfo", 453, 597);
        
        g.drawLine(386, 617, 386, 637);
        g.drawLine(386, 617, 382, 627);
        g.drawLine(386, 617, 390, 627);
        g.drawString("Gira", 453, 633);
        
        g.drawLine(386, 652, 386, 672);
        g.drawLine(386, 672, 382, 662);
        g.drawLine(386, 672, 390, 662);
        g.drawString("Desce", 453, 668);
                                                          /*****************/
        g.setColor(Color.darkGray);
        g.draw3DRect(369, 41, 145, 39, false);               // Grade Pontos
        for(int i=0; i<7; i++) {
            g.draw3DRect(369, i*41+42+a, 145, 38, false);    // Grade Peças
        }
        g.draw3DRect(369, 396, 145, 38, false);              // Grade Trunfos
        g.draw3DRect(369, 451, 145, 38, false);              // Grade High Score
        g.draw3DRect(369, 691, 145, 24, false);              // Grade Timer
        g.drawString(timer.contar(jogo1.pausado), 427, 708); // Timer

        /* Marca Next */
        if(jogo1.formaNext.tipo == 'I') {
            g.fill3DRect(369, 43+a, 146, 38, visual);
        }
        if(jogo1.formaNext.tipo == 'J') {
            g.fill3DRect(369, 84+a, 146, 38, visual);
        }
        if(jogo1.formaNext.tipo == 'L') {
            g.fill3DRect(369, 125+a, 146, 38, visual);
        }
        if(jogo1.formaNext.tipo == 'O') {
            g.fill3DRect(369, 166+a, 146, 38, visual);
        }
        if(jogo1.formaNext.tipo == 'S') {
            g.fill3DRect(369, 207+a, 146, 38, visual);
        }
        if(jogo1.formaNext.tipo == 'T') {
            g.fill3DRect(369, 248+a, 146, 38, visual);
        }
        if(jogo1.formaNext.tipo == 'Z') {
            g.fill3DRect(369, 289+a, 146, 38, visual);
        }
        
        g.setColor(Color.GRAY);
        for(int i=0; i<5; i++) {
            g.draw3DRect(370, i*35+507, 142, 31, false);      // Grade Legenda
        }
        g.drawString(pontuacao.highScore()[0].substring(0, 7), 452, 467);
                                                              // Nome High Score
        g.setColor(Color.WHITE);
        g.drawString("Pontos", 386, 65);                      // Texto Pontos
        if(pontuacao.getTotalPontos() < 10000) {
            g.drawString(Integer.toString(pontuacao.getTotalPontos()), 475, 65);
        }                                                     // Pontos
        else {
            g.drawString(Integer.toString(pontuacao.getTotalPontos()), 455, 65);
        }                                                     // Pontos
        
        g.fill3DRect(14, 37, 344, 684, false);                // Borda da Grade
        
        g.drawString(pontuacao.highScore()[1], 452, 482);     // High Score
        
                                                              /* Peças */
        g.setColor(Color.pink);
        g.fill3DRect(385, 56+a, 10, 10, visual);
        g.fill3DRect(395, 56+a, 10, 10, visual);
        g.fill3DRect(405, 56+a, 10, 10, visual);
        g.fill3DRect(415, 56+a, 10, 10, visual);
        g.drawString(Integer.toString(pontuacao.I), 475, 66+a);
        g.drawString("Trunfos", 384, 420);                      // Texto Trunfos
        g.drawString(Integer.toString(jogo1.trunfo), 475, 420); // Trunfos
        
        g.setColor(Color.cyan);
        g.fill3DRect(405, 87+a, 10, 10, visual);
        g.fill3DRect(405, 97+a, 10, 10, visual);
        g.fill3DRect(405, 107+a, 10, 10, visual);
        g.fill3DRect(395, 107+a, 10, 10, visual);
        g.drawString(Integer.toString(pontuacao.J), 475, 107+a);
        
        g.setColor(Color.blue);
        g.fill3DRect(395, 128+a, 10, 10, visual);
        g.fill3DRect(395, 138+a, 10, 10, visual);
        g.fill3DRect(395, 148+a, 10, 10, visual);
        g.fill3DRect(405, 148+a, 10, 10, visual);
        g.drawString(Integer.toString(pontuacao.L), 475, 148+a);
        
        g.setColor(Color.red);
        g.fill3DRect(395, 175+a, 10, 10, visual);
        g.fill3DRect(395, 185+a, 10, 10, visual);
        g.fill3DRect(405, 175+a, 10, 10, visual);
        g.fill3DRect(405, 185+a, 10, 10, visual);
        g.drawString(Integer.toString(pontuacao.O), 475, 189+a);
        g.drawString("High", 384, 467);                         // Texto High
        g.drawString("Score", 384, 482);                        // Texto Score
        
        g.setColor(Color.yellow);
        g.fill3DRect(400, 215+a, 10, 10, visual);
        g.fill3DRect(410, 215+a, 10, 10, visual);
        g.fill3DRect(390, 225+a, 10, 10, visual);
        g.fill3DRect(400, 225+a, 10, 10, visual);
        g.drawString(Integer.toString(pontuacao.S), 475, 230+a);
        
        g.setColor(Color.magenta);
        g.fill3DRect(395, 251+a, 10, 10, visual);
        g.fill3DRect(395, 261+a, 10, 10, visual);
        g.fill3DRect(395, 271+a, 10, 10, visual);
        g.fill3DRect(405, 261+a, 10, 10, visual);
        g.drawString(Integer.toString(pontuacao.T), 475, 271+a);
        
        g.setColor(Color.green);
        g.fill3DRect(390, 297+a, 10, 10, visual);
        g.fill3DRect(400, 297+a, 10, 10, visual);
        g.fill3DRect(400, 307+a, 10, 10, visual);
        g.fill3DRect(410, 307+a, 10, 10, visual);
        g.drawString(Integer.toString(pontuacao.Z), 475, 312+a);
                                                              /*********/
        /** Grade do jogo */
        for(int i=0; i<20; i++) {
            for(int j=0; j<10; j++) {

                g.setColor(jogo1.fundo.matrix[i][j]);
                g.fill3DRect((j*34)+15,(i*34)+38, 34, 34, visual);
                if(desenhaGrade) {
                    g.setColor(Color.darkGray);
                    g.draw3DRect((j*34)+15,(i*34)+38, 34, 34, false);
                }
                /*
                else {
                    g.setColor(Color.BLACK);
                    g.draw3DRect((j*34)+15,(i*34)+38, 34, 34, false);
                }
                */
            }
        }
        
        g.dispose();
        bs.show();
    }
    
    /** Solicita o nome do jogador ao fim do jogo */
    public String pegaNome() {
        String nomeJogador = null;
        nomeJogador = javax.swing.JOptionPane.showInputDialog("Quem é você?");
        
        if(nomeJogador == null || "".equals(nomeJogador)) {
            nomeJogador = "Anônimo";
        }
        return nomeJogador;
    }
    
    @Override
    public void keyPressed(KeyEvent evt) {

        switch(evt.getKeyCode()) {

            case KeyEvent.VK_SHIFT:
                visual = !visual;
                break;
            case KeyEvent.VK_NUMPAD0:
                desenhaGrade = !desenhaGrade;
                break;
            case KeyEvent.VK_ENTER:
                novo = true;
                this.setVisible(false);
                break;
            case KeyEvent.VK_CONTROL:
                jogo1.pausado = !jogo1.pausado;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(1);
                break;
            case KeyEvent.VK_LEFT:
                jogo1.formaAtual.praEsquerda();
                jogo1.praLeft = true;
                break;
            case KeyEvent.VK_UP:
                jogo1.formaAtual.gira();
                jogo1.girou = true;
                break;
            case KeyEvent.VK_RIGHT:
                jogo1.formaAtual.praDireita();
                jogo1.praRight = true;
                break;
            case KeyEvent.VK_DOWN:
                jogo1.formaAtual.praBaixo();
                break;
            case KeyEvent.VK_SPACE:
                jogo1.usaTrunfo();
                break;
            case KeyEvent.VK_M:
                jogo1.som.mute();
                break;
            case KeyEvent.VK_T:
                jogo1.trollar();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
