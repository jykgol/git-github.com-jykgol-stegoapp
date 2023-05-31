/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jyk22
 */
public class OperationPanel_second extends JPanel {

    private final JButton testDiag = new JButton("Corr Diag");
    private final JButton testVert = new JButton("Corr Vert");
    private final JButton testHoriz = new JButton("Corr Horiz");
    private final JButton testCombine = new JButton("Corr Combine");
    private final JButton Shenon = new JButton("Shenon");
    private final JButton delete = new JButton("Delete");

    public OperationPanel_second() {

        add(testHoriz);
        add(testVert);
        add(testDiag);
        add(testCombine);
        add(Shenon);
    }

    public JButton getShenonButton() {
        return Shenon;
    }

    public JButton getTestDiagButton() {
        return testDiag;
    }

    public JButton getTestVertButton() {
        return testVert;
    }

    public JButton getTestHorizButton() {
        return testHoriz;
    }

    public JButton getTestCombineButton() {
        return testCombine;
    }

    public JButton getDeleteButton() {
        return delete;
    }

}
