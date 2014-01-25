/**
 * 
 */
package northgate.maths;

import java.io.File;

import northgate.maths.Swing.EquFrame;
import northgate.maths.opengl.GLWindow;

/**
 * @author Kyle
 *
 */
public class Parent {
	
	public static EquFrame equframe;
	public static GLWindow glwindow;
	
	
	public static void main(String[] args) throws Exception {
		LoadNatives();
		createSwingWindow();
		createGLWindow();

	}
	
	public static void createGLWindow() throws InterruptedException{
		glwindow = new GLWindow();
	}
	
	public static void createSwingWindow(){
		equframe = new EquFrame();
	}
	
	public static void LoadNatives(){
		switch(System.getProperty("os.name").charAt(0)){
		case 'W':
			System.setProperty("org.lwjgl.librarypath", new File("res/windows").getAbsolutePath());
			break;
		case 'M':
			System.setProperty("org.lwjgl.librarypath", new File("res/macosx").getAbsolutePath());
			break;
				
		
		}
		
		
	}
	

}
