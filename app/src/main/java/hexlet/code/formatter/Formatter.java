package hexlet.code.formatter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.LinkedHashMap;

public class Formatter {
    private static String checkValue(Object value) {
        if (value instanceof Map || value instanceof List || value instanceof Object[])  {
            return "[complex value]";
        } else if (value == null) {
            return null;
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
    public static String makePlain(Map<String, Object> data, Map<String, Object> data2) {
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(data.keySet());
        keySet.addAll(data2.keySet());
        var builder = new StringBuilder();
        for (String key: keySet) {
            if (data.containsKey(key) && !data2.containsKey(key)) {
                builder.append("Property ").append("'")
                        .append(key).append("'").append(" was removed").append("\n");
            } else if (!data.containsKey(key) && data2.containsKey(key)) {
                builder.append("Property ").append(checkValue(key))
                        .append(" was added with value: ")
                        .append(checkValue(data2.get(key)))
                        .append("\n");
            } else if (data.containsKey(key) && data2.containsKey(key)
                    && !Objects.equals(data.get(key), data2.get(key))) {
                builder.append("Property ").append(checkValue(key))
                        .append(" was updated. From ")
                        .append(checkValue(data.get(key))).append(" to ")
                        .append(checkValue(data2.get(key)))
                        .append("\n");
            }
        }
        return builder.toString().trim();
    }
    public static String makeStylish(Map<String, Object> data, Map<String, Object> data2) {
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(data.keySet());
        keySet.addAll(data2.keySet());
        var builder = new StringBuilder("{\n");
        for (String key: keySet) {
            if (data.containsKey(key) && !data2.containsKey(key)) {
                builder.append("  - ").append(key).append(": ").append(data.get(key)).append("\n");
            } else if (!data.containsKey(key) && data2.containsKey(key)) {
                builder.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (data.containsKey(key) && data2.containsKey(key)
                    && !Objects.equals(data.get(key), data2.get(key))) {
                builder.append("  - ").append(key).append(": ").append(data.get(key)).append("\n");
                builder.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (data.containsKey(key) && data2.containsKey(key)
                    && Objects.equals(data.get(key), data2.get(key))) {
                builder.append("    ").append(key).append(": ").append(data.get(key)).append("\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }
    public static String makeJson(Map<String, Object> data, Map<String, Object> data2) throws JsonProcessingException {
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
    }
}
