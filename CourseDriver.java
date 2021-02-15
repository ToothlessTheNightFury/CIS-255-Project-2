import java.util.*;

public class CourseDriver {

    /**
     * MANAGE STUDENTS
     * 1. Search
     * 2. View All
     * 3. Exit
     *
     * SEARCH
     * Please enter the full name of the student. (Maybe implement fuzzy search later)
     * John Doe
     *
     * VIEW ALL STUDENTS
     * 1. Student1
     * 2. Student2
     * 3. Student3
     * 4. Student4
     * ...
     */

    enum Menu {
        MAIN ("MAIN MENU", new String[] {"Add a course", "Manage Courses", "Manage Students", "Exit"}),
        MANAGE_COURSES ("MANAGE COURSES", new String[] {"Add a student", "Delete a student", "Delete the course", "Back"}),
        MANAGE_STUDENTS ("MANAGE STUDENTS", new String[] {"Search", "View All", "Back"}),
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
                    manageStudents();
                    break;
                case 4:
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
                    deleteStudent();
                    break;
                case 3:
                    deleteCourse();
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
        if (choice == options.length)
            return -1;

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

        String studentID =  Utils.promptStr("STUDENT ID\nPlease enter the ID number of the student, starting with S.\n");
        System.out.println();

        boolean tuitionPaid = Utils.promptYN("STUDENT TUITION\nHas the student paid their tuition? (Y/N)\n");
        System.out.println();

        boolean isAdded = courseList.get(courseIndex).addStudent(new Student (studentName, studentID, tuitionPaid));
        System.out.printf("isAdded: %b\n\n", isAdded);

        if (isAdded) {
            System.out.printf("Successfully added student %s with student ID %s.\n\n", studentName, studentID);
        }
        else {
            System.out.printf("Unable to add student %s with student ID %s. Tuition may not have been paid or student is already registered.\n\n", studentName, studentID);
        }
    }

    private void deleteStudent() {

        String cmd = "";
        while (!cmd.equals("EXIT")) {

            cmd = "";

            switch (Utils.menu(Menu.DELETE_STUDENT.getName(), Menu.DELETE_STUDENT.getOptions())) {
                case 1:
                    deleteStudentByID();
                    break;
                case 2:
                    deleteStudentByMenu();
                    break;
                case 3:
                    cmd = "EXIT";
                    break;
            }
        }
    }

    private void deleteStudentByID() {

    }

    private void deleteStudentByMenu() {

    }

    private void deleteCourse() {


    }

    private void manageStudents() {

    }
}
