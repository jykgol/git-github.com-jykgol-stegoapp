/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author jyk22
 */
public class ProgressDialog extends JDialog{
	public ProgressDialog(JFrame frame, String title){
		super(frame, title, true);
		setLayout(new BorderLayout());
		setResizable(false);
		setSize(350, 70);
		setLocationRelativeTo(null);	
	}
}
