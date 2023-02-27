package hexlet.code;
import hexlet.code.formatter.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String pathfile1, String pathfile2) throws Exception {
        return generate(pathfile1, pathfile2, "stylish");
    }
    public static String generate(String pathfile1, String pathfile2, String format) throws Exception {
        Path path = Paths.get(pathfile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(pathfile2).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        // Читаем файл
        String content = Files.readString(path);
        String content2 = Files.readString(path2);
        Map<String, Object> data = Parser.parserJson(content, pathfile1);
        Map<String, Object> data2 = Parser.parserJson(content2, pathfile2);
        switch (format) {
            case "stylish" -> {
                return Formatter.makeStylish(data, data2);
            }
            case "plain" -> {
                return Formatter.makePlain(data, data2);
            }
            case "json" -> {
                return Formatter.makeJson(data, data2);
            }
            default -> throw new Exception("Formatting error");
        }
    }
}
