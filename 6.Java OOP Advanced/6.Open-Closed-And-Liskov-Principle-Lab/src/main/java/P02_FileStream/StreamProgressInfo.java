package P02_FileStream;

public class StreamProgressInfo {
    private Streamable streamable;

    public StreamProgressInfo(Streamable streamable) {
        this.streamable = streamable;
    }

    public int calculateCurrentPercent() {
        return (this.streamable.getBytesSent() * 100) / this.streamable.getLength();
    }
}
