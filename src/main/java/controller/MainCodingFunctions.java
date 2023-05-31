/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DNK.DNK_functions;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import servieces.EncryptDecrypt;
import views.ImagePanel;
import views.ProgressDialog;

/**
 *
 * @author jyk22
 */
public class MainCodingFunctions {

    private MainFunctions MainFun = new MainFunctions();
    private Integer[][] inputMatrix;
    private Integer N;
    private final String Cat = "Arnold Cats Map";

    public void Do(String type, ImagePanel IP, String chaoticmap, JFrame MainWindow, Integer[][] InputMatrix) {
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt(InputMatrix);
        String title = "Encrypting";

        ProgressDialog loading = new ProgressDialog(MainWindow, title);

        Runnable proccess = () -> {
            Integer[][] result;
            encryptDecrypt.GetNum(type);
            result = encryptDecrypt.encrypt(chaoticmap);

            BufferedImage resultImage = new BufferedImage(result.length, result[0].length, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < resultImage.getWidth(); i++) {
                for (int j = 0; j < resultImage.getHeight(); j++) {
                    resultImage.setRGB(i, j, result[i][j]);
                }
            }
            IP.setImage(resultImage);

            loading.dispose();
        };

        Thread proccessThread = new Thread(proccess);
        proccessThread.start();
        loading.setVisible(true);
    }

    public int Do_Max(ImagePanel InputImage, ImagePanel ResultPanel1, ImagePanel ResultPanel2,
            JFrame MainWindow, Integer[][] InputMatrix, int RuleR, int RuleG, int RuleB, int count,
            String ChaoticMapChoosed) {
        setMatrix(InputImage);
        count = 0;
        int flag = 0;
        do {
            Do("1", ResultPanel1, ChaoticMapChoosed, MainWindow, inputMatrix);
            count++;

            flag++;
            if (flag == 300) {
                break;
            }
        } while (!MainFun.Check_Images(InputImage, ResultPanel1));

        return count;

    }

    public void Do_decrypt(ImagePanel IP, JFrame MainWindow, Integer[][] InputMatrix, String ChaoticMapChoosed) {
        EncryptDecrypt encryptDecrypt = new EncryptDecrypt(InputMatrix);
        String title = "Decrypting";

        ProgressDialog loading = new ProgressDialog(MainWindow, title);

        Runnable proccess = () -> {
            Integer[][] result;
            // encryptDecrypt.GetNum(type);
            result = encryptDecrypt.decrypt(ChaoticMapChoosed);

            BufferedImage resultImage = new BufferedImage(result.length, result[0].length,
                    BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < resultImage.getWidth(); i++) {
                for (int j = 0; j < resultImage.getHeight(); j++) {
                    resultImage.setRGB(i, j, result[i][j]);
                }
            }
            IP.setImage(resultImage);

            loading.dispose();
        };

        Thread proccessThread = new Thread(proccess);
        proccessThread.start();
        loading.setVisible(true);
    }

    public void DNK_encrypt(ImagePanel IP, ImagePanel ResultPanel, JFrame mainWindow, int ruleR, int ruleG, int ruleB) {
        String title = "Encrypting DNK";
        ProgressDialog loading = new ProgressDialog(mainWindow, title);

        Runnable proccess = () -> {
            // BufferedImage InputImage = ImageResize(IP.getImage());
            BufferedImage InputImage = (IP.getImage());
            DNK_functions DNK = new DNK_functions();
            int H = InputImage.getHeight();
            int W = InputImage.getWidth();
            BufferedImage ResImage = new BufferedImage(W, H, InputImage.getType());
            Integer[][] RedBinary = new Integer[H][W];
            Integer[][] GreenBinary = new Integer[H][W];
            Integer[][] BlueBinary = new Integer[H][W];
            Color inp, res;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {

                    inp = new Color(InputImage.getRGB(j, i));

                    RedBinary[i][j] = DNK.DnkEncode(inp.getRed(), ruleR);
                    GreenBinary[i][j] = DNK.DnkEncode(inp.getGreen(), ruleG);
                    BlueBinary[i][j] = DNK.DnkEncode(inp.getBlue(), ruleB);
                    res = new Color(RedBinary[i][j], GreenBinary[i][j], BlueBinary[i][j]);
                    ResImage.setRGB((j), (i), res.getRGB());

                    // if (i % 2 == 0) { //четная строка
                    //
                    // if (j % 2 == 0) { //четный столбец
                    // ResImage.setRGB((W - j - 1), (H - i - 1), res.getRGB());
                    // } else { //нечетный столбец
                    // ResImage.setRGB((W - j - 1), (i), res.getRGB());
                    // }
                    //
                    // } else { //нечетная строка
                    // if (j % 2 == 0) { //четный столбец
                    // ResImage.setRGB((j), (i), res.getRGB());
                    // } else { //нечетный столбец
                    // ResImage.setRGB((j), (H - i - 1), res.getRGB());
                    // }
                    // }
                }
            }
            ResultPanel.setImage(ResImage);
            loading.dispose();
        };

        Thread proccessThread = new Thread(proccess);
        proccessThread.start();
        loading.setVisible(true);
    }

    public void DNK_decrypt(ImagePanel IP, ImagePanel ResultPanel, JFrame mainWindow, int ruleR, int ruleG, int ruleB) {

        DNK_encrypt(IP, ResultPanel, mainWindow, ruleR, ruleG, ruleB);
        for (int i = 0; i < 4; i++) {
            DNK_encrypt(ResultPanel, ResultPanel, mainWindow, ruleR, ruleG, ruleB);
        }

    }

    public int BestChaoticMap(ImagePanel InputImage, ImagePanel ResultPanel, String ChaoticMapChoosed,
            JFrame mainWindow, Integer[][] InputMatrix, int count) {
        setMatrix(InputImage);
        count = 0;
        double min = 100, corr;
        int iterration = 0, flag;
        if ("Baker".equals(ChaoticMapChoosed)) {
            flag = 0;
        } else {
            flag = 101;
        }

        do {
            count++;
            Do("1", ResultPanel, ChaoticMapChoosed, mainWindow, inputMatrix);
            corr = CorrelationCombineAvg(ResultPanel);
            if (Math.abs(corr) < Math.abs(min)) {
                iterration = count;
                min = corr;
            }
            flag++;

        } while (ChaoticMapChoosed.equals(Cat) ? !MainFun.Check_Images(InputImage, ResultPanel) : flag != 150);

        setMatrix(InputImage);
        Do(Integer.toString(iterration), ResultPanel, ChaoticMapChoosed, mainWindow, inputMatrix);

        // String message;
        //
        // message = flag == 300 ? "Max correlation is = " + Math.abs(min) + " on - " +
        // iterration + " iterration but no stopping signes" : "Max correlation is = " +
        // Math.abs(min) + " on - " + iterration + " iterration";
        // JOptionPane.showMessageDialog(null, message,
        // "Error!", JOptionPane.INFORMATION_MESSAGE);
        return iterration;
    }

    private void setMatrix(ImagePanel panel) {
        BufferedImage tempImage = panel.getImage();
        this.N = tempImage.getHeight();
        this.inputMatrix = new Integer[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                inputMatrix[i][j] = tempImage.getRGB(i, j);
            }
        }
    }

    private double CorrelationHorizontal(ImagePanel Panel, int Height) {
        BufferedImage image1 = Panel.getImage();
        int width = image1.getWidth();
        double SumA = 0, SumB = 0, Ea, Eb, SumTop = 0, SumBotA = 0, SumBotB = 0, Bot, Result;
        Color ColorA, ColorB;

        for (int i = 0; i < width; i++) {
            ColorA = new Color(image1.getRGB(i, Height));
            ColorB = new Color(image1.getRGB(i, Height + 1));
            SumA += getBrightness(ColorA);
            SumB += getBrightness(ColorB);
        }
        Ea = SumA / width;
        Eb = SumB / width;
        for (int i = 0; i < image1.getWidth(); i++) {
            ColorA = new Color(image1.getRGB(i, Height));
            ColorB = new Color(image1.getRGB(i, Height + 1));
            SumTop += (getBrightness(ColorA) - Ea) * (getBrightness(ColorB) - Eb);
            SumBotA += Math.pow(getBrightness(ColorA) - Ea, 2);
            SumBotB += Math.pow(getBrightness(ColorB) - Eb, 2);

        }
        Bot = Math.sqrt(SumBotA * SumBotB);

        Result = SumTop / Bot;
        // JOptionPane.showMessageDialog(null, "Correlation is "+Result,
        // "Error!", JOptionPane.INFORMATION_MESSAGE);
        return Result;

    }

    public double CorrelationHorizontalAvg(ImagePanel Panel) {
        BufferedImage image1 = Panel.getImage();
        int hight = image1.getWidth();
        double Avg = 0;
        for (int i = 0; i < hight - 1; i++) {
            Avg += CorrelationHorizontal(Panel, i);
        }
        return Avg / (hight - 1);
    }

    private double CorrelationVertical(ImagePanel Panel, int Width) {
        BufferedImage image1 = Panel.getImage();
        int Height = image1.getHeight();
        double SumA = 0, SumB = 0, Ea, Eb, SumTop = 0, SumBotA = 0, SumBotB = 0, Bot, Result;
        Color ColorA, ColorB;

        for (int i = 0; i < Height; i++) {
            ColorA = new Color(image1.getRGB(Width, i));
            ColorB = new Color(image1.getRGB(Width + 1, i));
            SumA += getBrightness(ColorA);
            SumB += getBrightness(ColorB);
        }
        Ea = SumA / Height;
        Eb = SumB / Height;
        for (int i = 0; i < Height; i++) {
            ColorA = new Color(image1.getRGB(Width, i));
            ColorB = new Color(image1.getRGB(Width + 1, i));
            SumTop += (getBrightness(ColorA) - Ea) * (getBrightness(ColorB) - Eb);
            SumBotA += Math.pow(getBrightness(ColorA) - Ea, 2);
            SumBotB += Math.pow(getBrightness(ColorB) - Eb, 2);

        }
        Bot = Math.sqrt(SumBotA * SumBotB);

        Result = SumTop / Bot;
        // JOptionPane.showMessageDialog(null, "Correlation is "+Result,
        // "Error!", JOptionPane.INFORMATION_MESSAGE);
        return Result;

    }

    public double CorrelationVerticalAvg(ImagePanel Panel) {
        BufferedImage image1 = Panel.getImage();
        int width = image1.getWidth();
        double Avg = 0;
        for (int i = 0; i < width - 1; i++) {
            Avg += CorrelationVertical(Panel, i);
        }
        return Avg / (width - 1);
    }

    private double CorrelationDiagonal1(ImagePanel Panel, int X, int Y) {
        BufferedImage image1 = Panel.getImage();
        int counting = 0;
        double SumA = 0, SumB = 0, Ea, Eb, SumTop = 0, SumBotA = 0, SumBotB = 0, Bot, Result;
        Color ColorA, ColorB;
        int x = X;
        int y = Y;

        do {
            ColorA = new Color(image1.getRGB(x, y));
            ColorB = new Color(image1.getRGB(x, y - 1));
            SumA += getBrightness(ColorA);
            SumB += getBrightness(ColorB);
            x -= 1;
            y -= 1;

            counting++;
        } while (x >= 0);

        x = X;
        y = Y;

        Ea = SumA / counting;
        Eb = SumB / counting;
        do {
            ColorA = new Color(image1.getRGB(x, y));
            ColorB = new Color(image1.getRGB(x, y - 1));
            SumTop += (getBrightness(ColorA) - Ea) * (getBrightness(ColorB) - Eb);
            SumBotA += Math.pow(getBrightness(ColorA) - Ea, 2);
            SumBotB += Math.pow(getBrightness(ColorB) - Eb, 2);

            x -= 1;
            y -= 1;
        } while (x >= 0);
        Bot = Math.sqrt(SumBotA * SumBotB);

        if (Bot != 0) {
            Result = SumTop / Bot;
        } else {
            Result = 1;
        }
        // JOptionPane.showMessageDialog(null, "Correlation is "+Result,
        // "Error!", JOptionPane.INFORMATION_MESSAGE);
        return Result;

    }

    private double CorrelationDiagonal2(ImagePanel Panel, int X, int Y) {
        BufferedImage image1 = Panel.getImage();
        int Size = image1.getHeight(), counting = 0;
        double SumA = 0, SumB = 0, Ea, Eb, SumTop = 0, SumBotA = 0, SumBotB = 0, Bot, Result;
        Color ColorA, ColorB;
        int x = X;
        int y = Y;

        do {
            ColorA = new Color(image1.getRGB(x, y));
            ColorB = new Color(image1.getRGB(x, y + 1));
            SumA += getBrightness(ColorA);
            SumB += getBrightness(ColorB);
            x += 1;
            y += 1;
            counting++;
        } while (x < Size);

        x = X;
        y = Y;

        Ea = SumA / counting;
        Eb = SumB / counting;
        do {
            ColorA = new Color(image1.getRGB(x, y));
            ColorB = new Color(image1.getRGB(x, y + 1));
            SumTop += (getBrightness(ColorA) - Ea) * (getBrightness(ColorB) - Eb);
            SumBotA += Math.pow(getBrightness(ColorA) - Ea, 2);
            SumBotB += Math.pow(getBrightness(ColorB) - Eb, 2);

            x += 1;
            y += 1;
        } while (x < Size);
        Bot = Math.sqrt(SumBotA * SumBotB);

        if (Bot != 0) {
            Result = SumTop / Bot;
        } else {
            Result = 1;
        }

        // JOptionPane.showMessageDialog(null, "Correlation is "+Result,
        // "Error!", JOptionPane.INFORMATION_MESSAGE);
        return Result;

    }

    public double CorrelationDiagonalAvg(ImagePanel Panel) {
        BufferedImage image1 = Panel.getImage();
        int Size = image1.getWidth();
        double Avg = 0;
        int x, y, counting = 0;

        x = 1;
        y = Size - 1;
        while (x < Size - 1) {
            Avg = Avg + CorrelationDiagonal1(Panel, x, y);
            x++;
            counting++;
        }
        x = Size - 2;
        y = 0;
        while (x > 0) {
            Avg = Avg + CorrelationDiagonal2(Panel, x, y);
            x--;
            counting++;
        }

        return Avg / counting;
    }

    public double CorrelationCombineAvg(ImagePanel Panel) {

        double a = Math.abs(CorrelationDiagonalAvg(Panel));
        double b = Math.abs(CorrelationVerticalAvg(Panel));
        double c = Math.abs(CorrelationHorizontalAvg(Panel));

        return (a + b + c);
    }

    public double getBrightness(Color col) {
        return Math.round(col.getRed() * 0.3 + col.getGreen() * 0.59 + col.getBlue() * 0.11);
    }

    public double Shenon(ImagePanel Panel) {

        BufferedImage img = Panel.getImage();
        double res = 0, res2 = 0, res3 = 0;
        int Size = img.getWidth();
        Color buf;
        Double[] Pr = new Double[256];
        Double[] Pg = new Double[256];
        Double[] Pb = new Double[256];
        for (int a = 0; a < 256; a++) {
            Pr[a] = 0.0;
            Pg[a] = 0.0;
            Pb[a] = 0.0;
        }

        for (int y = 0; y < Size; y++) {
            for (int x = 0; x < Size; x++) {

                buf = new Color(img.getRGB(x, y));
                Pr[buf.getRed()]++;
                Pg[buf.getGreen()]++;
                Pb[buf.getBlue()]++;
            }
        }

        for (int a = 0; a < 256; a++) {
            Pr[a] /= Math.pow(Size, 2);
            Pg[a] /= Math.pow(Size, 2);
            Pb[a] /= Math.pow(Size, 2);

            if (Pr[a] != 0) {
                res += Pr[a] * (Math.log(Pr[a]) / Math.log(2));
            }
            if (Pg[a] != 0) {
                res2 += Pg[a] * (Math.log(Pg[a]) / Math.log(2));
            }
            if (Pb[a] != 0) {
                res3 += Pb[a] * (Math.log(Pb[a]) / Math.log(2));
            }

        }

        JOptionPane.showMessageDialog(null,
                "Corr red = " + -res + " \nCorr green = " + -res2 + " \nCorr blue = " + -res3,
                "Shenon correlation", JOptionPane.INFORMATION_MESSAGE);
        return (res3 + res2 + res) * -1 / 3;
    }

    public void ChangeBit(ImagePanel panel) {
        BufferedImage img = panel.getImage();
        Color col = new Color(img.getRGB(0, 0));
        int gr = col.getGreen() == 255 ? 0 : col.getGreen() + 1;
        int re = col.getRed() == 255 ? 0 : col.getRed() + 1;
        int bl = col.getBlue() == 255 ? 0 : col.getBlue() + 1;
        Color newColor = new Color(re, gr, bl);
        img.setRGB(0, 0, newColor.getRGB());

        JOptionPane.showMessageDialog(null,
                "Old red = " + col.getRed() + " Old green = " + col.getGreen() + " Old blue = " + col.getBlue()
                        + " New red = " + re + " New green = " + gr + " New blue = " + bl,
                "Error!", JOptionPane.INFORMATION_MESSAGE);
        panel.setImage(img);
    }

    public void AddImages(ImagePanel panel1, ImagePanel panel2, ImagePanel resultPanel, int xStart, int yStart) {

        BufferedImage img1 = panel1.getImage();
        BufferedImage img2 = panel2.getImage();
        int size = img1.getWidth();
        BufferedImage res = new BufferedImage(size, size, img1.getType());
        Color col1, col2;
        int r, g, b;
        int n = 0, buf;
        int step = xStart + size * yStart; // 10

        while (n != Math.pow(size, 2)) {

            if ((n + step) > Math.pow(size, 2) - 1) {
                buf = n + step - (int) Math.pow(size, 2);
            } else {
                buf = n + step;
            }
            col1 = new Color(img1.getRGB(n % size, n / size));
            col2 = new Color(img2.getRGB(buf % size, buf / size));

            r = col1.getRed() ^ col2.getRed();
            g = col1.getGreen() ^ col2.getGreen();
            b = col1.getBlue() ^ col2.getBlue();

            res.setRGB(n % size, n / size, new Color(r, g, b).getRGB());

            n++;

        } // 60536

        resultPanel.setImage(res);
    }

    public void EncodeHelpInfo(ImagePanel panel, int[] R, int[] G, int[] B, int[] XY) {
        BufferedImage img = panel.getImage();
        String bakerR = LengthSetToNine(Integer.toBinaryString(R[0]));
        String catR = LengthSetToNine(Integer.toBinaryString(R[1]));
        String bakerG = LengthSetToNine(Integer.toBinaryString(G[0]));
        String catG = LengthSetToNine(Integer.toBinaryString(G[1]));
        String bakerB = LengthSetToNine(Integer.toBinaryString(B[0]));
        String catB = LengthSetToNine(Integer.toBinaryString(B[1]));
        String Xstart = LengthSetToNine(Integer.toBinaryString(XY[0]));
        String Ystart = LengthSetToNine(Integer.toBinaryString(XY[1]));
        int size = img.getWidth();
        String[] bakerSubR = new String[] { bakerR.substring(0, 3), bakerR.substring(3, 6), bakerR.substring(6, 9) };
        String[] bakerSubG = new String[] { bakerG.substring(0, 3), bakerG.substring(3, 6), bakerG.substring(6, 9) };
        String[] bakerSubB = new String[] { bakerB.substring(0, 3), bakerB.substring(3, 6), bakerB.substring(6, 9) };
        String[] YstartSub = new String[] { Ystart.substring(0, 3), Ystart.substring(3, 6), Ystart.substring(6, 9) };
        String[] XstartSub = new String[] { Xstart.substring(0, 3), Xstart.substring(3, 6), Xstart.substring(6, 9) };
        String[] catSubR = new String[] { catR.substring(0, 3), catR.substring(3, 6), catR.substring(6, 9) };
        String[] catSubG = new String[] { catG.substring(0, 3), catG.substring(3, 6), catG.substring(6, 9) };
        String[] catSubB = new String[] { catB.substring(0, 3), catB.substring(3, 6), catB.substring(6, 9) };
        int red, green, blue;
        int step = size / 8;
        Color c, res = null;

        for (int i = 0; i < 8; i++) {
            c = new Color(img.getRGB(step * i, 0));
            red = c.getRed() > 249 ? c.getRed() - 10 : c.getRed();
            blue = c.getBlue() > 249 ? c.getBlue() - 10 : c.getBlue();
            green = c.getGreen() > 249 ? c.getGreen() - 10 : c.getGreen();
            switch (i) {
                case 0:
                    res = new Color(red - (red % 10) + Integer.parseInt(bakerSubR[0], 2),
                            green - (green % 10) + Integer.parseInt(bakerSubR[1], 2),
                            blue - (blue % 10) + Integer.parseInt(bakerSubR[2], 2));
                    break;
                case 1:
                    res = new Color(red - (red % 10) + Integer.parseInt(catSubR[0], 2),
                            green - (green % 10) + Integer.parseInt(catSubR[1], 2),
                            blue - (blue % 10) + Integer.parseInt(catSubR[2], 2));
                    break;
                case 2:
                    res = new Color(red - (red % 10) + Integer.parseInt(bakerSubG[0], 2),
                            green - (green % 10) + Integer.parseInt(bakerSubG[1], 2),
                            blue - (blue % 10) + Integer.parseInt(bakerSubG[2], 2));
                    break;
                case 3:
                    res = new Color(red - (red % 10) + Integer.parseInt(catSubG[0], 2),
                            green - (green % 10) + Integer.parseInt(catSubG[1], 2),
                            blue - (blue % 10) + Integer.parseInt(catSubG[2], 2));
                    break;
                case 4:
                    res = new Color(red - (red % 10) + Integer.parseInt(bakerSubB[0], 2),
                            green - (green % 10) + Integer.parseInt(bakerSubB[1], 2),
                            blue - (blue % 10) + Integer.parseInt(bakerSubB[2], 2));
                    break;
                case 5:
                    res = new Color(red - (red % 10) + Integer.parseInt(catSubB[0], 2),
                            green - (green % 10) + Integer.parseInt(catSubB[1], 2),
                            blue - (blue % 10) + Integer.parseInt(catSubB[2], 2));
                    break;
                case 6:
                    res = new Color(red - (red % 10) + Integer.parseInt(XstartSub[0], 2),
                            green - (green % 10) + Integer.parseInt(XstartSub[1], 2),
                            blue - (blue % 10) + Integer.parseInt(XstartSub[2], 2));
                    break;
                case 7:
                    res = new Color(red - (red % 10) + Integer.parseInt(YstartSub[0], 2),
                            green - (green % 10) + Integer.parseInt(YstartSub[1], 2),
                            blue - (blue % 10) + Integer.parseInt(YstartSub[2], 2));
                    break;
                default:
                    // System.out.println("nam pizda");
                    break;
            }

            img.setRGB(step * i, 0, res.getRGB());
        }
        panel.removeImage();
        panel.setImage(img);
    }

    public int[] DecodeHelpInfo(ImagePanel panel) {
        BufferedImage img = panel.getImage();
        int size = img.getWidth();
        String[] bakerSubR = new String[3];
        String[] bakerSubG = new String[3];
        String[] bakerSubB = new String[3];
        String[] YstartSub = new String[3];
        String[] XstartSub = new String[3];
        String[] catSubR = new String[3];
        String[] catSubB = new String[3];
        String[] catSubG = new String[3];
        int[] res = new int[8];
        int step = size / 8, red, green, blue;
        Color c;
        for (int i = 0; i < 8; i++) {
            c = new Color(img.getRGB(step * i, 0));
            red = c.getRed();
            blue = c.getBlue();
            green = c.getGreen();

            switch (i) {
                case 0:
                    bakerSubR[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    bakerSubR[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    bakerSubR[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                case 1:
                    catSubR[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    catSubR[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    catSubR[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                case 2:
                    bakerSubG[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    bakerSubG[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    bakerSubG[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                case 3:
                    catSubG[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    catSubG[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    catSubG[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                case 4:
                    bakerSubB[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    bakerSubB[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    bakerSubB[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                case 5:
                    catSubB[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    catSubB[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    catSubB[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                case 6:
                    XstartSub[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    XstartSub[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    XstartSub[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                case 7:
                    YstartSub[0] = LengthSetToThree(Integer.toBinaryString(red % 10));
                    YstartSub[1] = LengthSetToThree(Integer.toBinaryString(green % 10));
                    YstartSub[2] = LengthSetToThree(Integer.toBinaryString(blue % 10));
                    break;
                default:
                    // System.out.println("nam pizda");
                    break;
            }
        }
        res[0] = Integer.parseInt(bakerSubR[0] + bakerSubR[1] + bakerSubR[2], 2);
        res[1] = Integer.parseInt(catSubR[0] + catSubR[1] + catSubR[2], 2);
        res[2] = Integer.parseInt(bakerSubG[0] + bakerSubG[1] + bakerSubG[2], 2);
        res[3] = Integer.parseInt(catSubG[0] + catSubG[1] + catSubG[2], 2);
        res[4] = Integer.parseInt(bakerSubB[0] + bakerSubB[1] + bakerSubB[2], 2);
        res[5] = Integer.parseInt(catSubB[0] + catSubB[1] + catSubB[2], 2);
        res[6] = Integer.parseInt(XstartSub[0] + XstartSub[1] + XstartSub[2], 2);
        res[7] = Integer.parseInt(YstartSub[0] + YstartSub[1] + YstartSub[2], 2);
        return res;
    }

    private String LengthSetToNine(String inputString) {
        if (inputString.length() < 9) {
            switch (9 - inputString.length()) {
                case 1 ->
                    inputString = "0".concat(inputString);
                case 2 ->
                    inputString = "00".concat(inputString);
                case 3 ->
                    inputString = "000".concat(inputString);
                case 4 ->
                    inputString = "0000".concat(inputString);
                case 5 ->
                    inputString = "00000".concat(inputString);
                case 6 ->
                    inputString = "000000".concat(inputString);
                case 7 ->
                    inputString = "0000000".concat(inputString);
                case 8 ->
                    inputString = "00000000".concat(inputString);
                default ->
                    System.out.println("error MCF LengthSet 2");
            }
        }
        return inputString;
    }

    private String LengthSetToThree(String inputString) {
        if (inputString.length() < 3) {
            switch (3 - inputString.length()) {
                case 1 ->
                    inputString = "0".concat(inputString);
                case 2 ->
                    inputString = "00".concat(inputString);
                default ->
                    System.out.println("error MCF LengthSet 1");
            }
        }
        return inputString;
    }

}
