package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable {
    @Parameters(index = "0", description = "path to first file")
    private String file1;
    @Parameters(index = "1", description = "path to second file")
    private String file2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;
    @Override
    public final Object call() throws Exception {
        System.out.println(Differ.generate(file1, file2));
        return null;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
