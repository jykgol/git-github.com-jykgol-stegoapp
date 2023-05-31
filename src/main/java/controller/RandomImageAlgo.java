package controller;

import views.ImagePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class RandomImageAlgo {

    public Integer[][] generate(ImagePanel imagePanel){

        if(imagePanel.getImage() == null){
            imagePanel.setImage(new BufferedImage(260, 260, TYPE_INT_RGB));
        }

        int width = imagePanel.getImage().getWidth();
        int height = imagePanel.getImage().getHeight();
        Integer[][] result = new Integer[width][height];

        Random rnd = new Random();
        rnd.setSeed(255);

        for (int a = 0; a < width; a++) {
            for (int b = 0; b < height; b++) {

                Color c = new Color( rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                imagePanel.getImage().setRGB(a, b, c.getRGB());
                result[a][b] = c.getRGB();
            }
        }

        imagePanel.updateUI();
        return result;
    }
}
