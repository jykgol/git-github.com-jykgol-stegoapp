/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jyk22
 */
public class ImagePanel extends JPanel {

    private final JPanel panel = new JPanel();
    private final JLabel imageCanvas = new JLabel();
    private BufferedImage localImage;
    private BufferedImage repaintedImage;

    public ImagePanel() {
        super(new BorderLayout());
        this.panel.add(imageCanvas);
        JScrollPane ImagePane = new JScrollPane(this.panel);
        CustomListener cus = new CustomListener();
        imageCanvas.addMouseWheelListener(cus);
        imageCanvas.addMouseListener(cus);
        add(ImagePane, BorderLayout.CENTER);
    }

    public Boolean setImage(BufferedImage image) {
        try {
            localImage = image;
            imageCanvas.setIcon(new ImageIcon(image));
            this.panel.repaint();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public BufferedImage getImage() {
        try {
            ImageIcon icon = (ImageIcon) imageCanvas.getIcon();
            BufferedImage bi = (BufferedImage) icon.getImage();
            return bi;
        } catch (Exception e) {
            System.out.println("ERROR:Image==null");
            return null;
        }
    }

    public Boolean removeImage() {
        try {
            localImage = null;
            repaintedImage = null;
            imageCanvas.setIcon(null);
            this.panel.repaint();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public byte[] extractBytes2() throws IOException {

        ImageIcon icon = (ImageIcon) imageCanvas.getIcon();
        BufferedImage bufferedImage = (BufferedImage) icon.getImage();
        ; // считываем картинку в bufferedImage

        ByteArrayOutputStream bos = new ByteArrayOutputStream(); // создаем байтовый поток
        ImageIO.write(bufferedImage, "png", bos); // записываем в поток нашу картинку
        bos.close(); // закрываем поток
        return bos.toByteArray(); // функция возвращает биты картинки из своего буфера
    } // end of extractBytes2()

    public class CustomListener extends MouseAdapter {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int lexi = e.getWheelRotation();
            if (getImage() != null) {
                if (lexi < 0) {
                    // "Mouse wheel moved UP ";
                    BufferedImage img = repaintedImage == null ? localImage : repaintedImage;
                    //
                    BufferedImage resizedImage = new BufferedImage((int) (img.getWidth() * 2),
                            (int) (img.getHeight() * 2), img.getType());
                    Graphics2D g = resizedImage.createGraphics();
                    g.drawImage(img, 0, 0, (int) (img.getWidth() * 2), (int) (img.getHeight() * 2), null);
                    g.dispose();

                    imageCanvas.setIcon(new ImageIcon(resizedImage));
                    repaintedImage = resizedImage;
                    panel.repaint();
                } else {
                    // message = "Mouse wheel moved DOWN ";
                    BufferedImage img = repaintedImage == null ? localImage : repaintedImage;

                    BufferedImage resizedImage = new BufferedImage((int) (img.getWidth() / 2),
                            (int) (img.getHeight() / 2), img.getType());
                    Graphics2D g = resizedImage.createGraphics();
                    g.drawImage(img, 0, 0, (int) (img.getWidth() / 2), (int) (img.getHeight() / 2), null);
                    g.dispose();

                    imageCanvas.setIcon(new ImageIcon(resizedImage));
                    repaintedImage = resizedImage;
                    panel.repaint();
                }

            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            repaintedImage = localImage;
            setImage(localImage);
        }
    }
}
