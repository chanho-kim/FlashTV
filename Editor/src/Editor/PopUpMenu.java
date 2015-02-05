package Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenu extends JPopupMenu{
	
	JMenuItem edit;
	JMenuItem delete;
	JMenuItem copy;
	
	public PopUpMenu(){
		super();
		
		edit = new JMenuItem("Edit");
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("EDIT!");
				Workboard.openEditMenu();
			}
		});
		
		copy = new JMenuItem("Copy");
		copy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("COPY!");
				Workboard.copy();
			}	
		});
		
		delete = new JMenuItem("Delete");
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DELETE!");
				Workboard.delete();
			}
		});
		
		this.add(edit);
		this.add(delete);
		this.add(copy);
	}
	
	public void removeCopy(){
		remove(copy);
	}
	
	public void insertCopy(){
		add(copy);
	}
	
}
