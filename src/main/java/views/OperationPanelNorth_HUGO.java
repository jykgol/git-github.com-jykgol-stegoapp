/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OperationPanelNorth_HUGO extends JPanel {

    private final JButton Encrypt = new JButton("Encrypt");
    private final JButton Decrypt = new JButton("Decrypt");

    public OperationPanelNorth_HUGO() {

        add(Encrypt);
        add(Decrypt);

    }

    public JButton getEncodeButton() {
        return Encrypt;
    }

    public JButton getDecodeButton() {
        return Decrypt;
    }

}
