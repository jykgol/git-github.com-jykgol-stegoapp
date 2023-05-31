/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import HACSfolder.HACS_Class;
import HACSfolder.SimpleRand;
import controller.HUGO.*;
import java.io.ByteArrayInputStream;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import views.ImagePanel;
import views.MainWindow;
import views.OperationPanel;
import views.OperationPanelNorth;
import views.OperationPanelNorth_BAKER_CAT;
import views.OperationPanelNorth_HACS;
import views.OperationPanelNorth_HUGO;
import views.OperationPanelNorth_SimleRand;
import views.OperationPanel_second;
import views.ProgressDialog;

/**
 *
 * @author jyk22
 */
public class MainController {

    private final MainWindow mainWindow;
    private final ImagePanel inputImagePanel;
    private final ImagePanel resultImagePanel;
    private final ImagePanel resultIP2;
    private final ImagePanel resultIP3;
    public BufferedImage inputImage;
    public Integer[][] inputMatrix;
    public Integer w;
    public Integer h;
    public int xStartRow = 0;
    public int yStartRow = 0;
    private Integer N;
    public OperationPanel operationPanel;
    private OperationPanelNorth_SimleRand operationPanelNorth_SimleRand;
    public int count = 0;
    private ImagePanel SelectedInteractFrom;
    private ImagePanel SelectedInteractTo;
    public int ruleR;
    public int ruleG;
    public int ruleB;
    public String ChaoticMapChoosed = "Baker";
    private String ChaoticMethodChoosed = "HACS";
    private String Baker = "Baker", Cat = "Arnold Cats Map";
    private String HACS = "HACS", Simple_Rand = "Simple Rand";
    private MainFunctions MainFun = new MainFunctions();
    private MainCodingFunctions MainCodingFun = new MainCodingFunctions();
    private double U0 = 3.14159, U1 = 0.542102;
    private SimpleRand simpleRand;

    public MainController(MainWindow mainWindow, OperationPanel operationPanel,
            OperationPanel_second operationPanel_second,
            OperationPanelNorth operationPanelNorth,
            OperationPanelNorth_HACS operationPanelNorth_HACS,
            OperationPanelNorth_HUGO operationPanelNorth_HUGO,
            OperationPanelNorth_SimleRand operationPanelNorth_SimleRand,
            OperationPanelNorth_BAKER_CAT operationPanelNorth_BAKER_CAT,
            ImagePanel inputIP, ImagePanel resultIP1,
            ImagePanel resultIP2, ImagePanel resultIP3) {
        this.mainWindow = mainWindow;
        this.inputImagePanel = inputIP;
        this.resultImagePanel = resultIP1;
        this.resultIP2 = resultIP2;
        this.resultIP3 = resultIP3;
        this.operationPanelNorth_SimleRand = operationPanelNorth_SimleRand;
        ImagePanel[] list = { inputImagePanel, resultImagePanel, this.resultIP2, this.resultIP3 };
        SelectedInteractFrom = list[0];
        SelectedInteractTo = list[0];
        simpleRand = new SimpleRand();
        HACS_Class HACS_object = new HACS_Class(260, simpleRand);

        operationPanelNorth_BAKER_CAT.getRuleRbox().addActionListener((ActionEvent e) -> {
            ruleR = operationPanelNorth_BAKER_CAT.getRuleR();
        });
        operationPanelNorth_BAKER_CAT.getRuleGbox().addActionListener((ActionEvent e) -> {
            ruleG = operationPanelNorth_BAKER_CAT.getRuleG();
        });
        operationPanelNorth_BAKER_CAT.getRuleBbox().addActionListener((ActionEvent e) -> {
            ruleB = operationPanelNorth_BAKER_CAT.getRuleB();
        });
        operationPanelNorth_BAKER_CAT.getChaoticMapChooseBox().addActionListener((ActionEvent e) -> {
            ChaoticMapChoosed = operationPanelNorth_BAKER_CAT.getChaoticMapChoose();
        });

        operationPanelNorth_HACS.getChaoticMethodChooseBox().addActionListener((ActionEvent e) -> {
            ChaoticMethodChoosed = operationPanelNorth_HACS.getChaoticMethodChoosed();
        });

        operationPanelNorth_SimleRand.getResetButton().addActionListener((ActionEvent e) -> {
            U0 = 3.14159;
            U1 = 0.542102;
            simpleRand.setU0(U0);
            simpleRand.setU1(U1);
            operationPanelNorth_SimleRand.setLabelU1(U1);
            operationPanelNorth_SimleRand.setLabelU0(U0);
        });
        // Operation panel BOTTOM PANEL Begin
        // -------------BOTTOM----------------------------BOTTOM-------------------------BOTTOM----------------
        // BOTTOM //
        operationPanel.getInteractFromComboBox().addActionListener((ActionEvent e) -> {
            SelectedInteractFrom = list[operationPanel.getInteractFromNumber()];
            if (SelectedInteractFrom.getImage() != null) {
                setMatrix(SelectedInteractFrom);
            }

        });
        operationPanel.getInteractToComboBox().addActionListener((ActionEvent e) -> {
            SelectedInteractTo = list[operationPanel.getInteractToNumber()];
            if (SelectedInteractFrom.getImage() != null) {
                setMatrix(SelectedInteractFrom);
            }

        });

        operationPanel.getBrowseButton().addActionListener((ActionEvent e) -> {
            MainFun.chooseImage(SelectedInteractFrom, mainWindow);
            if (SelectedInteractFrom.getImage() != null) {
                setMatrix(SelectedInteractFrom);
            }
            ruleR = operationPanelNorth_BAKER_CAT.getRuleR();
            ruleG = operationPanelNorth_BAKER_CAT.getRuleG();
            ruleB = operationPanelNorth_BAKER_CAT.getRuleB();
        });
        operationPanel.getTransferButton().addActionListener((ActionEvent e) -> {
            SelectedInteractTo.setImage(SelectedInteractFrom.getImage());
        });
        operationPanel.getDeleteButton().addActionListener((ActionEvent e) -> {
            SelectedInteractTo.removeImage();
        });
        operationPanel_second.getTestDiagButton().addActionListener((ActionEvent e) -> {
            double a = MainCodingFun.CorrelationDiagonalAvg(SelectedInteractTo);
            JOptionPane.showMessageDialog(null, "Diagonal correlation is " + a,
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        });

        operationPanel_second.getTestVertButton().addActionListener((ActionEvent e) -> {
            double a = MainCodingFun.CorrelationVerticalAvg(SelectedInteractTo);
            JOptionPane.showMessageDialog(null, "Vertical correlation is " + a,
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        });
        operationPanel_second.getTestHorizButton().addActionListener((ActionEvent e) -> {
            double a = MainCodingFun.CorrelationHorizontalAvg(SelectedInteractTo);
            // double a = MainCodingFun.Shenon(SelectedInteractTo);
            JOptionPane.showMessageDialog(null, "Horizontal correlation is (Shenon)" + a,
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        });
        operationPanel_second.getShenonButton().addActionListener((ActionEvent e) -> {
            MainCodingFun.Shenon(SelectedInteractTo);
            // JOptionPane.showMessageDialog(null, "Shenon is " + a,
            // "Error!", JOptionPane.INFORMATION_MESSAGE);
        });
        operationPanel_second.getTestCombineButton().addActionListener((ActionEvent e) -> {
            double d = MainCodingFun.CorrelationCombineAvg(SelectedInteractTo);
            JOptionPane.showMessageDialog(null, "Correlation is " + d,
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
            // int[] res = this.BestE(this.resultIP3, this.resultImagePanel,
            // this.inputImagePanel);
            // System.out.println(Arrays.toString(res));
        });
        operationPanel.getSaveButton().addActionListener((ActionEvent e) -> {
            try {
                MainFun.Do_save(SelectedInteractTo);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Operation panel BOTTOM PANEL END
        // -------------BOTTOM----------------------------BOTTOM-------------------------BOTTOM----------------
        // BOTTOM //
        operationPanelNorth_BAKER_CAT.getDNKButton().addActionListener((ActionEvent e) -> {
            MainCodingFun.DNK_encrypt(SelectedInteractFrom, SelectedInteractTo, this.mainWindow, ruleR, ruleG, ruleB);
        });
        operationPanelNorth_BAKER_CAT.getDNK_DecodeButton().addActionListener((ActionEvent e) -> {
            MainCodingFun.DNK_decrypt(SelectedInteractFrom, SelectedInteractTo, this.mainWindow, ruleR, ruleG, ruleB);
        });

        operationPanelNorth_BAKER_CAT.getForwardButton().addActionListener((ActionEvent e) -> {
            String a = "1";
            MainCodingFun.Do(a, SelectedInteractTo, ChaoticMapChoosed, this.mainWindow, inputMatrix);
        });

        operationPanelNorth_BAKER_CAT.getBackButton().addActionListener((ActionEvent e) -> {

            MainCodingFun.Do_decrypt(SelectedInteractTo, this.mainWindow, inputMatrix, ChaoticMapChoosed);
        });

        operationPanelNorth_BAKER_CAT.getFinishButton().addActionListener((ActionEvent e) -> {
            operationPanelNorth_BAKER_CAT.setLabel(MainCodingFun.Do_Max(SelectedInteractFrom, SelectedInteractTo,
                    this.resultIP3, this.mainWindow, inputMatrix, ruleR, ruleG, ruleB,
                    this.count, ChaoticMapChoosed));
        });

        operationPanelNorth_BAKER_CAT.getTx().addActionListener((ActionEvent e) -> {
            String a = e.getActionCommand();

            if (!ChaoticMapChoosed.equals(Baker)) {
                MainCodingFun.Do(a, SelectedInteractTo, ChaoticMapChoosed, this.mainWindow, inputMatrix);
            } else {
                for (int i = 0; i < Integer.parseInt(a); i++) {
                    MainCodingFun.Do_decrypt(SelectedInteractTo, this.mainWindow,
                            inputMatrix, ChaoticMapChoosed);
                }
            }
        });

        operationPanelNorth.getBestChaoticMapChoosedButton().addActionListener((ActionEvent e) -> {
            BufferedImage buf = resultIP3.getImage();

            int size = buf.getWidth();
            BufferedImage bufR = new BufferedImage(size, size, buf.getType());
            BufferedImage bufG = new BufferedImage(size, size, buf.getType());
            BufferedImage bufB = new BufferedImage(size, size, buf.getType());
            Color col;
            int[] r, g, b;
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    col = new Color(buf.getRGB(x, y));
                    bufR.setRGB(x, y, new Color(col.getRed(), col.getRed(), col.getRed()).getRGB());
                    bufG.setRGB(x, y, new Color(col.getGreen(), col.getGreen(), col.getGreen()).getRGB());
                    bufB.setRGB(x, y, new Color(col.getBlue(), col.getBlue(), col.getBlue()).getRGB());
                }
            }
            resultIP3.setImage(bufR);
            r = this.BestE(resultIP3, this.resultImagePanel, this.inputImagePanel);
            bufR = inputImagePanel.getImage();
            resultIP3.setImage(bufG);
            g = this.BestE(resultIP3, this.resultImagePanel, this.inputImagePanel);
            bufG = inputImagePanel.getImage();
            resultIP3.setImage(bufB);
            b = this.BestE(resultIP3, this.resultImagePanel, this.inputImagePanel);
            bufB = inputImagePanel.getImage();
            resultIP3.removeImage();
            resultImagePanel.removeImage();
            inputImagePanel.removeImage();

            resultIP3.setImage(ThreeImageUnited(bufR, bufG, bufB));
            this.MainCodingFun.DNK_encrypt(resultIP3, resultIP3, mainWindow, ruleR, ruleG, ruleB);

            SinMap(resultIP2, resultImagePanel);
            BestAddImages(0.0010);
            // System.out.println("xStartRow = " + xStartRow + " yStartRow = " + yStartRow);
            int[] XY = new int[] { xStartRow, yStartRow };

            MainCodingFun.EncodeHelpInfo(resultIP2, r, g, b, XY);

            resultIP3.setImage(inputImagePanel.getImage());
            resultImagePanel.removeImage();
            inputImagePanel.removeImage();

        });
        operationPanelNorth.getDecodeButton().addActionListener((ActionEvent e) -> {

            int[] res = MainCodingFun.DecodeHelpInfo(resultIP2);
            int bakerCountR = res[0], bakerCountG = res[2], bakerCountB = res[4];
            int catCountR = res[1], catCountG = res[3], catCountB = res[5];
            int xStart = res[6], yStart = res[7];
            SinMap(resultIP2, resultImagePanel);
            MainCodingFun.AddImages(resultIP3, resultImagePanel,
                    inputImagePanel, xStart, yStart);
            this.MainCodingFun.DNK_encrypt(inputImagePanel, inputImagePanel, mainWindow, ruleR, ruleG, ruleB);

            BufferedImage buf = inputImagePanel.getImage();
            int size = buf.getWidth();
            BufferedImage bufR = new BufferedImage(size, size, buf.getType());
            BufferedImage bufG = new BufferedImage(size, size, buf.getType());
            BufferedImage bufB = new BufferedImage(size, size, buf.getType());
            Color col;
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    col = new Color(buf.getRGB(x, y));
                    bufR.setRGB(x, y, new Color(col.getRed(), col.getRed(), col.getRed()).getRGB());
                    bufG.setRGB(x, y, new Color(col.getGreen(), col.getGreen(), col.getGreen()).getRGB());
                    bufB.setRGB(x, y, new Color(col.getBlue(), col.getBlue(), col.getBlue()).getRGB());
                }
            }

            this.Decode(bufR, catCountR, bakerCountR);
            bufR = resultImagePanel.getImage();
            this.Decode(bufG, catCountG, bakerCountG);
            bufG = resultImagePanel.getImage();
            this.Decode(bufB, catCountB, bakerCountB);
            bufB = resultImagePanel.getImage();

            this.resultIP3.setImage(ThreeImageUnited(bufR, bufG, bufB));

            inputImagePanel.removeImage();
            resultImagePanel.removeImage();
            //
        });
        operationPanelNorth.getBestEButton().addActionListener((ActionEvent e) -> {

            MainCodingFun.AddImages(SelectedInteractFrom, SelectedInteractTo, resultIP3, xStartRow, yStartRow);

        });
        operationPanelNorth.getChangeBitButton().addActionListener((ActionEvent e) -> {
            xStartRow++;
            // yStartRow++;
            JOptionPane.showMessageDialog(null,
                    "old position (x=" + (xStartRow - 1) + ",y=" + (yStartRow - 1) + ") Now starting from position x = "
                            + xStartRow + " y = " + yStartRow,
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        });
        operationPanelNorth.getNPCRButton().addActionListener((ActionEvent e) -> {
            NPCR_Static(SelectedInteractFrom, SelectedInteractTo);
        });
        operationPanelNorth.getUACIButton().addActionListener((ActionEvent e) -> {
            UACI(SelectedInteractFrom, SelectedInteractTo);
        });

        operationPanelNorth.getSinMapButton().addActionListener((ActionEvent e) -> {
            SinMap(SelectedInteractFrom, SelectedInteractTo);
        });

        // HACS begin
        // -------------HACS----------------------------HACS-------------------------HACS----------------
        // HACS //
        operationPanelNorth_HACS.getHACS_MAP_KD_FullButton().addActionListener((ActionEvent e) -> {
            HACS_object.HACSChaoticMap_FullImage(this.SelectedInteractFrom, ChaoticMethodChoosed);
            if (ChaoticMethodChoosed == Simple_Rand) {
                updateSimpleRand();
            }
        });
        operationPanelNorth_HACS.getHACS_MAP_KD_HorizontalButton().addActionListener((ActionEvent e) -> {
            HACS_object.HACSChaoticMap_Gorizontal(this.SelectedInteractFrom, ChaoticMethodChoosed);
            if (ChaoticMethodChoosed == Simple_Rand) {
                updateSimpleRand();
            }

        });
        operationPanelNorth_HACS.getHACS_MAP_KD_VerticalButton().addActionListener((ActionEvent e) -> {
            HACS_object.HACSChaoticMap_Vertical(this.SelectedInteractFrom, ChaoticMethodChoosed);
            if (ChaoticMethodChoosed == Simple_Rand) {
                updateSimpleRand();
            }
        });
        operationPanelNorth_HACS.getHACS_MAP_KD_Full_DecodeButton().addActionListener((ActionEvent e) -> {
            HACS_object.HACSChaoticMap_FullImageDecode(this.SelectedInteractFrom, ChaoticMethodChoosed);
            if (ChaoticMethodChoosed == Simple_Rand) {
                updateSimpleRand();
            }
        });
        operationPanelNorth_HACS.getHACS_MAP_KD_Vertical_DecodeButton().addActionListener((ActionEvent e) -> {
            HACS_object.HACSChaoticMap_Vertical_Decode(this.SelectedInteractFrom, ChaoticMethodChoosed);
            if (ChaoticMethodChoosed == Simple_Rand) {
                updateSimpleRand();
            }
        });
        operationPanelNorth_HACS.getHACS_MAP_KD_Horizontal_DecodeButton().addActionListener((ActionEvent e) -> {
            HACS_object.HACSChaoticMap_Gorizontal_Decode(this.SelectedInteractFrom, ChaoticMethodChoosed);
            if (ChaoticMethodChoosed == Simple_Rand) {
                updateSimpleRand();
            }
        });

        // K-D end
        // -------------HACS----------------------------HACS-------------------------HACS----------------
        // HACS //
        operationPanelNorth_HACS.getHACS_Rand_1Button().addActionListener((ActionEvent e) -> {
            if (ChaoticMethodChoosed == HACS)
                HACS_object.RandomImage(this.SelectedInteractFrom, 260);
            else {
                HACS_object.RandomImageSimple(this.SelectedInteractFrom, 260);
                updateSimpleRand();
            }
        });
        operationPanelNorth_HACS.getHACS_Rand_2Button().addActionListener((ActionEvent e) -> {
            if (ChaoticMethodChoosed == HACS)
                HACS_object.RandomImage2(this.SelectedInteractFrom, 260);
            else {
                HACS_object.RandomImageSimple(this.SelectedInteractFrom, 260);
                updateSimpleRand();
            }
        });

        operationPanelNorth_HACS.getEncodeButton().addActionListener((ActionEvent e) -> {
            int size = this.resultIP3.getImage().getWidth();
            HACS_object.HACSChaoticMap_Gorizontal(this.resultIP3, ChaoticMethodChoosed);
            HACS_object.HACSChaoticMap_Vertical(this.resultIP3, ChaoticMethodChoosed);
            HACS_object.RandomImage(this.resultImagePanel, size);
            HACS_object.RandomImage2(this.resultIP2, size);
            this.MainCodingFun.AddImages(resultIP3, resultIP2, resultIP3, 0, 0);
            BestAddImages(0.0015);
            resultIP3.setImage(inputImagePanel.getImage());
            resultIP2.removeImage();
            resultImagePanel.removeImage();
            inputImagePanel.removeImage();

            // this.MainCodingFun.AddImages(resultIP3, resultImagePanel, resultIP3, 0, 0);
        });
        operationPanelNorth_HACS.getDecodeButton().addActionListener((ActionEvent e) -> {
            int size = this.resultIP3.getImage().getWidth();

            HACS_object.RandomImage(this.resultImagePanel, size);
            HACS_object.RandomImage2(this.resultIP2, size);

            this.MainCodingFun.AddImages(resultIP3, resultImagePanel, resultIP3, xStartRow, yStartRow);
            this.MainCodingFun.AddImages(resultIP3, resultIP2, resultIP3, 0, 0);
            HACS_object.HACSChaoticMap_Vertical_Decode(this.resultIP3, ChaoticMethodChoosed);
            HACS_object.HACSChaoticMap_Gorizontal_Decode(this.resultIP3, ChaoticMethodChoosed);

            resultIP2.removeImage();
            resultImagePanel.removeImage();
        });

        // HACS end
        // -------------HACS----------------------------HACS-------------------------HACS----------------
        // HACS //

        operationPanelNorth.getRandomImageButton().addActionListener((ActionEvent e) -> {
            RandomImageAlgo algo = new RandomImageAlgo();
            algo.generate(SelectedInteractFrom);
        });

        // HUGO start
        // -------------HUGO----------------------------HUGO-------------------------HUGO----------------
        // HUGO //

        operationPanelNorth_HUGO.getEncodeButton().addActionListener((ActionEvent e) -> {
            try {

                Convert c = new Convert();
                ImageProcess impro = new ImageProcess();
                byte[] secret_pic_byte;
                String type_encrypt = "R";

                secret_pic_byte = this.SelectedInteractTo.extractBytes2();
                // преобразуем картинку в биты

                byte[] secret_pic_byte_stego = c.buildStego(secret_pic_byte); // формируем сообщение =
                                                                              // длинна_битов_картинки+биты_картинки

                BufferedImage carrier_pic = this.SelectedInteractFrom.getImage();

                BufferedImage carrier_pic_2 = new BufferedImage(carrier_pic.getWidth(), carrier_pic.getHeight(),
                        carrier_pic.getType()); // создаем копию типа BufferedImage нашей картинки
                                                // тут будут отмечены координаты куда встроена картинка

                String filename = "Test.txt";
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename));
                bos.write(SelectedInteractFrom.extractBytes2());
                bos.flush();
                bos.close();

                if (type_encrypt == "B") {
                    if (!impro.B_Hide(carrier_pic, carrier_pic_2, secret_pic_byte_stego)) { // вызываем функцию
                                                                                            // шифрования
                                                                                            // сообщения
                        JOptionPane.showMessageDialog(null, "Недостаточно места для шифрования");
                    } else {
                        SelectedInteractFrom.setImage(carrier_pic);
                        SelectedInteractTo.setImage(carrier_pic_2);
                    }
                } else if (type_encrypt == "R") {
                    if (!impro.R_Hide(carrier_pic, carrier_pic_2, secret_pic_byte_stego)) { // вызываем функцию
                                                                                            // шифрования
                                                                                            // сообщения
                        JOptionPane.showMessageDialog(null, "Недостаточно места для шифрования");
                    } else {
                        SelectedInteractFrom.setImage(carrier_pic);
                        SelectedInteractTo.setImage(carrier_pic_2);
                    }
                } else if (type_encrypt == "G") {
                    if (!impro.G_Hide(carrier_pic, carrier_pic_2, secret_pic_byte_stego)) { // вызываем функцию
                                                                                            // шифрования
                                                                                            // сообщения
                        JOptionPane.showMessageDialog(null, "Недостаточно места для шифрования");
                    } else {
                        SelectedInteractFrom.setImage(carrier_pic);
                        SelectedInteractTo.setImage(carrier_pic_2);
                    }

                }

            } catch (Exception ex) {

            }
        });

        operationPanelNorth_HUGO.getDecodeButton().addActionListener((ActionEvent e) -> {
            try {
                Decode d = new Decode();
                BufferedImage result_pic = this.SelectedInteractFrom.getImage(); // считываение картинки со встроенным
                                                                                 // сообщением
                BufferedImage hints_pic = this.SelectedInteractTo.getImage(); // считываение картинки с отметками
                                                                              // шифрования
                int MAX_INT_LEN = 4;
                // int DATA_SIZE = 8;
                byte[] hidden_pic_byte_length;
                byte[] hidden_pic_byte = null;
                String type_encrypt = "R";
                switch (type_encrypt) {
                    case "R":
                        hidden_pic_byte_length = d.extractHiddenBytes_R(result_pic, MAX_INT_LEN, hints_pic); // получение
                                                                                                             // длинны
                                                                                                             // сообщения
                        System.out.println("Byte length of message: " + d.ByteToInt(hidden_pic_byte_length));
                        hidden_pic_byte = d.extractHiddenBytes_R(result_pic, d.ByteToInt(hidden_pic_byte_length),
                                hints_pic); // получение секретного сообщения
                        break;
                    case "G":
                        hidden_pic_byte_length = d.extractHiddenBytes_G(result_pic, MAX_INT_LEN, hints_pic); // получение
                                                                                                             // длинны
                                                                                                             // сообщения
                        System.out.println("Byte length of message: " + d.ByteToInt(hidden_pic_byte_length));
                        hidden_pic_byte = d.extractHiddenBytes_G(result_pic, d.ByteToInt(hidden_pic_byte_length),
                                hints_pic); // получение секретного сообщения
                        break;
                    case "B":
                        hidden_pic_byte_length = d.extractHiddenBytes_B(result_pic, MAX_INT_LEN, hints_pic); // получение
                                                                                                             // длинны
                                                                                                             // сообщения
                        System.out.println("Byte length of message: " + d.ByteToInt(hidden_pic_byte_length));
                        hidden_pic_byte = d.extractHiddenBytes_B(result_pic, d.ByteToInt(hidden_pic_byte_length),
                                hints_pic); // получение секретного сообщения
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "что то пошло не так с выбором цвета шифрования");
                        break;
                }

                InputStream is = new ByteArrayInputStream(hidden_pic_byte);
                BufferedImage newBi = ImageIO.read(is);
                SelectedInteractFrom.removeImage();
                SelectedInteractFrom.setImage(newBi);
            } catch (IOException ex) {

            }
        });
        // HUGO end
        // -------------HUGO----------------------------HUGO-------------------------HUGO----------------
        // HUGO //

    }

    private void updateSimpleRand() {
        operationPanelNorth_SimleRand.setLabelU0(simpleRand.getU0());
        operationPanelNorth_SimleRand.setLabelU1(simpleRand.getU0());
    }

    public int[] BestE(ImagePanel input, ImagePanel bufpanel, ImagePanel resultpanel) {
        int countCat = 0, countBaker = 0;
        double min = 100, corr2;
        int iterrationCat = 0, iterrationBaker = 0;

        do {
            setMatrix(input);
            countCat++;
            MainCodingFun.Do(Integer.toString(countCat), bufpanel, Cat, mainWindow, inputMatrix);
            setMatrix(resultImagePanel);

            for (int i = 0; i < 150; i++) {
                countBaker++;
                MainCodingFun.Do("1", resultpanel, Baker, mainWindow, inputMatrix);
                corr2 = MainCodingFun.CorrelationCombineAvg(resultpanel);

                if (Math.abs(corr2) < Math.abs(min)) {
                    iterrationBaker = countBaker;
                    iterrationCat = countCat;
                    min = corr2;
                }
                if (min < 0.0025) {
                    break;
                }
            }

            if (min < 0.0025) {
                break;
            }
            countBaker = 0;
        } while (!MainFun.Check_Images(input, resultpanel));

        return new int[] { iterrationBaker, iterrationCat };
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

    private int NPCR_fun(int col1, int col2) {
        return col1 == col2 ? 0 : 1;
    }

    private void NPCR_Static(ImagePanel panel, ImagePanel panel2) {

        BufferedImage img1 = panel.getImage();
        BufferedImage img2 = panel2.getImage();
        int sumR = 0, Size = img1.getWidth();
        int sumG = 0, sumB = 0;
        double NPCRr, NPCRg, NPCRb;
        for (int y = 0; y < Size; y++) {
            for (int x = 0; x < Size; x++) {
                sumR += NPCR_fun(new Color(img1.getRGB(x, y)).getRed(), new Color(img2.getRGB(x, y)).getRed()) * 100;
                sumG += NPCR_fun(new Color(img1.getRGB(x, y)).getGreen(), new Color(img2.getRGB(x, y)).getGreen())
                        * 100;
                sumB += NPCR_fun(new Color(img1.getRGB(x, y)).getBlue(), new Color(img2.getRGB(x, y)).getBlue()) * 100;
            }
        }
        NPCRr = sumR / Math.pow(Size, 2);
        NPCRg = sumG / Math.pow(Size, 2);
        NPCRb = sumB / Math.pow(Size, 2);
        // double res = (NPCRr + NPCRg + NPCRb) / 3;
        // JOptionPane.showMessageDialog(null, "NPCR = " + res,
        // "Error!", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null,
                "NPCR red = " + NPCRr + " \nNPCR green = " + NPCRg + " \nNPCR blue = " + NPCRb,
                "NPCR", JOptionPane.INFORMATION_MESSAGE);
    }

    private void UACI(ImagePanel panel, ImagePanel panel2) {

        BufferedImage img1 = panel.getImage();
        BufferedImage img2 = panel2.getImage();
        int sumR = 0, sumG = 0, sumB = 0, Size = img1.getWidth();
        double UACIr, UACIg, UACIb;
        for (int y = 0; y < Size; y++) {
            for (int x = 0; x < Size; x++) {
                sumR += Math.abs(new Color(img1.getRGB(x, y)).getRed() - new Color(img2.getRGB(x, y)).getRed()) * 100
                        / 255;
                sumG += Math.abs(new Color(img1.getRGB(x, y)).getGreen() - new Color(img2.getRGB(x, y)).getGreen())
                        * 100 / 255;
                sumB += Math.abs(new Color(img1.getRGB(x, y)).getBlue() - new Color(img2.getRGB(x, y)).getBlue()) * 100
                        / 255;
            }
        }
        UACIr = sumR / Math.pow(Size, 2);
        UACIg = sumG / Math.pow(Size, 2);
        UACIb = sumB / Math.pow(Size, 2);

        // UACI = (UACIr + UACIg + UACIb) / 3;
        // JOptionPane.showMessageDialog(null, "UACI = " + UACI,
        // "Error!", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null,
                "UACI red = " + UACIr + " \nUACI green = " + UACIg + " \nUACI blue = " + UACIb,
                "UACI", JOptionPane.INFORMATION_MESSAGE);
    }

    private void BestAddImages(double quality) {
        String title = "Best Add Images Encoding";
        ProgressDialog loading = new ProgressDialog(this.mainWindow, title);
        int size = resultImagePanel.getImage().getWidth();
        Runnable proccess = () -> {
            double bufCorr = 100;
            double min = 100;
            int minx = 0, miny = 0;
            long startTime = System.nanoTime(), endTime;
            for (yStartRow = 0; yStartRow < size; yStartRow += 2) {
                for (xStartRow = 0; xStartRow < size; xStartRow += 3) {
                    MainCodingFun.AddImages(resultIP3, resultImagePanel,
                            inputImagePanel, xStartRow, yStartRow);
                    bufCorr = MainCodingFun.CorrelationCombineAvg(inputImagePanel);
                    if (bufCorr < min) {
                        min = bufCorr;
                        minx = xStartRow;
                        miny = yStartRow;
                        // System.out.println(" y = " + yStartRow + "x = " + xStartRow + " min = " +
                        // min);
                    }
                    if (bufCorr < quality) {
                        break;
                    }
                }
                endTime = (System.nanoTime() - startTime) / 1000000000;
                if (endTime > 45) {
                    MainCodingFun.AddImages(resultIP3, resultImagePanel,
                            inputImagePanel, minx, miny);
                    JOptionPane.showMessageDialog(null,
                            "Прошло больше 45 секунд, остановимся на том что есть (не повезло =)",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                if (bufCorr < quality) {
                    break;
                }
            }
            xStartRow = minx;
            yStartRow = miny;
            loading.dispose();
        };
        Thread proccessThread = new Thread(proccess);
        proccessThread.start();
        loading.setVisible(true);
    }

    private void SinMap(ImagePanel Panel, ImagePanel ResultPanel) {
        int xStartRow = this.xStartRow, yStartRow = this.yStartRow;
        BufferedImage inp1 = Panel.getImage();
        Color buf3;
        int size = inp1.getHeight();
        BufferedImage inp = new BufferedImage(size, size, inp1.getType());
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                buf3 = new Color(inp1.getRGB(x, y));
                inp.setRGB(x, y, buf3.getRGB());
            }
        }

        int xStart = xStartRow < size ? xStartRow : xStartRow % size;
        int yStart = yStartRow < size ? yStartRow : yStartRow % size;

        String title = "Sin Map Encoding";
        ProgressDialog loading = new ProgressDialog(this.mainWindow, title);

        Runnable proccess = () -> {

            int x, y = yStart, n = 0;
            do {
                if (!(y == yStart && n == 0)) {
                    for (x = xStart; x < size; x++) {
                        inp.setRGB(x, y, SinMapFun(inp, x, y, size).getRGB());
                    }
                }
                y = y == size - 1 ? 0 : y + 1;
                for (x = 0; x < xStart; x++) {
                    inp.setRGB(x, y, SinMapFun(inp, x, y, size).getRGB());
                }

                if (y == yStart) {
                    n++;
                }
            } while (!(n == 60));

            loading.dispose();
        };

        Thread proccessThread = new Thread(proccess);
        proccessThread.start();
        loading.setVisible(true);
        ResultPanel.setImage(inp);

    }

    private Color SinMapFun(BufferedImage inp, int x, int y, int size) {

        int r, g, b;
        double a1;
        Color buf, buf1, buf2;

        if (x == 0) {
            if (y == 0) {
                buf = new Color(inp.getRGB(size - 1, size - 1));
            } else {
                buf = new Color(inp.getRGB(size - 1, y - 1));
            }
        } else {
            buf = new Color(inp.getRGB(x - 1, y));
        }
        r = buf.getRed();
        g = buf.getGreen();
        b = buf.getBlue();
        a1 = 0.5 * Math.abs(Math.sin(Math.PI * ((double) r / 255 + (double) g / 255 + (double) b / 255) / 3)) + 0.5;
        buf1 = new Color(inp.getRGB(x, y));
        r = ((int) Math.round(buf1.getGreen() + (int) (a1 * 255)) % 255);
        g = ((int) Math.round(buf1.getBlue() + (int) (a1 * 255)) % 255);
        b = ((int) Math.round(buf1.getRed() + (int) (a1 * 255)) % 255);
        buf2 = new Color(r, g, b);

        return buf2;
    }

    // private int[] Encode(BufferedImage img) {
    // resultIP3.setImage(img);
    // int CatCount = MainCodingFun.BestChaoticMap(resultIP3, resultImagePanel,
    // Cat, this.mainWindow, inputMatrix, count);
    // int BakerCount = MainCodingFun.BestChaoticMap(resultImagePanel,
    // inputImagePanel,
    // Baker, this.mainWindow, inputMatrix, count);

    // resultIP3.setImage(inputImagePanel.getImage());
    // resultImagePanel.removeImage();
    // inputImagePanel.removeImage();

    // return new int[] { BakerCount, CatCount };
    // }

    private BufferedImage ThreeImageUnited(BufferedImage imgR, BufferedImage imgG, BufferedImage imgB) {
        int size = imgR.getHeight();
        BufferedImage res = new BufferedImage(size, size, imgR.getType());

        Color r, g, b;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                r = new Color(imgR.getRGB(x, y));
                g = new Color(imgG.getRGB(x, y));
                b = new Color(imgB.getRGB(x, y));

                res.setRGB(x, y, new Color(r.getRed(), g.getGreen(), b.getBlue()).getRGB());
            }
        }

        return res;
    }

    private void Decode(BufferedImage img, int catCount, int bakerCount) {
        resultImagePanel.setImage(img);
        setMatrix(resultImagePanel);
        for (int i = 0; i < bakerCount; i++) {
            MainCodingFun.Do_decrypt(resultImagePanel, mainWindow,
                    inputMatrix, Baker);
        }
        for (int i = 0; i < catCount; i++) {
            MainCodingFun.Do_decrypt(resultImagePanel, mainWindow,
                    inputMatrix, Cat);
        }
    }

}
