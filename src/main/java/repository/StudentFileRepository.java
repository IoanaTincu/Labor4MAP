package repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Course;
import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFileRepository extends PersonRepository<Student> implements IFileRepository<Student> {

    private String fileName;

    public StudentFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void readDataFromFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader(fileName));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode node : parser) {
            Student student = new Student();

            student.setId(node.path("id").asLong());
            student.setFirstName(node.path("firstName").asText());
            student.setLastName(node.path("lastName").asText());
            student.setTotalCredits(node.path("totalCredits").asInt());

            JsonNode jsonArray = node.get("enrolledCourses");
            if (jsonArray.size() > 0)
                student.setEnrolledCourses(convertJsonArray(jsonArray));

            repoList.add(student);
        }

        reader.close();
    }

    public List<Long> convertJsonArray(JsonNode jsonArray) {
        List<Long> convertedArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++)
            convertedArray.add(jsonArray.get(i).asLong());
        return convertedArray;
    }

    @Override
    public void writeDataToFile() {

    }
}
