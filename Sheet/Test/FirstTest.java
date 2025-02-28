import abschluss.Application;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class FirstTest {

    @Test
    public void test1() {

        String simulatedInput;
        try {
            simulatedInput = Files.readString(Path.of("Sheet/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String expectedOutput;
        try {
            expectedOutput = Files.readString(Path.of("Sheet/output"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Application.main(new String[] {"Sheet/config.txt"});

        assertEquals(expectedOutput, outputStream.toString());
    }
}
