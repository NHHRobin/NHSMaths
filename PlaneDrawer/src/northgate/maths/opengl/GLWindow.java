/**
 * 
 */
package northgate.maths.opengl;



import java.util.Arrays;
import northgate.maths.Parent;
import northgate.maths.Swing.EquFrame;
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
//TODO: Update Controls, Viewing, Color, Cart/Vector Lines (GL_LINES)
public class GLWindow {
	
	// Historical, Maps used in Projection to Frustum Conversion
    @SuppressWarnings("unused")
	private final float piover180 = 0.0174532925f;
	
    // to Display
	public Vector3D[][] drawvecs = new Vector3D[EquFrame.numPlane][4];
	public boolean[] needWriting = new boolean[EquFrame.numPlane];
	public float[][] color = new float[EquFrame.numPlane][3];
	public float[] alpha = new float[EquFrame.numPlane];
	
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
	
	public boolean[] Pressed = new boolean[4];
	
	public GLWindow() throws InterruptedException {
	
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Renderer");
			Display.setResizable(false);
			Display.create();
		} catch (LWJGLException e) { // Fix for old Computers | Nvidia Processors
			if(e.getMessage() == "Pixel Format Not Accelerated"){
				System.setProperty("Dorg.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
				Display.setTitle(Display.getTitle() + " -missing latest Gcard drivers (Running in safe mode)");
				try {
					Display.create();
				} catch (LWJGLException e1) {
					System.out.print("Error Occured, OpenGL SoftwareMode failed, Could not Create Display");
					e1.printStackTrace();
				}
			}
		}
		
		initVar();
		initGL();
		
		while(!Display.isCloseRequested()){
			
			glPushMatrix();
			Update();
			GLRender();
			glPopMatrix();
			Display.update();
			upDatevecs();
			Thread.sleep(1000/60);
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
		
		// Axis
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
		
		// Draw Planes
		for(int i = 0 ; i < drawvecs.length; i++){
			if(needWriting[i]){
				glColor4d(color[i][0], color[i][1], color[i][2], alpha[i]);
				if (drawvecs[i][3] == null) {
					glBegin(GL_LINES);
					for(int j = 0 ; j < 2; j++){
						glVertex3d(drawvecs[i][j].getX(), drawvecs[i][j].getY(), drawvecs[i][j].getZ());
					}
				} else {
					glBegin(GL_QUADS);
					for(int j = 0 ; j < drawvecs[i].length; j++){
						glVertex3d(drawvecs[i][j].getX(), drawvecs[i][j].getY(), drawvecs[i][j].getZ());
					}
				}
				glEnd();
			}
		}

	
		
		
	}
	
	private void Update() {
		// Mouse Handling
		MouseX = Mouse.getX();
		MouseY = Mouse.getY();
		
		float xChange = MouseX - oldMouseX;
		float yChange = MouseY - oldMouseY;
		
		oldMouseX = MouseX;
		oldMouseY = MouseY;
		
		// Left Click
		if(Mouse.isButtonDown(1)){
			camX -= xChange/100;
			camY -= yChange/100;
		}
		
		// Right Click
		if(Mouse.isButtonDown(0)){
			yRot -= yChange/10;
			xRot += xChange/10;
		}
		camZ += -Mouse.getDWheel()/100;
		
		// Keyboard Handling
		
		// LockOut Checks
		if (Pressed[0] && !Keyboard.isKeyDown(Keyboard.KEY_LEFT))  Pressed[0] = false;
		if (Pressed[1] && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) Pressed[1] = false;
		if (Pressed[2] && !Keyboard.isKeyDown(Keyboard.KEY_UP)) Pressed[2] = false;
		if (Pressed[3] && !Keyboard.isKeyDown(Keyboard.KEY_DOWN)) Pressed[3] = false;
		
		// Jump to Original Position, Space
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			camX = 2;
			camY = 2;
			camZ = 20;
			yRot = 0;
			xRot = 0;
		}
		
		// Quick Axis Lock, Enter
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			xRot = normalize(xRot);
			yRot = normalize(yRot);
		}
		
		// Rotate via Arrow Keys
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) xRot += 0.1f;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) xRot -= 0.1f;
		if(Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) yRot += 0.1f;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) yRot -= 0.1f;
		
		// Rotate 90 degrees, LCTRL + Direction
		if(!Pressed[0] && Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			xRot += 90;
			Pressed[0] = true;
		}
		
		if(!Pressed[1] && Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			xRot -= 90;
			Pressed[1] = true;
		}
		
		if(!Pressed[2] && Keyboard.isKeyDown(Keyboard.KEY_UP) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			yRot += 90;
			Pressed[2] = true;
		}
		
		if(!Pressed[3] && Keyboard.isKeyDown(Keyboard.KEY_DOWN) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			yRot -= 90;
			Pressed[3] = true;
		}
		
		// Rotation Limiter; Keep Rot's between |360|
		if (xRot >  360) xRot -= 360;
		if (yRot >  360) yRot -= 360;
		if (xRot < -360) xRot += 360;
		if (yRot < -360) yRot += 360;
		
	}
	
	private float normalize(float x) {
		if (x > 315)  return    0;
		if (x > 225)  return  270;
		if (x > 135)  return  180;
		if (x > 45)	  return   90;
		if (x > -45)  return    0;
		if (x > -135) return  -90;
		if (x > -225) return -180;
		if (x >  315) return -270;
		return -360;
	}
	
	private void initGL() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glFrustum(-0.0552, 0.0552, -0.0414, 0.0414, 0.1f, 300f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glViewport(0, 0, 800, 600);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}
	
	private void initVar() {
		
		oldMouseX = Mouse.getX();
		oldMouseY = Mouse.getY();
		camX = 2;
		camY = 2;
		camZ = 20;
		yRot = 0;
		xRot = 0;
		
		Arrays.fill(Pressed, false);
		
		float[][] validColors = {new float[]{0, 1, 1},
				                 new float[]{0.5f, 1, 0},
				                 new float[]{1, 0, 1},
				                 new float[]{0.5f, 1, 0.5f},
				                 new float[]{1, 0.5f, 0.5f}
								};
		
		
		color = validColors;
	}

	/**
	 * 
	 */


	public void upDatevecs(){
		this.drawvecs = Parent.equframe.exportVectors();
		this.needWriting = Parent.equframe.getNeedDraw();
		this.alpha = Parent.equframe.getAlpha();
	}

}
