/**
 * 
 */
package northgate.maths.Swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import northgate.maths.Parent;

/**
 * @author Kyle
 *
 */
@SuppressWarnings("serial")
public class EquFrame extends JFrame implements ActionListener{
	
	//cart constants
	private static final int X = 0;
	private static final int Y = 1;
	private static final int Z = 2;
	private static final int D = 3;
	
	//vec constants
	private static final int POSX = 0;
	private static final int POSY = 1;
	private static final int POSZ = 2;
	
	private static final int DIR1X = 3;
	private static final int DIR1Y = 4;
	private static final int DIR1Z = 5;
	
	private static final int DIR2X = 6;
	private static final int DIR2Y = 7;
	private static final int DIR2Z = 8;
	
	
	private final int numPlane = 5;
	
	private Equation[] equations = new Equation[numPlane];
	
	public JButton btn = new JButton("Draw");
	
	public EquFrame(){
		super("Editing Panel");
		setBounds(0, 0, 250, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		addEquations();
		
		btn.setBounds(getWidth() - 80, getHeight() - 50, 70, 20);
		btn.addActionListener(this);
		add(btn);
		
		setVisible(true);
	}
	
	public void addEquations(){
		for(int i = 0; i < numPlane; i++){
			equations[i] = new Equation(5, 5 + 110*i);
			add(equations[i]);
		}
	}
	
	public void exportVectors(){
		Vector3D[][] vecs = new Vector3D[5][4];
		boolean[] needDraw = new boolean[5];
		Arrays.fill(needDraw, true);
		String v;
		
		for(int i = 0; i < vecs.length; i++){
			if(equations[i].TypeSel.getSelectedIndex() == 0){
				//cart
				
				float[] out = new float[equations[i].cartInputs.length];
				for (int p = 0; p < equations[i].cartInputs.length; p++) {
					v = equations[i].cartInputs[p].getText();

					if (v == null) {
						out[p] = 0;
					} else {
						try {
							out[p] = Float.valueOf(v);
						} catch (Exception e) {
							out[p] = 0;
						}
					}
				}
				
				//XXX if cart plane looks wrong its in here		
				vecs[i][0].setX(-50);
				vecs[i][0].setY((out[D] - (out[X] * -50) - (out[Z] * 50)) * (1/out[Y])); 
				vecs[i][0].setZ(50); 
				
				vecs[i][1].setX(-50); 
				vecs[i][1].setY((out[D] - (out[X] * -50) - (out[Z] * -50)) * (1/out[Y])); 
				vecs[i][1].setZ(-50); 
				
				vecs[i][2].setX(50); 
				vecs[i][2].setY((out[D] - (out[X] * 50) - (out[Z] * -50)) * (1/out[Y])); 
				vecs[i][2].setZ(-50); 
				
				vecs[i][3].setX(50); 
				vecs[i][3].setY((out[D] - (out[X] * 50) - (out[Z] * 50)) * (1/out[Y])); 
				vecs[i][3].setZ(50); 
				
			}
			
			if(equations[i].TypeSel.getSelectedIndex() == 1){
				//vector
				
				float[] out = new float[equations[i].vecInputs.length];
				for (int p = 0; p < equations[i].vecInputs.length; p++) {
					v = equations[i].vecInputs[p].getText();

					if (v == null) {
						out[p] = 0;
					} else {
						try {
							out[p] = Float.valueOf(v);
						} catch (Exception e) {
							out[p] = 0;
						}
					}
				}
				//XXX if vec plane looks wrong its in here
				vecs[i][0].setX();
				vecs[i][0].setY(); 
				vecs[i][0].setZ(); 
				
				vecs[i][1].setX(); 
				vecs[i][1].setY(); 
				vecs[i][1].setZ(); 
				
				vecs[i][2].setX(); 
				vecs[i][2].setY(); 
				vecs[i][2].setZ(); 
				
				vecs[i][3].setX(); 
				vecs[i][3].setY(); 
				vecs[i][3].setZ(); 			
			}
			
			if(equations[i].TypeSel.getSelectedIndex() == 2){
				needDraw[i] = false;
				vecs[i][0] = new Vector3D(0,0,0);
				vecs[i][1] = new Vector3D(0,0,0);
				vecs[i][2] = new Vector3D(0,0,0);
				vecs[i][3] = new Vector3D(0,0,0);
				
			}
			
		}
		Parent.glwindow.upDatevecs(vecs, needDraw);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == btn) {
			exportVectors();
		}
	}
	

}
