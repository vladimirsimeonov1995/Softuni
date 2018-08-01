package P02_FileStream;

public class File extends StreamableImp{

    private String name;

    public File(int length, int bytesSent, String name) {
        super(length, bytesSent);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
