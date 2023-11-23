package controllers.sound;

import java.io.InputStream;

public class SoundFX {
    
    Sound sfx; // Sound effect

    public void play(String sound) {
        
        InputStream is = getClass().getResourceAsStream("/sounds/sfx/"+sound);

        try {
            this.sfx = new Sound(is);
            this.sfx.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.sfx.stop();
    }

    public void loop() {
        this.sfx.loop();
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
