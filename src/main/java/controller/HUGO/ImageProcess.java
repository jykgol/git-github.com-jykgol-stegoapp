package controller.HUGO;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageProcess {

    public boolean B_Hide(BufferedImage img, BufferedImage img2, byte[] stego) throws Exception {

        FillWhite(img2); // заполняем нашу копию ббелым цветом

        int totalLen = stego.length;
        System.out.println("Total byte length of message: " + totalLen); // вывод на экран длинны сообщения которое надо
                                                                         // зашифровать

        int j_max = img.getWidth(); // переменная - ширина картинки
        int i = 1; // не с 0 потому что крайние пиксели не подходят под условие шифрования
        int j = 1; // i - координата высоты j - координата ширины

        for (byte b : stego) { // для каждого элемента сообщения которое нужно зашифровать как for (int
                               // x;x<stego.lengrh;x++) b=stego[x];
            for (int k = 7; k >= 0; k--) { // переходим от байта к битам. первый бит нужно сдвинуть на 7 вправо, чтобы
                                           // он оказался на 8 позиции, второй на 6,третий на 5, и т.д.
                if (i < img.getHeight() - 1) // проверка на границы по высоте
                {
                    if (j < j_max - 1) { // проверка границы по ширине
                        Color c = new Color(img.getRGB(j, i)); // проверяем область вокруг пикселя на однородность
                        Color c1 = new Color(img.getRGB(j + 1, i)); // С6-С3-С8
                        Color c2 = new Color(img.getRGB(j - 1, i)); // С2-С-С1
                        Color c3 = new Color(img.getRGB(j, i + 1)); // С5-С4-С7
                        Color c4 = new Color(img.getRGB(j, i - 1)); //
                        Color c5 = new Color(img.getRGB(j - 1, i - 1));
                        Color c6 = new Color(img.getRGB(j - 1, i + 1));
                        Color c7 = new Color(img.getRGB(j + 1, i - 1));
                        Color c8 = new Color(img.getRGB(j + 1, i + 1));
                        if (8 < Math.abs(Math
                                .abs(((c.getBlue() + c1.getBlue() + c2.getBlue() + c3.getBlue() + c4.getBlue()
                                        + c5.getBlue() + c6.getBlue() + c7.getBlue() + c8.getBlue()) / 9))
                                - Math.abs(c.getBlue()))) {
                            int Val = c.getBlue() & 1;
                            switch (Val) {
                                case 1:
                                    img2.setRGB(j, i, Color.BLACK.getRGB()); // в идентичную координату второй картинки
                                                                             // этот пиксель заполняем черным
                                    break;
                                case 0:
                                    img2.setRGB(j, i, Color.RED.getRGB()); // в идентичную координату второй картинки
                                                                           // этот пиксель заполняем красным
                                    break;
                            }
                            img.setRGB(j, i, B_Hide_process(c, b, k).getRGB()); // если пиксель подходит то шифруем в
                                                                                // последний бит байта синего цвета бит
                                                                                // сообщения
                        } else {
                            k++; // если условие не выполняется нужно чтобы наше сообщение не перешло по циклу,
                                 // получается k+1-1=k;
                        }
                        j++; // в любом случае (подходит пиксель или нет) нужно идти дальше по ширине.
                    } else {
                        j = 1; // если закончилась ширина картинки переходи на следующую строку
                        i++;
                        k++; // т.к бит сообщения не зашифровывается в таком случае снова увеличиваем k чтобы
                             // его не пропустило
                    }
                } else {
                    return false;
                }
            }

        }
        return true;
    } // end of singleHide()

    public boolean R_Hide(BufferedImage img, BufferedImage img2, byte[] stego) throws Exception {

        FillWhite(img2); // заполняем нашу копию ббелым цветом
        int totalLen = stego.length;
        System.out.println("Total byte length of message: " + totalLen); // вывод на экран длинны сообщения которое надо
                                                                         // зашифровать

        int j_max = img.getWidth(); // переменная - ширина картинки
        int i = 1; // не с 0 потому что крайние пиксели не подходят под условие шифрования
        int j = 1; // i - координата высоты j - координата ширины

        for (byte b : stego) { // для каждого элемента сообщения которое нужно зашифровать как for (int
                               // x;x<stego.lengrh;x++) b=stego[x];
            for (int k = 7; k >= 0; k--) { // переходим от байта к битам. первый бит нужно сдвинуть на 7 вправо, чтобы
                                           // он оказался на 8 позиции, второй на 6,третий на 5, и т.д.
                if (i < img.getHeight() - 1) // проверка на границы по высоте
                {
                    if (j < j_max - 1) { // проверка границы по ширине
                        Color c = new Color(img.getRGB(j, i)); // проверяем область вокруг пикселя на однородность
                        Color c1 = new Color(img.getRGB(j + 1, i)); // С6-С3-С8
                        Color c2 = new Color(img.getRGB(j - 1, i)); // С2-С-С1
                        Color c3 = new Color(img.getRGB(j, i + 1)); // С5-С4-С7
                        Color c4 = new Color(img.getRGB(j, i - 1)); //
                        Color c5 = new Color(img.getRGB(j - 1, i - 1));
                        Color c6 = new Color(img.getRGB(j - 1, i + 1));
                        Color c7 = new Color(img.getRGB(j + 1, i - 1));
                        Color c8 = new Color(img.getRGB(j + 1, i + 1));
                        if (8 < Math
                                .abs(Math
                                        .abs(((c.getRed() + c1.getRed() + c2.getRed() + c3.getRed() + c4.getRed()
                                                + c5.getRed() + c6.getRed() + c7.getRed() + c8.getRed()) / 9))
                                        - Math.abs(c.getRed()))) {
                            int Val = c.getRed() & 1;
                            switch (Val) {
                                case 1:
                                    img2.setRGB(j, i, Color.BLACK.getRGB()); // в идентичную координату второй картинки
                                                                             // этот пиксель заполняем черным
                                    break;
                                case 0:
                                    img2.setRGB(j, i, Color.RED.getRGB()); // в идентичную координату второй картинки
                                                                           // этот пиксель заполняем красным
                                    break;
                            }
                            img.setRGB(j, i, R_Hide_process(c, b, k).getRGB()); // если пиксель подходит то шифруем в
                                                                                // последний бит байта синего цвета бит
                                                                                // сообщения
                        } else {
                            k++; // если условие не выполняется нужно чтобы наше сообщение не перешло по циклу,
                                 // получается k+1-1=k;
                        }
                        j++; // в любом случае (подходит пиксель или нет) нужно идти дальше по ширине.
                    } else {
                        j = 1; // если закончилась ширина картинки переходи на следующую строку
                        i++;
                        k++; // т.к бит сообщения не зашифровывается в таком случае снова увеличиваем k чтобы
                             // его не пропустило
                    }
                } else {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean G_Hide(BufferedImage img, BufferedImage img2, byte[] stego) throws Exception {

        FillWhite(img2); // заполняем нашу копию ббелым цветом

        int totalLen = stego.length;
        System.out.println("Total byte length of message: " + totalLen); // вывод на экран длинны сообщения которое надо
                                                                         // зашифровать

        int j_max = img.getWidth(); // переменная - ширина картинки
        int i = 1; // не с 0 потому что крайние пиксели не подходят под условие шифрования
        int j = 1; // i - координата высоты j - координата ширины

        for (byte b : stego) { // для каждого элемента сообщения которое нужно зашифровать как for (int
                               // x;x<stego.lengrh;x++) b=stego[x];
            for (int k = 7; k >= 0; k--) { // переходим от байта к битам. первый бит нужно сдвинуть на 7 вправо, чтобы
                                           // он оказался на 8 позиции, второй на 6,третий на 5, и т.д.
                if (i < img.getHeight() - 1) // проверка на границы по высоте
                {
                    if (j < j_max - 1) { // проверка границы по ширине
                        Color c = new Color(img.getRGB(j, i)); // проверяем область вокруг пикселя на однородность
                        Color c1 = new Color(img.getRGB(j + 1, i)); // С6-С3-С8
                        Color c2 = new Color(img.getRGB(j - 1, i)); // С2-С-С1
                        Color c3 = new Color(img.getRGB(j, i + 1)); // С5-С4-С7
                        Color c4 = new Color(img.getRGB(j, i - 1)); //
                        Color c5 = new Color(img.getRGB(j - 1, i - 1));
                        Color c6 = new Color(img.getRGB(j - 1, i + 1));
                        Color c7 = new Color(img.getRGB(j + 1, i - 1));
                        Color c8 = new Color(img.getRGB(j + 1, i + 1));
                        if (8 < Math.abs(Math
                                .abs(((c.getGreen() + c1.getGreen() + c2.getGreen() + c3.getGreen() + c4.getGreen()
                                        + c5.getGreen() + c6.getGreen() + c7.getGreen() + c8.getGreen()) / 9))
                                - Math.abs(c.getGreen()))) {
                            int Val = c.getGreen() & 1;
                            switch (Val) {
                                case 1:
                                    img2.setRGB(j, i, Color.BLACK.getRGB()); // в идентичную координату второй картинки
                                                                             // этот пиксель заполняем черным
                                    break;
                                case 0:
                                    img2.setRGB(j, i, Color.RED.getRGB()); // в идентичную координату второй картинки
                                                                           // этот пиксель заполняем красным
                                    break;
                            }
                            img.setRGB(j, i, G_Hide_process(c, b, k).getRGB()); // если пиксель подходит то шифруем в
                                                                                // последний бит байта синего цвета бит
                                                                                // сообщения
                        } else {
                            k++; // если условие не выполняется нужно чтобы наше сообщение не перешло по циклу,
                                 // получается k+1-1=k;
                        }
                        j++; // в любом случае (подходит пиксель или нет) нужно идти дальше по ширине.
                    } else {
                        j = 1; // если закончилась ширина картинки переходи на следующую строку
                        i++;
                        k++; // т.к бит сообщения не зашифровывается в таком случае снова увеличиваем k чтобы
                             // его не пропустило
                    }
                } else {
                    return false;
                }
            }

        }
        return true;
    } // end of singleHide()

    void FillWhite(BufferedImage img) { // заполнение всех пикселей картинки белым цветом

        Color newcolor = new Color(255, 255, 255);

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                img.setRGB(j, i, newcolor.getRGB());
            }
        }
    }

    Color B_Hide_process(Color c, byte b, int k) // процесс изменения цвета пикселя
    {

        int red = c.getRed();
        int blue = c.getBlue(); // число синего цвета
        int green = c.getGreen();
        int bitVal = (b >>> k) & 1; // бит секрета
        blue = (byte) ((blue & 0xFF) ^ bitVal);
        Color newColor = new Color(red, green, (blue & 0xFF));
        return newColor;
    }

    Color R_Hide_process(Color c, byte b, int k) // процесс изменения цвета пикселя
    {

        int blue = c.getBlue();
        int red = c.getRed();
        int green = c.getGreen();
        int bitVal = (b >>> k) & 1;
        red = (byte) ((red & 0xFF) ^ bitVal);
        Color newColor = new Color((red & 0xFF), green, blue);
        return newColor;
    }

    Color G_Hide_process(Color c, byte b, int k) // процесс изменения цвета пикселя
    {

        int blue = c.getBlue();
        int green = c.getGreen();
        int red = c.getRed();
        int bitVal = (b >>> k) & 1;
        green = (byte) ((green & 0xFF) ^ bitVal);
        Color newColor = new Color(red, (green & 0xFF), blue);
        return newColor;
    }

}// end of ImageProcess