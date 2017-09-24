package personal;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

public class Filerename {	

	public static void main(String[] args) {
		
		System.out.println("Select folder path for which to list all file names.");
		String absolutePath = promptForFolder();
		System.out.println("Chosen folder path: " + absolutePath);
		File[] files = new File(absolutePath).listFiles();

		for (File theFile : files) {
		    if (theFile.isFile()) {

		    	
		    }
		}
		
		
	}
		
	public static String promptForFolder() {
		Component parent = null;
	    JFileChooser fc = new JFileChooser();
	    fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

	    if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION )
	    {
	        return fc.getSelectedFile().getAbsolutePath();
	    }

	    return null;
	}

	}