/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OperationPanelNorth_HACS extends JPanel {

    private final String[] ChooseList1 = { "HACS", "Simple Rand" };
    private final JButton Encode = new JButton("Encode");
    private final JButton Decode = new JButton("Decode");
    private final JButton HACS_MAP_KD_Full = new JButton("K-D Full");
    private final JButton HACS_MAP_KD_Horizontal = new JButton("K-D Hor");
    private final JButton HACS_MAP_KD_Vertical = new JButton("K-D Ver");
    private final JButton HACS_MAP_KD_Full_Decode = new JButton("K-D Full Decode");
    private final JButton HACS_MAP_KD_Horizontal_Decode = new JButton("K-D Hor Decode");
    private final JButton HACS_MAP_KD_Vertical_Decode = new JButton("K-D Ver Decode");
    private final JButton HACS_Rand_1 = new JButton("HACS_R im 1");
    private final JButton HACS_Rand_2 = new JButton("HACS_R im 2");
    private final JComboBox<String> ChaoticMethodChoose = new JComboBox<>(ChooseList1);

    public OperationPanelNorth_HACS() {

        add(new JLabel("K-D = Кнут-Дюрстенфельда"));
        add(Encode);
        add(Decode);
        // encode
        add(HACS_MAP_KD_Horizontal);
        add(HACS_MAP_KD_Vertical);
        // decode
        add(HACS_MAP_KD_Horizontal_Decode);
        add(HACS_MAP_KD_Vertical_Decode);
        // rand
        add(HACS_Rand_1);
        add(HACS_Rand_2);

        add(new JLabel("Selected Method"));
        add(ChaoticMethodChoose);
    }

    public JButton getHACS_MAP_KD_FullButton() {
        return HACS_MAP_KD_Full;
    }

    public JButton getHACS_MAP_KD_HorizontalButton() {
        return HACS_MAP_KD_Horizontal;
    }

    public JButton getHACS_MAP_KD_VerticalButton() {
        return HACS_MAP_KD_Vertical;
    }

    public JButton getEncodeButton() {
        return Encode;
    }

    public JButton getDecodeButton() {
        return Decode;
    }

    public JButton getHACS_Rand_1Button() {
        return HACS_Rand_1;
    }

    public JButton getHACS_MAP_KD_Full_DecodeButton() {
        return HACS_MAP_KD_Full_Decode;
    }

    public JButton getHACS_MAP_KD_Vertical_DecodeButton() {
        return HACS_MAP_KD_Vertical_Decode;
    }

    public JButton getHACS_Rand_2Button() {
        return HACS_Rand_2;
    }

    public JButton getHACS_MAP_KD_Horizontal_DecodeButton() {
        return HACS_MAP_KD_Horizontal_Decode;
    }

    public JComboBox<String> getChaoticMethodChooseBox() {
        return ChaoticMethodChoose;
    }

    public String getChaoticMethodChoosed() {
        return ChaoticMethodChoose.getItemAt(ChaoticMethodChoose.getSelectedIndex()).toString();
    }

}
