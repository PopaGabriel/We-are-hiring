package Graphics.Renderers;

import java.awt.*;

/*
this class is used for easier change of the interface colors
instead of manually going through all objects and changing their
color we just have to change one of these
*/
public class StaticColorsPalet extends Color{
    public StaticColorsPalet(int r, int g, int b) {
        super(r, g, b);
    }
    public static Color getColorCellIdle() {
        return Color.LIGHT_GRAY;
    }
    public static Color getColorCellSelect() {
        return Color.YELLOW;
    }
    public static Color getColorCellCorner() {
        return Color.GREEN;
    }
    public static Color getColorWriting() {
        return Color.ORANGE;
    }
}
