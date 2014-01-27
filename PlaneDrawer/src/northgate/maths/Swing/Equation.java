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
@SuppressWarnings("serial")
public class Equation extends JPanel implements ActionListener{
	
	//Constants
	public final static int posCartLine = 0;
	public final static int posVecLine = 1;
	public final static int posCartPlane = 2;
	public final static int posVecPlane = 3;
	public final static int posNone = 4;
	
	//Logic Vars
	//TODO: Change this back to single per Equation boolean
	// Changed due to errors occurring, backtraced to different source
	public static boolean[] needUpdate = new boolean[EquFrame.numPlane];
	public Integer Id;
	
	//Vector Plane Vars
	Vector3D posVec;
	Vector3D fdirVec;
	Vector3D sdirVec;
	public JTextField[] vecPInputs = new JTextField[9];
	public JLabel vecPLabel = new JLabel("r = (  _  ) + t(  _  ) + s(  _  )");
	
	//Vector Line Vars
	public JTextField[] vecLInputs = new JTextField[6];
	public JLabel vecLLabel = new JLabel("r = (  _  ) + t(  _  )");
	
	//Cartesian Plane Vars
	public double x;
	public double y;
	public double z;
	public double K;
	public JTextField[] cartPInputs = new JTextField[4];
	public JLabel cartPLabel = new JLabel("_x+      _y+      _z=      _");
	
	//Cartesian Line Vars
	public JTextField[] cartLInputs = new JTextField[3];
	public JLabel cartLLabel = new JLabel("_x+      _y=      _");
	
	public String[] Type = {"Cartesian-Line", "Vector-Line", "Cartesian-Plane", "Vector-Plane", "None"};
	public JComboBox<String> TypeSel = new JComboBox<String>(Type);
	
	public Equation(int x,int y, int Id){
		
		this.Id = Id;
		EquationListener l = new EquationListener(this);
		
		TypeSel.setSelectedIndex(posNone);
		setBounds(x, y, 230, 100);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(null);
		TypeSel.addActionListener(this);
		TypeSel.setBounds(getWidth()/2 - 100, 5, 200, 20);
		add(TypeSel);
		
		iCarL(l);
		iVecL(l);
		iCarP(l);
		iVecP(l);
		
		add(cartLLabel);
		add(vecLLabel);
		add(cartPLabel);
		add(vecPLabel);

		this.update(true);
	}
	
	//TODO
	public void iCarL(EquationListener l) {
		cartLLabel.setBounds(50, 45, 225, 15);
		cartLLabel.setVisible(false);
		
		cartLInputs[0] = new JTextField();
		cartLInputs[0].setBounds(35, 45, 20, 15);
		cartLInputs[1] = new JTextField();
		cartLInputs[1].setBounds(75, 45, 20, 15);
		cartLInputs[2] = new JTextField();
		cartLInputs[2].setBounds(115, 45, 20, 15);
		for (JTextField j : cartLInputs){
			j.getDocument().putProperty("parent", this);
			j.getDocument().addDocumentListener(l);
			add(j);
			j.setVisible(false);
		}
	}
	
	//TODO
	public void iVecL(EquationListener l) {
		vecLLabel.setBounds(50, 45, 225, 15);
		vecLLabel.setVisible(false);
		
		vecLInputs[0] = new JTextField();
		vecLInputs[0].setBounds(72, 25, 20, 15);
		vecLInputs[1] = new JTextField();
		vecLInputs[1].setBounds(72, 45, 20, 15);
		vecLInputs[2] = new JTextField();
		vecLInputs[2].setBounds(72, 65, 20, 15);
		vecLInputs[3] = new JTextField();
		vecLInputs[3].setBounds(116, 25, 20, 15);
		vecLInputs[4] = new JTextField();
		vecLInputs[4].setBounds(116, 45, 20, 15);
		vecLInputs[5] = new JTextField();
		vecLInputs[5].setBounds(116, 65, 20, 15);
		for (JTextField j : vecLInputs){
			j.getDocument().putProperty("parent", this);
			j.getDocument().addDocumentListener(l);
			add(j);
			j.setVisible(false);
		}
	}

	public void iCarP(EquationListener l) {
		cartPLabel.setBounds(50, 45, 225, 15);
		cartPLabel.setVisible(false);
		
		cartPInputs[0] = new JTextField();
		cartPInputs[0].setBounds(35, 45, 20, 15);
		cartPInputs[1] = new JTextField();
		cartPInputs[1].setBounds(75, 45, 20, 15);
		cartPInputs[2] = new JTextField();
		cartPInputs[2].setBounds(115, 45, 20, 15);
		cartPInputs[3] = new JTextField();
		cartPInputs[3].setBounds(155, 45, 20, 15);
		for (JTextField j : cartPInputs){
			j.getDocument().putProperty("parent", this);
			j.getDocument().addDocumentListener(l);
			add(j);
			j.setVisible(false);
		}
	}
	
	public void iVecP(EquationListener l) {
		vecPLabel.setBounds(50, 45, 225, 15);
		vecPLabel.setVisible(false);
		
		vecPInputs[0] = new JTextField();
		vecPInputs[0].setBounds(72, 25, 20, 15);
		vecPInputs[1] = new JTextField();
		vecPInputs[1].setBounds(72, 45, 20, 15);
		vecPInputs[2] = new JTextField();
		vecPInputs[2].setBounds(72, 65, 20, 15);
		vecPInputs[3] = new JTextField();
		vecPInputs[3].setBounds(116, 25, 20, 15);
		vecPInputs[4] = new JTextField();
		vecPInputs[4].setBounds(116, 45, 20, 15);
		vecPInputs[5] = new JTextField();
		vecPInputs[5].setBounds(116, 65, 20, 15);
		vecPInputs[6] = new JTextField();
		vecPInputs[6].setBounds(161, 25, 20, 15);
		vecPInputs[7] = new JTextField();
		vecPInputs[7].setBounds(161, 45, 20, 15);
		vecPInputs[8] = new JTextField();
		vecPInputs[8].setBounds(161, 65, 20, 15);
		for (JTextField j : vecPInputs){
			j.getDocument().putProperty("parent", this);
			j.getDocument().addDocumentListener(l);
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
			case posCartLine:
				initCartesianLine();
				break;
			case posVecLine:
				initVectorLine();
				break;
			case posCartPlane:
				initCartesian();
				break;
			case posVecPlane:
				initVector();
				break;
			default:
				break;
			}
		}

		this.update(true);
	}

	/**
	 * 
	 */
	public void Clear() {
		posVec = null;
		fdirVec = null;
		sdirVec = null;
		x = 0;
		y = 0;
		z = 0;
		cartLLabel.setVisible(false);
		vecLLabel.setVisible(false);
		cartPLabel.setVisible(false);
		vecPLabel.setVisible(false);
		
		for (JTextField j : vecLInputs){
			j.setVisible(false);
		}
		
		for (JTextField j : cartLInputs){
			j.setVisible(false);
		}
		
		for (JTextField j : vecPInputs){
			j.setVisible(false);
		}
		
		for (JTextField j : cartPInputs){
			j.setVisible(false);
		}
		
		this.update(true);
	}

	/**
	 * 
	 */
	private void initVector() {
		
		for (JTextField j : vecPInputs){
			j.setVisible(true);
		}
		
		vecPLabel.setVisible(true);
	}

	/**
	 * 
	 */
	private void initCartesian() {
		
		for (JTextField j : cartPInputs){
			j.setVisible(true);
		}
		
		cartPLabel.setVisible(true);
	}
	
	private void initVectorLine() {
		
		for (JTextField j : vecLInputs){
			j.setVisible(true);
		}
		
		vecLLabel.setVisible(true);
	}

	/**
	 * 
	 */
	private void initCartesianLine() {
		
		for (JTextField j : cartLInputs){
			j.setVisible(true);
		}
		
		cartLLabel.setVisible(true);
	}
	
	public boolean toPos(Integer i) {
		if (i < 0 || i >= Type.length) return false;
		TypeSel.setSelectedIndex(i);
		return true;
	}
	
	public void update(Boolean b) {
		Equation.needUpdate[Id] = b;
	}
	
}

