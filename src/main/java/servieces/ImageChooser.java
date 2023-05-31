/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servieces;

import filter.ImageFilter;
import javax.swing.JFileChooser;

/**
 *
 * @author jyk22
 */
public class ImageChooser extends JFileChooser{

	public ImageChooser() {
		addChoosableFileFilter(new ImageFilter());
		setAcceptAllFileFilterUsed(false);
	}
	
}