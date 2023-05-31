
package controller.HUGO;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Decode {

    public int ByteToInt(byte[] msg_byte) { // преобразование байтового массива в интовскую переменную
        int msg_int = ((msg_byte[0] & 0xff) << 24)
                | ((msg_byte[1] & 0xff) << 16)
                | ((msg_byte[2] & 0xff) << 8)
                | (msg_byte[3] & 0xff);
        return msg_int;
    } // end of ByteToInt()

    public byte[] extractHiddenBytes_B(BufferedImage img, int size, BufferedImage img2) {

        byte[] hiddenBytes = new byte[size]; // создание массива байтов для записи результатов декодирования
        int j = 0;
        int i = 0;
        int j_max = img2.getWidth();

        for (int l = 0; l < size; l++) {
            for (int k = 0; k < 8; k++) {
                if (j < j_max) {
                    Color c = new Color(img2.getRGB(j, i));
                    if (c.equals(Color.RED)) { // получение координат черных пикселй из картинки с отметками
                        Color c1 = new Color(img.getRGB(j, i));
                        hiddenBytes[l] = (byte) ((hiddenBytes[l] << 1) | (c1.getBlue() & 1)); // запись последнего бита
                                                                                              // байта синего цвета
                        img2.setRGB(j, i, Color.WHITE.getRGB()); // окрашивание пикселя картинки с отметками в белый
                                                                 // цвет, чтобы не прочитать второй раз одно и то же
                    } else if (c.equals(Color.BLACK)) {
                        Color c1 = new Color(img.getRGB(j, i));
                        hiddenBytes[l] = (byte) (((hiddenBytes[l] << 1) | ((c1.getBlue() & 1) ^ 1))); // запись
                                                                                                      // последнего бита
                                                                                                      // байта синего
                                                                                                      // цвета
                        img2.setRGB(j, i, Color.WHITE.getRGB());
                    } else {
                        k--; // если условие не выполняется то цикл отсается на месте k=k+1-1=k
                    }
                    j++;
                } else {
                    j = 0;
                    i++;
                    k--;
                }
            }
        }

        return hiddenBytes;
    } // end of extractHiddenBytes()

    public byte[] extractHiddenBytes_R(BufferedImage img, int size, BufferedImage img2) {

        byte[] hiddenBytes = new byte[size]; // создание массива байтов для записи результатов декодирования
        int j = 0;
        int i = 0;
        int j_max = img2.getWidth();

        for (int l = 0; l < size; l++) {
            for (int k = 0; k < 8; k++) {
                if (j < j_max) {
                    Color c = new Color(img2.getRGB(j, i));
                    if (c.equals(Color.RED)) { // получение координат черных пикселй из картинки с отметками
                        Color c1 = new Color(img.getRGB(j, i));
                        hiddenBytes[l] = (byte) ((hiddenBytes[l] << 1) | (c1.getRed() & 1)); // запись последнего бита
                                                                                             // байта синего цвета
                        img2.setRGB(j, i, Color.WHITE.getRGB()); // окрашивание пикселя картинки с отметками в белый
                                                                 // цвет, чтобы не прочитать второй раз одно и то же
                    } else if (c.equals(Color.BLACK)) {
                        Color c1 = new Color(img.getRGB(j, i));
                        hiddenBytes[l] = (byte) (((hiddenBytes[l] << 1) | ((c1.getRed() & 1) ^ 1))); // запись
                                                                                                     // последнего бита
                                                                                                     // байта синего
                                                                                                     // цвета
                        img2.setRGB(j, i, Color.WHITE.getRGB());
                    } else {
                        k--; // если условие не выполняется то цикл отсается на месте k=k+1-1=k
                    }
                    j++;
                } else {
                    j = 0;
                    i++;
                    k--;
                }
            }
        }

        return hiddenBytes;
    } // end of extractHiddenBytes()

    public byte[] extractHiddenBytes_G(BufferedImage img, int size, BufferedImage img2) {

        byte[] hiddenBytes = new byte[size]; // создание массива байтов для записи результатов декодирования
        int j = 0;
        int i = 0;
        int j_max = img2.getWidth();

        for (int l = 0; l < size; l++) {
            for (int k = 0; k < 8; k++) {
                if (j < j_max) {
                    Color c = new Color(img2.getRGB(j, i));
                    if (c.equals(Color.RED)) { // получение координат черных пикселй из картинки с отметками
                        Color c1 = new Color(img.getRGB(j, i));
                        hiddenBytes[l] = (byte) ((hiddenBytes[l] << 1) | (c1.getGreen() & 1)); // запись последнего бита
                                                                                               // байта синего цвета
                        img2.setRGB(j, i, Color.WHITE.getRGB()); // окрашивание пикселя картинки с отметками в белый
                                                                 // цвет, чтобы не прочитать второй раз одно и то же
                    } else if (c.equals(Color.BLACK)) {
                        Color c1 = new Color(img.getRGB(j, i));
                        hiddenBytes[l] = (byte) (((hiddenBytes[l] << 1) | ((c1.getGreen() & 1) ^ 1))); // запись
                                                                                                       // последнего
                                                                                                       // бита байта
                                                                                                       // синего цвета
                        img2.setRGB(j, i, Color.WHITE.getRGB());
                    } else {
                        k--; // если условие не выполняется то цикл отсается на месте k=k+1-1=k
                    }
                    j++;
                } else {
                    j = 0;
                    i++;
                    k--;
                }
            }
        }

        return hiddenBytes;
    } // end of extractHiddenBytes()

}
