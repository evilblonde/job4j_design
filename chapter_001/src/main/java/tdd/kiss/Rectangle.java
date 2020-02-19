package tdd.kiss;

import java.util.Comparator;
import java.util.Objects;

public class Rectangle {
    public int width;
    public int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width
                && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    public static class RectangleComparator implements Comparator<Rectangle> {
        @Override
        public int compare(Rectangle rectangle, Rectangle t1) {
            return Integer.compare(rectangle.height * rectangle.width, t1.height * t1.width);
        }
    }
}
