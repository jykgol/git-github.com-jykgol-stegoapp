/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import servieces.ImageChooser;
import views.ImagePanel;

/**
 *
 * @author jyk22
 */
public class MainFunctions {

    private JFileChooser fileChooser = new JFileChooser();
    private ImageChooser imageChooser = new ImageChooser();

    public BufferedImage chooseImage(ImagePanel IP, Component mainWindow) {
        BufferedImage tempImage = null;
        int returnVal = imageChooser.showDialog(mainWindow, "Load Image");
        if (returnVal == ImageChooser.APPROVE_OPTION) {
            File inputImageFile = imageChooser.getSelectedFile();
            try {
                File imageFile = new File(inputImageFile.getPath());
                imageFile.getName();

                InputStream is = new BufferedInputStream(new FileInputStream(imageFile));
                String mimeType = URLConnection.guessContentTypeFromStream(is);

                if (mimeType != null && (mimeType.equals("image/jpeg") || mimeType.equals("image/png"))) {
                    BufferedImage LoadedImage = ImageIO.read(imageFile);

                    tempImage = ImageResize(LoadedImage);
                    IP.setImage(tempImage);

                }
            } catch (IOException ex) {

            }
        }
        return tempImage;
    }

    private BufferedImage ImageResize(BufferedImage Im) {
        int Height = (Im.getHeight() % 4 == 0 ? Im.getHeight() : Im.getHeight() + (4 - Im.getHeight() % 4));
        int Width = (Im.getWidth() % 4 == 0 ? Im.getWidth() : Im.getWidth() + (4 - Im.getHeight() % 4));
        int buf, bufbuf;

        bufbuf = Height > Width ? Height : Width;
        buf = bufbuf;

        while (buf % 2 == 0) {
            buf = buf / 2;
        }
        if (buf != 1) {
            Height = bufbuf;
            Width = bufbuf;
        } else {
            Height = bufbuf + 4;
            Width = bufbuf + 4;
        }

        BufferedImage resizedImage = new BufferedImage(Width, Height, Im.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(Im, 0, 0, Width, Height, null);
        g.dispose();

        return resizedImage;
    }

    public void Do_save(ImagePanel IP) throws IOException {
        fileChooser.setDialogTitle("Save file to");
        fileChooser.setApproveButtonText("Save");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(null);
        String resPath = fileChooser.getSelectedFile().toString();
        resPath = resPath.endsWith(".png") ? resPath : resPath + ".png";
        File outputfile = new File(resPath);
        // System.out.println(outputfile);
        if (IP.getImage() != null) {
            ImageIO.write(IP.getImage(), "png", outputfile);
            JOptionPane.showMessageDialog(null, "The Image was saved Successfully!",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "The Image could not be saved!",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public boolean Check_Images(ImagePanel InputImage, ImagePanel ResultPanel) {
        BufferedImage In = InputImage.getImage();
        BufferedImage Res = ResultPanel.getImage();
        int width = In.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width / 2; j++) {
                if (In.getRGB(i, j) != Res.getRGB(i, j)) {
                    return false;
                }
            }

        }

        return true;
    }

}
