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

/**
 * @author Kyle
 *
 */
@SuppressWarnings("serial")
public class EquFrame extends JFrame implements ActionListener{
	
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
		
		for(int i = 0; i < vecs.length; i++){
			if(equations[i].TypeSel.getSelectedIndex() == 0){
				//cart
				
			}
			
			if(equations[i].TypeSel.getSelectedIndex() == 1){
				//vector
				
				float[] out = new float[equations[i].vecInputs.length];
				
				for (int p = 0; p < equations[i].vecInputs.length; p++) {
					JTextField f = equations[i].vecInputs[p];
					String v = f.getText();
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
				
				// Calculate 4 Positions
				float[] pos = {};
				pos[0] = 0;
				pos[1] = 0;
				pos[2] = 0;
				vecs[i][0] = new Vector3D(pos[0], pos[1], pos[2]); // 1
				
			}
			
			if(equations[i].TypeSel.getSelectedIndex() == 2){
				needDraw[i] = false;
				vecs[i][0] = new Vector3D(0,0,0);
				vecs[i][1] = new Vector3D(0,0,0);
				vecs[i][2] = new Vector3D(0,0,0);
				vecs[i][3] = new Vector3D(0,0,0);
				
			}
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == btn) {
			exportVectors();
		}
	}
	

}
