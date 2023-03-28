package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;


public class JsonFormatter {
    public static String makeJson(List<Map<String, Object>> differences) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(differences);
    }
}
