package tetris;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/** @author UFPel - POO - Grupo 1 - 2011 */

public class Som {
    
    private AudioInputStream stream[] = new AudioInputStream[10];
    private AudioFormat format;
    private Clip clip;
    private Boolean mute = false;
    
    public void tocaAudio(int seleciona){  
    // This method does not return until the audio file is completely loaded  
        
        if (!mute){
        
        try {
            
                stream[0] = AudioSystem.getAudioInputStream(new File("anda.wav"));
                stream[1] = AudioSystem.getAudioInputStream(new File("linha.wav"));
                stream[2] = AudioSystem.getAudioInputStream(new File("gira.wav"));
                stream[3] = AudioSystem.getAudioInputStream(new File("lado.wav"));
                stream[4] = AudioSystem.getAudioInputStream(new File("desce.wav"));
                stream[5] = AudioSystem.getAudioInputStream(new File("fixa.wav"));
                stream[6] = AudioSystem.getAudioInputStream(new File("trunfo.wav"));
                stream[7] = AudioSystem.getAudioInputStream(new File("fim.wav"));
                
                // definicao
                format = stream[0].getFormat();
                if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    format = new AudioFormat(  
                        AudioFormat.Encoding.PCM_SIGNED,  
                        format.getSampleRate(),  
                        format.getSampleSizeInBits()*2,  
                        format.getChannels(),  
                        format.getFrameSize()*2,  
                        format.getFrameRate(),  
                        true);        // big endian
                }
                
                // Create the clip  
                DataLine.Info info = new DataLine.Info(  
                    Clip.class, stream[0].getFormat(), ((int)stream[0].getFrameLength()*format.getFrameSize()));  
                clip = (Clip) AudioSystem.getLine(info);

                for (int i=0; i<=7; i++){
                    stream[i] = AudioSystem.getAudioInputStream(format, stream[i]);
                }
            
                clip.open(stream[seleciona]);
                
                // Start playing
                clip.start();

            } catch (UnsupportedAudioFileException e) {
            } catch (IOException e) {
            } catch (LineUnavailableException e) {
            }
        }
    }
    
    public void mute(){
        if (mute){
            mute = false;
        }
        else{
            mute = true;
        }
    }
}
