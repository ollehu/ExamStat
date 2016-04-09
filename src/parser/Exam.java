package parser;

public class Exam {
	String courseName;
	String examDate;
	int fail = 0;
	int three = 0;
	int four = 0;
	int five = 0;


	public Exam(){

	}

	public Exam(int fail, int three, int four, int five){
		this.fail = fail;
		this.three = three;
		this.four = four;
		this.five = five;
	}

	public void print(){
		System.out.println(String.format("Course: %s Date: %s (%d U) (%d 3) (%d 4) (%d 5) with average %f. \n", courseName, examDate, fail, three, four, five, getAverage()));
	}

	public float getAverage(){
		if (three + four + five > 0){
			return (float)(three*3 + four*4 + five*5)/(float)(three + four + five);
		} else {
			return 0;
		}
	}
	
	
}