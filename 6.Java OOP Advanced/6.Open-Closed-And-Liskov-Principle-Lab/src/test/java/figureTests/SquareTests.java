package figureTests;

import P05_Square.Rectangle;
import P05_Square.Square;
import org.junit.Assert;
import org.junit.Test;

public class SquareTests {

    @Test
    public void getSidesTest() {
        Rectangle square = new Square();
        square.setHeight(5);
        Assert.assertEquals(5, square.getWidth());
        Assert.assertEquals(5, square.getHeight());
        square.setWidth(10);
        Assert.assertEquals(10, square.getWidth());
        Assert.assertEquals(10, square.getHeight());
    }

    @Test
    public void getAreaTest() {
        Rectangle square = new Square();
        square.setWidth(10);
        Assert.assertEquals(100, square.getArea());
    }

    @Test
    public void getPerimeterTest() {
        Rectangle square = new Square();
        square.setWidth(10);
        Assert.assertEquals(40, square.getPerimeter());
    }



}
