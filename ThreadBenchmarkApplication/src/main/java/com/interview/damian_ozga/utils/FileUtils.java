package com.interview.damian_ozga.utils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// TODO: ADD LOGS, JAVA DOCS, TESTY
public class FileUtils {

    private static final String TEMP_DIRECTORY = "\\benchmark";
    private static final String TEST_FILE = "benchmarkDocument.json";

    public static File getBenchmarkJSONFile() {
        String directory = System.getProperty("user.home") + TEMP_DIRECTORY;
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, TEST_FILE);
        if (file.exists()) {
            return file;
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(getDummyJsonContent());
        } catch (IOException e) {
            throw new RuntimeException("Writing to file failed.");
        }
        return file;
    }

    public static void cleanupTestJSONFile() {
        String directory = System.getProperty("user.home") + TEMP_DIRECTORY;
        File file = new File(directory, TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        File dir = new File(directory);
        if (dir.exists() && dir.isDirectory()) {
            dir.delete();
        }
    }

    public static Document createDocumentFromJSONFile(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(file);
            return Document.parse(jsonNode.toString());
        } catch (IOException jExc) {
            throw new RuntimeException("Creation document from JSON file filed.", jExc);
        }
    }

    public static <T> T mapJsonFileToClass(File file, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException iOe) {
            throw new RuntimeException("Mapping json file to class " + clazz.getName() + " failed.");
        }
    }

    private static String getDummyJsonContent() {
        return """
        {
            "key": "XyBBa910",
            "name": "John Doe",
            "email": "john.doe@example.com",
            "address": {
                "street": "123 Main St",
                "city": "Anytown",
                "state": "CA",
                "zipcode": "12345"
            },
            "phoneNumbers": [
                {
                    "type": "home",
                    "number": "555-555-5555"
                },
                {
                    "type": "work",
                    "number": "555-555-5556"
                }
            ]
        }
        """;
    }

}