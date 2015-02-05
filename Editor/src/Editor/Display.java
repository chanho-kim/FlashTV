package Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Editor.Components.Alert;
import Editor.Components.Button;
import Editor.Components.Clock;
import Editor.Components.Label;

public class Display extends JFrame implements ComponentListener{
	
	JLabel text;
	Font displayFont, clockFont, areaFont;
	
	public Display(GraphicsConfiguration gc){
		super(gc);
		setTitle("Display - To go back to editting, close either one of the windows");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Rectangle a = gc.getBounds();
		setSize((int)a.getWidth(),(int)a.getHeight()-50);
		getContentPane().setBackground(MainPanel.displayBg);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(true);
		setMinimumSize(new Dimension(400,400));
		addComponentListener(this);
		
		
		text = new JLabel();
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		int size = Integer.parseInt(MainPanel.displayTxtSize);
		int sizeC = Integer.parseInt(MainPanel.displayTxtSizeC);
		int sizeT = Integer.parseInt(MainPanel.displayTxtSizeT);
		if(MainPanel.bold){
			displayFont = new Font(MainPanel.displayFt.getName(), Font.BOLD, size);
			clockFont = new Font(MainPanel.displayFt.getName(), Font.BOLD, sizeC);
			areaFont = new Font(MainPanel.displayFt.getName(), Font.BOLD, sizeT);
		}
		else{
			displayFont = new Font(MainPanel.displayFt.getName(), Font.PLAIN, size);
			clockFont = new Font(MainPanel.displayFt.getName(), Font.PLAIN, sizeC);
			areaFont = new Font(MainPanel.displayFt.getName(), Font.PLAIN, sizeT);
		}
		
		text.setForeground(MainPanel.displayTxtColor);
		text.setBounds(1, 0, this.getWidth()-1, this.getHeight());
		
		text.setVerticalAlignment(SwingConstants.CENTER);
		text.setVerticalTextPosition(SwingConstants.CENTER);
		//text.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		add(text);
	}
	
	public void displayText(String s, boolean clock, boolean area){
		if(clock) text.setFont(clockFont);
		else if(area) text.setFont(areaFont);
		else text.setFont(displayFont);
		text.setText(s);
		wrapText(text);
	}
	
	public void alert(Color c)
	{	
		if(c.equals(Color.red))
			getContentPane().setBackground(Color.red);
		else
			getContentPane().setBackground(Color.black);
	}
	
	private void wrapText(JComponent c){
		JLabel label = new JLabel();
		String endResult = "";
		String temp = "";
		String string = "";
	
		int length;
		int limit = c.getWidth() - 10;
		if(c instanceof Label || c instanceof JLabel){
			label = (JLabel) c;
			string = label.getText().replace("<br>", " ");		
		}
		else return;
		
		length = Workboard.measureStringLength(c,string);
		if(length <= limit){
			if(c instanceof Label || c instanceof JLabel) label.setText(string);
			return;
		}
		String[] str = string.split("\\s+");
		for(int i=0; i<str.length; i+=1){
			if(!temp.equals("")) temp = temp + " " + str[i];
			else temp = temp + str[i];
			length = Workboard.measureStringLength(c, temp);
			if(length > limit){
				length = Workboard.measureStringLength(c, str[i]);
				if(length > limit){
					temp = convertStringToMultiple(c,temp);
					endResult = temp;
					String[] strs = temp.split("<br>");
					temp = strs[strs.length-1];
				}
				else{
					endResult = endResult + "<br>" + str[i];
					temp = str[i];
				}
			}
			else{
				if(!endResult.equals("")){
					endResult = endResult + " " + str[i];
				}
				else endResult = endResult + str[i];
			}
		}
		if(c instanceof Label || c instanceof JLabel) label.setText("<html><center>" + endResult + "</html>");
		return;
	}
	
	private String convertStringToMultiple(JComponent c, String string){
		int limit = c.getWidth()-10;
		int length;
		String substring = "", substring2 = "";
		for(int i = 1; i<string.length()+1; i+=1){
			substring = string.substring(0,i);
			length = Workboard.measureStringLength(c, substring);
			if(length > limit){
				substring = string.substring(0,i-1);
				substring2 = string.substring(i-1);
				break;
			}
		}
		length = Workboard.measureStringLength(c, substring2);
		if(length > limit){
			substring2 = convertStringToMultiple(c, substring2);
		}
		if(substring2.length() != 0){
			substring = substring + "<br>" + substring2;
		}
		return substring;
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
		int width = this.getWidth();
		int height = this.getHeight();
		text.setBounds(0,0,width,height);
		this.repaint();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
