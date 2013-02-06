package tetris;

//import java.util.logging.Level;
//import java.util.logging.Logger;

/** @author UFPel - POO - Grupo 1 - 2011 */

/** Classe que reúne e executa, em diferentes threads,
 * as classes executáveis do jogo */
public class Tetris {

    public static void main(String[] args) {

        Executar1 executavel1 = new Executar1();
        Thread thread1 = new Thread(executavel1);
        //Executar2 executavel2 = new Executar2();
        //Thread thread2 = new Thread(executavel2);

        thread1.start();
        //thread2.start();
    }
}
