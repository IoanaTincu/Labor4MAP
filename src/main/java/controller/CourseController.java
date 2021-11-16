package controller;

import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseRepository;
import repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CourseController {

    private CourseRepository courseRepo;
    private PersonRepository<Student> studentRepo;
    private PersonRepository<Teacher> teacherRepo;

    public CourseController(CourseRepository courseRepo, PersonRepository<Student> studentRepo, PersonRepository<Teacher> teacherRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    /**
     * sorts courses descending by the number of enrolled students
     *
     * @return sorted list of students
     */
    public List<Course> sortCoursesByStudentsEnrolled() {
        return courseRepo.findAll().stream()
                .sorted((course, otherCourse) -> otherCourse.getStudentsEnrolled().size() - course.getStudentsEnrolled().size())
                .collect(Collectors.toList());
    }

    /**
     * filters the courses with the specified number of credits
     *
     * @param credits number of credits
     * @return list of courses
     */
    public List<Course> filterCoursesWithSpecifiedCredits(int credits) {
        return courseRepo.findAll().stream()
                .filter(course -> course.getCredits() == credits)
                .collect(Collectors.toList());
    }
}
