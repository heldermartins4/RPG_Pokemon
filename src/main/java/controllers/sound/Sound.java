
package controllers.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private Clip clip;

    public Sound(InputStream inputStream) {
        try {

            System.out.println("Creating AudioInputStream");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            System.out.println("AudioInputStream created successfully");

            // Verificar se o formato do AudioInputStream é suportado
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                throw new LineUnavailableException("Audio format not supported");
            }

            // Abrir o Clip apenas se o AudioInputStream for não nulo
            if (audioInputStream != null) {
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioInputStream);
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
