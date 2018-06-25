package p05_OnlineRadioDatabase;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private List<Song> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    private String playlistLength() {

        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        for (Song song : songs) {
            seconds += song.getSeconds();
            minutes += song.getMinutes();
        }

        while (seconds > 59) {
            minutes++;
            seconds -= 60;
        }

        while (minutes > 59) {
            hours++;
            minutes -= 60;
        }


        return String.format("Playlist length: %dh %dm %ds", hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return String.format("Songs added: %d\n%s", this.songs.size(), playlistLength());


    }

}
