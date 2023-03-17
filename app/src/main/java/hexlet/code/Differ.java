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
        Path path = Paths.get(pathfile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(pathfile2).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        // Читаем файл
        String content = Files.readString(path);
        String content2 = Files.readString(path2);
        // Получаем формат
        String fileFormat1 = pathfile1.substring(pathfile1.lastIndexOf(".") + 1);
        String fileFormat2 = pathfile2.substring(pathfile2.lastIndexOf(".") + 1);
        // Получаем содержимое
        Map<String, Object> data1 = Parser.getData(content, fileFormat1);
        Map<String, Object> data2 = Parser.getData(content2, fileFormat2);
        // Получаем различия
        List<Map<String, Object>> differences = ListOfDifferences.getDifferences(data1, data2);
        return Formatter.getFormattedDifferences(differences, format);
    }
}
