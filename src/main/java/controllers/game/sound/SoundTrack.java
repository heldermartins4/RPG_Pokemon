package controllers.game.sound;

// import java.io.File;
import java.io.InputStream;

public class SoundTrack {
    private Sound[] tracks;
    private int currentTrackIndex;

    public SoundTrack(int numTracks) {
        tracks = new Sound[numTracks];
        currentTrackIndex = 0;
    }

    public void addTrack(int index, InputStream inputStream) {
        if (index >= 0 && index < tracks.length) {
            try {
                // System.out.println("Adding track at index " + index);
                tracks[index] = new Sound(inputStream);
                // System.out.println("Track added successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Invalid track index");
        }
    }

    public void playCurrentTrack() {
        if (tracks[currentTrackIndex] != null) {
            tracks[currentTrackIndex].play();
        }
    }

    public void loopCurrentTrack() {
        if (tracks[currentTrackIndex] != null) {
            tracks[currentTrackIndex].loop();
        }
    }

    public void stopCurrentTrack() {
        if (tracks[currentTrackIndex] != null) {
            tracks[currentTrackIndex].stop();
        }
    }

    public void setCurrentTrack(int index) {
        if (index >= 0 && index < tracks.length) {
            currentTrackIndex = index;
        } else {
            throw new IllegalArgumentException("Invalid track index");
        }
    }
}