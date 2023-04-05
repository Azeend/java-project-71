package hexlet.code;


import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.LinkedHashMap;

public class TreeDiffer {
    static final String ADDED = "added";
    static final String REMOVED = "removed";
    static final String UPDATED = "updated";
    static final String UNCHANGED = "unchanged";

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
                diffMap.put("action", REMOVED);
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                diffMap.put("key", key);
                diffMap.put("newValue", data2.get(key));
                diffMap.put("action", ADDED);
            } else if (!isEqual(data1.get(key), data2.get(key))) {
                diffMap.put("key", key);
                diffMap.put("oldValue", data1.get(key));
                diffMap.put("newValue", data2.get(key));
                diffMap.put("action", UPDATED);
            } else {
                diffMap.put("key", key);
                diffMap.put("oldValue", data1.get(key));
                diffMap.put("action", UNCHANGED);
            }
            differences.add(diffMap);
        }
        return differences;
    }
    private static Boolean isEqual(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == value2;
        }

        return value1.equals(value2);
    }
}
