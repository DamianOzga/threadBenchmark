package com.interview.damian_ozga.utils;


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

    public static File createTestJSONFile(){
        String directory = System.getProperty("user.home") + TEMP_DIRECTORY;
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, TEST_FILE);
        if(file.exists()){
            return file;
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(getDummyJsonContent());
        } catch ( IOException e) {
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

    public static Document createDocumentFromJSONFile(File file){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(file);
            return Document.parse(jsonNode.toString());
        } catch (IOException jExc){
            throw new RuntimeException("Creation document from JSON file filed.", jExc);
        }
    }

    public static <T> T mapJsonFileToClass(File file, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException iOe) {
            throw new RuntimeException("Mapping json file to class " + clazz.getName() + "filed.");
        }
    }

    private static String getDummyJsonContent(){
        return "{\n" +
                "  \"user\": {\n" +
                "    \"id\": \"XyBBa910\",\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"email\": \"john.doe@example.com\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"123 Main St\",\n" +
                "      \"city\": \"Anytown\",\n" +
                "      \"state\": \"CA\",\n" +
                "      \"zipcode\": \"12345\"\n" +
                "    },\n" +
                "    \"phoneNumbers\": [\n" +
                "      {\n" +
                "        \"type\": \"home\",\n" +
                "        \"number\": \"555-555-5555\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"work\",\n" +
                "        \"number\": \"555-555-5556\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
}
