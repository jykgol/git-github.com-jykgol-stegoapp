/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HACSfolder;

import java.awt.Color;
import java.awt.image.BufferedImage;
import views.ImagePanel;

/**
 *
 * @author jyk22
 */
public class HACS_Class {

    private double[] P, Q, R, S;
    private final double X = 10, Y = 25, E = 1, F = 1;
    private final double Z = -4.660;
    private static final String HACS = "HACS";
    private static final String Simple_Rand = "Simple Rand";
    private final SimpleRand simpleRand;

    public HACS_Class(double size, SimpleRand SimpleRand) {

        simpleRand = SimpleRand;
        int Size = (int) Math.pow(size, 2);

        P = new double[Size];
        Q = new double[Size];
        R = new double[Size];
        S = new double[Size];
        P[0] = 0.2;
        Q[0] = 0.1;
        R[0] = 0.75;
        S[0] = 2;
        int round = Size;

        for (int i = 1; i < Size; i++) {
            P[i] = Math.abs(Math.round(X * (Y - P[i - 1]))) % round;
            Q[i] = (int) Math.abs(Math.round(-P[i - 1] * R[i - 1] - Z * Q[i - 1] + F * S[i - 1])) % round;
            R[i] = Math.abs(Math.round(-Y + P[i - 1] * Q[i - 1])) % round;
            S[i] = Math.abs(Math.round(-E * Q[i - 1])) % round;
        }
    }

    public void HACSChaoticMap_FullImage(ImagePanel panel1, String method) {

        BufferedImage InputImage = panel1.getImage();
        int size = InputImage.getWidth();
        int Size = (int) Math.pow(size, 2);
        Color bufLast, bufRand;
        int x, y, xRand, yRand, aRand;

        for (int a = Size - 1; a >= 0; a--) {
            x = a % size;
            y = a / size;
            if (method == HACS) {
                aRand = (int) Math.abs(Q[a] % a);
            } else {
                aRand = (int) (simpleRand.NewRand() * a);
            }
            xRand = aRand % size;
            yRand = aRand / size;

            bufLast = new Color(InputImage.getRGB(x, y));
            bufRand = new Color(InputImage.getRGB(xRand, yRand));

            InputImage.setRGB(x, y, bufRand.getRGB());
            InputImage.setRGB(xRand, y, bufLast.getRGB());
        }
        panel1.setImage(InputImage);
    }

    public void HACSChaoticMap_FullImageDecode(ImagePanel panel1, String method) {

        BufferedImage InputImage = panel1.getImage();
        int size = InputImage.getWidth();
        int Size = (int) Math.pow(size, 2);
        Double[] SRmassive = new Double[Size];

        if (method == Simple_Rand) {
            for (int i = 0; i < Size; i++) {
                SRmassive[i] = simpleRand.NewRand();
            }
        }

        Color bufLast, bufRand;
        int x, y, xRand, yRand, aRand, i = 0;

        for (int a = 0; a < Size; a++) {
            x = a % size;
            y = a / size;
            if (method == HACS) {
                aRand = (int) Math.abs(Q[a] % a);
            } else {
                i++;
                aRand = (int) (SRmassive[Size - i] * a);
            }
            xRand = aRand % size;
            yRand = aRand / size;

            bufLast = new Color(InputImage.getRGB(x, y));
            bufRand = new Color(InputImage.getRGB(xRand, yRand));

            InputImage.setRGB(x, y, bufRand.getRGB());
            InputImage.setRGB(xRand, y, bufLast.getRGB());
        }
        panel1.setImage(InputImage);
    }

    public void HACSChaoticMap_Gorizontal(ImagePanel panel1, String method) {

        BufferedImage InputImage = panel1.getImage();
        int size = InputImage.getWidth();
        Color bufLast, bufRand;
        int a, xRand;
        for (int y = size - 1; y >= 0; y--) {
            for (int x = size - 1; x >= 0; x--) {
                if (method == HACS) {
                    a = x + y * size;
                    xRand = (int) (Math.abs(Q[a] % x)) % size;
                } else {
                    xRand = (int) (simpleRand.NewRand() * x);
                }
                bufLast = new Color(InputImage.getRGB(x, y));
                bufRand = new Color(InputImage.getRGB(xRand, y));

                InputImage.setRGB(x, y, bufRand.getRGB());
                InputImage.setRGB(xRand, y, bufLast.getRGB());

            }
        }

        panel1.setImage(InputImage);

    }

    public void HACSChaoticMap_Gorizontal_Decode(ImagePanel panel1, String method) {

        BufferedImage InputImage = panel1.getImage();
        int size = InputImage.getWidth();
        int Size = size * size;
        Double[] SRmassive = new Double[Size];

        if (method == Simple_Rand) {
            for (int i = 0; i < Size; i++) {
                SRmassive[i] = simpleRand.NewRand();
            }
        }
        Color bufLast, bufRand;
        int a = 0, xRand;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                if (method == HACS) {
                    a = x + y * size;
                    xRand = (int) Math.abs(Q[a] % x) % size;
                } else {
                    a++;
                    xRand = (int) (SRmassive[Size - a] * x);
                }

                bufLast = new Color(InputImage.getRGB(x, y));
                bufRand = new Color(InputImage.getRGB(xRand, y));

                InputImage.setRGB(x, y, bufRand.getRGB());
                InputImage.setRGB(xRand, y, bufLast.getRGB());

            }
        }

        panel1.setImage(InputImage);
    }

    public void HACSChaoticMap_Vertical(ImagePanel panel1, String method) {

        BufferedImage InputImage = panel1.getImage();
        int size = InputImage.getWidth();
        Color bufLast, bufRand;
        int a, yRand;
        for (int x = size - 1; x >= 0; x--) {
            for (int y = size - 1; y >= 0; y--) {

                if (method == HACS) {
                    a = x + y * size;
                    yRand = (int) Math.abs(R[a] % y) % size;
                } else {
                    yRand = (int) (simpleRand.NewRand() * y);
                }

                bufLast = new Color(InputImage.getRGB(x, y));
                bufRand = new Color(InputImage.getRGB(x, yRand));

                InputImage.setRGB(x, y, bufRand.getRGB());
                InputImage.setRGB(x, yRand, bufLast.getRGB());

            }
        }

        panel1.setImage(InputImage);
    }

    public void HACSChaoticMap_Vertical_Decode(ImagePanel panel1, String method) {

        BufferedImage InputImage = panel1.getImage();
        int size = InputImage.getWidth();
        int Size = size * size;
        Double[] SRmassive = new Double[Size];

        if (method == Simple_Rand) {
            for (int i = 0; i < Size; i++) {
                SRmassive[i] = simpleRand.NewRand();
            }
        }
        Color bufLast, bufRand;
        int a = 0, yRand;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (method == HACS) {
                    a = x + y * size;
                    yRand = (int) Math.abs(R[a] % y) % size;
                } else {
                    a++;
                    yRand = (int) (SRmassive[Size - a] * y);
                }

                bufLast = new Color(InputImage.getRGB(x, y));
                bufRand = new Color(InputImage.getRGB(x, yRand));

                InputImage.setRGB(x, y, bufRand.getRGB());
                InputImage.setRGB(x, yRand, bufLast.getRGB());

            }
        }

        panel1.setImage(InputImage);
    }

    public void RandomImage(ImagePanel panel1, int size) {
        BufferedImage randImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        int r, g, b, a;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                a = x + y * size;
                r = (int) Math.abs(Q[a] % 256);
                g = (int) Math.abs(R[a] % 256);
                b = (int) Math.abs(S[a] % 256);
                randImage.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
        panel1.setImage(randImage);
    }

    public void RandomImageSimple(ImagePanel panel1, int size) {
        BufferedImage randImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        int r, g, b;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                r = (int) Math.abs(simpleRand.NewRand() * 256);
                g = (int) Math.abs(simpleRand.NewRand() * 256);
                b = (int) Math.abs(simpleRand.NewRand() * 256);
                randImage.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
        panel1.setImage(randImage);
    }

    public void RandomImage2(ImagePanel panel1, int size) {
        BufferedImage randImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        int r, g, b, a;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                a = x + y * size;
                r = (int) Math.abs((Q[a] * R[a]) % 256);
                g = (int) Math.abs((R[a] - S[a]) % 256);
                b = (int) Math.abs((S[a] + Q[a]) % 256);
                randImage.setRGB(x, y, new Color(r, g, b).getRGB());
            }
        }
        panel1.setImage(randImage);
    }

}
