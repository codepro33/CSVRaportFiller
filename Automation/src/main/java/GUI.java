import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
	JFrame frame;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	String bolt;
	String uber;
	String formula;
	Boolean close = false;
	
	JFileChooser fileChooser = new JFileChooser();
	
	 GUI(){
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
	    frame = new JFrame();
		frame.setSize(400,400);
		frame.setLayout(layout);
		frame.setLocationRelativeTo(null);
		frame.setTitle("AIZA Multitool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		button1 = new JButton("Select Bolt");
		button1.addActionListener(this);
		c.gridx=2;
		c.gridy=2;
		c.weightx=1;
		c.weighty=1;
		c.anchor= GridBagConstraints.NORTHEAST;
		frame.add(button1,c);
		
		button2 = new JButton("Select Uber");
		c.anchor=GridBagConstraints.NORTHWEST;
		button2.addActionListener(this);
		frame.add(button2,c);
		
		button3 = new JButton("Select Formula");
		c.anchor=GridBagConstraints.PAGE_START;
		button3.addActionListener(this);
		frame.add(button3,c);
		
		button4 = new JButton("OK");
		c.anchor=GridBagConstraints.CENTER;
		button4.addActionListener(this);
		frame.add(button4,c);
		frame.setVisible(true);
	}
	 @Override
	 public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button1) {
			int choice = fileChooser.showOpenDialog(null);
			
			if(choice == JFileChooser.APPROVE_OPTION) {
				
				bolt = fileChooser.getSelectedFile().getAbsolutePath();
				
			}
		}
		
		if(e.getSource()==button2) {
			int choice = fileChooser.showOpenDialog(null);
			
			if(choice== JFileChooser.APPROVE_OPTION) {
				
				uber = fileChooser.getSelectedFile().getAbsolutePath();
				
			}
		}
		
		if(e.getSource()==button3) {
			int choice = fileChooser.showOpenDialog(null);
			
			if(choice== JFileChooser.APPROVE_OPTION) {
				
				formula = fileChooser.getSelectedFile().getAbsolutePath();
				
			}
		}
		
		if(e.getSource()==button4) {

				close = true; 
				frame.dispose();
				
		}
		
	 }


	
}
