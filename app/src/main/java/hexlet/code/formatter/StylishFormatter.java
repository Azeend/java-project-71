package hexlet.code.formatter;

import java.util.Map;
import java.util.List;

public class StylishFormatter {
    public static String makeStylish(List<Map<String, Object>> differences) {
        var builder = new StringBuilder("{\n");
        for (Map<String, Object> diff : differences) {
            switch (diff.get("action").toString()) {
                case "removed" -> builder.append("  - ").append(diff.get("key")).append(": ")
                        .append(diff.get("oldValue")).append("\n");
                case "added" -> builder.append("  + ").append(diff.get("key")).append(": ")
                        .append(diff.get("newValue")).append("\n");
                case "unchanged" -> builder.append("    ").append(diff.get("key")).append(": ")
                        .append(diff.get("oldValue")).append("\n");
                default -> {
                    builder.append("  - ").append(diff.get("key")).append(": ")
                            .append(diff.get("oldValue")).append("\n");
                    builder.append("  + ").append(diff.get("key")).append(": ")
                            .append(diff.get("newValue")).append("\n");
                }
            }
        }
        builder.append("}");
        return builder.toString();
    }
    /*public static String makeStylish(Map<String, Object> data, Map<String, Object> data2) {
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
    }*/
}
