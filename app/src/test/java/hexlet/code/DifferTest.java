package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    private final Path pathStylish =
            Paths.get("src/test/resources/fixtures/ExpectedStylish.yml").toAbsolutePath().normalize();
    private final Path pathPlain =
            Paths.get("src/test/resources/fixtures/ExpectedPlain").toAbsolutePath().normalize();
    private final Path pathJson =
            Paths.get("src/test/resources/fixtures/ExpectedJson.json").toAbsolutePath().normalize();
    @Test
    public void generateTest() throws Exception {
        String expected = Files.readString(pathStylish);
        String actual = generate("src/test/resources/fixtures/TestFile1.json",
                "src/test/resources/fixtures/TestFile2.json");
        assertEquals(expected, actual);
    }
    @Test
    public void generateTest2() throws Exception {
        String expected = Files.readString(pathStylish);
        String actual = generate("src/test/resources/fixtures/TestFile1.yml",
                "src/test/resources/fixtures/TestFile2.yml");
        assertEquals(expected, actual);
    }
    @Test
    public void generateTestStylish() throws Exception {
        String expected = Files.readString(pathStylish);
        String actual = generate("src/test/resources/fixtures/TestFile1.yml",
                "src/test/resources/fixtures/TestFile2.yml", "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void generateTestPlain() throws Exception {
        String expected = Files.readString(pathPlain);
        String actual = generate("src/test/resources/fixtures/TestFile1.yml",
                "src/test/resources/fixtures/TestFile2.yml", "plain");
        assertEquals(expected, actual);
    }
    @Test
    public void generateTestJson() throws Exception {
        String expected = Files.readString(pathJson);
        String actual = generate("src/test/resources/fixtures/TestFile1.json",
                "src/test/resources/fixtures/TestFile2.json", "json");
        assertEquals(expected, actual);
    }
}
