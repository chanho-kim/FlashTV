package Editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

import Editor.Components.Alert;
import Editor.Components.Button;
import Editor.Components.Clock;
import Editor.Components.Label;
import Editor.Components.TextBox;

public class Playboard extends JFrame implements ActionListener, FocusListener{
	
	public static Display display;
	private static JComponent selected = null;
	private static Color saved = null;
	private static Alert selectedAlert = null;
	private static Color savedAlert = null;
	private static int alertTimes = 0;
	private static int autoAlertTimes = 0;
	private int autoAlertCycle;
	private static boolean alertOn = false;
	private static boolean autoAlertOn = false;
	private static Color alertColor = null;
	
	private static ArrayList<Clock> clocks; 
	private static ArrayList<Color> clockBg;
	private static ArrayList<Border> clockBorder;
	
	private JPanel contentPane;
	public static JPanel playPane;
	
	private static boolean clockOn;
	
	private static Border txtBoxFocus = BorderFactory.createLineBorder(Color.LIGHT_GRAY,3);
	private static Border txtBoxSave;
	private static boolean txtBoxPlay;
	private static TextBox txtBoxPlaying;
	
	private static Border savedBorder;
	
	//private static Border playBorder = BorderFactory.createLineBorder(Color.GREEN,2);
	private static Border playBorder = BorderFactory.createLineBorder(new Color(50,205,50),4);
	
		
	private static ActionListener autoAlert = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(autoAlertTimes == 0){
				autoAlertOff(); 
				autoAlertTimer.stop();
			}
			else{
				toggleAutoAlert();
				autoAlertTimes -= 1;
			}
		}
	};
	private static Timer autoAlertTimer = new Timer(500, autoAlert);
	
	private static ActionListener alert = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(alertTimes == 0){
				alertOff(); 
				//selectedAlert.setBackground(savedAlert);
				selectedAlert.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
				alertFlash.stop();
				selectedAlert = null;
			}
			else{
				toggleAlert();
				alertTimes -= 1;
			}
		}
	};
	private static Timer alertFlash = new Timer(0, alert);
	
	
	private static ActionListener time = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			display.displayText(getTime(), true, false);
		}
	};
	private static Timer clockTimer = new Timer(50,time);
	
	private static ActionListener hold = new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			holdTimer.stop();
			clockTimer.start();
			clockOn();
			if(selected != null){
				//selected.setBackground(saved);
				selected.setBorder(savedBorder);
				selected = null;
			}
			if(txtBoxPlay){
				txtBoxPlay = false;
				if(txtBoxPlaying.hasFocus()){
					txtBoxPlaying.setBorder(txtBoxFocus);
				}
				else{
					txtBoxPlaying.setBorder(txtBoxSave);
				}
				txtBoxPlaying = null;
			}
		}
	};
	private static Timer holdTimer = new Timer(MainPanel.convertDisplayDuration(),hold);
	
	private Action enterKey = new AbstractAction() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
    		if(e.getSource() instanceof TextBox){
    			if(selected != null){
					//selected.setBackground(saved);
					selected.setBorder(savedBorder);
					selected = null;
				}  
    			if(autoAlertTimer.isRunning()){
    				autoAlertOff();
    				autoAlertTimer.stop();
    			}
    			clockTimer.stop();
    			clockOff();
    			if(txtBoxPlaying != null){
    				if(!txtBoxPlaying.equals(e.getSource())){
    					txtBoxPlaying.setBorder(Workboard.defaultBorder);
    				}
    			}
    			txtBoxPlaying = (TextBox) e.getSource();
    			display.displayText(txtBoxPlaying.getText(), false, true);
    			if(MainPanel.autoAlert){
    				if(alertFlash.isRunning()){
    					//selectedAlert.setBackground(savedAlert);
    					selectedAlert.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    					alertFlash.removeActionListener(alert);
    				}	    				
    				autoAlertTimes = autoAlertCycle;
    				autoAlertTimer.restart();
    			}    			
    			txtBoxPlaying.setBorder(playBorder);
    			txtBoxPlay = true;
    			txtBoxPlaying.setText("");    		
    			holdTimer.restart();
    		}
	    }
	};
	
	Playboard(int width, int height, Component[] components, GraphicsConfiguration gc, GraphicsConfiguration gc2){
		super(gc);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, width, height);
		contentPane.setPreferredSize(new Dimension(width-30, height-30));
		add(contentPane);
		setLayout(null);
		
		playPane = new JPanel();
		playPane.setBounds(0, 0, width, height);
		playPane.setPreferredSize(new Dimension(width, height));
		playPane.setBackground(MainPanel.board.getBackground());
		playPane.setLayout(null);
		contentPane.add(playPane);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(width,height);
		setTitle("Control Panel - To go back to editting, close either one of the windows");
		setContentPane(contentPane);
		getContentPane().setBackground(MainPanel.board.getBackground());
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(true);
		setMinimumSize(new Dimension(400,400));
		
				
		if(gc2 != null)	display = new Display(gc2);
		else display = new Display(gc);
		
		display.setVisible(true);
		
		clocks = new ArrayList<Clock>();
		clockBg = new ArrayList<Color>();
		clockBorder = new ArrayList<Border>();
		
		holdTimer = new Timer(MainPanel.convertDisplayDuration(),hold);
		autoAlertTimer = new Timer(MainPanel.convertAutoAlertSpeed()/2,autoAlert);
		autoAlertCycle = Integer.parseInt(MainPanel.autoAlertTimes) * 2;
		autoAlertTimes = autoAlertCycle;
		
		txtBoxPlaying = null;
		
		for(int i=0; i<components.length; i+=1){
			if(components[i] instanceof Button){
				Button temp = (Button)components[i];
				Button button = new Button(temp.getText());
				button.setBounds(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				button.setBackground(temp.getBackground());
				button.setForeground(temp.getForeground());
				button.setBorder(temp.getBorder());
				button.setFont(temp.getFont());
				button.setActionCommand(temp.getDisplayTxt());
				button.addActionListener(this);
				playPane.add(button);	
			}
			else if(components[i] instanceof Label){
				Label temp = (Label)components[i];
				Label label = new Label(temp.getText(),SwingConstants.CENTER);
				label.setBounds(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
				label.setBackground(temp.getBackground());
				label.setForeground(temp.getForeground());
				label.setBorder(temp.getBorder());
				label.setFont(temp.getFont());
				label.setOpaque(true);
				playPane.add(label);				
			}
			else if(components[i] instanceof Clock){
				Clock clock = new Clock();
				clock.setText(((AbstractButton) components[i]).getText());
				clock.setBounds(components[i].getX(), components[i].getY(), components[i].getWidth(), components[i].getHeight());
				clock.setBackground(components[i].getBackground());
				clock.setForeground(components[i].getForeground());
				clock.setBorder(((JComponent) components[i]).getBorder());
				clock.setFont(components[i].getFont());
				clock.setActionCommand("Clock");
				clock.addActionListener(this);
				clocks.add(clock);
				clockBg.add(clock.getBackground());
				clockBorder.add(clock.getBorder());
				playPane.add(clock);
			}
			else if(components[i] instanceof Alert){
				Alert alert = new Alert();
				alert.setText(((AbstractButton) components[i]).getText());
				alert.setBounds(components[i].getX(), components[i].getY(), components[i].getWidth(), components[i].getHeight());
				alert.setBackground(components[i].getBackground());
				alert.setForeground(components[i].getForeground());
				alert.setBorder(((JComponent) components[i]).getBorder());
				alert.setFont(components[i].getFont());
				alert.setActionCommand("Alert");
				alert.addActionListener(this);
				alert.setAlertColor(((Alert) components[i]).getAlertColor());
				alert.setAlertSpeed(((Alert) components[i]).getAlertSpeed());
				alert.setAlertTimes(((Alert) components[i]).getAlertTimes());
				playPane.add(alert);
			}
			else if(components[i] instanceof TextBox){
				TextBox txtBox = new TextBox();
				txtBox.setBounds(components[i].getX(), components[i].getY(), components[i].getWidth(), components[i].getHeight());
				txtBox.setBackground(components[i].getBackground());
				txtBox.setForeground(components[i].getForeground());
				txtBox.setBorder(((JComponent) components[i]).getBorder());
				txtBox.setFont(components[i].getFont());
				txtBox.setEditable(true);
				txtBox.setWrapStyleWord(true);
				txtBox.setLineWrap(true);
				txtBox.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"enterKey");
		    	txtBox.getActionMap().put("enterKey",enterKey);
		    	txtBox.addFocusListener(this);
				playPane.add(txtBox);
			}
			
		}
		clockTimer.start();
		clockOn();
		clockOn = true;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Clock")){
			if(!clockOn){
				if(selected != null){
					//selected.setBackground(saved);
					selected.setBorder(savedBorder);
					selected = null;
				}
				if(txtBoxPlay){
					txtBoxPlaying.setBorder(txtBoxSave);
					txtBoxPlay = false;
				}
				holdTimer.stop();
				clockTimer.start();
				clockOn();
			}
		}
		else if(e.getActionCommand().equals("Alert")){
			if(alertFlash.isRunning()){
				//selectedAlert.setBackground(savedAlert);
				selectedAlert.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
				alertFlash.removeActionListener(alert);
				alertFlash.stop();
			}
			if(autoAlertTimer.isRunning()){
				autoAlertOff();
				autoAlertTimer.stop();
			}
			selectedAlert = (Alert) e.getSource();
			//savedAlert = (Color) selectedAlert.getBackground();
			//selectedAlert.setBackground(new Color(255-savedAlert.getRed(),255-savedAlert.getGreen(),255-savedAlert.getBlue()));
			selectedAlert.setBorder(playBorder);
			alertTimes = selectedAlert.getAlertTimesAsInt() * 2;
			alertColor = selectedAlert.getAlertColor();
			alertFlash = new Timer(selectedAlert.getAlertSpeedAsInt(), alert);
			alertFlash.start();
		}		
		else{
			if(clockTimer.isRunning()){
				clockTimer.stop();
				clockOff();
			}
			if(e.getSource() instanceof Button){
				if(selected != null){
					//selected.setBackground(saved);
					selected.setBorder(savedBorder);
				}
				if(txtBoxPlay){
					txtBoxPlaying.setBorder(txtBoxSave);
					txtBoxPlay = false;
				}
				if(autoAlertTimer.isRunning()){
					autoAlertOff();
					autoAlertTimer.stop();
				}
				selected = (Button) e.getSource();
				//saved = selected.getBackground();
				savedBorder = selected.getBorder();
				//selected.setBackground(new Color(255-saved.getRed(),255-saved.getGreen(),255-saved.getBlue()));
				selected.setBorder(playBorder);
			}
			display.displayText(e.getActionCommand(), false, false);
			if(MainPanel.autoAlert){
				if(alertFlash.isRunning()){
					//selectedAlert.setBackground(savedAlert);
					selectedAlert.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
					alertFlash.removeActionListener(alert);
				}				
				autoAlertTimes = autoAlertCycle;
				autoAlertTimer.restart();
			}
			holdTimer.restart();
		}
	}
	
	private static void toggleAlert(){
		if(!alertOn){
			display.getContentPane().setBackground(alertColor);
			alertOn = true;
		}
		else{
			display.getContentPane().setBackground(MainPanel.displayBg);
			alertOn = false;
		}
	}
	
	private static void toggleAutoAlert(){
		if(!autoAlertOn){
			display.getContentPane().setBackground(MainPanel.autoAlertColor);
			autoAlertOn = true;
		}
		else{
			display.getContentPane().setBackground(MainPanel.displayBg);
			autoAlertOn = false;
		}
	}
	
	private static void alertOff(){
		display.getContentPane().setBackground(MainPanel.displayBg);
		alertOn = false;
	}
	
	private static void autoAlertOn(){
		display.getContentPane().setBackground(MainPanel.autoAlertColor);
		autoAlertOn = true;
	}
	private static void autoAlertOff(){
		display.getContentPane().setBackground(MainPanel.displayBg);
		autoAlertOn = false;
	}
	
	private static void clockOn(){
		clockOn = true;
		Iterator iterator = clocks.iterator();
		while(iterator.hasNext()){
			Clock clock = (Clock) iterator.next();
			//clock.setBackground(new Color(255-clock.getBackground().getRed(),255-clock.getBackground().getGreen(),255-clock.getBackground().getBlue()));
			clock.setBorder(playBorder);
		}
	}
	
	private static void clockOff(){
		clockOn = false;
		Iterator iterator = clocks.iterator();
		Iterator iterator2 = clockBg.iterator();
		Iterator iterator3 = clockBorder.iterator();
		while(iterator.hasNext()){
			Clock clock = (Clock) iterator.next();
			Color color = (Color) iterator2.next();
			Border border = (Border) iterator3.next();
			//clock.setBackground(color);	
			clock.setBorder(border);
		}
	}
	
	private static String getTime()
    {
    	Date time = new Date();  
    	int hours = time.getHours();
    	int minutes = time.getMinutes();
    	int seconds = time.getSeconds();
    	
    	if(hours == 0)
    		hours = 12;
    	else if(hours > 12)
    		hours -= 12;
    	if(minutes < 10 && seconds < 10)
    		return  hours + ":0" + minutes + ":0" + seconds;
    	else if(minutes >= 10 && seconds < 10)
    		return  hours + ":" + minutes + ":0" + seconds;
    	else if(minutes < 10 && seconds >= 10)
    		return  hours + ":0" + minutes + ":" + seconds;
    	else
    		return  hours + ":" + minutes + ":" + seconds;
    }

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() instanceof TextBox){
			TextBox txtBox = (TextBox) e.getSource();
			txtBoxSave = txtBox.getBorder();
			txtBox.setBorder(txtBoxFocus);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() instanceof TextBox && txtBoxPlay != true){
			TextBox txtBox = (TextBox) e.getSource();
			txtBox.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		}
	}
}
