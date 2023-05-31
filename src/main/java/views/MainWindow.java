/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controller.MainController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jyk22
 */
public class MainWindow extends JFrame {

    private final int width = 1024;
    private final int height = 600;

    public MainWindow() throws HeadlessException {
        super("DNK_V_Beta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ImagePanel inputIP = new ImagePanel();
        ImagePanel resultIP1 = new ImagePanel();
        ImagePanel resultIP2 = new ImagePanel();
        ImagePanel resultIP3 = new ImagePanel();

        JPanel iePanel = new JPanel(new GridLayout(2, 2));
        iePanel.add(inputIP);
        iePanel.add(resultIP1);
        iePanel.add(resultIP2);
        iePanel.add(resultIP3);

        OperationPanel operationPanel = new OperationPanel();
        OperationPanel_second operationPanel_second = new OperationPanel_second();
        OperationPanelNorth operationPanelNorth = new OperationPanelNorth();
        OperationPanelNorth_HACS operationPanelNorth_HACS = new OperationPanelNorth_HACS();
        OperationPanelNorth_SimleRand operationPanelNorth_SimleRand = new OperationPanelNorth_SimleRand();
        OperationPanelNorth_BAKER_CAT operationPanelNorth_BAKER_CAT = new OperationPanelNorth_BAKER_CAT();
        OperationPanelNorth_HUGO operationPanelNorth_HUGO = new OperationPanelNorth_HUGO();

        JPanel Content2 = new JPanel(new GridLayout(2, 1));
        Content2.add(operationPanel);
        Content2.add(operationPanel_second);

        MainController mainController = new MainController(this, operationPanel, operationPanel_second,
                operationPanelNorth,
                operationPanelNorth_HACS,
                operationPanelNorth_HUGO,
                operationPanelNorth_SimleRand,
                operationPanelNorth_BAKER_CAT,
                inputIP, resultIP1, resultIP2, resultIP3);

        add(new JLabel("<html><h3>&nbsp;DNK v 0.3.1</h3></html>"), BorderLayout.NORTH);
        add(iePanel, BorderLayout.CENTER);
        add(Content2, BorderLayout.SOUTH);

        JPanel Content = new JPanel(new GridLayout(2, 1));
        Content.add(operationPanelNorth_BAKER_CAT);
        Content.add(operationPanelNorth);

        JPanel northPanel = new JPanel(new GridLayout(1, 1));
        northPanel.add(Content);

        add(northPanel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(width, height));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        operationPanel.getChooseOprationPanelNorthComboBox().addActionListener(e -> {
            int choose = operationPanel.getChooseOprationPanelNorthNumber();
            JPanel Content1;
            switch (choose) {
                case 0 -> {
                    northPanel.removeAll();
                    Content1 = new JPanel(new GridLayout(2, 1));
                    Content1.add(operationPanelNorth_BAKER_CAT);
                    Content1.add(operationPanelNorth);
                    northPanel.add(Content1);
                    northPanel.revalidate();
                }
                case 1 -> {
                    northPanel.removeAll();
                    Content1 = new JPanel(new GridLayout(2, 1));
                    Content1.add(operationPanelNorth_HACS);
                    Content1.add(operationPanelNorth_SimleRand);
                    northPanel.add(Content1);
                    northPanel.revalidate();
                }
                case 2 -> {
                    northPanel.removeAll();
                    Content1 = new JPanel(new GridLayout(1, 1));
                    Content1.add(operationPanelNorth_HUGO);
                    northPanel.add(Content1);
                    northPanel.revalidate();
                }
                default -> {
                }
            }
        });
    }

}
