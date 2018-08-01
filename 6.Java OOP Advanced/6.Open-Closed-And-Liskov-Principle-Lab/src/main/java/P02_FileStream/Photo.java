package P02_FileStream;

public class Photo extends StreamableImp {

    private String photographer;

    public Photo(int length, int bytesSent, String photographer) {
        super(length, bytesSent);
        this.photographer = photographer;
    }

    public String getPhotographer() {
        return this.photographer;
    }
}
