package util;

import java.util.Map;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    
    // Image cache to avoid repeated loading
    private static final Map<String, ImageIcon> imageCache = new HashMap<>();
    private static final Map<String, ImageIcon> iconCache = new HashMap<>();

    public static ImageIcon getImage(String path, int width, int height) {
        String key = path + "_" + width + "_" + height;
        if (imageCache.containsKey(key)) {
            return imageCache.get(key);
        }

        try {
            ImageIcon icon = new ImageIcon(ImageLoader.class.getResource(path));
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH); // Image: digunakan untuk memanipulasi atau mengubah ukuran gambar dengan method .getScaledInstance().
            ImageIcon scaledIcon = new ImageIcon(scaledImage);                                        // ImageIcon: membungkus object image agar bisa langsung digunkan pada komponen GUI
            imageCache.put(key, scaledIcon);
            return scaledIcon;
        } catch (Exception e) {
            System.err.println("Failed to load image: " + path);
            return null;
        }
    }

    public static ImageIcon getIcon(String iconName, int size) {
        String key = iconName + "_" + size;
        if (iconCache.containsKey(key)) {
            return iconCache.get(key);
        }
        
        try {
            String path = "/img_src/" + iconName + ".png";
            ImageIcon icon = new ImageIcon(ImageLoader.class.getResource(path));
            Image scaledImage = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            iconCache.put(key, scaledIcon);
            return scaledIcon;
        } catch (Exception e) {
            System.err.println("Failed to load icon: " + iconName);
            return null;
        }
    }
    
    public static void clearCaches() {
        imageCache.clear();
        iconCache.clear();
    }
}