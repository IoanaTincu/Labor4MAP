package com.company;

import controller.CourseController;
import controller.StudentController;
import exceptions.InvalidCourseException;
import exceptions.NullValueException;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseFileRepository;
import repository.StudentFileRepository;
import repository.TeacherFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, NullValueException, InvalidCourseException {
        // write your code here

        StudentFileRepository studentFileRepository = new StudentFileRepository("students.json");
        studentFileRepository.readDataFromFile();

        List<Student> studentList = studentFileRepository.findAll();
        for (Student student : studentList)
            System.out.println(student.toString());
        // studentList.get(0).setTotalCredits(32);
        studentFileRepository.writeDataToFile();

        Student student1 = new Student(1052, "Marcu", "Andrei", 15, new ArrayList<>(Arrays.asList(1L, 3L)));
        studentFileRepository.save(student1);

        System.out.println(studentFileRepository.delete(1143L));

        // studentFileRepository.update(new Student(1052, "Marcu", "Andrei", 15, new ArrayList<>(Arrays.asList(new Long(1), new Long(9)))));

        TeacherFileRepository teacherFileRepository = new TeacherFileRepository("teachers.json");
        teacherFileRepository.readDataFromFile();

        List<Teacher> teacherList = teacherFileRepository.findAll();
        for (Teacher teacher : teacherList)
            System.out.println(teacher.toString());
        teacherFileRepository.writeDataToFile();

        CourseFileRepository courseFileRepository = new CourseFileRepository("courses.json");
        courseFileRepository.readDataFromFile();

        List<Course> courseList = courseFileRepository.findAll();
        for (Course course : courseList)
            System.out.println(course.toString());
        courseFileRepository.writeDataToFile();

        courseFileRepository.update(new Course(1226, "Matematici speciale", 1200, 31, new ArrayList<>(Arrays.asList(6L, 3L)), 15));

        System.out.println("Students by total credits");
        StudentController studentController = new StudentController(studentFileRepository, courseFileRepository);
        List<Student> sortedStudentsByTotalCredits = studentController.sortStudentsByTotalCredits();
        for (Student student : sortedStudentsByTotalCredits)
            System.out.println(student.toString());

        CourseController courseController = new CourseController(courseFileRepository, studentFileRepository, teacherFileRepository);
        List<Course> sortedCoursesByStudentsEnrolled = courseController.sortCoursesByStudentsEnrolled();
        for (Course course : sortedCoursesByStudentsEnrolled)
            System.out.println(course.toString());

        System.out.println("Filter");
        List<Student> filteredStudentsAttendingCourse = studentController.filterStudentsAttendingCourse(9L);
        for (Student student : filteredStudentsAttendingCourse)
            System.out.println(student.toString());

        List<Course> filteredCourses = courseController.filterCoursesWithSpecifiedCredits(6);
        for (Course course : filteredCourses)
            System.out.println(course.toString());


        //-----------------------------------------------------------------------------------------------

        System.out.println("StudentController");
        Student student2 = new Student(811, "Laurentiu", "Ilie", 16, new ArrayList<>(Arrays.asList(653L, 855L)));
        // System.out.println(studentController.save(student2));

        // System.out.println(studentController.delete(811L));

        View view = new View(studentController, courseController);
        //view.sortStudentsByTotalCredits();
        //view.filterStudentsAttendingCourse();
        //view.findOne();
        view.runStudentMenu();
    }
}
