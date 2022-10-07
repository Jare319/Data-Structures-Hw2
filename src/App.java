import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    
    public static void main(String[] args) throws Exception {
        DoubleLList classList = new DoubleLList();
        displayMenu(false, classList);
    }

    public static void displayMenu(boolean dataInput, DoubleLList classList) {
        Scanner usrInput = new Scanner(System.in);
        int choice;
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
        choice = usrInput.nextInt();
        usrInput.close();
        switch (choice) {
            case 1:
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
                displayMenu(dataInput, classList);
                break;
            case 2:
                if (dataInput) {
                    deleteCourse(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList);
                break;
            case 3:
                if (dataInput) {
                    newCourse(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList);
                break;
            case 4:
                if (dataInput) {
                    deleteStudent(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList);
                break;
            case 5:
                if (dataInput) {
                    addStudent(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList);
                break;
            case 6:
                if (dataInput) {
                    transferStudent(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList);
                break;
            case 7:
                if (dataInput) {
                    displayCourseList(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList);
                break;
            case 8:
                if (dataInput) {
                    displayStudentList(classList);
                } else {
                    System.out.println("Data has not been input, please select 1 first");
                }
                displayMenu(dataInput, classList);
                break;
            case 9:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Please input a valid option");
                displayMenu(dataInput, classList);
        }
    }

    private static void displayStudentList(DoubleLList classList) {

    }

    private static void displayCourseList(DoubleLList classList) {
        ClassNode node = classList.getHead();
        while (node.getNext() != null) {
            System.out.println(node.getCourseNumber());
            node = node.getNext();
        }
    }

    private static void transferStudent(DoubleLList classList) {

    }

    private static void addStudent(DoubleLList classList) {

    }

    private static void deleteStudent(DoubleLList classList) {

    }

    private static void newCourse(DoubleLList classList) {

    }

    private static void deleteCourse(DoubleLList classList) {
        String input;
        Scanner usrInput = new Scanner(System.in);
        System.out.println("Enter the course number to delete in the format \"XXXX XXXX\" (Ex: MATH 1111");
        input = usrInput.nextLine();
        if (classList.deleteCourse(input)) {
            System.out.println("Summary of the record:");
            System.out.println("Number of courses registered: " + classList.getCourseCount());
            System.out.println("Number of total students: " + classList.getStudentCount());
        } else {
            System.out.println("A course with this number could not be found, please try again...");
        }
        usrInput.close();
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
        System.out.println("\nInput file is read successfully..");
        System.out.println("Summary of the record:");
        System.out.println("Number of courses registered: " + classList.getCourseCount());
        System.out.println("Number of students registered: " + classList.getStudentCount());
        fileScnr.close();
    }
}
