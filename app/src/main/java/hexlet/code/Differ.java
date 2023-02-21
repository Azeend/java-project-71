package hexlet.code;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String pathfile1, String pathfile2) throws Exception {
        Path path = Paths.get(pathfile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(pathfile2).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        // Читаем файл
        String content = Files.readString(path);
        String content2 = Files.readString(path2);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        Map<String, Object> data2 = objectMapper.readValue(content2, new TypeReference<Map<String, Object>>() { });

        //Map<String, Object> result = new LinkedHashMap<>();
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(data.keySet());
        keySet.addAll(data2.keySet());
        var builder = new StringBuilder("{\n");
        for (String key: keySet) {
            if (data.containsKey(key) && !data2.containsKey(key)) {
                //result.put("- " + key, data.get(key));
                builder.append("- ").append(key).append(": ").append(data.get(key)).append("\n");
            } else if (!data.containsKey(key) && data2.containsKey(key)) {
                //result.put("+ " + key, data2.get(key));
                builder.append("+ ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (data.containsKey(key) && data2.containsKey(key) && !data.get(key).equals(data2.get(key))) {
                //result.put("- " + key, data.get(key));
                builder.append("- ").append(key).append(": ").append(data.get(key)).append("\n");
                //result.put("+ " + key, data2.get(key));
                builder.append("+ ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (data.containsKey(key) && data2.containsKey(key) && data.get(key).equals(data2.get(key))) {
                //result.put("  " + key, data.get(key));
                builder.append("  ").append(key).append(": ").append(data.get(key)).append("\n");
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
