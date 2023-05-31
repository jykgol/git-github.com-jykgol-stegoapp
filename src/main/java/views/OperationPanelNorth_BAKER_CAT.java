/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jyk22
 */
public class OperationPanelNorth_BAKER_CAT extends JPanel {

    private final String[] ChooseList = { "1", "2", "3", "4", "5", "6", "7", "8" };
    private final String[] ChooseList1 = { "Baker", "Arnold Cats Map" };

    private final JButton backButton = new JButton("<");
    private final JButton forwardButton = new JButton(">");
    private final JTextField tx = new JTextField(5);
    private final JButton finishButton = new JButton("Itarate until original image");
    private final JLabel lb = new JLabel("0");
    private final JButton DNK = new JButton("DNK");
    private final JButton DNK_Decode = new JButton("DNK_Decode");
    private final JComboBox<String> RuleR = new JComboBox<>(ChooseList);
    private final JComboBox<String> RuleG = new JComboBox<>(ChooseList);
    private final JComboBox<String> RuleB = new JComboBox<>(ChooseList);
    private final JComboBox<String> ChaoticMapChoose = new JComboBox<>(ChooseList1);

    public OperationPanelNorth_BAKER_CAT() {
        add(backButton);
        add(forwardButton);
        add(tx);
        add(finishButton);
        add(lb);
        add(DNK);
        add(DNK_Decode);
        add(new JLabel("r"));
        add(RuleR);
        add(new JLabel("g"));
        add(RuleG);
        add(new JLabel("b"));
        add(RuleB);
        add(new JLabel("Chaotic Map -"));
        add(ChaoticMapChoose);

    }

    public JButton getDNKButton() {
        return DNK;
    }

    public JButton getDNK_DecodeButton() {
        return DNK_Decode;
    }

    public JButton getForwardButton() {
        return forwardButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getFinishButton() {
        return finishButton;
    }

    public void setLabel(int count) {
        lb.setText(Integer.toString(count));
    }

    public JTextField getTx() {
        return tx;
    }

    public String getTextFieldNum() {
        return tx.getText();
    }

    public JComboBox<String> getRuleRbox() {
        return RuleR;
    }

    public JComboBox<String> getRuleGbox() {
        return RuleG;
    }

    public JComboBox<String> getRuleBbox() {
        return RuleB;
    }

    public JComboBox<String> getChaoticMapChooseBox() {
        return ChaoticMapChoose;
    }

    public int getRuleR() {
        return Integer.parseInt(RuleR.getItemAt(RuleR.getSelectedIndex()).toString());
    }

    public int getRuleG() {
        return Integer.parseInt(RuleG.getItemAt(RuleG.getSelectedIndex()).toString());
    }

    public int getRuleB() {
        return Integer.parseInt(RuleB.getItemAt(RuleB.getSelectedIndex()).toString());
    }

    public String getChaoticMapChoose() {
        return ChaoticMapChoose.getItemAt(ChaoticMapChoose.getSelectedIndex()).toString();
    }
}
