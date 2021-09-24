package core;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    private static ObjectMapper mapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        
        // more code to be produced

        return defaultObjectMapper;
    }
}
