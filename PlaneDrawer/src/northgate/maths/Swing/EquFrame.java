/**
 * 
 */
package northgate.maths.Swing;

import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.JFrame;

/**
 * @author Kyle
 *
 */
public class EquFrame extends JFrame{
	
	private final int numPlane = 5;
	
	private Equation[] equations = new Equation[numPlane];
	
	
	public EquFrame(){
		super("Editing Panel");
		setBounds(0, 0, 250, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		addEquations();
		
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
		
		for(int i = 0; i < vecs.length; i++){
			if(equations[i].TypeSel.getSelectedIndex() == 0){
				//cart
				
			}
			
			if(equations[i].TypeSel.getSelectedIndex() == 1){
				//vector
			}
			
			if(equations[i].TypeSel.getSelectedIndex() == 2){
				needDraw[i] = false;
				vecs[i][0].setX(0);
				vecs[i][0].setY(0);
				vecs[i][0].setZ(0);
				vecs[i][1].setX(0);
				vecs[i][1].setY(0);
				vecs[i][1].setZ(0);
				vecs[i][2].setX(0);
				vecs[i][2].setY(0);
				vecs[i][2].setZ(0);
				vecs[i][3].setX(0);
				vecs[i][3].setY(0);
				vecs[i][3].setZ(0);
				
			}
			
		}
		
	}
	

}
