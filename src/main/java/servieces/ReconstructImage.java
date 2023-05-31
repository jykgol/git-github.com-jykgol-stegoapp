/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servieces;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author jyk22
 */

public class ReconstructImage {

    private final BufferedImage inputImage;
    private final Integer w;
    private final Integer h;
    private final Integer N;
    public BufferedImage result;

    public ReconstructImage(BufferedImage inputImage) {
        this.inputImage = inputImage;

        
        this.w = (this.inputImage.getHeight() % 2 == 0 ? this.inputImage.getHeight() + 1 : this.inputImage.getHeight());
        this.h = (this.inputImage.getWidth() % 2 == 0 ? this.inputImage.getWidth() + 1 : this.inputImage.getWidth());
        
        if (this.w < this.h) {
            this.N = this.h;
        } else {
            this.N = this.w;
        }
        result = new BufferedImage(this.N, this.N, BufferedImage.TYPE_INT_RGB);
        this.reconstruct();
    }

    private void reconstruct() {
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if (i >= this.w || j >= this.h) {
                    this.result.setRGB(i, j, new Color(255, 255, 255).getRGB());
                } else {
                    this.result.setRGB(i, j, new Color(inputImage.getRGB(i, j)).getRGB());
                }
            }
        }
    }

    public BufferedImage getNewImage() {
        return this.result;
    }
}
