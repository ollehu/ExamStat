package parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExamParser {

	
	ArrayList<Exam> examList = new ArrayList<Exam>();
	
	public ExamParser(String courseName){
		parse(courseName);
	}
	
	/**
	 * Shortcut
	 * @param courseName
	 * @return
	 * @throws IOException
	 */
	public void parse(String courseName){
		parse(courseName, "TEN1", false);
	}
	
	/**
	 * Parse a course
	 * 
	 * @param courseName Course (ex. TAMS65)
	 * @param examIdentifier Exam identifier (ex. TEN1)
	 * @param onlyPopularWritings Only gather info from writings with attendance > 100. 
	 * 								Will exclude some re-exams.
	 */
	public void parse(String courseName, String examIdentifier, boolean onlyPopularWritings){
		
		String url = "http://www4.student.liu.se/tentaresult/?kurskod=" + courseName + "&provkod=" + examIdentifier + "&datum=&kursnamn=&sort=0&search=S%F6k";

		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements exams = doc.select("tr[valign=\"TOP\"]");

		int examCount = 0;
		for (Element exam : exams) {
			Exam tempExam = new Exam();
			tempExam.courseName = courseName;

			try{
				for(int i = 4; true; i +=2){
					if (exam.select("tr").get(0).select("td").get(i).text().equals("U") || 
							exam.select("tr").get(0).select("td").get(i).text().equals("1")){
						tempExam.fail = Integer.parseInt(exam.select("tr").get(0).select("td").get(i + 1).text());
					} else if (exam.select("tr").get(0).select("td").get(i).text().equals("3")){
						tempExam.three = Integer.parseInt(exam.select("tr").get(0).select("td").get(i + 1).text());
					} else if (exam.select("tr").get(0).select("td").get(i).text().equals("4")){
						tempExam.four = Integer.parseInt(exam.select("tr").get(0).select("td").get(i + 1).text());
					} else if (exam.select("tr").get(0).select("td").get(i).text().equals("5")){
						tempExam.five = Integer.parseInt(exam.select("tr").get(0).select("td").get(i + 1).text());
					} else {
						//Debug...
						/*System.out.println("Special case... (is not included)");
						System.out.println("Text is: " + exam.select("tr").get(0).select("td").get(i).text());
						System.out.println("i is: " + i);*/
					}

				}

			} catch (IndexOutOfBoundsException e){
				//No more results
				if (tempExam.fail + tempExam.three + tempExam.four + tempExam.five > 0){
					if (onlyPopularWritings){
						if (tempExam.fail + tempExam.three + tempExam.four + tempExam.five >= 100){
							System.out.println("Finished with exam: " + examCount);
							examCount +=1;
							tempExam.examDate = getDate(exam.select("tr").get(0).select("td").get(0).text());
							examList.add(tempExam);
						} else {
							//System.out.println("Not enough attendance");
						}
					} else {
						//System.out.println("Finished with exam: " + examCount);
						examCount +=1;
						tempExam.examDate = getDate(exam.select("tr").get(0).select("td").get(0).text());
						examList.add(tempExam);
					}
				} else {
					//System.out.println("Empty exam");
				}
			}
		}
		
	}
	
	public String getDate(String s){
		return s.substring(s.length() - 10, s.length());
	}

	public float getAverage(){
		int average = 0;
		for (Exam exam : examList){
			average += exam.getAverage();
		}

		return (float)average/(float)examList.size();
	}
	
	public int numberOfExams(){
		return examList.size();
	}
	
	public void printExams(){
		for (Exam exam : examList){
			exam.print();
		}
	}

}
