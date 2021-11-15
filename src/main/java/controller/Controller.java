package controller;

import model.Course;
import model.Student;
import repository.CourseFileRepository;
import repository.StudentFileRepository;
import repository.TeacherFileRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private StudentFileRepository studentFileRepo;
    private TeacherFileRepository teacherFileRepo;
    private CourseFileRepository courseFileRepo;

    public Controller(StudentFileRepository studentFileRepo, TeacherFileRepository teacherFileRepo, CourseFileRepository courseFileRepo) {
        this.studentFileRepo = studentFileRepo;
        this.teacherFileRepo = teacherFileRepo;
        this.courseFileRepo = courseFileRepo;
    }

    /**
     * sorts students descending by the number of total credits
     *
     * @return sorted list of students
     */
    public List<Student> sortStudentsByTotalCredits() {
        return studentFileRepo.findAll().stream()
                // .sorted((student, otherStudent) -> otherStudent.getTotalCredits() - student.getTotalCredits())
                .sorted(Comparator.comparingInt(Student::getTotalCredits).reversed())
                .collect(Collectors.toList());
    }

    /**
     * sorts courses descending by the number of enrolled students
     *
     * @return sorted list of students
     */
    public List<Course> sortCoursesByStudentsEnrolled() {
        return courseFileRepo.findAll().stream()
                .sorted((course, otherCourse) -> otherCourse.getStudentsEnrolled().size() - course.getStudentsEnrolled().size())
                .collect(Collectors.toList());
    }

    /**
     * filters the students who attend the course with the given id
     *
     * @param courseId id of the course
     * @return list of students who attend the course
     */
    public List<Student> filterStudentsAttendingCourse(Long courseId) {
        return studentFileRepo.findAll().stream()
                // .filter(student -> student.getEnrolledCourses().contains(courseId))
                .filter(student -> {for (Long id : student.getEnrolledCourses()) if (id == courseId) return true; return false;})
                .collect(Collectors.toList());
    }

    /**
     * filters the courses with the specified number of credits
     *
     * @param credits number of credits
     * @return list of courses
     */
    public List<Course> filterCoursesWithSpecifiedCredits(int credits) {
        return courseFileRepo.findAll().stream()
                .filter(course -> course.getCredits() == credits)
                .collect(Collectors.toList());
    }
}
