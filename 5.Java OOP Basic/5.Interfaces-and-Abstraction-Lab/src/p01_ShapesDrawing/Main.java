package p01_ShapesDrawing;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Drawable> figures = new ArrayList<>(){{
            add(new Circle(5));
            add(new Rectangle(4,5));
        }};

        for (Drawable figure : figures) {
            printDrawable(figure);
        }


    }

    public static void printDrawable(Drawable drawable){
        drawable.draw();
    }

}
