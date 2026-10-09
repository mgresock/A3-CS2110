package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class End2EndTest {

    /**
     * The original `System.out`.
     */
    static PrintStream systemOut;

    /**
     * Replacement for `System.out` during test execution.
     */
    static PrintStream out;
    static ByteArrayOutputStream outBytes;

    @BeforeEach
    void captureOutput() {
        outBytes = new ByteArrayOutputStream();
        out = new PrintStream(outBytes);
        systemOut = System.out;
        System.setOut(out);
    }

    @AfterEach
    void restoreOutput() {
        out.close();
        System.setOut(systemOut);
    }

    /**
     * Checks that each line produced by these two scanners are the same, which avoids platform
     * specific line ending anomalies.
     */
    void assertSameLines(Scanner expectedSc, Scanner actualSc) {
        int line = 1;
        while (expectedSc.hasNextLine()) {
            String expectedLine = expectedSc.nextLine();
            assertTrue(actualSc.hasNextLine(), "Simulation output has fewer lines than expected.");
            String actualLine = actualSc.nextLine();
            assertEquals(expectedLine, actualLine, "Error on line " + line++);
        }
        assertFalse(actualSc.hasNextLine(), "Simulation output has more lines than expected.");
    }

    void runE2ETest(String name) throws FileNotFoundException {
        File gameInputFile = new File(name + "_input.txt");
        File expectedFile = new File(name + "_expected_output.txt");

        Scanner sc = new Scanner(gameInputFile);
        // use a game engine with a specific random seed for reproducible results
        GameEngine engine = new GameEngine(sc, true, new Random(123456L));
        engine.initializeGame();
        engine.runMainGameLoop();

        out.flush();
        String actualOutput = outBytes.toString();
        Scanner actualSc = new Scanner(actualOutput);
        Scanner expectedSc = new Scanner(expectedFile);
        assertSameLines(expectedSc, actualSc);
    }

    @DisplayName(
            "WHEN the commands in 'sample_game_input.txt' are executed, the simulator produces "
                    + "the correct output as specified in `sample_game_expected_output.txt")
    @Test
    void runEnd2EndSample() throws FileNotFoundException {
       runE2ETest("sample_game");
    }
}
