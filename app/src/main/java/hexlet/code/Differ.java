package hexlet.code;
import hexlet.code.formatter.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String pathfile1, String pathfile2) throws Exception {
        return generate(pathfile1, pathfile2, "stylish");
    }
    public static String generate(String pathfile1, String pathfile2, String format) throws Exception {
        // Читаем файл
        String content = getData(pathfile1);
        String content2 = getData(pathfile2);
        // Получаем формат
        String fileFormat1 = getFormat(pathfile1);
        String fileFormat2 = getFormat(pathfile2);
        // Получаем содержимое
        Map<String, Object> data1 = Parser.getDataAsMap(content, fileFormat1);
        Map<String, Object> data2 = Parser.getDataAsMap(content2, fileFormat2);
        // Получаем различия
        List<Map<String, Object>> differences = TreeDiffer.getDifferences(data1, data2);
        return Formatter.format(differences, format);
    }
    private static String getData(String pathfile) throws Exception {
        Path path = Paths.get(pathfile).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String content = Files.readString(path);
        return content;
    }
    private static String getFormat(String pathfile) {
        String fileFormat = pathfile.substring(pathfile.lastIndexOf(".") + 1);
        return fileFormat;
    }
}
