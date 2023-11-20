package pokegame.main;

import java.io.IOException;
import java.io.InputStream;

import controllers.game.sound.SoundTrack;

public class Main {
    public static void main(String[] args) {
        SoundTrack soundTrack = new SoundTrack(3);

        // Certifique-se de que o arquivo está na pasta resources/sounds/tracks/
        try (InputStream inputStream = Main.class.getResourceAsStream("/sound/tracks/soundtrack_main.wav")) {
            soundTrack.addTrack(0, inputStream);
            soundTrack.playCurrentTrack();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container container = new Container();
        // soundTrack.stopCurrentTrack();
    }
}