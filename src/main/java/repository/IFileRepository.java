package repository;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IFileRepository<T> extends ICrudRepository<T> {

    void readDataFromFile() throws IOException;
    void writeDataToFile();
}
