package tetris;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.*;

/** @author UFPel - POO - Grupo 1 - 2011 */

public class Som {
    
    public void tocaAudio(int seleciona){  
        try {
            //audios que fazem parte do jogo  
            AudioInputStream stream1 = AudioSystem.getAudioInputStream(new File("linha.wav"));
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(new File("gira.wav"));
            
            // definicao
            AudioFormat format = stream1.getFormat();  

            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(  
                    AudioFormat.Encoding.PCM_SIGNED,  
                    format.getSampleRate(),  
                    format.getSampleSizeInBits()*2,  
                    format.getChannels(),  
                    format.getFrameSize()*2,  
                    format.getFrameRate(),  
                    true);        // big endian
                stream1 = AudioSystem.getAudioInputStream(format, stream1);  
                stream2 = AudioSystem.getAudioInputStream(format, stream2);  
            }
            // Create the clip  
            DataLine.Info info = new DataLine.Info(  
                Clip.class, stream1.getFormat(), ((int)stream1.getFrameLength()*format.getFrameSize()));  
            Clip clip = (Clip) AudioSystem.getLine(info);

            // This method does not return until the audio file is completely loaded  
            if (seleciona == 1){
                clip.open(stream1);
            }
            else if (seleciona == 2){
                clip.open(stream2);
            }
            
            // Start playing
            clip.start();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        } catch (UnsupportedAudioFileException e) {
        }
    }
}
