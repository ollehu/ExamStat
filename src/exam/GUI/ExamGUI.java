package exam.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class ExamGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private ButtonPanel buttonPanel;
	private TablePanel tablePanel;
	
	public ExamGUI(){
		super("Exam GUI");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		buttonPanel = new ButtonPanel();
		buttonPanel.setMaximumSize(new Dimension(200,50));
		tablePanel = new TablePanel();
		
		add(buttonPanel, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(400, 500));
		
		buttonPanel.setup(tablePanel);
		
		pack();
		setVisible(true);		
	}
}