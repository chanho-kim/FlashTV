package Editor.Components;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Editor.MainPanel;
import Editor.PopUpMenu;
import Editor.Workboard;

public class TextBox extends JTextArea{
	public PopUpMenu menu;
	private ImageIcon cornerImage = Workboard.cornerImage;
	
	public TextBox(){
		super();
		menu = new PopUpMenu();
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
