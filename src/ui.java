/**
 * Created by h205p2 on 9/22/17.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ui {
    static ArrayList<Student> studArray = new ArrayList<Student>();
    static Scanner in = new Scanner(System.in);
    static int numStud;
    static Section firstClass;
    static Teacher firstTeacher;
    static ArrayList<Section> classes = new ArrayList<Section>();
    static Section classChoice;


    public static void main(String[] args) {

        welcome();
        firstMenu();



    }

    public static void welcome() {
        System.out.println("Welcome to PowerSchool 2.0");
        System.out.println();
        System.out.println("To enter information, type it below and press ENTER");
        System.out.println("What is your name?");
        String userName = in.next();
        System.out.println("Suhhh " + userName);
        System.out.println();
    }

    public static void firstMenu() {
        System.out.println("Type in 'st' to create students, type in 'c' to create a class, type in 't' to create a teacher, type in");
        System.out.println("'a' to add a student to an existing class, type in 'r' to remove a student from a class, type in '?' to see if a class is awesome, and type in 's' to search" +
                "for students in a class.");
        System.out.println("I recommend starting with creating a class, but it's up to you.");
        String choice = in.next();
        switch (choice) {
            case "st":
                createStudent();
                break;

            case "c":
                createClass();
                break;

            case "t":
                createTeacher();
                break;

            case "a":
                studentClass();

                break;
            case "r":
                removeStudent();
                break;
            case "?":
                awesomeness();
                break;
            case "s":
                searchStud();
                break;
            default:
                System.out.println("Type in a real letter you deeker.");
                break;
        }
    }

    public static void createStudent() {
        System.out.println("How many students would you like to create?");
        numStud = in.nextInt();
        for (int i = 0; i < numStud; i++) {
            boolean studAwe;
            System.out.println("What's the student's first name?");
            String studentFirst = in.next().toLowerCase();
            System.out.println("What's the student's last name?");
            String studentLast = in.next().toLowerCase();
            System.out.println("What grade is the student in?");
            int studentGrade = in.nextInt();
            System.out.println("Is the student awesome? Type 'yes' or 'no'");
            String awe = in.next();
            awe = awe.toLowerCase();

            if (awe.equals("yes")) {
                studAwe = true;
            } else {
                studAwe = false;
            }
            studArray.add(new Student(studentGrade, studentFirst, studentLast, studAwe));
            System.out.println("You've created the student " + studentFirst);

        }
        firstMenu();


    }

    public static void createClass() {
        System.out.println("Dope! You're going to create a class!");
        System.out.println();
        System.out.println("What is the class called?");
        String className = in.next().toLowerCase();
        System.out.println();
        System.out.println("What is the maximum amount of students in this class?");
        int maxStudents = in.nextInt();
        firstClass = new Section(className, maxStudents);
        classes.add(firstClass);
        System.out.println("Congrats! You've made a " + className + " class with a maximum of " + maxStudents + " students.");
        firstMenu();
    }

    public static void createTeacher() {
        System.out.println("What is the teacher's first name?");
        String teacherFirst = in.next();
        System.out.println();
        System.out.println("What is the teacher's last name?");
        String teacherLast = in.next();
        System.out.println();
        System.out.println("What class do they teach?");
        String teacherClass = in.next();
        firstTeacher = new Teacher(teacherFirst, teacherLast);
        System.out.println("You've created " + firstTeacher.firstName + " the teacher!");
        firstMenu();
    }

    public static void studentClass() {
        if(classes.size() == 0){
            System.out.println("You need to create a class first!");
            firstMenu();

        }
        boolean classYuh = false;
        System.out.println("What class would you like to add students to?");
        String userClass = in.next().toLowerCase();

        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).name.equals(userClass)) {
                classChoice = classes.get(i);
                classYuh = true;
            }
        }
        if(!classYuh){
            System.out.println("That's not a class! Try again!");
            studentClass();
        }
        System.out.println("How many students would you like to add?");
        numStud = in.nextInt();
        if (numStud > classChoice.maxSize) {
            System.out.println("That is too many students for the class! The maximum class size is " + classChoice.maxSize + " students.");
            System.out.println("How many students would you like to add?");
            numStud = in.nextInt();
            adder();

        }
        if (numStud > studArray.size()) {
            System.out.println("You don't have that many students yet! You only have " + studArray.size() + " students." +
                    "Type 'st' to create new students, or type 'n' to use a new number");
            String yuh = in.next();
            if (yuh.equals("st")) {
                createStudent();
                studentClass();
            }
            if (yuh.equals("n")) {
                System.out.println("How many students would you like to add?");
                numStud = in.nextInt();
                adder();
            }

        }
        adder();
    }

    public static void adder() {
        boolean stud = false;
        for (int i = 0; i < numStud; i++) {
            System.out.println("What student would you like to add? Type their first name below:");
            String firstAdd = in.next().toLowerCase();
            for (int j = 0; j < studArray.size(); j++) {
                if (studArray.get(j).firstName.equals(firstAdd)) {
                    classChoice.addStudent(studArray.get(j));
                    stud = true;
                }
            }
            if(!stud) {
                System.out.println("This student doesn't exist! Try again");
                adder();
            }
        }
        System.out.println("Good Job! You've added "+numStud+" students to "+classChoice.name+"!");
        firstClass.sectionSeatsRemaining();
        firstMenu();

    }
    public static void removeStudent(){
        boolean exist = false;
        System.out.println("Time to remove a student!");
        System.out.println("First, enter the student's first and last name to get their id");
        System.out.println("First name:");
        String idFirst = in.next().toLowerCase();
        System.out.println("Last name:");
        String idLast = in.next().toLowerCase();
        for(int i = 0; i<studArray.size(); i++) {

            if(studArray.get(i).firstName.toLowerCase().equals(idFirst) && studArray.get(i).lastName.toLowerCase().equals(idLast)) {
                System.out.println(idFirst + "'s id number is " + studArray.get(i).id);
                for(int j = 0; i<classes.size(); i++){
                    for(int k = 0; k<classes.get(j).students.size(); k++){
                        if(classes.get(j).students.get(k).firstName.equals(idFirst) && classes.get(j).students.get(k).lastName.equals(idLast)){
                            classChoice = classes.get(j);
                        }
                    }


                }
                exist = true;
            }
        }
        if(!exist){
            System.out.println("That student doesn't exist! Try again!");
            removeStudent();

        }
        System.out.println("Now type the student id of the student who you'd like to remove!");
        int classID = in.nextInt();
        classChoice.removeStudent(classID);
        System.out.println("Let's see if it worked!");
        System.out.println("Type in the id of the student you just removed to search for the student!");
        int removeID = in.nextInt();
        System.out.println(classChoice.search(removeID));
        firstMenu();
    }
    public static void awesomeness(){
        System.out.println("Time to find out the awesomeness of a class!");
        System.out.println("What class would you like to check the awesomeness of?");
        String aweClass = in.next().toLowerCase();
        for(int i = 0; i<classes.size(); i++){
            if(classes.get(i).name.toLowerCase().equals(aweClass)){
                System.out.println("Now let's see if this class is awesome!");
                System.out.println("Remember how you typed in if each student was awesome or not?");
                System.out.println("Calculating...");

                System.out.println("Running algorithms...");

                System.out.println("Hazing interns...");

                System.out.println("Done!");
                System.out.println(firstClass.isAwesome());
                firstMenu();
            }
        }
        System.out.println("That class doesn't exist! Try again!");
        awesomeness();


    }
    public static void searchStud(){
        System.out.println("Time to search for a student's information!");
        System.out.println("Type in a student's first name.");
        String studFirst = in.next().toLowerCase();
        System.out.println("Type in their last name.");
        String studLast =  in.next().toLowerCase();
        for(int i = 0; i<classes.size(); i++) {
            classes.get(i).search(classes.get(i).getID(studFirst, studLast));
            System.out.println(classes.get(i).search(classes.get(i).getID(studFirst, studLast)));
        }
        firstMenu();

    }

}

