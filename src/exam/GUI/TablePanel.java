package exam.GUI;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JPanel{

	private JTable table;
	
	public TablePanel(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		table = new JTable(new DefaultTableModel(new Object[]{"Col1", "Col2"}, 20));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		add(table);
	}
	
	public void addExamRow(ArrayList<String> row){
		
	}
	
}
