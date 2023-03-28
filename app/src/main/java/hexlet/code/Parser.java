package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    public static Map<String, Object> getDataAsMap(String content, String fileFormat) throws Exception {
        switch (fileFormat) {
            case "json" -> {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(content, new TypeReference<>() { });
            }
            case "yml", "yaml" -> {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                return objectMapper.readValue(content, new TypeReference<>() { });
            }
            default -> throw new Exception("Unavailable  format");
        }

    }
}
