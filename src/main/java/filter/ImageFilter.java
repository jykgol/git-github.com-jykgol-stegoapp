/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author jyk22
 */

public class ImageFilter extends FileFilter{
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = Utils.getExtension(f);
		if (extension != null) {
				if (extension.equals(Utils.png) || extension.equals(Utils.jpg)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String getDescription() {
		return "jpg/png";
	}
}
