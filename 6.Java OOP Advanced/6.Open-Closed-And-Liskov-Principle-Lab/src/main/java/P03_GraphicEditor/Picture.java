package P03_GraphicEditor;

public class Picture implements Drawable {

    private int length;
    private int width;

    public Picture(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public String draw() {
        return String.format("I am picture with %d length and %d width",this.length,this.width);
    }
}
