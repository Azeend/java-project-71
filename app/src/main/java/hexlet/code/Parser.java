package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parserJson(String content, String filepath) throws JsonProcessingException {
        ObjectMapper objectMapper = format(filepath);
        return objectMapper.readValue(content, new TypeReference<>() { });
    }
    public static ObjectMapper format(String filepath) {
        return filepath.endsWith(".json") ? new ObjectMapper() : new YAMLMapper();
    }
}
