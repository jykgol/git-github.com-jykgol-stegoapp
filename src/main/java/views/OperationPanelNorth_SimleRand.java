/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OperationPanelNorth_SimleRand extends JPanel {

    private final JLabel U0 = new JLabel("3.14159");
    private final JLabel U1 = new JLabel("0.542102");
    private final JButton Reset = new JButton("Reset U1 and U0");

    public OperationPanelNorth_SimleRand() {

        add(new JLabel("Simple Random"));

        add(new JLabel("U0  ="));
        add(U0);
        add(new JLabel("U1  ="));
        add(U1);
        add(Reset);
    }

    public void setLabelU0(double count) {
        U0.setText(Double.toString(count));
    }

    public void setLabelU1(double count) {
        U1.setText(Double.toString(count));
    }

    public double getLabelU0(double count) {
        return Double.valueOf(U0.getText());
    }

    public double getLabelU1(double count) {
        return Double.valueOf(U1.getText());
    }

    public JButton getResetButton() {
        return Reset;
    }

}
