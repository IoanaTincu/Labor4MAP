package com.company;

import controller.CourseController;
import controller.StudentController;
import exceptions.InvalidCourseException;
import exceptions.InvalidMenuOptionException;
import exceptions.NullValueException;
import model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    private StudentController studentController;
    private CourseController courseController;

    public View(StudentController studentController, CourseController courseController) {
        this.studentController = studentController;
        this.courseController = courseController;
    }

    public void sortStudentsByTotalCredits() {
        List<Student> students = studentController.sortStudentsByTotalCredits();
        for (Student student : students)
            System.out.println(student);
    }

    public void filterStudentsAttendingCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course id for the filter: ");
        Long courseId = scanner.nextLong();

        List<Student> students = studentController.filterStudentsAttendingCourse(courseId);
        for (Student student : students)
            System.out.println(student);
    }

    public void findOne() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id: ");
        Long studentId = scanner.nextLong();

        try {
            System.out.println(studentController.findOne(studentId));
        } catch (NullValueException e) {
            System.out.println(e);
        }
    }

    public void findAll() {
        List<Student> students = studentController.findAll();
        for (Student student : students)
            System.out.println(student);
    }

    public void save() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id: ");
        Long id = scanner.nextLong();
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        System.out.println("Enter total credits: ");
        int totalCredits = scanner.nextInt();
        System.out.println("Enter the size of the list of enrolled courses: ");
        int size = scanner.nextInt();
        System.out.println("Enter courses: ");

        Long courseId;
        List<Long> courseList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            courseId = scanner.nextLong();
            courseList.add(courseId);
        }

        Student newStudent = new Student(id, firstName, lastName, totalCredits, courseList);
        try {
            studentController.save(newStudent);
        } catch (NullValueException | IOException | InvalidCourseException e) {
            System.out.println(e);
        }
    }

    void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id: ");
        Long id = scanner.nextLong();

        try {
            System.out.println(studentController.delete(id));
        } catch (NullValueException | IOException e) {
            System.out.println(e);
        }
    }

    void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student id: ");
        Long id = scanner.nextLong();
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        System.out.println("Enter total credits: ");
        int totalCredits = scanner.nextInt();
        System.out.println("Enter the size of the list of enrolled courses: ");
        int size = scanner.nextInt();
        System.out.println("Enter courses: ");

        Long courseId;
        List<Long> courseList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            courseId = scanner.nextLong();
            courseList.add(courseId);
        }

        Student newStudent = new Student(id, firstName, lastName, totalCredits, courseList);
        try {
            System.out.println(studentController.update(newStudent));
        } catch (NullValueException | IOException e) {
            System.out.println(e);
        }
    }

    void size() {
        System.out.println(studentController.size());
    }

    void printStudentMenu() {
        System.out.println("1. Sort students by number of total credits");
        System.out.println("2. Filter students attending course");
        System.out.println("3. Find student by id");
        System.out.println("4. Find all students");
        System.out.println("5. Save student");
        System.out.println("6. Delete student");
        System.out.println("7. Update student");
        System.out.println("8. Print the size of the student list");
        System.out.println("9. Exit");
    }

    void runStudentMenu() {
        boolean done = false;
        while (!done)
            try {
                printStudentMenu();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Choose option: ");
                int option = scanner.nextInt();

                if (option < 1 || option > 8)
                    throw new InvalidMenuOptionException("Invalid value");
                if (option == 9)
                    done = true;
                if (option == 1)
                    sortStudentsByTotalCredits();
                if (option == 2)
                    filterStudentsAttendingCourse();
                if (option == 3)
                    findOne();
                if (option == 4)
                    findAll();
                if (option == 5)
                    save();
                if (option == 6)
                    delete();
                if (option == 7)
                    update();
                if (option == 8)
                    size();
            } catch (InvalidMenuOptionException e) {
                System.out.println(e);
            }
    }

    void runMenu() {
        System.out.println("Menu:");

    }
}
