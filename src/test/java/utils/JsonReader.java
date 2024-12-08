package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.Map;

public class JsonReader {
    static ObjectMapper mapper;
    static String filePath = "/AppData.json";

    public static Map<Object, Object> jsonReader() {

        mapper = new ObjectMapper();
        Map<Object, Object> json = null;

        try {
            json = mapper.readValue(Paths.get(System.getProperty("user.dir") + filePath).toFile(), Map.class);

        } catch (Exception ex) {
            System.out.println("problem on jsonRead method");
        }
        return json;
    }


}
