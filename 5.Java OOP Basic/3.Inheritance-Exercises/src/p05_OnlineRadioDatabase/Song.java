package p05_OnlineRadioDatabase;

public class Song {

    private String artistName;
    private String songName;
    private int minutes;
    private int seconds;

    public Song(String artistName,String songName,String songLength){
        this.setArtistName(artistName);
        this.setSongName(songName);
        this.setSongLength(songLength);
    }

    private void setArtistName(String artistName){
        if(artistName.length() <3 || artistName.length() > 20)
            throw new IllegalArgumentException("Artist name should be between 3 and 20 symbols.");

        this.artistName = artistName;
    }

    private void setSongName(String songName){
        if(songName.length() < 3 || songName.length() > 30)
            throw new IllegalArgumentException("Song name should be between 3 and 30 symbols.");

        this.songName = songName;
    }

    private void setSongLength(String time){
        String[] timeInfo = time.split(":");

        if(timeInfo.length > 2 || !timeInfo[0].matches("\\d+") || !timeInfo[1].matches("\\d+"))
            throw new IllegalArgumentException("Invalid song length.");
        else {
            int minute = Integer.parseInt(timeInfo[0]);
            this.setMinutes(minute);

            int second = Integer.parseInt(timeInfo[1]);
            this.setSeconds(second);
        }
    }

    private void setMinutes(int minutes){
        if(minutes <0 || minutes > 14)
            throw new IllegalArgumentException("Song minutes should be between 0 and 14.");

        this.minutes = minutes;
    }

    private void setSeconds(int seconds){
        if(seconds < 0 || seconds > 59)
            throw new IllegalArgumentException("Song seconds should be between 0 and 59.");

        this.seconds = seconds;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }
}
