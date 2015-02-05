package Editor.Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Editor.MainPanel;
import Editor.PopUpMenu;
import Editor.Workboard;

public class Alert extends JButton{
	public PopUpMenu menu;
	private Color alertColor;
	private String alertTimes;
	private String alertSpeed;
	private ImageIcon cornerImage = Workboard.cornerImage;
	
	public Alert(){
		super("Alert");
		menu = new PopUpMenu();
		alertColor = Color.RED;
		alertTimes = "5";
		alertSpeed = "0.50";
	}
	
	public Alert(String string){
		super(string);
		menu = new PopUpMenu();
		alertColor = Color.RED;
		alertTimes = "5";
		alertSpeed = "0.50";
	}
	
	public Color getAlertColor(){
		return alertColor;		
	}
	
	public String getAlertTimes(){
		return alertTimes;
	}
	
	public String getAlertSpeed(){
		return alertSpeed;
	}
	
	public void setAlertColor(Color color){
		alertColor = color;
	}
	
	public void setAlertTimes(String string){
		alertTimes = string;
	}
	
	public void setAlertSpeed(String string){
		alertSpeed = string;
	}
	
	public int getAlertSpeedAsInt(){
		float b = Float.parseFloat(alertSpeed);
		b = b / 2;
		b = b * 1000;
		b = Math.round(b);
		int a = (int)b;
		return a;
	}
	
	public int getAlertTimesAsInt(){
		int a = Integer.parseInt(alertTimes);
		return a;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(MainPanel.editMode && Workboard.selected != null && !Workboard.dragSelected){
			if(Workboard.selected.equals(this)){
				Rectangle rect = new Rectangle(this.getWidth()-15,this.getHeight()-15,15,15);
				cornerImage.paintIcon(this, g, this.getWidth()-15, this.getHeight()-15);
			}
		}
	}
}
