package core;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    private static ObjectMapper mapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // more code to be produced

        return defaultObjectMapper;
    }

    public static JsonNode parse (String str) throws JsonMappingException, JsonProcessingException {
        return mapper.readTree(str);
    }

    public static Map<String,String> readFile(String path_string) throws JsonParseException, JsonMappingException, IOException {
        // Reads a file given a path. Path must be relative to modules-template/core
        Path dirPath = Paths.get("").toAbsolutePath();
        Map<String, ?> json = mapper.readValue(dirPath.resolve(path_string).toFile(), HashMap.class);
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, ?> entry : json.entrySet()) {
            map.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return map;

    }

    public static Map<String, String> getUsers() throws IOException{
        return readFile("data/users.json");
    }

    
}
