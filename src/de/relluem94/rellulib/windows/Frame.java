package de.relluem94.rellulib.windows;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private Dimension minSize; 
	private Dimension maxSize; 
	private Dimension prefSize; 
	private Dimension size;
	private boolean resizable; 
	private boolean dispose; 
	private boolean snapper; 
	
	private JFrame frame;
	private JPanel p;
	
	public Frame(String title, Dimension minSize, Dimension maxSize, Dimension prefSize, Dimension size,
			boolean resizable, boolean dispose, boolean snapper){
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.prefSize = prefSize;
		this.size = size;
		this.resizable = resizable;
		this.dispose = dispose;
		this.snapper = snapper;
		this.frame = new JFrame(title);
		this.p = new JPanel();
		this.p.setBounds(0, 0, size.width, size.height);
	}
	
	public void addToPanel(Component c){
		p.add(c);
	}
	
	public void disposeFrame(){
		frame.dispose();
	}
	
	public JFrame makeFrame(Container pane){
		frame.setSize(size);
		frame.setPreferredSize(prefSize);
		frame.setMaximumSize(maxSize);
		frame.setMinimumSize(minSize);
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setResizable(resizable);
		frame.setVisible(true);
		if(snapper){
			frame.addComponentListener(new WindowSnapper());
		}
		
		if(dispose){
			frame.setVisible(false);
		}
		else{
			frame.setVisible(true);
		}
		
		return frame;
	}
	
	
	
	public Dimension getSize() {
		return size;
	}

	public JPanel getPanel() {
		return p;
	}
}
