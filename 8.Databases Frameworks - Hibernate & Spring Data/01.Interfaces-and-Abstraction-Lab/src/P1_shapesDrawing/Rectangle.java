package P1_shapesDrawing;

public class Rectangle implements Drawable {

    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public int getWidth() {
        return this.width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw() {

        for (int i = 0; i < height; i++) {

            System.out.print("*");

            for (int k = 1; k < width - 1; k++) {

                System.out.print(" ");
                if (i == 0 || i == (height - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print(" ");
            System.out.print("*");
            System.out.print("\n");
        }
    }
}
