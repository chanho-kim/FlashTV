package Editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.FileOutputStream;

public class Verification extends JDialog implements WindowFocusListener, ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField sk;


	/**
	 * Create the dialog.
	 */
	public Verification() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Verification");
		setLocationRelativeTo(null);
		addWindowFocusListener(this);
		setResizable(false);
		setBounds(100, 100, 440, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name:");
		lblNewLabel.setBounds(5, 5, 424, 37);
		contentPanel.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(5, 42, 424, 37);
		name.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPanel.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Serial Key:");
		lblNewLabel_1.setBounds(5, 79, 424, 37);
		contentPanel.add(lblNewLabel_1);
		
		sk = new JTextField();
		sk.setBounds(5, 116, 424, 37);
		sk.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPanel.add(sk);
		sk.setColumns(10);
		
		JButton confirm = new JButton("Confirm");
		confirm.setActionCommand("confirm");
		confirm.addActionListener(this);
		confirm.setBounds(219, 164, 100, 35);
		contentPanel.add(confirm);
		
		JButton cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);	
		cancel.setBounds(329, 164, 100, 35);
		contentPanel.add(cancel);
	}


	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowLostFocus(WindowEvent arg0) {
		JOptionPane.showMessageDialog(this, "Verification Failed.", "Error!", JOptionPane.ERROR_MESSAGE);
		Frame.deleteConfig();
		System.exit(0);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if(clicked.getActionCommand().equals("confirm")){
			if(sk.getText().equals(MainPanel.sc) && name.getText().equals(MainPanel.name)){
				this.removeWindowFocusListener(this);
				JOptionPane.showMessageDialog(this, "Verification Success.", "Verification Status", JOptionPane.PLAIN_MESSAGE);
				
				File myFile = new File("config");
				
				try {
					Frame.deleteConfig();
					myFile.createNewFile();

					FileOutputStream out = new FileOutputStream("config");
					
					String input = System.getProperty("os.name");
					input = Frame.convertTo32Bytes(input); 
					byte[] in = Frame.encrypt(input, Frame.encryptionkey);
					out.write(in);
					
					input = System.getProperty("os.arch");
					input = Frame.convertTo32Bytes(input); 
					in = Frame.encrypt(input, Frame.encryptionkey);
					out.write(in);
					
					input = System.getProperty("os.version");
					input = Frame.convertTo32Bytes(input); 
					in = Frame.encrypt(input, Frame.encryptionkey);
					out.write(in);
					
					input = System.getProperty("user.name");
					input = Frame.convertTo32Bytes(input); 
					in = Frame.encrypt(input, Frame.encryptionkey);
					out.write(in);
					
					input = System.getProperty("user.home");
					input = Frame.convertTo32Bytes(input); 
					in = Frame.encrypt(input, Frame.encryptionkey);
					out.write(in);
					
					
					out.close();
				} catch (Exception e1) {
					e1.printStackTrace();
					System.exit(0);
				}
				
				this.setVisible(false);
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(this, "Verification Failed.", "Error!", JOptionPane.ERROR_MESSAGE);
				Frame.deleteConfig();
				System.exit(0);
			}
		}
		else{
			JOptionPane.showMessageDialog(this, "Verification Failed.", "Error!", JOptionPane.ERROR_MESSAGE);
			Frame.deleteConfig();
			System.exit(0);
		}
	}
}
