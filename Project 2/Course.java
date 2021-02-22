import java.util.*;

// TODO: BEN: Add javadoc for all methods?
// TODO: BEN: Make internal functions private

public class Course {

	public final int DEFAULT_MAX_STUDENTS_IN_COURSE = 30;
	public final int DEFAULT_MAX_STUDENTS_IN_WAITLIST = 15;

	private Student[] studentRoster = new Student[DEFAULT_MAX_STUDENTS_IN_COURSE];
	private Student[] studentWaitList = new Student[DEFAULT_MAX_STUDENTS_IN_WAITLIST];

	private String courseName;

	private int maxCourseStudents = DEFAULT_MAX_STUDENTS_IN_COURSE;
	private int maxWaitListStudents = DEFAULT_MAX_STUDENTS_IN_WAITLIST;

	private int numStudentsOnRoster = 0;
	private int numStudentsOnWaitList = 0;

	public Course (String courseName) {
		this.courseName = courseName;
	}

	public Course (String courseName, int maxCourseStudents, int maxWaitListStudents) {

		studentRoster = new Student[maxCourseStudents];
		studentWaitList = new Student[maxWaitListStudents];

		this.courseName = courseName;
		this.maxCourseStudents = maxCourseStudents;
		this.maxWaitListStudents = maxWaitListStudents;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName (String courseName) {
		this.courseName = courseName;
	}

	public int getMaxRegistration() {
		return maxCourseStudents;
	}

	/*
	 * Cannot set max registration since this is determined by the school
	 */

	public int getMaxWaitList() {
		return maxWaitListStudents;
	}

	/*
	 * Cannot set max wait list number since this is determined by the school
	 */

	public int getNumStudentsOnRoster() {
		return numStudentsOnRoster;
	}

	public int getNumStudentsOnWaitList() {
		return numStudentsOnWaitList;
	}

	public Student getStudentRoster (int index) {
		return studentRoster[index];
	}

	public Student getStudentWaitList (int index) {
		return studentWaitList[index];
	}

	public String toString() {

		String str = String.format("[%s]\n====================\nROSTER (%d/%d)\n%s\nWAIT LIST (%d/%d)\n%s",
				courseName.toUpperCase(), numStudentsOnRoster, maxCourseStudents, studentRosterToString(), numStudentsOnWaitList, maxWaitListStudents, waitListToString());

		return str;
	}

	public String studentRosterToString() {

		String str = "";

		for (int i = 0; i < numStudentsOnRoster; i++) {
			str += String.format("%s (%s)\n", studentRoster[i].getName(), studentRoster[i].getID());
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

	
	public Student[] getRoster() {
		return studentRoster;
	}
	
	// Returns true if student on roster or waitlist, otherwise false
	public boolean isAlreadyRegistered (Student student) {

		if (isOnRoster(student) || isOnWaitList(student)) {
			return true;
		}

		return false;
	}

	// returns true if student is on roster, otherwise returns false
	public boolean isOnRoster (Student student) {

		for (int i = 0; i < numStudentsOnRoster; i++) {

			if (student.equals(studentRoster[i])) {
				return true;
			}
		}

		return false;
	}

	// Returns true if student on waitlist, otherwise false
	public boolean isOnWaitList (Student student) {

		for (int i = 0; i < numStudentsOnWaitList; i++) {

			if (student.equals(studentWaitList[i])) {
				return true;
			}
		}

		return false;
	}

	// Returns index of student if on roster, otherwise returns -1
	private int indexOfRosterStudent (Student student) {

		for (int i = 0; i < numStudentsOnRoster; i++) {

			if (student.equals(studentRoster[i])) {
				return i;
			}
		}

		return -1;
	}

	// Returns index of student if on waitlist, otherwise returns -1
	private int indexOfWaitListStudent (Student student) {

		for (int i = 0; i < numStudentsOnWaitList; i++) {

			if (student.equals(studentWaitList[i])) {
				return i;
			}
		}

		return -1;
	}

	// Returns true if added student, otherwise false
	// Fills roster, then waitlist
	public boolean addStudent (Student student) {

		boolean addedStudent = false;

		if (!student.isTuitionPaid() || isAlreadyRegistered(student)) {
			return false;
		}

		if (numStudentsOnRoster < maxCourseStudents) {

			studentRoster[numStudentsOnRoster] = student;
			numStudentsOnRoster += 1;
			addedStudent = true;
		}
		else if (numStudentsOnWaitList < maxWaitListStudents) {

			studentWaitList[numStudentsOnWaitList] = student;
			numStudentsOnWaitList += 1;
			addedStudent = true;
		} // else no room on studentRoster && studentWaitList
		return addedStudent;
	}

	// Returns true if dropped student, otherwise false
	public boolean dropStudent (Student student) {

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
		
		return droppedStudent;
	}

	private void deleteStudentFromRoster (int index) {

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

	private void deleteStudentFromWaitList (int index) {

		// Reduce number of students by 1
		if (numStudentsOnWaitList != 0) {
			numStudentsOnWaitList -= 1;
		}

		// Shift all students down by 1
		for (int i = index; i < numStudentsOnWaitList; i++) {
			studentWaitList[i] = studentWaitList[i + 1];
		}

		studentWaitList[numStudentsOnWaitList] = null;
	}

	// Adds student to roster, delete from waitlist
	// Returns true if added from waitlist, false if empty list
	private boolean addStudentFromWaitList() {

		boolean addedFromWaitList = false;

		if (numStudentsOnWaitList > 0) {
			// name on position 4 is now last student on waitlist. Changing to index at 0
			studentRoster[numStudentsOnRoster] = studentWaitList[0]; // Arrays begin at index 0
			numStudentsOnRoster += 1;

			deleteStudentFromWaitList(0);
			addedFromWaitList = true;
		}

		return addedFromWaitList;
	}

	public Student searchStudent (String studentID) {

		Student student = new Student("", studentID, true);

		for (int i = 0; i < numStudentsOnRoster; i++) {

			if (student.equals(studentRoster[i])) {
				student = studentRoster[i];
			}
		}

		for (int i = 0; i < numStudentsOnWaitList; i++) {

			if (student.equals(studentWaitList[i])) {
				student = studentWaitList[i];
			}
		}

		return student;
	}
}