package main;

import java.util.ArrayList;

import parser.ExamParser;

public class ExamStat {

	public static void main(String[] args) {
		ArrayList<String> queue = new ArrayList<String>();
		
		
		//queue.add("TAMS65");
		//queue.add("TAMS79");
		//queue.add("TATA41");
		queue.add("TSKS10");
		
		for (String s : queue){
			ExamParser parser = new ExamParser(s);
			System.out.println("Average for course " + s + " is " + parser.getAverage());
			System.out.println("With " + parser.numberOfExams() + " number of exams.");
			System.out.println("Exams:");
			parser.printExams();
		}
	}
	
}
