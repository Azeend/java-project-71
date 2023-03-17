package hexlet.code;

import org.junit.jupiter.api.Test;
import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    private final String expectedStylish = """
            {
              - age: 21
              + age: 20
              + children: true
                name: Mark
              - student: true
            }""";
    private final String expectedPlain = """
            Property 'age' was updated. From 21 to 20
            Property 'children' was added with value: true
            Property 'student' was removed""";
    private final String expectedJson = """
            [ {
              "key" : "age",
              "oldValue" : 21,
              "newValue" : 20,
              "action" : "updated"
            }, {
              "key" : "children",
              "newValue" : true,
              "action" : "added"
            }, {
              "key" : "name",
              "oldValue" : "Mark",
              "action" : "unchanged"
            }, {
              "key" : "student",
              "oldValue" : true,
              "action" : "removed"
            } ]""";
    @Test
    public void genereteTest() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFile1.json",
                "src/test/resources/fixtures/TestFile2.json", "stylish");
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void generateTest2() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFile1.yml",
                "src/test/resources/fixtures/TestFile2.yml", "stylish");
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void generateTest3() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFileJson.json",
                "src/test/resources/fixtures/TestFileJson2.json", "stylish");
        assertEquals(expectedStylish, actual);
    }
    @Test
    public void generateTest4() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFile1.yml",
                "src/test/resources/fixtures/TestFile2.yml", "plain");
        assertEquals(expectedPlain, actual);
    }
    @Test
    public void generateTest5() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFileJson.json",
                "src/test/resources/fixtures/TestFileJson2.json", "plain");
        assertEquals(expectedPlain, actual);
    }
    @Test
    public void generateTest6() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFileJson.json",
                "src/test/resources/fixtures/TestFileJson2.json", "json");
        assertEquals(expectedJson, actual);
    }
    @Test
    public void generateTest7() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFile1.yml",
                "src/test/resources/fixtures/TestFile2.yml", "json");
        assertEquals(expectedJson, actual);
    }
}
