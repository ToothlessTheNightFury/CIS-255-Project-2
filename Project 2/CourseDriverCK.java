import java.util.*;
/*
 * This one is Christopher's Practice
 */

public class CourseDriverCK {

	final static String COURSE_NAME = "Java";
	final static int MAX_ENROLLED_NUM = 3, MAX_WAITLIST_NUM = 3;

	static Course interactiveCourse = new Course(COURSE_NAME, MAX_ENROLLED_NUM, MAX_WAITLIST_NUM);

	public void printIntroScreen() {
		int inputNumber = 0;
		Scanner scan = new Scanner(System.in);

		do {
			System.out.println("Please select one of the following: ");
			System.out.println("Press 1 to add a student. ");
			System.out.println("Press 2 to drop a student. ");
			System.out.println("Press 3 to view a text representation of the course. ");
			System.out.println("Press 4 close the program. ");

			inputNumber = Integer.parseInt(scan.nextLine());

		} while (inputNumber < 1 || inputNumber > 4);
		inputChoice(inputNumber);
	}

	public void inputChoice(int userInput) {

		switch (userInput) {
		case 1:
			studentInfoToAdd();
			break;
		case 2:
			studentInfoToDrop();
			break;
		case 3:
			System.out.println(interactiveCourse);
			break;
		default:// choice 4, do nothing
			break;
		}

	}

	public void studentInfoToAdd() {

		String possibleAddedStudentName, possibleAddedStudentID, paidTuition;
		boolean studentAdded = false, tuitionPaid = false;

		System.out.println("What is the name of the student? ");
		Scanner scan = new Scanner(System.in);
		possibleAddedStudentName = scan.nextLine();
		System.out.println("What is the student's ID number? ");
		possibleAddedStudentID = scan.nextLine();
		System.out.println("Is this student's tuition paid? (y/n) ");
		paidTuition = scan.nextLine();
		if (paidTuition.equalsIgnoreCase("y")) {
			tuitionPaid = true;
		}

		Student studentInfo = new Student(possibleAddedStudentName, possibleAddedStudentID, tuitionPaid);

		studentAdded = interactiveCourse.addStudent(studentInfo);

		if (studentAdded) {
			System.out.println("Student successfully added! ");
		} else {// did not add student
			System.out.println("The student could not be added! ");
		}

	}

	public void studentInfoToDrop() {
		String possibleDroppedStudentID;
		boolean successfulDropped = false;
		System.out.println("What is the ID of the student to be dropped? ");
		Scanner scan = new Scanner(System.in);
		possibleDroppedStudentID = scan.nextLine();

		Student[] studentRoster = interactiveCourse.getRoster();
		Student studentCheck = new Student(null, possibleDroppedStudentID, true);
		
		//for (Student studentCheck : studentRoster) {
		for (int i = 0; i < studentRoster.length && successfulDropped==false; i++) {
			if (studentRoster[i].getID().equals(possibleDroppedStudentID)) {
				interactiveCourse.dropStudent(studentRoster[i]);
				successfulDropped = true;
				System.out.println("Successfully dropped! ");
				//return;
			}
			
		}

	}

}

/*
 * Write an interactive driver program that gets information from the user to
 * create Students and add/drop them to a Course.
 * 
 * Create a Course object (you can decide the name and roster/waitlist sizes).
 * Note: this part does not need to be interactive. You can hard-code the Course
 * information into your program. Use a loop to interactively allow the user:
 * 
 * Add a student by entering the student's name, id, and paid tuition status.
 * 
 * Drop a student by entering the student's information (you can decide what
 * 
 * information is needed) View a text representation of the course Display the
 * result (success/failure) of each add/drop.
 * 
 */
