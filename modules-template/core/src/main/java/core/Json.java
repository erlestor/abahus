package core;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.User;


public class Json {

    public static void main(String[] args) {
        try {
            checkPassword("email", "pwd");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static ObjectMapper mapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        // more code to be produced

        return defaultObjectMapper;
    }

    public static JsonNode parse (String str) throws JsonMappingException, JsonProcessingException {
        return mapper.readTree(str);
    }

    public static <T> Map<String,?> readFile(String path_string) throws JsonParseException, JsonMappingException, IOException {
        // Reads a file given a path. Path must be relative to modules-template/core
        Path dirPath = Paths.get("").toAbsolutePath();
        Map<String, ?> json = mapper.readValue(dirPath.resolve(path_string).toFile(), HashMap.class);
         
        return json;
    }

    public static Map<String, String> readObject(String path_string) throws JsonParseException, JsonMappingException, IOException {
        Map<String,?> json = readFile(path_string);
        System.out.println(json.getClass().getSimpleName());
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, ?> entry : json.entrySet()) {
            map.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return map;  
    }

    public static <T> List<T> rf (String path_string, Class<T[]> clazz) throws JsonParseException, JsonMappingException, IOException {
        return Arrays.asList(mapper.readValue(Paths.get("").toAbsolutePath().resolve(path_string).toFile(), clazz));
    }

    public static boolean checkPassword(String email, String pwd) throws IOException {
        List<User> map = rf("data/users.json", User[].class);
        
        for (User user : map) {
            if (user.getEmail().equals(email)) {
                return user.getPassword().equals(pwd);
            }
        }
        return false;        
     }

     public static List<House> getAllHouses() throws JsonParseException, JsonMappingException, IOException {
         return rf("data/houses.json", House[].class);
     }

    
}
