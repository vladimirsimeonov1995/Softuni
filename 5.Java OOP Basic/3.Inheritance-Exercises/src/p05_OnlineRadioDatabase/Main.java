package p05_OnlineRadioDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfSongs = Integer.parseInt(reader.readLine());

        Playlist playlist = new Playlist();

        for (int i = 0; i < numberOfSongs; i++) {
            String[] info = reader.readLine().split(";");
            try {
                Song song = new Song(info[0],info[1],info[2]);
                playlist.addSong(song);
                System.out.println("Song added.");
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }

        System.out.println(playlist);


    }
}
