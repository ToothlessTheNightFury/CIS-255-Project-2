
public class CourseTester {

	public static void main(String[] args) {

		// array created only for the purposes of this tester file
		Student[] studentsInSchool = new Student[15];
		studentsInSchool[0] = new Student("Adam Ant", "S925", true);
		studentsInSchool[1] = new Student("Bob Barker", "S713", false);
		studentsInSchool[2] = new Student("Chevy Chase", "S512", true);
		studentsInSchool[3] = new Student("Doris Day", "S513", true);
		studentsInSchool[4] = new Student("Emilio Estevez", "S516", true);
		studentsInSchool[5] = new Student("Farrah Fawcet", "S956", true);
		studentsInSchool[6] = new Student("Greta Garbo", "S419", true);
		studentsInSchool[7] = new Student("Helen Hunt", "S281", true);
		studentsInSchool[8] = new Student("Jack Johnson", "S790", true);
		studentsInSchool[9] = new Student("Kim Kardashian", "S336", true);
		studentsInSchool[10] = new Student("Martina McBride", "S156", true);
		studentsInSchool[11] = new Student("Renne Russo", "S219", true);
		studentsInSchool[12] = new Student("Susan Serandon", "S472", true);
		studentsInSchool[13] = new Student("Vince Vaughn", "S892", true);
		studentsInSchool[14] = new Student("Walt Whitman", "S901", true);

		Course course = new Course("Media Studies", 5, 5);

		/*
		 * To test the extra credit, comment out the line above and un-comment this
		 * line. The rest of the program should run exactly the same.
		 */
		//CourseAL course = new CourseAL("Media Studies", 5, 5);

		System.out.println(course + "\n");
		System.out.println("***********************TESTING ADD METHOD*****************");
		System.out.println("*****Students BB, RR, SS, VV, and WW should NOT be added. All others should be added successfully.");
		for (Student student : studentsInSchool) {
			boolean added = course.addStudent(student);
			System.out.println(student + (added ? " added successfully" : " not added"));
		}
		System.out.println("\n*****Course should now contain AA, CC, DD, EE, and FF on the course roster (in any order) and GG, HH JJ, KK, and MM on the waitlist (in that exact order).");
		System.out.println("\n" + course + "\n");

		// try to add students already added
		Student studentToAdd = studentsInSchool[2]; // CC
		boolean added = course.addStudent(studentToAdd);
		System.out.println("*****For " + studentToAdd.getName()
				+ ", add method should return false (student is already enrolled on roster): \n" + added);
		studentToAdd = studentsInSchool[7]; // HH
		added = course.addStudent(studentToAdd);
		System.out.println("*****For " + studentToAdd.getName()
				+ ", add method should return false (student is already enrolled on waitlist): \n" + added);

		System.out.println("\n\n***********************TESTING DROP METHOD*****************");
		// drop student from roster
		Student studentToDrop = studentsInSchool[2]; // CC
		boolean dropped = course.dropStudent(studentToDrop);
		System.out.println("*****For " + studentToDrop.getName()
				+ ", drop method should return true (student is enrolled on the roster): \n" + dropped);
		System.out.println("\n*****Course should now contain AA, DD, EE, FF, and GG on the course roster (in any order) and HH, JJ, KK, and MM on the waitlist (in that exact order).");
		System.out.println("\n" + course + "\n");

		// drop student not on roster or waitlist
		studentToDrop = studentsInSchool[14]; // WW
		dropped = course.dropStudent(studentToDrop);
		System.out.println("*****For " + studentToDrop.getName()
				+ ", drop method should return false (student is not enrolled): \n" + dropped);
		System.out.println("\n*****Course should still contain AA, DD, EE, FF, and GG on the course roster (in any order) and HH, JJ, KK, and MM on the waitlist (in that exact order).");
		System.out.println("\n" + course + "\n");

		// drop student on waitlist
		studentToDrop = studentsInSchool[8]; // JJ
		dropped = course.dropStudent(studentToDrop);
		System.out.println("*****For " + studentToDrop.getName()
				+ ", drop method should return true (student is on the waitlist): \n" + dropped);
		System.out.println("\n*****Course should now contain AA, DD, EE, FF, and GG on the course roster (in any order) and HH, KK, and MM on the waitlist (in that exact order).");
		System.out.println("\n" + course + "\n");

		// drop student on roster
		studentToDrop = studentsInSchool[0]; // AA
		dropped = course.dropStudent(studentToDrop);
		System.out.println("*****For " + studentToDrop.getName()
				+ ", drop method should return true (student is enrolled on the roster): \n" + dropped);
		System.out.println("\n*****Course should now contain DD, EE, FF, GG, and HH on the course roster (in any order) and KK and MM on the waitlist (in that exact order).");
		System.out.println("\n" + course + "\n");

		// drop student on roster
		studentToDrop = studentsInSchool[3]; // DD
		dropped = course.dropStudent(studentToDrop);
		System.out.println("*****For " + studentToDrop.getName()
				+ ", drop method should return true (student is enrolled on the roster): \n" + dropped);
		System.out.println("\n*****Course should now contain EE, FF, GG, HH, KK on the course roster (in any order) and MM on the waitlist (in that exact order).");
		System.out.println("\n" + course + "\n");

		// drop student not on roster or waitlist
		dropped = course.dropStudent(studentToDrop);
		System.out.println("*****For " + studentToDrop.getName()
				+ ", drop method should return false (student is not on the roster or waitlist): \n" + dropped);

		// drop student on roster
		studentToDrop = studentsInSchool[4]; // EE
		dropped = course.dropStudent(studentToDrop);
		System.out.println("\n*****For " + studentToDrop.getName()
				+ ", drop method should return true (student is enrolled on the roster): \n" + dropped);
		System.out.println("\n*****Course should now contain FF, GG, HH, KK, MM on the course roster (in any order) and no students on the waitlist.");
		System.out.println("\n" + course + "\n");

		// drop all remaining students
		studentToDrop = studentsInSchool[5]; // FF
		dropped = course.dropStudent(studentToDrop);

		studentToDrop = studentsInSchool[6]; // GG
		dropped = course.dropStudent(studentToDrop);

		studentToDrop = studentsInSchool[7]; // HH
		dropped = course.dropStudent(studentToDrop);

		studentToDrop = studentsInSchool[9]; // KK
		dropped = course.dropStudent(studentToDrop);

		studentToDrop = studentsInSchool[10]; // MM
		dropped = course.dropStudent(studentToDrop);

		System.out.println("\n*****Course and waitlist should now be empty.");
		System.out.println("\n" + course + "\n");

		// drop student not on roster or waitlist
		studentToDrop = studentsInSchool[0]; // AA
		dropped = course.dropStudent(studentToDrop);
		System.out.println("*****For " + studentToDrop.getName()
				+ ", drop method should return false (student is not enrolled): \n" + dropped);
		System.out.println("\n*****Course and waitlist should now be empty.");
		System.out.println("\n" + course + "\n");
	}

}
