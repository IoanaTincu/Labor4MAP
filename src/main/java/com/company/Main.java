package com.company;

import controller.Controller;
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

    public static void main(String[] args) throws IOException, NullValueException {
        // write your code here

        StudentFileRepository studentFileRepository = new StudentFileRepository("students.json");
        studentFileRepository.readDataFromFile();

        List<Student> studentList = studentFileRepository.findAll();
        for (Student student : studentList)
            System.out.println(student.toString());
        studentList.get(0).setTotalCredits(32);
        studentFileRepository.writeDataToFile();

        Student student1 = new Student(1052, "Marcu", "Andrei", 15, new ArrayList<>(Arrays.asList(new Long(1), new Long(3))));
        studentFileRepository.save(student1);

        System.out.println(studentFileRepository.delete(new Long(1143)));

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

        courseFileRepository.update(new Course(1226, "Matematici speciale", 1200, 31, new ArrayList<>(Arrays.asList(new Long(6), new Long(3))), 15));

        System.out.println("Students by total credits");
        Controller controller = new Controller(studentFileRepository, teacherFileRepository, courseFileRepository);
        List<Student> sortedStudentsByTotalCredits = controller.sortStudentsByTotalCredits();
        for (Student student : sortedStudentsByTotalCredits)
            System.out.println(student.toString());

        List<Course> sortedCoursesByStudentsEnrolled = controller.sortCoursesByStudentsEnrolled();
        for (Course course : sortedCoursesByStudentsEnrolled)
            System.out.println(course.toString());

        System.out.println("Filter");
        List<Student> filteredStudentsAttendingCourse = controller.filterStudentsAttendingCourse(new Long(9));
        for (Student student : filteredStudentsAttendingCourse)
            System.out.println(student.toString());

        List<Course> filteredCourses = controller.filterCoursesWithSpecifiedCredits(6);
        for (Course course : filteredCourses)
            System.out.println(course.toString());
    }
}
