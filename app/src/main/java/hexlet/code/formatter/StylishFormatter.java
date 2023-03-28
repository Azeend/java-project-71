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
}
