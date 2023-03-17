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
    /*public static String makeJson(Map<String, Object> data, Map<String, Object> data2) {
        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(data.keySet());
        keySet.addAll(data2.keySet());
        for (String key: keySet) {
            if (data.containsKey(key) && !data2.containsKey(key)) {
                result.put("- " + key, data.get(key));
            } else if (!data.containsKey(key) && data2.containsKey(key)) {
                result.put("+ " + key, data2.get(key));
            } else if (data.containsKey(key) && data2.containsKey(key)
                    && !Objects.equals(data.get(key), data2.get(key))) {
                result.put("- " + key, data.get(key));
                result.put("+ " + key, data2.get(key));
            } else if (data.containsKey(key) && data2.containsKey(key)
                    && Objects.equals(data.get(key), data2.get(key))) {
                result.put("  " + key, data.get(key));
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }*/
}
