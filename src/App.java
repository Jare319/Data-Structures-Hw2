import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    
    public static void main(String[] args) throws Exception {
        DoubleLList classList = new DoubleLList();
        Scanner usrInput = new Scanner(System.in);
        displayMenu(false, classList, usrInput);
        usrInput.close();
    }

    public static void displayMenu(boolean dataInput, DoubleLList classList, Scanner usrInput) {
        String choice;
        System.out.println("\n------------------------------------------------------------");
        System.out.println("WHAT WOULD YOU LIKE TO DO? (input only a single number)");
        System.out.println("1. Import data from inputFile.txt");
        System.out.println("2. Delete a course");
        System.out.println("3. Insert a new course");
        System.out.println("4. Delete a student");
        System.out.println("5. Insert a new student");
        System.out.println("6. Transfer a student from one course to another");
        System.out.println("7. Display the course list");
        System.out.println("8. Display the student list");
        System.out.println("9. Exit\n");
        choice = usrInput.nextLine();

        switch (choice) {
            case "1":
                if (!dataInput) {
                    try {
                        readInput(classList);
                        dataInput = true;
                    } catch (FileNotFoundException e) {
                        System.out.println("inputFile.txt not found");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Data has already been input, please select a different command");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "2":
                if (dataInput) {
                    deleteCourse(classList, usrInput);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "3":
                if (dataInput) {
                    newCourse(classList, usrInput);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "4":
                if (dataInput) {
                    deleteStudent(classList, usrInput);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "5":
                if (dataInput) {
                    addStudent(classList, usrInput);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "6":
                if (dataInput) {
                    transferStudent(classList, usrInput);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "7":
                if (dataInput) {
                    displayCourseList(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "8":
                if (dataInput) {
                    displayStudentList(classList, usrInput);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList, usrInput);
                break;
            case "9":
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Please input a valid option");
                displayMenu(dataInput, classList, usrInput);
        }
    }

    private static void printSummary (DoubleLList classList) {
        System.out.println("Summary of the record:");
        System.out.println("Number of courses registered: " + classList.getCourseCount());
        System.out.println("Number of total students: " + classList.getStudentCount());
    }

    private static void displayStudentList(DoubleLList classList, Scanner usrInput) {
        String input;
        StudentNode studentNode;
        ClassNode classNode;
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Enter the course number in the format \"XXXX XXXX\" (Ex: MATH 1111)");
        input = usrInput.nextLine();
        System.out.println("List of students enrolled in " + input + " are as follows:\n");
        System.out.printf("%-15s%-30s%-30s%-50s%n", "Student's ID:", "Students Name:", "Student's Email:", "Student's Address:");
        classNode = classList.search(input);
        if (classNode != null) {
            studentNode = classNode.getStudentList().getHead();
            while (studentNode != null) {
                System.out.printf("%-15s%-30s%-30s%-50s%n", studentNode.getStudentId(), studentNode.getStudentName(), studentNode.getEmail(), studentNode.getAddress());
                studentNode = studentNode.getNext();
            }
        }
    }

    private static void displayCourseList(DoubleLList classList) {
        ClassNode node = classList.getHead();
        System.out.println("\n------------------------------------------------------------");
        System.out.println("List of registered courses are as follows:");
        while (node != null) {
            System.out.println("Course number: " + node.getCourseNumber());
            System.out.println("Course name: " + node.getCourseName());
            System.out.println("Number of students enrolled: " + node.getStudentCount());
            System.out.println();
            node = node.getNext();
        }
    }

    private static void transferStudent(DoubleLList classList, Scanner usrInput) {
        ClassNode newCourse;
        ClassNode oldCourse;
        String name;
        StudentNode studentNode;
        StudentNode tempNode;
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Enter the course number the student wants to drop from in the format \"XXXX XXXX\" (Ex: MATH 1111):");
        oldCourse = classList.search(usrInput.nextLine());
        if (oldCourse == null) {
            System.out.println("The course can't be found...\n");
        } else {
            System.out.println("Enter the student's name:");
            name = usrInput.nextLine();
            System.out.println("Enter the course number the student wants to enroll in in the format \"XXXX XXXX\" (Ex: MATH 1111):");
            newCourse = classList.search(usrInput.nextLine());
            if (newCourse == null) {
                System.out.println("This course can't be found...\n");
            } else {
                tempNode = oldCourse.getStudentList().getHead();
                while (tempNode != null) {
                    if (tempNode.getStudentName().equals(name)) {
                        studentNode = tempNode;
                        newCourse.addStudent(name, studentNode.getStudentId(), studentNode.getEmail(), studentNode.getAddress());
                        oldCourse.deleteStudent(studentNode.getStudentId());
                        printSummary(classList);
                        break;
                    }
                    tempNode = tempNode.getNext();
                }
            }
        }
    }

    private static void addStudent(DoubleLList classList, Scanner usrInput) {
        String courseNumber;
        String id;
        String email;
        String adr;
        String name;
        ClassNode node;
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Enter the course number the student wants to enroll in in the format \"XXXX XXXX\" (Ex: MATH 1111):");
        courseNumber = usrInput.nextLine();
        node = classList.search(courseNumber);
        if (node == null) {
            System.out.println("That course can't be found...\n");
        } else {
            System.out.println("Enter the student's name:");
            name = usrInput.nextLine();
            System.out.println("Enter the student's ID:");
            id = usrInput.nextLine();
            System.out.println("Enter student's email:");
            email = usrInput.nextLine();
            System.out.println("Enter student's emergency contact address:");
            adr = usrInput.nextLine();
            System.out.println(); 
            node.addStudent(name, id, email, adr);
            printSummary(classList);
        }
    }

    private static void deleteStudent(DoubleLList classList, Scanner usrInput) {
        String studentId;
        String courseNumber;
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Enter the student ID number to delete in the format \"AXXXXXX\" (Ex: A123456)");
        studentId = usrInput.nextLine();
        System.out.println("Enter the course number from which the student is to be dropped from in the format \"XXXX XXXX\" (Ex: MATH 1111):");
        courseNumber = usrInput.nextLine();
        ClassNode node = classList.search(courseNumber);
        if (node == null) {
            System.out.println("A student with this id could not be found, please try again...");
        } else {
            node.deleteStudent(studentId);
            printSummary(classList);
        }
    }

    private static void newCourse(DoubleLList classList, Scanner usrInput) {
        String courseNumber;
        String courseName;
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Enter the new course number to add in the format \"XXXX XXXX\" (Ex: MATH 1111)");
        courseNumber = usrInput.nextLine();
        System.out.println("Enter the name for the new course:");
        courseName = usrInput.nextLine();
        classList.addCourse(courseName, courseNumber);
        printSummary(classList);
    }

    private static void deleteCourse(DoubleLList classList, Scanner usrInput) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Enter the course number to delete in the format \"XXXX XXXX\" (Ex: MATH 1111)");
        if (!classList.deleteCourse(usrInput.nextLine())) {
            System.out.println("A course with this number could not be found, please try again...");
        } else {
            printSummary(classList);
        }
    }

    private static void readInput(DoubleLList classList) throws FileNotFoundException {
        /* 
        For reference, the values of inArr are as follows:

        inputArr[0] = Course number
        inputArr[1] = Course name
        inputArr[2] = Student last name
        inputArr[3] = Student first name
        inputArr[4] = Student ID
        inputArr[5] = Student email
        inputArr[6] = Student street/city
        inputArr[7] = Student state/zip

         */

        File input = new File("inputFile.txt");
        Scanner fileScnr = new Scanner(input);
        fileScnr.nextLine();

        while (fileScnr.hasNextLine()) {
            String[] inArr = fileScnr.nextLine().split(",");
            ClassNode node = classList.search(inArr[0]);
            if (node != null) {
                try {
                    node.addStudent(inArr[3], inArr[2], inArr[4], inArr[5], inArr[6], inArr[7]);
                } catch (Exception e) {
                    node.addStudent(inArr[3], inArr[2], inArr[4], inArr[5], inArr[6]);
                }
                
            } else {
                classList.addCourse(inArr[1], inArr[0]);
                try {
                    classList.getTail().addStudent(inArr[3], inArr[2], inArr[4], inArr[5], inArr[6], inArr[7]);
                } catch (Exception e) {
                    classList.getTail().addStudent(inArr[3], inArr[2], inArr[4], inArr[5], inArr[6]);
                }
            }
        }
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Input file is read successfully..");
        printSummary(classList);
        fileScnr.close();
    }
}
