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
		System.out.println(String.format("Course: %s Date: %s (%d U) (%d 3) (%d 4) (%d 5) with average %f and pass %f.", courseName, examDate, fail, three, four, five, getAverage(), getPassedPercentage()));
	}

	public float getAverage(){
		if (three + four + five > 0){
			return (float)(three + four*2 + five*3)/(float)(three + four + five);
		} else {
			return 0;
		}
	}
	
	public float getPassedPercentage(){
		return 100*((float)(three + four + five)/(float)(fail + three + four + five));
	}
	
	
}
