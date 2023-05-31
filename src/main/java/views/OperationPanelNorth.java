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
public class OperationPanelNorth extends JPanel {

    private final JButton BestChaoticMapChoosed = new JButton("Encode");
    private final JButton Decode = new JButton("Decode");
    private final JButton BestE = new JButton("AddImages");
    private final JButton SinMap = new JButton("Sin Map");
    private final JButton ChangeBit = new JButton("Change Sin position");
    private final JButton NPCR = new JButton("NPCR");
    private final JButton UACI = new JButton("UACI");
    private final JButton randomImageButton = new JButton("Random Image");

    public OperationPanelNorth() {

        add(BestChaoticMapChoosed);
        add(Decode);
        add(BestE);
        add(SinMap);
        add(ChangeBit);
        add(NPCR);
        add(UACI);
    }

    public JButton getSinMapButton() {
        return SinMap;
    }

    public JButton getBestChaoticMapChoosedButton() {
        return BestChaoticMapChoosed;
    }

    public JButton getBestEButton() {
        return BestE;
    }

    public JButton getChangeBitButton() {
        return ChangeBit;
    }

    public JButton getNPCRButton() {
        return NPCR;
    }

    public JButton getUACIButton() {
        return UACI;
    }

    public JButton getDecodeButton() {
        return Decode;
    }

    public JButton getRandomImageButton() {
        return randomImageButton;
    }

    // public JButton getDiffusion(){ return diffusionButton; }
}
