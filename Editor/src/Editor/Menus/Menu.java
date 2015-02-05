package Editor.Menus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Editor.MainPanel;
import Editor.Workboard;
import Editor.Components.Alert;
import Editor.Components.Button;
import Editor.Components.Clock;
import Editor.Components.Label;
import Editor.Components.TextBox;

public class Menu extends JFrame{
	Menu(){
		super();
	}
	
	public void getComponent(){
	}
		

	public static int determineFgColorIndex(Color color){
		if(color.equals(Color.WHITE)) return 0;
		else if(color.equals(Color.BLACK)) return 1;
		else if(color.equals(Color.BLUE)) return 2;
		else if(color.equals(Color.GREEN)) return 3;
		else if(color.equals(Color.RED)) return 4;
		else if(color.equals(Color.YELLOW)) return 5;
		else if(color.equals(Color.PINK)) return 6;
		else if(color.equals(Color.ORANGE)) return 7;
		else if(color.equals(Color.MAGENTA)) return 8;
		else if(color.equals(Color.GRAY)) return 9;
		else return -1;
	}
	

	
	public static int determineBgColorIndex2(Color color){
		if(color.equals(Color.WHITE)) return 0;
		else if(color.equals(Color.BLACK)) return 1;
		else if(color.equals(Color.BLUE)) return 2;
		else if(color.equals(Color.GREEN)) return 3;
		else if(color.equals(Color.RED)) return 4;
		else if(color.equals(Color.YELLOW)) return 5;
		else if(color.equals(Color.PINK)) return 6;
		else if(color.equals(Color.ORANGE)) return 7;
		else if(color.equals(Color.MAGENTA)) return 8;
		else if(color.equals(Color.GRAY)) return 9;
		else return -1;
	}
	
	public static int determineSizeIndex(int size){
		if(size == 5) return 0;
		else if(size == 8) return 1;
		else if(size == 12) return 2;
		else if(size == 15) return 3;
		else if(size == 20) return 4;
		else if(size == 24) return 5;
		else if(size == 30) return 6;
		else if(size == 36) return 7;
		else if(size == 48) return 8;
		else if(size == 56) return 9;
		else if(size == 72) return 10;
		
		else return -1;
	}
	

	public static int determineFtIndex(Font font){
		if(font.getName().equals("Times New Roman")) return 0;
		else if(font.getName().equals("Arial")) return 1;
		else if(font.getName().equals("Verdana")) return 2;
		else if(font.getName().equals("Courier New")) return 3;
		else if(font.getName().equals("Comic Sans MS")) return 4;
		else if(font.getName().equals("Impact")) return 5;
		else if(font.getName().equals("Lucida Console")) return 6;
		else return -1;		
	}
	
	public static boolean determineBold(Font font){
		if(font.isBold()) return true;
		else return false;
	}
	
	public static boolean determineItalic(Font font){
		if(font.isItalic()) return true;
		else return false;
	}

	public static Color determineColor(int index){
		if(index == 0){
			return Color.WHITE;
		}
		else if(index == 1){
			return Color.BLACK;
		}
		else if(index == 2){
			return Color.BLUE;
		}
		else if(index == 3){
			return Color.GREEN;
		}
		else if(index == 4){
			return Color.RED;
		}
		else if(index == 5){
			return Color.YELLOW;
		}
		else if(index == 6){
			return Color.PINK;
		}
		else if(index == 7){
			return Color.ORANGE;
		}
		else if(index == 8){
			return Color.MAGENTA;
		}
		else if(index == 9){
			return Color.GRAY;
		}		
		else return null;
	}
	
	public static Font determineFont(int sizeIndex, int FtIndex, boolean bold, boolean italic){
		int size = 5;
		String name = "Times New Roman";
//		if(sizeIndex == 0) size = 5;
//		else if(sizeIndex == 1) size = 8;
//		else if(sizeIndex == 2) size = 12;
//		else if(sizeIndex == 3) size = 15;
//		else if(sizeIndex == 4) size = 20;
//		else if(sizeIndex == 5) size = 24;
//		else if(sizeIndex == 6) size = 30;
//		else if(sizeIndex == 7) size = 36;
//		else if(sizeIndex == 8) size = 48;
//		else if(sizeIndex == 9) size = 56;
//		else if(sizeIndex == 10) size = 72;
		
		size = sizeIndex;
		
//		if(FtIndex == 0) name = "SansSerif";
//		else if(FtIndex == 1) name = "DialogInput";
//		else if(FtIndex == 2) name = "Monospaced";
		
		if(FtIndex == 0) name = "Times New Roman";
		else if(FtIndex == 1) name = "Arial";
		else if(FtIndex == 2) name = "Verdana";
		else if(FtIndex == 3) name = "Courier New";
		else if(FtIndex == 4) name = "Comic Sans MS";
		else if(FtIndex == 5) name = "Impact";
		else if(FtIndex == 6) name = "Lucida Console";
		
		int style = Font.PLAIN;
		if(bold) style += Font.BOLD;
		if(italic) style += Font.ITALIC;
		
		return new Font(name,style,size);		
	}
		
	
	public static Font determineFont2(int FtIndex, int sizeIndex, int bold, int italic){
		int size = 5;
		String name = "SansSerif";
//		if(sizeIndex == 0) size = 5;
//		else if(sizeIndex == 1) size = 8;
//		else if(sizeIndex == 2) size = 12;
//		else if(sizeIndex == 3) size = 15;
//		else if(sizeIndex == 4) size = 20;
//		else if(sizeIndex == 5) size = 24;
//		else if(sizeIndex == 6) size = 30;
//		else if(sizeIndex == 7) size = 36;
//		else if(sizeIndex == 8) size = 48;
//		else if(sizeIndex == 9) size = 56;
//		else if(sizeIndex == 10) size = 72;
		
		size = sizeIndex;
		
		if(FtIndex == 0) name = "Times New Roman";
		else if(FtIndex == 1) name = "Arial";
		else if(FtIndex == 2) name = "Verdana";
		else if(FtIndex == 3) name = "Courier New";
		else if(FtIndex == 4) name = "Comic Sans MS";
		else if(FtIndex == 5) name = "Impact";
		else if(FtIndex == 6) name = "Lucida Console";
		
		int style = Font.PLAIN;
		if(bold == 1) style += Font.BOLD;
		if(italic == 1) style += Font.ITALIC;
		
		return new Font(name,style,size);	
	}
	
	public static JComponent copy(JComponent component){
		JComponent temp = null;
		if(component instanceof Button){
			temp = new Button(((Button)component).getText());
			((Button) temp).changeDisplayTxt(((Button)component).getDisplayTxt());
		}
		else if(component instanceof Label){
			temp = new Label(((Label)component).getText());
			((Label) temp).setHorizontalAlignment(SwingConstants.CENTER);
			((Label) temp).setVerticalAlignment(SwingConstants.CENTER);
		}
		else if(component instanceof Clock){
			temp = new Clock(((Clock)component).getText());
		}
		else if(component instanceof TextBox){
			temp = new TextBox();
			((TextBox) temp).setEditable(false);
		}
		else if(component instanceof Alert){
			temp = new Alert(((Alert)component).getText());
			((Alert) temp).setAlertColor(((Alert) component).getAlertColor());
			((Alert) temp).setAlertSpeed(((Alert) component).getAlertSpeed());
			((Alert) temp).setAlertTimes(((Alert) component).getAlertTimes());
		}
		else return null;
		temp.setBounds(component.getBounds());
		temp.setBackground(component.getBackground());
		temp.setForeground(component.getForeground());
		if(!(temp instanceof Label))temp.setBorder(MainPanel.board.defaultBorder);
		else temp.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		temp.setFont(component.getFont());
		return temp;
	}	
}
