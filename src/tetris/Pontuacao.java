package tetris;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Classe que define os ítens e critérios de pontuação do jogo,
 * salva e recupera pontuações anteriores */
public class Pontuacao implements Serializable {
    
    protected transient int I;
    protected transient int J;
    protected transient int L;
    protected transient int O;
    protected transient int S;
    protected transient int T;
    protected transient int Z;
    protected transient int linhas;
    //protected transient int totalPontos;
    protected int contaJogadores = 0;
    protected int substituiJogador = 0;
    protected int indexHigh = 0;
    protected String[][] ranking;
    protected transient String tempo;
    
    public Pontuacao() {
        ranking = new String[1000][3];
        I = 0;
        J = 0;
        L = 0;
        O = 0;
        S = 0;
        T = 0;
        Z = 0;
        linhas = 0;
        //totalPontos = 0;

    }
    
    /** Conta o número de ocorrências de cada peça */
    public void contaPeca(char peca) {
        switch(peca) {
            case 'I':
                I++;
                break;
            case 'J':
                J++;
                break;
            case 'L':
                L++;
                break;
            case 'O':
                O++;
                break;
            case 'S':
                S++;
                break;
            case 'T':
                T++;
                break;
            case 'Z':
                Z++;
                break;
            default:
                break;
        }
    }
    
    /** Define critérios de pontuação para linhas completas
     * e atribui os pontos correspondentes */
    public void somaLinhas(int linhas) {
        if(linhas == 1)
            this.linhas += 50;
        if(linhas == 2)
            this.linhas += 150;
        if(linhas == 3)
            this.linhas += 250;
        if(linhas == 4)
            this.linhas += 500;
    }
    
    /** Retorna o total de pontos por meio da soma de todos os ítens */
    public int getTotalPontos() {
        return (linhas + I + J + L + O + S + T + Z);
    }
    
    /** Busca a mais alta pontuação no ranking
     * e retorna junto com o nome do jogador */
    public String[] highScore() {
        
        String[] maior = new String[3];
        int teste = 0;
        
        if(contaJogadores == 0) {
            maior[0] = "Ninguém";
            maior[1] = "0";
            maior[2] = "00:00";
            return maior;
        }
        
        for(int i=0; i<contaJogadores; i++) {
            if(Integer.parseInt(ranking[i][1]) > teste) {
                teste = Integer.parseInt(ranking[i][1]);
                indexHigh = i;
            }
        }
        
        maior[0] = ranking[indexHigh][0];
        maior[1] = ranking[indexHigh][1];
        maior[2] = ranking[indexHigh][2]; //data.getTime();
        return maior;
    }
    
    /** Ao fim do jogo, insere o jogador no ranking com seus pontos
     * (máximo de 1000 jogadores) */
    public void insereJogador(String nomeJogador) {

        while(nomeJogador.length() < 10) {
            nomeJogador = nomeJogador.concat(" ");
        }
        if(nomeJogador.length() > 10) {
            nomeJogador = nomeJogador.substring(0, 10);
        }

        if(contaJogadores < 1000) {
            ranking[contaJogadores][0] = nomeJogador; // Nome
            ranking[contaJogadores][1] = Integer.toString(getTotalPontos()); // Pontos
            ranking[contaJogadores][2] = tempo; // Tempo
            contaJogadores++;
        }
        else {
            if(substituiJogador == 1000) {
                substituiJogador = 0;
            }
            if(substituiJogador == indexHigh) {
                if(++substituiJogador == 1000) {
                    substituiJogador = 0;
                }
            }
            ranking[substituiJogador][0] = nomeJogador; // Nome
            ranking[substituiJogador][1] = Integer.toString(getTotalPontos()); // Pontos
            ranking[substituiJogador][2] = tempo; // Tempo
            substituiJogador++;
        }
    }
    
    /** Ao fim do jogo, salva a pontuação e o ranking em um arquivo de objeto */
    public void salvaPontuacao() {
        
        try {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("pontuacao"));
        out.writeObject(this);
        out.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /** Restaura dados de pontuação anteriores, se houver */
    public void recuperaPontuacao() throws ClassNotFoundException {
        
        Pontuacao estado = new Pontuacao();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("pontuacao"));
            estado = (Pontuacao) in.readObject();
            contaJogadores = estado.contaJogadores;
            substituiJogador = estado.substituiJogador;
            ranking = estado.ranking;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Salva o ranking em um arquivo de texto */
    public void exportaRanking() {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileWriter("Ranking.txt"));
            
            outputStream.println("=============== TETRIS ================");
            outputStream.println();
            outputStream.println("                Ranking");
            outputStream.println();
            outputStream.println("---------------------------------------");
            outputStream.println("Jogador\t\tPontos\t\tDuração");
            outputStream.println("---------------------------------------");
            outputStream.println();
            
            for(int i=0; i<contaJogadores; i++) {
                outputStream.println(ranking[i][0] + "\t"+("[" + ranking[i][1] +
                    "]                ").substring(0, 16)+"[" + ranking[i][2] + "]");
            }
            
            outputStream.println();
            outputStream.println("=======================================");
            outputStream.println();
            outputStream.println("Rodrigo Oliveira");
            
            outputStream.close();
        }
        catch(IOException erroDeIO){
            System.out.println(erroDeIO.getMessage());
        }
    }
}
