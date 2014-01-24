/**
 * 
 */
package northgate.maths.opengl;



import northgate.maths.Swing.Vector3D;





import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
/**
 * @author Kyle
 *
 */
public class GLWindow {
	
    private final float piover180 = 0.0174532925f;
	
	public Vector3D[][] drawvecs = new Vector3D[5][4];
	public boolean[] needWriting = new boolean[5];
	public final float[] axis = {
			0, 0, -100,
			0, 0, 100,
			0, -100, 0,
			0, 100, 0,
			-100, 0, 0,
			100, 0, 0
			};
	
	public float camX, camY, camZ;
	public float xRot, yRot;
	
	public int MouseX, MouseY;
	public int oldMouseX, oldMouseY;
	
	public GLWindow() {
	
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Renderer");
			Display.setResizable(false);
			Display.create();
		} catch (LWJGLException e) {
			if(e.getMessage() == "Pixel Format Not Accelerated"){
				System.setProperty("Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
				Display.setTitle(Display.getTitle() + " -missing latest Gcard drivers (Running in safe mode)");
				try {
					Display.create();
				} catch (LWJGLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		oldMouseX = Mouse.getX();
		oldMouseY = Mouse.getY();
		camX = 2;
		camY = 2;
		camZ = 20;
		
		initGL();
		
		while(!Display.isCloseRequested()){
			
			glPushMatrix();
			Update();
			GLRender();
			glPopMatrix();
			Display.update();
		}
		Display.destroy();
		System.exit(0);
	}
	
	/**
	 * 
	 */
	private void GLRender() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();

		glTranslatef(-camX, -camY, -camZ);
		glRotatef(xRot, 0, 1, 0);
		glRotatef(yRot, 1, 0, 0);



			glBegin(GL_LINES);
			glColor3d(1, 0, 0);
			glVertex3f(axis[0], axis[1], axis[2]);
			glVertex3f(axis[3], axis[4], axis[5]);
			
			glColor3d(0, 1, 0);
			glVertex3f(axis[6], axis[7], axis[8]);
			glVertex3f(axis[9], axis[10], axis[11]);
			
			glColor3d(0, 0, 1);
			glVertex3f(axis[12], axis[13], axis[14]);
			glVertex3f(axis[15], axis[16], axis[17]);
			
			
			glEnd();
			
			for(int i = 0 ; i < drawvecs.length; i++){
				if(needWriting[i]){
				glBegin(GL_QUADS);
				for(int j = 0 ; j < drawvecs[i].length; j++){
					glVertex3d(drawvecs[i][j].getX(), drawvecs[i][j].getY(), drawvecs[i][j].getZ());
				}
				glEnd();
			
				}
			}

	
		
		
	}

	/**
	 * 
	 */
	private void Update() {
		MouseX = Mouse.getX();
		MouseY = Mouse.getY();
		
		
		float xChange = MouseX - oldMouseX;
		float yChange = MouseY - oldMouseY;
		
		oldMouseX = MouseX;
		oldMouseY = MouseY;
		if(Mouse.isButtonDown(0)){
			camX += xChange/100;
			camY += yChange/100;
			
	
		}
		if(Mouse.isButtonDown(1)){
			yRot -= yChange/10;
			xRot += xChange/10;
		}
		camZ += -Mouse.getDWheel()/100;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			camX = 2;
			camY = 2;
			camZ = 20;
			yRot = 0;
			xRot = 0;
		}
		
	}
		

	/**
	 * 
	 */
	private void initGL() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum(-0.0552, 0.0552, -0.0414, 0.0414, 0.1f, 100f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, 800, 600);
		glEnable(GL_DEPTH_TEST);
		
		
	}

	/**
	 * 
	 */


	public void upDatevecs(Vector3D[][] vecs, boolean[] needwriting){
		
	}

}
