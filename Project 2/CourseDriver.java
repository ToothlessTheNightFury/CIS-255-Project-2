import java.util.*;

public class CourseDriver {

    enum Menu {
        MAIN ("MAIN MENU", new String[] {"Add a course", "Manage Courses", "Exit"}),
        MANAGE_COURSES ("MANAGE COURSES", new String[] {"Add student", "Drop student", "Delete course", "Back"}),
        DELETE_STUDENT ("DELETE A STUDENT", new String[] {"By ID", "By List", "Back"});

        private String name;
        private String[] options;

        private Menu (String name, String[] options) {
            this.name = name;
            this.options = options;
        }

        public String getName() {
            return name;
        }

        public String[] getOptions() {
            return options;
        }
    }

    private ArrayList<Course> courseList = new ArrayList<Course>();

    public void run() {

        mainMenu();
    }

    private void mainMenu() {

        String cmd = "";
        while (!cmd.equals("EXIT")) {

            cmd = "";

            switch (Utils.menu(Menu.MAIN.getName(), Menu.MAIN.getOptions())) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    cmd = "EXIT";
                    break;
            }
        }
    }

    private void addCourse() {

        String courseName = Utils.promptStr("ADD COURSE\nPlease enter the name of your course.\n");
        System.out.println();

        int maxCourseStudents = (int) Utils.promptNumPos("MAX COURSE STUDENTS\nPlease enter the maximum number of students that can be enrolled in the course.\n");
        System.out.println();

        int maxWaitListStudents = (int) Utils.promptNumPos("MAX WAIT LIST STUDENTS\nPlease enter the maximum number of students that can be on the wait list.\n");
        System.out.println();

        courseList.add(new Course (courseName, maxCourseStudents, maxWaitListStudents));
        System.out.printf("Created course %s with %d max course students and %d max wait list students.\n\n", courseName, maxCourseStudents, maxWaitListStudents);
    }

    private void manageCourses() {

        int courseIndex = courseMenu();
        if (courseIndex == -1) {
            return;
        }

        String cmd = "";
        while (!cmd.equals("EXIT")) {

            cmd = "";
            System.out.println(courseList.get(courseIndex));

            switch (Utils.menu(courseList.get(courseIndex).getCourseName().toUpperCase(), Menu.MANAGE_COURSES.getOptions())) {
                case 1:
                    addStudent (courseIndex);
                    break;
                case 2:
                    dropStudent (courseIndex);
                    break;
                case 3:
                    if (deleteCourse (courseIndex)) {
                        cmd = "EXIT";
                    }
                    break;
                case 4:
                    cmd = "EXIT";
                    break;
            }
        }
    }

    // Returns course chosen, or -1 if back
    private int courseMenu() {

        String[] courseListStr = courseListToStr();
        String[] options = Arrays.copyOf(courseListStr, courseListStr.length + 1);
        options[courseListStr.length] = "Back";

        int choice = Utils.menu("MANAGE COURSES", options);

        // If user hits Back
        if (choice == options.length) {
            return -1;
        }

        // Index starts at 0, choices start at 1
        return choice - 1;
    }

    private String[] courseListToStr() {

        String[] courseListStr = new String[courseList.size()];

        for (int i = 0; i < courseList.size(); i++) {
            courseListStr[i] = courseList.get(i).getCourseName();
        }

        return courseListStr;
    }

    private void addStudent (int courseIndex) {

        String studentName = Utils.promptStr("STUDENT NAME\nPlease enter the name of the student.\n");
        System.out.println();

        String studentID =  Utils.promptStr("STUDENT ID\nPlease enter the ID number of the student.\n");
        System.out.println();

        boolean tuitionPaid = Utils.promptYN("STUDENT TUITION\nHas the student paid their tuition? (Y/N)\n");
        System.out.println();

        boolean isAdded = courseList.get(courseIndex).addStudent(new Student (studentName, studentID, tuitionPaid));

        if (isAdded) {
            System.out.printf("Successfully added student %s with student ID %s.\n\n", studentName, studentID);
        }
        else {
            System.out.printf("Unable to add student %s with student ID %s. Tuition may not have been paid or student is already registered.\n\n", studentName, studentID);
        }
    }

    private void dropStudent (int courseIndex) {

        String cmd = "";
        while (!cmd.equals("EXIT")) {

            cmd = "";

            switch (Utils.menu(Menu.DELETE_STUDENT.getName(), Menu.DELETE_STUDENT.getOptions())) {
                case 1:
                    dropStudentByID (courseIndex);
                    cmd = "EXIT";
                    break;
                case 2:
                    dropStudentByMenu (courseIndex);
                    cmd = "EXIT";
                    break;
                case 3:
                    cmd = "EXIT";
                    break;
            }
        }
    }

    private void dropStudentByID (int courseIndex) {

        String studentID = Utils.promptStr("STUDENT ID\nPlease enter the ID of the student to delete, starting with S.\n");
        System.out.println();

        Student student = courseList.get(courseIndex).searchStudent(studentID);
        boolean isDeleted = courseList.get(courseIndex).dropStudent(student);

        if (isDeleted) {
            System.out.printf("Successfully dropped student %s with student ID %s.\n\n", student.getName(), student.getID());
        }
        else {
            System.out.printf("Unable to find student with student ID %s. Please make sure the ID was typed correctly.\n\n", studentID);
        }
    }

    private void dropStudentByMenu (int courseIndex) {

        String[] studentListStr = studentListToStr(courseIndex);
        String[] options = Arrays.copyOf(studentListStr, studentListStr.length + 1);
        options[studentListStr.length] = "Back";

        Course course = courseList.get(courseIndex);

        int choice = 0;

        choice = Utils.menu("DROP STUDENT", options);

        if (choice != options.length) {

            int studentIndex = choice - 1;

            if (Utils.promptYN(String.format("Do you want to delete %s? (Y/N)\n", studentListStr[studentIndex]))) {

                Student dropStudent = new Student("", "", true);

                // Student is on roster
                if (studentIndex < course.getNumStudentsOnRoster()) {
                    dropStudent = course.getStudentRoster(studentIndex);
                }
                else {
                    dropStudent = course.getStudentWaitList(studentIndex - course.getNumStudentsOnRoster());
                }

                courseList.get(courseIndex).dropStudent(dropStudent);
                System.out.printf("\nSuccessfully dropped student %s with student ID %s.\n\n", dropStudent.getName(), dropStudent.getID());
            }
            else {
                System.out.println();
            }
        }
    }

    private String[] studentListToStr (int courseIndex) {

        Course course = courseList.get(courseIndex);
        int numStudents = course.getNumStudentsOnRoster() + course.getNumStudentsOnWaitList();

        String[] studentListStr = new String[numStudents];

        for (int i = 0; i < course.getNumStudentsOnRoster(); i++) {
            studentListStr[i] = String.format("%s (%s)", course.getStudentRoster(i).getName(), course.getStudentRoster(i).getID());
        }

        for (int i = 0; i < course.getNumStudentsOnWaitList(); i++) {
            studentListStr[i + course.getNumStudentsOnRoster()] = String.format("%s (%s)", course.getStudentWaitList(i).getName(), course.getStudentWaitList(i).getID());
        }

        return studentListStr;
    }

    private boolean deleteCourse (int courseIndex) {

        boolean deletedCourse = false;
        String courseName = courseList.get(courseIndex).getCourseName();

        System.out.printf("DELETE COURSE\nType in the course name '%s' to delete the course, or BACK to return. WARNING: This action is irreversible.\n", courseName);

        String input = "";

        do {

            input = Utils.promptStr();
            System.out.println();

            if (input.equals(courseName)) {
                courseList.remove(courseIndex);
                System.out.printf("Successfully deleted course %s.\n\n", courseName);
                deletedCourse = true;
            }
            else if (!input.equalsIgnoreCase("BACK")) {
                System.out.printf("Type the course name '%s' to delete the course, or BACK to return.\n", courseName);
            }

        } while (!input.equalsIgnoreCase("back") && !deletedCourse);

        return deletedCourse;
    }

    public static void main (String[] args) {

        CourseDriver driver = new CourseDriver();
        driver.run();
    }
}
