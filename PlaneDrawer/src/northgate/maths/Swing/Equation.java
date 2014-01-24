/**
 * 
 */
package northgate.maths.Swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Kyle
 *
 */
public class Equation extends JPanel implements ActionListener{
	
	//Vector Vars
	Vector3D posVec;
	Vector3D fdirVec;
	Vector3D sdirVec;
	public JTextField[] vecInputs = new JTextField[9];
	public JLabel vecLabel = new JLabel("r = (  _  ) + t(  _  ) + s(  _  )");
	
	//Cartesian vars
	public double x;
	public double y;
	public double z;
	public double K;
	public JTextField[] cartInputs = new JTextField[4];
	public JLabel cartLabel = new JLabel("_x+      _y+      _z=      _");
	
	public String[] Type = {"Cartesian", "Vector", "None"};
	public JComboBox TypeSel = new JComboBox(Type);

	
	
	public Equation(int x,int y){
		TypeSel.setSelectedIndex(2);
		setBounds(x, y, 230, 100);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(null);
		TypeSel.addActionListener(this);
		TypeSel.setBounds(20, 20, 50, 20);
		add(TypeSel);
		
		cartLabel.setBounds(50, 45, 225, 15);
		cartLabel.setVisible(false);
		vecLabel.setBounds(50, 45, 225, 15);
		vecLabel.setVisible(false);
		add(cartLabel);
		add(vecLabel);
		cartInputs[0] = new JTextField();
		cartInputs[0].setBounds(35, 45, 20, 15);
		cartInputs[1] = new JTextField();
		cartInputs[1].setBounds(75, 45, 20, 15);
		cartInputs[2] = new JTextField();
		cartInputs[2].setBounds(115, 45, 20, 15);
		cartInputs[3] = new JTextField();
		cartInputs[3].setBounds(155, 45, 20, 15);
		for (JTextField j : cartInputs){
			add(j);
			j.setVisible(false);
		}
		
		vecInputs[0] = new JTextField();
		vecInputs[0].setBounds(72, 25, 20, 15);
		vecInputs[1] = new JTextField();
		vecInputs[1].setBounds(72, 45, 20, 15);
		vecInputs[2] = new JTextField();
		vecInputs[2].setBounds(72, 65, 20, 15);
		vecInputs[3] = new JTextField();
		vecInputs[3].setBounds(116, 25, 20, 15);
		vecInputs[4] = new JTextField();
		vecInputs[4].setBounds(116, 45, 20, 15);
		vecInputs[5] = new JTextField();
		vecInputs[5].setBounds(116, 65, 20, 15);
		vecInputs[6] = new JTextField();
		vecInputs[6].setBounds(161, 25, 20, 15);
		vecInputs[7] = new JTextField();
		vecInputs[7].setBounds(161, 45, 20, 15);
		vecInputs[8] = new JTextField();
		vecInputs[8].setBounds(161, 65, 20, 15);
		for (JTextField j : vecInputs){
			add(j);
			j.setVisible(false);
		}
	}

	/* 
	 * @param arg0
	 */
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == TypeSel){
			Clear();
			switch(TypeSel.getSelectedIndex()){
			case 0:
				initCartesian();
				break;
			case 1:
				initVector();
				break;
			case 2:
				break;
			}
		}
		
	}

	/**
	 * 
	 */
	private void Clear() {
		posVec = null;
		fdirVec = null;
		sdirVec = null;
		x = 0;
		y = 0;
		z = 0;
		cartLabel.setVisible(false);
		vecLabel.setVisible(false);
		
		for (JTextField j : vecInputs){
			j.setVisible(false);
		}
		
		for (JTextField j : cartInputs){
			j.setVisible(false);
		}
		
		
		
	}

	/**
	 * 
	 */
	private void initVector() {
		
		for (JTextField j : vecInputs){
			j.setVisible(true);
		}
		
		vecLabel.setVisible(true);
	}

	/**
	 * 
	 */
	private void initCartesian() {
		
		for (JTextField j : cartInputs){
			j.setVisible(true);
		}
		
		cartLabel.setVisible(true);
	}
	
}

