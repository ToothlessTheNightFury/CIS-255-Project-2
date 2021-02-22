import java.util.*;

public class CourseAL {

	private ArrayList<Student> studentRoster = new ArrayList<Student>();
	private ArrayList<Student> studentWaitList = new ArrayList<Student>();

	private String courseName;
	private int maxCourseStudents, maxWaitListStudents;
	

	public CourseAL(String courseName) {
		this.courseName = courseName;
	}

	public CourseAL(String courseName, int maxCourseStudents, int maxWaitListStudents) {

		studentRoster = new ArrayList<Student>();
		studentWaitList = new ArrayList<Student>();

		this.courseName = courseName;
		this.maxCourseStudents = maxCourseStudents;
		this.maxWaitListStudents = maxWaitListStudents;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
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
		return studentRoster.size();
	}

	public int getNumStudentsOnWaitList() {
		return studentWaitList.size();
	}

	public Student getStudentRoster(int index) {
		return studentRoster.get(index);
	}

	public String toString() {

		String str = String.format("[%s]\n====================\nROSTER (%d/%d)\n%s\nWAIT LIST (%d/%d)\n%s",
				courseName.toUpperCase(), studentRoster.size(), maxCourseStudents, studentRosterToString(),
				studentWaitList.size(), maxWaitListStudents, waitListToString());

		return str;
	}

	public String studentRosterToString() {

		String str = "";

		for (int i = 0; i < studentRoster.size(); i++) {
			str += String.format("%s\n", studentRoster.get(i));
		}

		if (str.isEmpty()) {
			str = "None\n";
		}

		return str;
	}

	public String waitListToString() {

		String str = "";

		for (int i = 0; i < studentWaitList.size(); i++) {
			str += String.format("%s\n", studentWaitList.get(i));
		}

		if (str.isEmpty()) {
			str = "None\n";
		}

		return str;
	}

	// Returns true if student on roster or waitlist, otherwise false
	public boolean isAlreadyRegistered(Student student) {

		if ((studentRoster.contains(student) || studentWaitList.contains(student))){
			return true;
		}

		return false;
	}


	// Returns index of student if on roster, otherwise returns -1
	private int indexOfRosterStudent(Student student) {
		return studentRoster.indexOf(student);
	}

	// Returns index of student if on waitlist, otherwise returns -1
	private int indexOfWaitListStudent(Student student) {
		return studentWaitList.indexOf(student);
	}

	// Returns true if added student, otherwise false
	// Fills roster, then waitlist
	public boolean addStudent(Student student) {

		boolean addedStudent = false;

		if (!student.isTuitionPaid() || isAlreadyRegistered(student)) {
			return false;
		}

		if (studentRoster.size() < maxCourseStudents) {

			studentRoster.add(student);
			addedStudent = true;
		} else if (studentWaitList.size() < maxWaitListStudents) {
			studentWaitList.add(student);
			addedStudent = true;
		} // else no room on studentRoster && studentWaitList
		return addedStudent;
	}

	// Returns true if dropped student, otherwise false
	public boolean dropStudent(Student student) {

		boolean droppedStudent = false;
		
		int indexOfRosterStudent = studentRoster.indexOf(student);
		int indexOfWaitListStudent = studentWaitList.indexOf(student);
		
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

	private void deleteStudentFromRoster(int index) {

		studentRoster.remove(index);
		addStudentFromWaitList();
		

	}

	private void deleteStudentFromWaitList(int index) {
		studentWaitList.remove(index);
	
	}

	// Adds student to roster, delete from waitlist
	// Returns true if added from waitlist, false if empty list
	private boolean addStudentFromWaitList() {

		boolean addedFromWaitList = false;

		if (studentWaitList.size() > 0) {
			// name on position 4 is now last student on waitlist. Changing to index at 0
			studentRoster.add(studentRoster.size()-1, studentWaitList.get(0));
			studentWaitList.remove(0);
			// AUTO delete in arrayList but does it get shifted over automatically?
			addedFromWaitList = true;
		}

		return addedFromWaitList;
	}

	public Student searchStudent(String studentID) {

		Student student = new Student("", studentID, true);

		for (int i = 0; i < studentRoster.size(); i++) {

			if (student.equals(studentRoster.get(i))) {
				student = studentRoster.get(i);
			}
		}

		for (int i = 0; i < studentWaitList.size(); i++) {

			if (student.equals(studentWaitList.get(i))) {
				student = studentWaitList.get(i);
			}
		}

		return student;
	}

	/*
	 * This class has the same methods as Course (listed above). Instead of using a
	 * Student[] to store the roster and waitlist, use an ArrayList<Student>. You
	 * can use the provided tester program to test your extra credit class (comment
	 * out line 24 and un-comment line 29).
	 * 
	 * Important: The equals method in Student compares two Student objects to see
	 * if they are the same (which, for our project, means that they have the same
	 * ID). This means you should not directly compare the IDs. In the extra credit,
	 * you should just compare two Student objects directly. (Remember what method
	 * you use to compare objects!)
	 * 
	 * For full credit, take advantage of the methods in the ArrayList class to
	 * implement the methods. Do not just swap out the array for an ArrayList- use
	 * the methods from the ArrayList class! You might be able to significantly
	 * streamline your code. Note that you will not earn full extra credit if you
	 * simply use an ArrayList to mimic an array.
	 */

}
