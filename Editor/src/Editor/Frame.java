package Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.UIManager.*;

public class Frame extends JFrame implements ComponentListener{

	public static MainPanel mainPanel;
	public static Frame frame;
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static JScrollPane scroller;
	public static ScrollPaneUI originalUI;
	Robot robot;
	private static int width;
	private static int height;
	private static String IV = "miscommunication";
	static String encryptionkey = "my first program";


	public static void main(String[] args) {
		
		
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		frame = new Frame();
		Frame.setDefaultLookAndFeelDecorated(true);
		frame.getContentPane().setLayout(null);
		scroller = new JScrollPane(MainPanel.board, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroller.setBounds(5,60,width-20,height-75);
		//scroller.setBorder(MainPanel.board.defaultBorder);
		scroller.setViewportBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 3));
		Frame.getContainer().add(scroller);
		
		originalUI = scroller.getUI();
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            
		        	UIManager.setLookAndFeel(info.getClassName());

		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		scroller.setUI(originalUI);
		
		frame.setVisible(true);
		frame.addComponentListener(frame);
		
		File file = new File("config");
		FileInputStream stream = null;
		/*
		try {
			stream = new FileInputStream(file);
			byte[] fileContent = new byte[(int)file.length()];
			stream.read(fileContent);
			
			byte[] a = Arrays.copyOfRange(fileContent, 0, 32);
			byte[] b = Arrays.copyOfRange(fileContent, 32, 64);
			byte[] c = Arrays.copyOfRange(fileContent, 64, 96);
			byte[] d = Arrays.copyOfRange(fileContent, 96, 128);
			byte[] e = Arrays.copyOfRange(fileContent, 128, 160);
			
			String s1 = decrypt(a,encryptionkey);
			String s2 = decrypt(b,encryptionkey);
			String s3 = decrypt(c,encryptionkey);
			String s4 = decrypt(d,encryptionkey);
			String s5 = decrypt(e,encryptionkey);
			
			if(!s1.equals(convertTo32Bytes(System.getProperty("os.name")))){
				JOptionPane.showMessageDialog(Frame.frame, "Verification Failed. Please re-verify.", "Error!", JOptionPane.ERROR_MESSAGE);
				stream.close();
				deleteConfig();
				Verification verify = new Verification();
				verify.setVisible(true);
			}
			if(!s2.equals(convertTo32Bytes(System.getProperty("os.arch")))){
				JOptionPane.showMessageDialog(Frame.frame, "Verification Failed. Please re-verify.", "Error!", JOptionPane.ERROR_MESSAGE);
				stream.close();
				deleteConfig();
				Verification verify = new Verification();
				verify.setVisible(true);
			}
			if(!s3.equals(convertTo32Bytes(System.getProperty("os.version")))){
				JOptionPane.showMessageDialog(Frame.frame, "Verification Failed. Please re-verify.", "Error!", JOptionPane.ERROR_MESSAGE);
				stream.close();
				deleteConfig();
				Verification verify = new Verification();
				verify.setVisible(true);
			}
			if(!s4.equals(convertTo32Bytes(System.getProperty("user.name")))){
				JOptionPane.showMessageDialog(Frame.frame, "Verification Failed. Please re-verify.", "Error!", JOptionPane.ERROR_MESSAGE);
				stream.close();
				deleteConfig();
				Verification verify = new Verification();
				verify.setVisible(true);
			}
			if(!s5.equals(convertTo32Bytes(System.getProperty("user.home")))){
				JOptionPane.showMessageDialog(Frame.frame, "Verification Failed. Please re-verify.", "Error!", JOptionPane.ERROR_MESSAGE);
				stream.close();
				deleteConfig();
				Verification verify = new Verification();
				verify.setVisible(true);
			}
			
		
			
			
			stream.close();
		} catch (FileNotFoundException e) {
			Verification verify = new Verification();
			verify.setVisible(true);
		} catch (Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		*/	
				
		
	}
	
	/**
	 * Create the frame.
	 */
	public Frame() {
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		setTitle("FLASH TV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 500);
		setMinimumSize(new Dimension(600,500));
		setBackground(Color.DARK_GRAY);
		setResizable(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		mainPanel = new MainPanel(0,0,width,height);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
	}
	
	public static Container getContainer(){
		return frame.getContentPane();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {	
		width = frame.getWidth();
		height = frame.getHeight()-60;
		Dimension dimension = MainPanel.board.getPreferredSize();
		int x = (int)dimension.getWidth();
		int y = (int)dimension.getHeight();
	//	scroller.setBounds(5, 60, width-25, height-100);
				
		if(x > width-15){
			x = Frame.mainPanel.getWidth()-20;
		}
		if(y > height-75){
			y = Frame.mainPanel.getHeight()-75;
		}
		Frame.scroller.setBounds(5, 60, x+15, y+15);
		Frame.getContainer().revalidate();
		Frame.getContainer().repaint();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {

	}
	
	public static int getScreenWidth(){
		return frame.getWidth();
	}
	
	public static int getScreenHeight(){
		return frame.getHeight();
	}
	
	public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	    return cipher.doFinal(plainText.getBytes("UTF-8"));

	  }
	 
	public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	    return new String(cipher.doFinal(cipherText),"UTF-8");
	}
	  
	public static String convertTo32Bytes(String in){
		if(in.length() < 32){
			for(int i = in.length(); i < 32; i+=1){
				in = in + "#";
			}
		}
		else if(in.length() > 32){
			for(int i = in.length(); i > 32; i-=1){
				in = in.substring(0,in.length()-1);
			}
		}
		return in;
	}
	
	public static void deleteConfig(){
		File config = new File("config");
		try{
			config.delete();
		} catch(Exception e){
		}
	}
}
