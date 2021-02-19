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

	private Student[] studentRoster = new Student[DEFAULT_MAX_STUDENTS_IN_COURSE];
	private Student[] studentWaitList = new Student[DEFAULT_MAX_STUDENTS_IN_WAITLIST];

	private String nameOfCourse;

	private int maxCourseStudents = DEFAULT_MAX_STUDENTS_IN_COURSE;
	private int maxStudentsOnWaitList = DEFAULT_MAX_STUDENTS_IN_WAITLIST;

	private int numStudentsOnRoster = 0;
	private int numStudentsOnWaitList = 0;

	public Course(String passedCourseName) {
		nameOfCourse = passedCourseName; // should we set studentRoster and studentWaitList to default number?
	}

	public Course(String passedCourseName, int passedMaxRegistered, int passedMaxWaitList) {

		studentRoster = new Student[passedMaxRegistered];
		studentWaitList = new Student[passedMaxWaitList];

		nameOfCourse = passedCourseName;
		maxCourseStudents = passedMaxRegistered;
		maxStudentsOnWaitList = passedMaxWaitList;
	}

	public String getCourseName() {
		return nameOfCourse;
	}

	public void setCourseName(String courseName) {
		this.nameOfCourse = courseName;
	}

	public int getMaxRegistration() {
		return maxCourseStudents;
	}
	/*
	 * Cannot set max registration since this is determined by the school
	 */

	public int getMaxWaitList() {
		return maxStudentsOnWaitList;
	}
	/*
	 * Cannot set max wait list number since this is determined by the school
	 */

	public String toString() {

		String answer = String.format("%s has: \nRoster: %d/%d\n%s\nWaitlist has: %d/%d\n%s", nameOfCourse,
				numStudentsOnRoster, maxCourseStudents, Arrays.deepToString(studentRoster), numStudentsOnWaitList,
				getMaxWaitList(), Arrays.deepToString(studentWaitList));
		return answer;
	}

	// Returns true if student on roster or waitlist, otherwise false
	public boolean isAlreadyRegistered(Student student) {
		if (isOnRoster(student) || isOnWaitList(student)) {
			return true;
		}
		return false;
	}

	// returns true if student is on roster, otherwise returns false
	public boolean isOnRoster(Student student) {
		for (int i = 0; i < studentRoster.length; i++) {
			if (student == studentRoster[i]) {
				return true;
			}
		}
		return false;
	}
	
	public Student[] getRoster() {
		return studentRoster;
	}
	
	public Student[] getWaitList() {
		return studentWaitList;
	}
	
	// Returns true if student on waitlist, otherwise false
	public boolean isOnWaitList(Student student) {
		for (int i = 0; i < studentWaitList.length; i++) {

			if (student == studentWaitList[i]) {
				return true;
			}
		}
		return false;
	}

	// Returns index of student if on roster, otherwise returns -1
	public int indexOfRosterStudent(Student student) {

		for (int i = 0; i < numStudentsOnRoster; i++) {

			if (student == studentRoster[i]) {
				return i;
			}
		}

		return -1;
	}

	// Returns index of student if on waitlist, otherwise returns -1
	public int indexOfWaitListStudent(Student student) {

		for (int i = 0; i < numStudentsOnWaitList; i++) {

			if (student == studentWaitList[i]) {
				return i;
			}
		}

		return -1;
	}

	// Returns true if added student, otherwise false
	// Fills roster, then waitlist
	public boolean addStudent(Student student) {

		boolean addedStudent = false;

		if (!student.isTuitionPaid() || isAlreadyRegistered(student)) {
			return false;
		}

		if (numStudentsOnRoster < maxCourseStudents) {

			studentRoster[numStudentsOnRoster] = student;
			numStudentsOnRoster += 1;
			addedStudent = true;
		} else if (numStudentsOnWaitList < maxStudentsOnWaitList) {

			studentWaitList[numStudentsOnWaitList] = student;
			numStudentsOnWaitList += 1;
			addedStudent = true;
		} // else no room on studentRoster && studentWaitList
		return addedStudent;
	}

	// Returns true if dropped student, otherwise false
	public boolean dropStudent(Student student) {

		if (!isAlreadyRegistered(student)) {
			return false;
		} else if (indexOfWaitListStudent(student) == -1) { // must be on roster (indexOfRosterStudent(student) == -1) || 
			deleteStudentFromRoster(indexOfRosterStudent(student));
			return true;
		} else { //(isOnWaitList(student))
			deleteStudentFromWaitList(indexOfWaitListStudent(student));
			return true;
		} 
			/*
			 * if(studentWaitList[0] != null) { // this only works because order doesn't
			 * matter what order. studentRoster[namePosition] = studentWaitList[0];
			 * deleteStudentFromWaitList(0); }
			 * 
			 */
		

	}

	public void deleteStudentFromRoster(int index) {

		studentRoster[index] = null;

		// Shift all students down by 1
		for (int i = index; i < numStudentsOnRoster - 1; i++) {

			studentRoster[i] = studentRoster[i + 1];
		}

		// Reduce number of students by 1
		numStudentsOnRoster -= 1;
		studentRoster[numStudentsOnRoster] = null;
		addStudentFromWaitList();

	}

	public void deleteStudentFromWaitList(int index) {

		// Shift all students down by 1
		for (int i = index; i < numStudentsOnWaitList - 1; i++) {

			studentWaitList[i] = studentWaitList[i + 1];

		}

		// Reduce number of students by 1
		if (numStudentsOnWaitList != 0) {
			numStudentsOnWaitList -= 1;
		}

		studentWaitList[numStudentsOnWaitList] = null;
	}

	// Adds student to roster, delete from waitlist
	// Returns true if added from waitlist, false if empty list
	public boolean addStudentFromWaitList() {

		boolean addedFromWaitList = false;

		if (numStudentsOnWaitList > 0) {
			// name on position 4 is now last student on waitlist. Changing to index at 0
			studentRoster[numStudentsOnRoster] = studentWaitList[0]; // Arrays begin at index 0
			numStudentsOnRoster += 1;
			numStudentsOnWaitList -= 1;

			// need to move over the WaitList of students
			for (int i=0; i<numStudentsOnWaitList; i++) {
				studentWaitList[i] = studentWaitList[i+1];
			}
			studentWaitList[numStudentsOnWaitList] = null;
			addedFromWaitList = true;
		}

		return addedFromWaitList;
	}

	// Returns student object of student ID.
	// Errors if invalid studentID inputted
	public Student getStudent(String studentID) {

		Student student = new Student("", "", false);
		return student;
	}

}