package utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }
    public static <T> T convertJsontoJava(String json, Class<T> cls){//generic method
        T javaResult = null;
        try {
            javaResult =   mapper.readValue(json,cls);
        } catch (IOException e) {
            System.err.println("json data javaya dönüştürülemedi");
        }
        return javaResult;
    }

}
