package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import exam.GUI.TablePanel;
import parser.ExamParser;

public class InputParser {
	
	File file;
	TablePanel panel;
	
	public InputParser(File file, TablePanel panel){
		this.file = file;
		this.panel = panel;
		parse();
	}
	
	public void parse(){
		
		Scanner scanner = null;
		ArrayList<String> courseList = new ArrayList<String>();
		
		try {
			scanner = new Scanner(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scanner.hasNext()){
			courseList.add(scanner.nextLine());
		}
		
		for (String s : courseList){
			ExamParser parser = new ExamParser(s);
			panel.addExamRow(parser.getTableRow());
		}
		
	}

}
