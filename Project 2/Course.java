import java.util.*;

// TODO: BEN: Add javadoc for all methods?

public class Course {

	public final int DEFAULT_MAX_STUDENTS_IN_COURSE = 30;
	public final int DEFAULT_MAX_STUDENTS_IN_WAITLIST = 15;

	Student[] studentRoster = new Student[DEFAULT_MAX_STUDENTS_IN_COURSE];
	Student[] studentWaitList = new Student[DEFAULT_MAX_STUDENTS_IN_WAITLIST];

	// BEN: Maybe courseName instead?
	private String nameOfCourse;

	private int maxCourseStudents = DEFAULT_MAX_STUDENTS_IN_COURSE;
	private int maxStudentsOnWaitList = DEFAULT_MAX_STUDENTS_IN_WAITLIST;

	private int numStudentsOnRoster = 0;
	private int numStudentsOnWaitList = 0;

	public Course(String passedCourseName) {
		nameOfCourse = passedCourseName; // should we set studentRoster and studentWaitList to default number?
	}

	public Course(String nameOfCourse, int maxCourseStudents, int maxStudentsOnWaitList) {

		studentRoster = new Student[maxCourseStudents];
		studentWaitList = new Student[maxStudentsOnWaitList];

		this.nameOfCourse = nameOfCourse;
		this.maxCourseStudents = maxCourseStudents;
		this.maxStudentsOnWaitList = maxStudentsOnWaitList;
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

	/*
	 * Outputs: Name of Course # of Students Enrolled / Max Num that can be enrolled
	 * Roster of enrolled students # of students of waitlist / Max num on waitlist
	 * Students on waitlist
	 * 
	 * Do not print nulls
	 */

	public String toString() {

		/*
		String str = String.format("%s has: \nRoster: %d/%d\n%s\nWaitlist has: %d/%d\n%s", nameOfCourse,
				numStudentsOnRoster, maxCourseStudents, Arrays.deepToString(studentRoster), numStudentsOnWaitList,
				getMaxWaitList(), Arrays.deepToString(studentWaitList));
		 */

		// BEN: Option 2 for toString()
		String str = String.format("[%s]\n====================\nROSTER (%d/%d)\n%s\nWAIT LIST (%d/%d)\n%s",
				nameOfCourse.toUpperCase(), numStudentsOnRoster, maxCourseStudents, studentRosterToString(), numStudentsOnWaitList, maxStudentsOnWaitList, waitListToString());

		return str;
	}

	public String studentRosterToString() {

		String str = "";

		for (int i = 0; i < numStudentsOnRoster; i++) {
			str += String.format("%s\n", studentRoster[i].getName());
		}

		if (str.isEmpty()) {
			str = "None\n";
		}

		return str;
	}

	public String waitListToString() {

		String str = "";

		for (int i = 0; i < numStudentsOnWaitList; i++) {
			str += String.format("%s\n", studentWaitList[i].getName());
		}

		if (str.isEmpty()) {
			str = "None\n";
		}

		return str;
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
		}
		else if (numStudentsOnWaitList < maxStudentsOnWaitList) {

			studentWaitList[numStudentsOnWaitList] = student;
			numStudentsOnWaitList += 1;
			addedStudent = true;
		} // else no room on studentRoster && studentWaitList
		return addedStudent;
	}

	// Returns true if dropped student, otherwise false
	public boolean dropStudent(Student student) {

		boolean droppedStudent = false;

		int indexOfRosterStudent = indexOfRosterStudent(student);
		int indexOfWaitListStudent = indexOfWaitListStudent(student);

		// If found student on roster
		if (indexOfRosterStudent != -1) {
			deleteStudentFromRoster(indexOfRosterStudent);
			droppedStudent = true;
		}

		// If found student on wait list
		else if (indexOfWaitListStudent != -1) {
			deleteStudentFromWaitList(indexOfWaitListStudent);
			droppedStudent = true;
		}

		/*
		if (!isAlreadyRegistered(student)) {
			return false;
		} else if (indexOfWaitListStudent(student) == -1) { // must be on roster (indexOfRosterStudent(student) == -1) || 
			deleteStudentFromRoster(indexOfRosterStudent(student));
			return true;
		} else { //(isOnWaitList(student))
			deleteStudentFromWaitList(indexOfWaitListStudent(student));
			return true;
		}
		 */
			/*
			 * if(studentWaitList[0] != null) { // this only works because order doesn't
			 * matter what order. studentRoster[namePosition] = studentWaitList[0];
			 * deleteStudentFromWaitList(0); }
			 * 
			 */
		
		return droppedStudent;
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
		// -1 is what messes up addStudentFromWaitlist() call
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

			// See if can replace with deleteStudentFromWaitlist(0)
			// need to move over the WaitList of students
			for (int i = 0; i < numStudentsOnWaitList; i++) {
				studentWaitList[i] = studentWaitList[i+1];
			}

			studentWaitList[numStudentsOnWaitList] = null;
			addedFromWaitList = true;
		}

		return addedFromWaitList;
	}
}