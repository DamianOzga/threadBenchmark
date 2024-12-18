package com.interview.damian_ozga.jmeter.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for file operations related to benchmarking.
 */
public class FileUtils {

    private static final String TEMP_DIRECTORY = "\\benchmark";
    private static final String TEST_FILE = "benchmarkDocument.json";

    /**
     * Creates and returns a benchmark JSON file.
     * If the file does not exist, it creates the necessary directories and writes dummy JSON content to the file.
     *
     * @return the benchmark JSON file
     */
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

    /**
     * Deletes the benchmark JSON file and its parent directory if they exist.
     */
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

    /**
     * Creates a MongoDB Document from a JSON file.
     *
     * @param file the JSON file
     * @return the created Document
     * @throws RuntimeException if reading the file or parsing the JSON fails
     */
    public static Document createDocumentFromJSONFile(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(file);
            return Document.parse(jsonNode.toString());
        } catch (IOException jExc) {
            throw new RuntimeException("Creating document from JSON file failed.", jExc);
        }
    }

    /**
     * Maps a JSON file to a given class type.
     *
     * @param file  the JSON file
     * @param clazz the class type to map to
     * @param <T>   the type of the class
     * @return the mapped object
     * @throws RuntimeException if reading the file or mapping the JSON fails
     */
    public static <T> T mapJsonFileToClass(File file, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException iOe) {
            throw new RuntimeException("Mapping JSON file to class " + clazz.getName() + " failed.");
        }
    }

    /**
     * Returns dummy JSON content for the benchmark file.
     *
     * @return the dummy JSON content
     */
    private static String getDummyJsonContent() {
        return "        {\n" +
                "            \"key\": \"XyBBa910\",\n" +
                "            \"name\": \"John Doe\",\n" +
                "            \"email\": \"john.doe@example.com\",\n" +
                "            \"address\": {\n" +
                "                \"street\": \"123 Main St\",\n" +
                "                \"city\": \"Anytown\",\n" +
                "                \"state\": \"CA\",\n" +
                "                \"zipcode\": \"12345\"\n" +
                "            },\n" +
                "            \"phoneNumbers\": [\n" +
                "                {\n" +
                "                    \"type\": \"home\",\n" +
                "                    \"number\": \"555-555-5555\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": \"work\",\n" +
                "                    \"number\": \"555-555-5556\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }";
    }

}