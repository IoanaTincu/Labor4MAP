package model;

import java.util.List;
import java.util.Objects;

public class Student extends Person {

    private int totalCredits;
    private List<Course> enrolledCourses;

    public Student(long id, String firstName, String lastName, int totalCredits, List<Course> enrolledCourses) {
        super(id, firstName, lastName);
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalCredits=" + totalCredits +
                ", enrolledCourses=" + enrolledCourses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return totalCredits == student.totalCredits && Objects.equals(enrolledCourses, student.enrolledCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalCredits, enrolledCourses);
    }
}
