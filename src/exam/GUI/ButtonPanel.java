package exam.GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import input.InputParser;

public class ButtonPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton pathButton;
	private JButton parseButton;
	
	private File file = null;
	private JFileChooser fc;
	
	public ButtonPanel(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		pathButton = new JButton("Open");
		pathButton.addActionListener(new pathButtonHandler());
		pathButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(pathButton);
		
		
		
		fc = new JFileChooser();
	}
	
	public void setup(TablePanel tablePanel){
		parseButton = new JButton("Parse");
		parseButton.addActionListener(new parseButtonHandler(tablePanel));
		parseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(parseButton);
	}
	
	public class pathButtonHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent ev){
			int returnValue = fc.showOpenDialog(ButtonPanel.this);
			
			if (returnValue == JFileChooser.APPROVE_OPTION){
				file = fc.getSelectedFile();
			}
		}
		
	}
	
	public class parseButtonHandler implements ActionListener{
		
		TablePanel panel;
		
		public parseButtonHandler(TablePanel panel){
			this.panel = panel;
		}
		
		public void actionPerformed(ActionEvent ev){
			if (file != null){
				new InputParser(file, panel);
			}
		}		
	}
}