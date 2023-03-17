package hexlet.code.formatter;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String getFormattedDifferences(List<Map<String, Object>> differences, String format)
            throws Exception {
        switch (format) {
            case "stylish" -> {
                return StylishFormatter.makeStylish(differences);
            }
            case "plain" -> {
                return PlainFormatter.makePlain(differences);
            }
            case "json" -> {
                return JsonFormatter.makeJson(differences);
            }
            default -> throw new Exception("Formatting error");
        }
    }
}
