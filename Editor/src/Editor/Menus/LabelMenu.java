package Editor.Menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Editor.MainPanel;
import Editor.Workboard;
import Editor.Components.Button;
import Editor.Components.Label;

public class LabelMenu extends JFrame implements ActionListener, WindowFocusListener{

	private JPanel contentPane;
	private JTextField label;
	private JComboBox bgColor;
	private JComboBox txtColor;
	private JComboBox txtSize;
	private JComboBox txtFont;
	private JButton confirm;
	private JButton cancel;
	private JCheckBox boldOption;
	private boolean confirmed = false;
	
	Label compLabel;
	private JCheckBox italic;
	
	public LabelMenu() {
		setBackground(SystemColor.menu);
		setTitle("Label Edit");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 313);
		setLocationRelativeTo(null);
		addWindowFocusListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(112, 251, 89, 23);
		confirm.addActionListener(this);
		contentPane.add(confirm);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(210, 251, 89, 23);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 294, 210);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel ButtonName = new JLabel("Label");
		ButtonName.setBounds(10, 6, 44, 22);
		panel.add(ButtonName);
		ButtonName.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		label = new JTextField();
		label.setBounds(152, 6, 132, 25);
		panel.add(label);
		label.setColumns(10);
		
		
		JLabel lblBackgroundColor = new JLabel("Background Color");
		lblBackgroundColor.setBounds(10, 46, 109, 14);
		panel.add(lblBackgroundColor);
		lblBackgroundColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextFont = new JLabel("Text Font");
		lblTextFont.setBounds(10, 82, 92, 14);
		panel.add(lblTextFont);
		lblTextFont.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		JLabel lblTextColor = new JLabel("Text Color");
		lblTextColor.setBounds(10, 118, 92, 14);
		panel.add(lblTextColor);
		lblTextColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextSize = new JLabel("Text Size");
		lblTextSize.setBounds(10, 154, 92, 14);
		panel.add(lblTextSize);
		lblTextSize.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		bgColor = new JComboBox();
		bgColor.setBounds(152, 42, 132, 25);
		panel.add(bgColor);
		bgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtFont = new JComboBox();
		txtFont.setBounds(152, 78, 132, 25);
		panel.add(txtFont);
		txtFont.setModel(new DefaultComboBoxModel(new String[] {"Times New Roman", "Arial", "Verdana", "Courier New", "Comic Sans MS", "Impact", "Lucida Console"}));
		
		txtColor = new JComboBox();
		txtColor.setBounds(152, 114, 132, 25);
		panel.add(txtColor);
		txtColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtSize = new JComboBox();
		txtSize.setEditable(true);
		txtSize.setBounds(152, 150, 132, 25);
		panel.add(txtSize);
		txtSize.setModel(new DefaultComboBoxModel(new String[] {"5", "8", "12", "15", "20", "24", "30", "36", "48", "56", "72"}));
		
		boldOption = new JCheckBox("Bold");
		boldOption.setBounds(147, 180, 54, 23);
		panel.add(boldOption);
		boldOption.setEnabled(true);
		boldOption.setBackground(new Color(0,0,0,0));
		
		italic = new JCheckBox("Italic");
		italic.setEnabled(true);
		italic.setBackground(new Color(0, 0, 0, 0));
		italic.setBounds(200, 180, 54, 23);
		panel.add(italic);
	}
	
	public void getComponent(Label label){
		this.compLabel = label;
		this.label.setText(label.getText());
		
		bgColor.setSelectedIndex(Menu.determineBgColorIndex2(label.getBackground()));
		txtFont.setSelectedIndex(Menu.determineFtIndex(label.getFont()));
		txtColor.setSelectedIndex(Menu.determineFgColorIndex(label.getForeground()));
		txtSize.setSelectedItem(label.getFont().getSize());
		boldOption.setSelected(Menu.determineBold(label.getFont()));
		italic.setSelected(Menu.determineItalic(label.getFont()));
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirm)){
			confirmed = true;
			compLabel.setText(label.getText());
			Workboard.wrapText(compLabel);
			setBgColor();
			setTextColor();
			setFont();
			//MainPanel.board.repaint();
			this.setVisible(false);
			this.dispose();
	
		}
		else{
			this.setVisible(false);
			this.dispose();
		}
	}
	
	private void setBgColor(){
		compLabel.setBackground(Menu.determineColor(this.bgColor.getSelectedIndex()));
	}
	
	
	private void setTextColor(){
		compLabel.setForeground(Menu.determineColor(this.txtColor.getSelectedIndex()));
	}
	
	private void setFont(){
		Object a = txtSize.getSelectedItem();
		if(a instanceof Integer){
			compLabel.setFont(Menu.determineFont((int)a,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
		}	
		else{
			try{
				int b = Integer.parseInt((String)a);
				compLabel.setFont(Menu.determineFont(b,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "Size must only include numbers!");
			}
		}
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		if(!confirmed){
			this.setVisible(false);
			this.dispose();
		}
	}
	
}
