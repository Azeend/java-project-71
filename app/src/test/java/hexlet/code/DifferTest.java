package hexlet.code;

import org.junit.jupiter.api.Test;
import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    private final String  expected = """
    {
    - age: 21
    + age: 20
    + children: true
      name: Mark
    - student: true
    }   """;


    @Test
    public void genereteTest() throws Exception {
        String actual = generate("src/test/resources/fixtures/TestFile1.json",
                "src/test/resources/fixtures/TestFile2.json");
        assertEquals(expected, actual);
    }
}
