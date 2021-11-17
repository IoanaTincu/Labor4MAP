package controller;

import exceptions.NullValueException;
import model.Course;
import model.Student;
import model.Teacher;
import repository.CourseFileRepository;
import repository.StudentFileRepository;
import repository.TeacherFileRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CourseController {

    private CourseFileRepository courseFileRepo;
    private StudentFileRepository studentFileRepo;
    private TeacherFileRepository teacherFileRepo;

    public CourseController(CourseFileRepository courseFleRepo, StudentFileRepository studentFileRepo, TeacherFileRepository teacherFileRepo) {
        this.courseFileRepo = courseFileRepo;
        this.studentFileRepo = studentFileRepo;
        this.teacherFileRepo = teacherFileRepo;
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

    public Course findOne(Long id) throws NullValueException {
        return courseFileRepo.findOne(id);
    }

    public List<Course> findAll() {
        return courseFileRepo.findAll();
    }

    public Course save(Course course) throws NullValueException {
        if (course == null)
            throw new NullValueException("Invalid entity");


    }
}
