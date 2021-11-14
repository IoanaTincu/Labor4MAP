package com.company;

import model.Student;
import repository.StudentFileRepository;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        StudentFileRepository studentFileRepository = new StudentFileRepository("students.json");
        try { studentFileRepository.readDataFromFile(); }
        catch (IOException e) {}

        List<Student> studentList = studentFileRepository.findAll();
        for (Student student : studentList)
            System.out.println(student.toString());
    }
}
