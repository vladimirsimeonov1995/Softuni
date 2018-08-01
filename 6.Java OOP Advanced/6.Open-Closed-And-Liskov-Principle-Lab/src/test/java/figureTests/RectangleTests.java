package figureTests;


import P05_Square.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTests {
    @Test
    public void getSidesTest() {
        Rectangle rect = new Rectangle();
        rect.setHeight(5);
        rect.setWidth(10);
        Assert.assertEquals(10, rect.getWidth());
        Assert.assertEquals(5, rect.getHeight());
    }

    @Test
    public void getAreaTest() {
        Rectangle rect = new Rectangle();
        rect.setHeight(5);
        rect.setWidth(10);
        Assert.assertEquals(50, rect.getArea());
    }

    @Test
    public void getPerimeterTest() {
        Rectangle rect = new Rectangle();
        rect.setHeight(5);
        rect.setWidth(10);
        Assert.assertEquals(30, rect.getPerimeter());
    }
}
