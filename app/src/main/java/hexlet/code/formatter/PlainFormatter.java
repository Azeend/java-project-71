package hexlet.code.formatter;

import java.util.Map;
import java.util.List;

public class PlainFormatter {
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
    public static String makePlain(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diffs : differences) {
            switch (diffs.get("action").toString()) {
                case "removed" -> {
                    result.append("Property ").append("'")
                            .append(diffs.get("key")).append("'").append(" was removed").append("\n");
                }
                case "added" -> {
                    result.append("Property ").append(checkValue(diffs.get("key")))
                            .append(" was added with value: ")
                            .append(checkValue(diffs.get("newValue")))
                            .append("\n");
                }
                case "updated" -> {
                    result.append("Property ").append(checkValue(diffs.get("key")))
                            .append(" was updated. From ")
                            .append(checkValue(diffs.get("oldValue"))).append(" to ")
                            .append(checkValue(diffs.get("newValue")))
                            .append("\n");
                }
                default -> {
                    result.append("");
                }
            }
        }
        return result.toString().trim();
    }
    /*public static String makePlain(Map<String, Object> data, Map<String, Object> data2) {
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
    }*/
}
