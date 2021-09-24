package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.jupiter.api.Test;

public class JsonTest {
    @Test
    public void simpleTest() {
        String jsonSource = "{ \"title\": \"JSON test string\"}";
        try {
            JsonNode node = Json.parse(jsonSource);
            assertEquals(node.get("title").asText(), "JSON test string");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFile() throws IOException {
        // Read value from jsonTestData
        Map<String, String> map = Json.readFile("src/test/java/core/jsonTestData.json");

        // In case jsonTestData was changed, actual value must be updated to match
        Map<String,String> actualValue = new HashMap<>();
        actualValue.put("name", "Andreas");
        actualValue.put("age", "24");

        // Check equality
        for (Map.Entry<String, String> entry : map.entrySet()) {
            assertEquals(entry.getValue().toString(), actualValue.get(entry.getKey()).toString());
        }
    }

    @Test
    public void readUsers() throws IOException {
        // only checks if error was thrown
        Json.getUsers();
    }
}
