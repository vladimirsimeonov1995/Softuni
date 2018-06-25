package p01_p02_Box;

public class Box {

    private double length;
    private double width;
    private double height;

    private double surfaceArea;
    private double lateralSurfaceArea;
    private double volume;


    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
        this.setAreasAndVolume();
    }

    private void setLength(double length) {
        if(length <= 0){
            throw new IllegalArgumentException("Length cannot be zero or negative. ");
        }

        this.length = length;
    }

    private void setWidth(double width) {
        if(width <= 0){
            throw new IllegalArgumentException("Width cannot be zero or negative. ");
        }

        this.width = width;
    }

    private void setHeight(double height) {
        if(height <= 0){
            throw new IllegalArgumentException("Height cannot be zero or negative. ");
        }

        this.height = height;
    }

    private double getLength() {
        return this.length;
    }

    private double getWidth() {
        return this.width;
    }

    private double getHeight() {
        return this.height;
    }

    public double getSurfaceArea() {
        return this.surfaceArea;
    }

    public double getLateralSurfaceArea() {
        return this.lateralSurfaceArea;
    }

    public double getVolume() {
        return this.volume;
    }

    private void setAreasAndVolume(){
        this.surfaceArea = 2*this.getLength()*this.getWidth()
                + 2*this.getLength()*this.getHeight()
                + 2*this.getWidth()*this.getHeight();

        this.lateralSurfaceArea = 2*this.getLength()*this.getHeight()
                +2*this.getWidth()*this.getHeight();

        this.volume = this.getLength()*this.getWidth()*this.getHeight();
    }

    @Override
    public String toString() {
        return String.format("Surface Area - %.2f\n" +
                        "Lateral Surface Area - %.2f\n" +
                        "Volume - %.2f\n",
                this.getSurfaceArea(),
                this.getLateralSurfaceArea(),
                this.getVolume());
    }
}