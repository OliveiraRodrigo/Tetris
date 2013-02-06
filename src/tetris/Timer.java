package tetris;

/** @author UFPel - POO - Grupo 1 - 2011 */

public class Timer {
    
    protected long inicio;
    protected long cents;
    protected long segundos;
    private long minutos;
    
    public void comecar() {
        
        inicio = System.currentTimeMillis();
        cents = 0;
        segundos = 0;
        minutos = 0;
    }
    
    public String contar(boolean pausado) {
        
        long temp = System.currentTimeMillis() - inicio;

        if(temp >= 100) {
            inicio += 100;
            if(!pausado) {
                cents++;
            }
        }
        
        if(cents == 10) {
            cents = 0;
            segundos++;
        }
        
        if(segundos == 60) {
            segundos = 0;
            minutos++;
        }
        
        if(minutos < 10) {
            if(segundos < 10) {
                return "0"+Long.toString(minutos)+":0"+Long.toString(segundos);
            }
            else {
                return "0"+Long.toString(minutos)+":"+Long.toString(segundos);
            }
        }
        else {
            if(segundos < 10) {
                return Long.toString(minutos)+":0"+Long.toString(segundos);
            }
            else {
                return Long.toString(minutos)+":"+Long.toString(segundos);
            }
        }
    }

    public long getCents() {
        return cents;
    }
    
    

}
