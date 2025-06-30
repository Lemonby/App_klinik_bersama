package util;

import java.awt.*;
import java.io.InputStream;

public class FontCreator {
    private static Font popins;

    public static Font getPopins() {
        if (popins == null) {
            try {
                // Use resorce font from package Font
                InputStream read = FontCreator.class.getResourceAsStream("/font/Poppins-Regular.ttf");
                if (read == null) throw new Exception("Font resource not found");

                popins = Font.createFont(Font.TRUETYPE_FONT, read);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(popins);

                System.out.println("Poppins font loaded from FontCreator");
            } catch (Exception e) {
                System.err.println("Error load font: " + e.getMessage());
                popins = new Font("Sanserif", Font.PLAIN, 14);
            }
        }

        return popins;
    }
}
