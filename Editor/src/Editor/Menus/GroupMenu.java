package Editor.Menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import Editor.Workboard;
import Editor.Components.TextBox;

public class GroupMenu extends JFrame implements ActionListener, WindowFocusListener{

	private JPanel contentPane;
	private JComboBox bgColor;
	private JComboBox txtColor;
	private JComboBox txtSize;
	private JComboBox txtFont;
	private JButton confirm;
	private JButton cancel;
	private JCheckBox boldOption;
	TextBox txtBox;
	private JCheckBox italic;
	private int lastIndexBg = 10;
	private int lastIndexFt = 7;
	private int lastIndexSize = 11;
	
	private ArrayList<JComponent> selection;
	private boolean confirmed = false;
	
	/**
	 * Create the frame.
	 */
	public GroupMenu() {
		setBackground(SystemColor.menu);
		setTitle("Group Edit");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 276);
		setLocationRelativeTo(null);
		addWindowFocusListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(112, 216, 89, 23);
		confirm.addActionListener(this);
		contentPane.add(confirm);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(210, 216, 89, 23);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 294, 175);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBackgroundColor = new JLabel("Background Color");
		lblBackgroundColor.setBounds(10, 10, 109, 14);
		panel.add(lblBackgroundColor);
		lblBackgroundColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextFont = new JLabel("Text Font");
		lblTextFont.setBounds(10, 46, 92, 14);
		panel.add(lblTextFont);
		lblTextFont.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		JLabel lblTextColor = new JLabel("Text Color");
		lblTextColor.setBounds(10, 82, 92, 14);
		panel.add(lblTextColor);
		lblTextColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextSize = new JLabel("Text Size");
		lblTextSize.setBounds(10, 118, 92, 14);
		panel.add(lblTextSize);
		lblTextSize.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		bgColor = new JComboBox();
		bgColor.setBounds(152, 6, 132, 25);
		panel.add(bgColor);
		bgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray", "No Change"}));
		bgColor.setSelectedIndex(10);
		
		txtFont = new JComboBox();
		txtFont.setBounds(152, 42, 132, 25);
		panel.add(txtFont);
		txtFont.setModel(new DefaultComboBoxModel(new String[] {"Times New Roman", "Arial", "Verdana", "Courier New", "Comic Sans MS", "Impact", "Lucida Console", "No Change"}));
		txtFont.setSelectedIndex(7);
		
		txtColor = new JComboBox();
		txtColor.setBounds(152, 78, 132, 25);
		panel.add(txtColor);
		txtColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray", "No Change"}));
		txtColor.setSelectedIndex(10);
		
		txtSize = new JComboBox();
		txtSize.setEditable(true);
		txtSize.setBounds(152, 114, 132, 25);
		panel.add(txtSize);
		txtSize.setModel(new DefaultComboBoxModel(new String[] {"5", "8", "12", "15", "20", "24", "30", "36", "48", "56", "72", "No Change"}));
		txtSize.setSelectedIndex(11);
		
		boldOption = new JCheckBox("Bold");
		boldOption.setBounds(147, 144, 54, 23);
		panel.add(boldOption);
		boldOption.setEnabled(true);
		boldOption.setBackground(new Color(0,0,0,0));
		
		italic = new JCheckBox("Italic");
		italic.setEnabled(true);
		italic.setBackground(new Color(0, 0, 0, 0));
		italic.setBounds(200, 144, 54, 23);
		panel.add(italic);
	}

	public void getComponent(ArrayList<JComponent> components){
		selection = components;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirm)){
			confirmed = true;
			Color bg = null;
			Font ft = null;
			Color fg = null;
			Font size = null;
			if(bgColor.getSelectedIndex() != lastIndexBg){
				bg = Menu.determineColor(bgColor.getSelectedIndex());	
			}
			if(txtFont.getSelectedIndex() != lastIndexFt){
				ft = Menu.determineFont(0, txtFont.getSelectedIndex(), false, false);
			}
			if(txtColor.getSelectedIndex() != lastIndexBg){
				fg = Menu.determineColor(txtColor.getSelectedIndex());
			}
			if(txtSize.getSelectedIndex() != lastIndexSize){
				Object a = txtSize.getSelectedItem();
				if(a instanceof Integer){
					size = Menu.determineFont((int)a, 0, false, false);
				}	
				else{
					try{
						int b = Integer.parseInt((String) a);
						size =  Menu.determineFont(b, 0, false, false);
					}catch(Exception e2){
						JOptionPane.showMessageDialog(this, "Size must only include numbers!");
					}
				}
			}
			
			Iterator iterator = selection.iterator();
			while(iterator.hasNext()){
				JComponent temp = (JComponent) iterator.next();
				int ftSize = temp.getFont().getSize();
				int style = temp.getFont().getStyle();
				if(bg != null){
					temp.setBackground(bg);
				}
				if(ft != null){
					temp.setFont(ft);
				}
				if(fg != null){
					temp.setForeground(fg);
				}
				if(size != null){
					ftSize = size.getSize();
					float realSize = ftSize;
					temp.setFont(temp.getFont().deriveFont(realSize));
				}
				else{
					float realSize = ftSize;
					temp.setFont(temp.getFont().deriveFont(realSize));
				}
				if(this.boldOption.isSelected() && this.italic.isSelected()){
					temp.setFont(temp.getFont().deriveFont(Font.BOLD + Font.ITALIC));
				}
				else if(this.boldOption.isSelected()){
					temp.setFont(temp.getFont().deriveFont(Font.BOLD));
				}
				else if(this.italic.isSelected()){
					temp.setFont(temp.getFont().deriveFont(Font.ITALIC));
				}
				else{
					temp.setFont(temp.getFont().deriveFont(style));
				}				
				
				Workboard.wrapText(temp);
			}
			
			this.setVisible(false);
			this.dispose();
		}
		else{
			this.setVisible(false);
			this.dispose();
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
