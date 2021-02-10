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

// TODO: BEN: Add javadoc for all methods?

public class Course {

	public final int DEFAULT_MAX_STUDENTS_IN_COURSE = 30;
	public final int DEFAULT_MAX_STUDENTS_IN_WAITLIST = 15;

	Student[] studentRoster = new Student[DEFAULT_MAX_STUDENTS_IN_COURSE];
	Student[] studentWaitList = new Student[DEFAULT_MAX_STUDENTS_IN_WAITLIST];

	private String nameOfCourse;

	// BEN: numStudentsAllowed ambiguous variable name. maxCourseStudents better
	private int numStudentsAllowed = DEFAULT_MAX_STUDENTS_IN_COURSE;
	private int maxStudentsOnWaitList = DEFAULT_MAX_STUDENTS_IN_WAITLIST;

	private int numStudentsOnRoster = 0;
	private int numStudentsOnWaitList = 0;

	public Course (String passedCourseName) {
		nameOfCourse = passedCourseName;
	}

	public Course (String passedCourseName, int passedMaxRegistered, int passedMaxWaitList) {

		studentRoster = new Student[passedMaxRegistered];
		studentWaitList = new Student[passedMaxWaitList];

		nameOfCourse = passedCourseName;
		numStudentsAllowed = passedMaxRegistered;
		maxStudentsOnWaitList = passedMaxWaitList;
	}
	
	public String getCourseName() {
		return nameOfCourse;
	}

	// BEN: Perhaps change error message or even not output as same name is OK
	public void setCourseName (String newCourseName) { //possibility of legal name change

		if (nameOfCourse.equals(newCourseName)) {
			System.out.println("This name is the same name! You're not changing anything.");
		}
		else {
			nameOfCourse = newCourseName;
		}
	}
	
	public int getMaxRegistration() {
		return numStudentsAllowed;
	}
	/* 
	Cannot set max registration since this is determined by the school
	*/
	
	public int getMaxWaitlist() {
		return maxStudentsOnWaitList;
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

	// CHRIS: Make mockup of to-string
	// BEN: String.format() would be good use here. %s to insert string
	public String toString() {
		return "" + "" + "" + "";// look at assignment on what is needed
	}

	// CHRIS: Finish method below
	// Returns true if student on roster or waitlist, otherwise false
	public boolean isAlreadyRegistered (Student student) {
		return isOnRoster(student) || isOnWaitList(student);
	} 

	// Returns index if student on roster, otherwise returns -1
	public boolean isOnRoster (Student student) {
		return (indexOfRosterStudent(student) != -1);
	}

	// Returns true if student on waitlist, otherwise false
	public boolean isOnWaitList (Student student) {
		return (indexOfWaitListStudent(student) != -1);
	}

	// Returns index of student if on roster, otherwise returns -1
	public int indexOfRosterStudent (Student student) {

		for (int i = 0; i < numStudentsOnRoster; i++) {

			if (student == studentRoster[i]) {
				return i;
			}
		}

		return -1;
	}

	// Returns index of student if on waitlist, otherwise returns -1
	public int indexOfWaitListStudent (Student student) {

		for (int i = 0; i < numStudentsOnWaitList; i++) {

			if (student == studentWaitList[i]) {
				return i;
			}
		}

		return -1;
	}

	// Returns true if added student, otherwise false
	// Fills roster, then waitlist
	public boolean addStudent (Student newStudent) {

		boolean addedStudent = false;

		if (!newStudent.isTuitionPaid()) return false;
		if (isAlreadyRegistered(newStudent)) return false;

		if (numStudentsOnRoster < numStudentsAllowed) {

			studentRoster[numStudentsOnRoster] = newStudent;
			numStudentsOnRoster += 1;
			addedStudent = true;
		}
		else if (numStudentsOnWaitList < maxStudentsOnWaitList) {

			studentRoster[numStudentsOnWaitList] = newStudent;
			numStudentsOnWaitList += 1;
			addedStudent = true;
		}
		return addedStudent;
	}

	// TODO: BEN: Look at why Chevy Chase and Jack Johnson retrn false
	// Returns true if dropped student, otherwise false
	public boolean dropStudent (Student student) {

		boolean droppedStudent = false;

		int indexOfRosterStudent = indexOfRosterStudent(student);
		int indexOfWaitListStudent = indexOfWaitListStudent(student);

		// If student found, returns index. Otherwise, returns -1
		if (indexOfRosterStudent != -1) {

			deleteStudentFromRoster(indexOfRosterStudent);
			droppedStudent = true;
		}
		else if (indexOfWaitListStudent != -1) {

			deleteStudentFromWaitList(indexOfWaitListStudent);
			droppedStudent = true;
		}

		return droppedStudent;
	}

	public void deleteStudentFromRoster (int index) {

		// Shift all students down by 1
		for (int i = index; i < numStudentsOnRoster - 1; i++) {

			studentRoster[i] = studentRoster[i + 1];
		}

		// Reduce number of students by 1
		numStudentsOnRoster -= 1;
		addStudentFromWaitList();
	}

	public void deleteStudentFromWaitList (int index) {

		// Shift all students down by 1
		for (int i = index; i < numStudentsOnWaitList - 1; i++) {

			studentWaitList[i] = studentWaitList[i + 1];
		}

		// Reduce number of students by 1
		numStudentsOnWaitList -= 1;
	}

	// Adds student to roster, delete from waitlist
	// Returns true if added from waitlist, false if empty list
	public boolean addStudentFromWaitList() {

		boolean addedFromWaitList = false;

		if (numStudentsOnWaitList > 0) {

			studentRoster[numStudentsOnRoster] = studentWaitList[numStudentsOnWaitList - 1]; // Arrays begin at index 0
			numStudentsOnRoster += 1;
			numStudentsOnWaitList -= 1;

			addedFromWaitList = true;
		}

		return addedFromWaitList;
	}

	// Returns student object of student ID.
	// Errors if invalid studentID inputted
	public Student getStudent (String studentID) {

		Student student = new Student ("", "", false);
		return student;
	}
}