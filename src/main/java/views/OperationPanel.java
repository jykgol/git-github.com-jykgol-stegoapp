/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jyk22
 */
public class OperationPanel extends JPanel {

    private final String[] ChooseList = { "0", "1", "2", "3" };
    private final String[] ChooseList2 = { "0", "1", "2" };
    private final JComboBox<String> ChooseOprationPanelNorth = new JComboBox<String>(ChooseList2);

    private final JButton browseButton = new JButton("Browse");
    private final JButton saveButton = new JButton("Save");
    private final JButton transfer = new JButton("Transfer");
    private final JLabel lb2 = new JLabel("Interact from \\ load to  - Panel ");
    private final JComboBox<String> InteractFrom = new JComboBox<String>(ChooseList);
    private final JLabel lb3 = new JLabel("Interact to \\ save from - Panel ");
    private final JComboBox<String> InteractTo = new JComboBox<String>(ChooseList);
    private final JButton delete = new JButton("Delete");

    public OperationPanel() {
        add(ChooseOprationPanelNorth);
        add(browseButton);
        add(saveButton);
        add(transfer);
        add(delete);
        add(lb2);
        add(InteractFrom);
        add(lb3);
        add(InteractTo);

    }

    public int getChooseOprationPanelNorthNumber() {
        return Integer
                .parseInt(ChooseOprationPanelNorth.getItemAt(ChooseOprationPanelNorth.getSelectedIndex()).toString());
    }

    public int getInteractFromNumber() {
        return Integer.parseInt(InteractFrom.getItemAt(InteractFrom.getSelectedIndex()).toString());
    }

    public void setInteractFromNumber(int index) {
        InteractFrom.setSelectedIndex(index);
    }

    public int getInteractToNumber() {
        return Integer.parseInt(InteractTo.getItemAt(InteractTo.getSelectedIndex()).toString());
    }

    public JComboBox<String> getChooseOprationPanelNorthComboBox() {
        return ChooseOprationPanelNorth;
    }

    public JComboBox<String> getInteractFromComboBox() {
        return InteractFrom;
    }

    public JComboBox<String> getInteractToComboBox() {
        return InteractTo;
    }

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getDeleteButton() {
        return delete;
    }

    public JButton getTransferButton() {
        return transfer;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

}
