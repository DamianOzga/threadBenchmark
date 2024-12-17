package com.interview.damian_ozga.utils;

import com.interview.damian_ozga.model.User;
import com.interview.damian_ozga.test_base.AbstractUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

/**
 * Unit tests for FileUtils class.
 */
public class FileUtilsTest extends AbstractUnitTest {

    private File testFile;

    /**
     * Sets up the test environment before each test method.
     * Creates a benchmark JSON file to be used in the tests.
     */
    @BeforeEach
    public void setUp() {
        testFile = FileUtils.getBenchmarkJSONFile();
    }

    /**
     * Cleans up the test environment after each test method.
     * Deletes the test JSON file created during the setup.
     */
    @AfterEach
    public void tearDown() {
        FileUtils.cleanupTestJSONFile();
    }

    /**
     * Test to verify that the created JSON file exists.
     */
    @Test
    @DisplayName("Created JSON file exists.")
    public void created_json_file_exists() {
        // given
        // when
        // then
        assertNotNull(testFile);
        assertTrue(testFile.exists());
    }

    /**
     * Test to verify that the JSON file has been deleted.
     */
    @Test
    @DisplayName("JSON file has been deleted.")
    public void json_file_has_been_deleted() {
        // given
        // when
        FileUtils.cleanupTestJSONFile();
        // then
        assertFalse(testFile.exists());
    }

    /**
     * Test to verify that a document can be created from the JSON file.
     */
    @Test
    @DisplayName("Created document from JSON file.")
    public void created_document_from_json_file() {
        // given
        // when
        Document document = FileUtils.createDocumentFromJSONFile(testFile);
        // then
        assertNotNull(document);
        assertEquals("XyBBa910", document.getString("key"));
    }

    /**
     * Test to verify that the JSON file can be mapped to a given class.
     */
    @Test
    @DisplayName("Map to given class.")
    public void map_to_given_class() {
        // given
        // when
        User user = FileUtils.mapJsonFileToClass(testFile, User.class);
        // then
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
    }
}