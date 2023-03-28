package hexlet.code;


import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Objects;
public class TreeDiffer {
    public static List<Map<String, Object>> getDifferences(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> differences =  new ArrayList<>();
        Set<String> keySet = new TreeSet<>();
        keySet.addAll(data1.keySet());
        keySet.addAll(data2.keySet());
        for (String key: keySet) {
            Map<String, Object> diffMap = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                diffMap.put("key", key);
                diffMap.put("oldValue", data1.get(key));
                diffMap.put("action", "removed");
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                diffMap.put("key", key);
                diffMap.put("newValue", data2.get(key));
                diffMap.put("action", "added");
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                diffMap.put("key", key);
                diffMap.put("oldValue", data1.get(key));
                diffMap.put("newValue", data2.get(key));
                diffMap.put("action", "updated");
            } else {
                diffMap.put("key", key);
                diffMap.put("oldValue", data1.get(key));
                diffMap.put("action", "unchanged");
            }
            differences.add(diffMap);
        }
        return differences;
    }
}
