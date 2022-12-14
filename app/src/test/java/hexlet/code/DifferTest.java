package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static hexlet.code.Differ.generate;
import static hexlet.code.Differ.getDataFormat;
import static hexlet.code.formatters.Plain.correctPlainValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DifferTest {

    private static final int TEST_INT = 5;
    private static final int[] TEST_ARRAY = new int[]{1, 2, 3};

    @Test
    public void differTestJsonToStylish() throws Exception {
        String actualStylish = generate("src/test/resources/file1Test.json", "src/test/resources/file2Test.json");
        String expectedStylish = Files.readString(Path.of("src/test/resources/jsonToStylish.txt"));
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
    public void differTestJsonToPlain() throws Exception {
        String actualPlain = generate("src/test/resources/file1Test.json",
                "src/test/resources/file2Test.json",
                "plain");
        String expectedPlain = Files.readString(Path.of("src/test/resources/jsonToPlain.txt"));
        assertEquals(expectedPlain, actualPlain);
    }

    @Test
    public void differTestJsonToJson() throws Exception {
        String actualJson = generate("src/test/resources/file1Test.json",
                "src/test/resources/file2Test.json",
                "json");
        String expectedJson = Files.readString(Path.of("src/test/resources/jsonToJson.txt"));
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void differTestYamlStylish() throws Exception {
        String actualStylish = generate("src/test/resources/file1Test.yaml", "src/test/resources/file2Test.yaml");
        String expectedStylish = Files.readString(Path.of("src/test/resources/yamlToStylish.txt"));
        assertEquals(expectedStylish, actualStylish);
    }

    @Test
    public void differTestYamlPlain() throws Exception {
        String actualPlain = generate("src/test/resources/file1Test.yaml",
                "src/test/resources/file2Test.yaml",
                "plain");
        String expectedYaml = Files.readString(Path.of("src/test/resources/yamlToPlain.txt"));
        assertEquals(expectedYaml, actualPlain);
    }

    @Test
    public void differTestYamlToJson() throws Exception {
        String actualJson = generate("src/test/resources/file1Test.yaml", "src/test/resources/file2Test.yaml", "json");
        String expectedJson = Files.readString(Path.of("src/test/resources/yamlToJson.txt"));
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void getDataFormatTest() {
        String actualJson = getDataFormat("src/main/resources/file1.json");
        String actualYaml = getDataFormat("src/main/resources/file1.yaml");
        String actualYml = getDataFormat("src/main/resources/file1.yml");
        String expectedJson = "JSON";
        String expectedYaml = "YAML";
        String expectedYml = "YML";
        assertEquals(expectedJson, actualJson);
        assertEquals(expectedYaml, actualYaml);
        assertEquals(expectedYml, actualYml);
    }

    @Test
    public void correctPlainValueTest() {
        assertNull(correctPlainValue(null));
        assertEquals("'test'", correctPlainValue("test"));
        assertEquals("5", correctPlainValue(TEST_INT));
        assertEquals("false", correctPlainValue(false));
        assertEquals("[complex value]", correctPlainValue(TEST_ARRAY));
    }
}
