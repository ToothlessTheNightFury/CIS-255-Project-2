import java.util.*;

/*
Features
 - Students on the roster can be stored in any order. (ArrayList)

 - Students on the waitlist should be stored in the order they were added. (Built-in ArrayList)

 - A student can only be added to the course if they have paid their tuition. (Student class)

 - A student cannot be on the roster or waitlist more than once. (isAlreadyRegistered(), for loop)

 - A student cannot be on both the roster and the waitlist. (isOnRoster(), isOnWaitlist())

 - If a student on the roster drops the course, the first student from the waitlist should be added to the roster (and removed from the waitlist). (dropStudent(studentID), addStudent(String studentID), addStudentFromWaitlist())

 - addStudent (String studentID) // Fill roster, then fill waitlist if roster is full

 - When searching for whether a student is on the roster or waitlist, compare the Student ids to see if a student is a match. isRegistered(String studentID)

 - getStudent(String studentID)

*/

public class Course {

	public final int DEFAULT_MAX_STUDENTS_IN_COURSE = 30;
	public final int DEFAULT_MAX_STUDENTS_IN_WAITLIST = 5;

	ArrayList<String> studentRoster;
	ArrayList<String> studentWaitList;

	private String nameOfCourse;
	private int maxStudentsAllowed;
	private int maxStudentsOnWaitlist;
	
	// Benson: Fix constructors
	public Course (String passedCourseName) {

		this(passedCourseName, DEFAULT_MAX_STUDENTS_IN_COURSE, DEFAULT_MAX_STUDENTS_IN_WAITLIST);
	}

	public Course (String passedCourseName, int passedMaxRegistered, int passedMaxWaitlist) {

		studentRoster = new ArrayList<String>;

		nameOfCourse = "";
		maxStudentsAllowed = passedMaxRegistered;
		maxStudentsOnWaitlist = passedMaxWaitlist;
	}
	
	public String getCourseName() {
		return nameOfCourse;
	}

	public void setCourseName(String newCourseName) { //possibility of legal name change
		if (nameOfCourse == newCourseName) {
			System.out.println("this name is the same name! You're not changing anything. ");
		} else {
			nameOfCourse = newCourseName;
		}
	}
	
	
	public int getMaxRegistration() {
		return maxStudentsAllowed;
	}
	/* 
	Cannot set max registration since this is determined by the school
	*/
	
	public int getMaxWaitlist() {
		return maxStudentsOnWaitlist;
	}
	/* 
	Cannot set max wait list number since this is determined by the school
	*/

	/*
		Outputs:
		Name of Course
		# of Students Enrolled / Max Num that can be enrolled
		Roster of enrolled students
		# of students of waitlist / Max num on waitlist
		Students on waitlist

		Do not print nulls
	*/

	// Chris: Make mockup of to-string
	public String toString() {
		return "" + "" + "" + "";// look at assignment on what is needed
	}

	// Chris: Finish method below
	// Returns true if student on roster or waitlist, otherwise false
	public boolean isAlreadyRegistered (String studentID) {

		return isOnRoster(studentID) || isOnWaitlist(studentID);
	} 

	// Returns true if student on roster, otherwise false
	public boolean isOnRoster (String studentID) {

		return false;
	}

	// Returns true if student on waitlist, otherwise false
	public boolean isOnWaitlist (String studentID) {

		return true;
	}

	// Benson: Take a look at this
	// Returns true if dropped student, otherwise false
	public boolean dropStudent (String studentID) {

		return false;
	}

	// Chris: Take a look at this
	// Returns true if added student, otherwise false
	// Fills roster, then waitlist 
	public boolean addStudent (String studentID) {

		return false;
	}

	// Adds student to roster, delete from waitlist
	// Returns true if added from waitlist, false if empty list
	public boolean addStudentFromWaitlist() {
		return false;
	}

	// Returns student object of student ID.
	// Errors if invalid studentID inputted
	public Student getStudent (String studentID) {

		Student student = new Student ("", "", false);
		return student;
	}
}