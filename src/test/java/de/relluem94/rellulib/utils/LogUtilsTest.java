package de.relluem94.rellulib.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LogUtilsTest {

    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        LogUtils.setOther(true);
        LogUtils.setError(true);
        LogUtils.setInfo(true);
        LogUtils.setWarning(true);
        LogUtils.setLog2File(false);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testLog() {
        LogUtils.log("testLog");
        assertTrue(outContent.toString().contains("testLog"));
    }

    @Test
    void testLogInLine() {
        LogUtils.logInLine("inline");
        assertTrue(outContent.toString().contains("inline"));
    }

    @Test
    void testDebug() {
        LogUtils.debug("debug", true);
        assertTrue(outContent.toString().contains("debug"));
    }

    @Test
    void testDebugShouldNot() {
        LogUtils.debug("debug", false);
        assertFalse(outContent.toString().contains("debug"));
    }

    @Test
    void testDebugInLine() {
        LogUtils.debugInLine("debugInLine", true);
        assertTrue(outContent.toString().contains("debugInLine"));
    }

    @Test
    void testDebugInLineShouldNot() {
        LogUtils.debugInLine("debugInLine", false);
        assertFalse(outContent.toString().contains("debugInLine"));
    }

    @Test
    void testError() {
        LogUtils.error("errorMessage");
        assertTrue(outContent.toString().contains("[ERROR]"));
    }

    @Test
    void testInfo() {
        LogUtils.info("infoMessage");
        assertTrue(outContent.toString().contains("[INFO]"));
    }

    @Test
    void testTest() {
        LogUtils.test("testMessage");
        assertTrue(outContent.toString().contains("[TEST]"));
    }

    @Test
    void testWarning() {
        LogUtils.warning("warnMessage");
        assertTrue(outContent.toString().contains("[WARNING]"));
    }

    @Test
    void testLine() {
        LogUtils.line("lineMessage");
        assertTrue(outContent.toString().contains("lineMessage"));
    }

    @Test
    void testList() {
        LogUtils.list(Arrays.asList("A", "B"));
        assertTrue(outContent.toString().contains("A"));
        assertTrue(outContent.toString().contains("B"));
    }

    @Test
    void testIntArray() {
        LogUtils.intArray(new Integer[]{1, 2});
        assertTrue(outContent.toString().contains("1"));
        assertTrue(outContent.toString().contains("2"));
    }

    @Test
    void testStringArray() {
        LogUtils.stringArray(new String[]{"X", "Y"});
        assertTrue(outContent.toString().contains("X"));
        assertTrue(outContent.toString().contains("Y"));
    }

    @SuppressWarnings("ConstantValue")
    @Test
    void testSettersAndGetters() {
        assertTrue(LogUtils.getInfo());
        assertTrue(LogUtils.getWarning());
        assertTrue(LogUtils.getError());
        assertTrue(LogUtils.getOther());

        LogUtils.setInfo(false);
        LogUtils.setWarning(false);
        LogUtils.setError(false);
        LogUtils.setOther(false);
        LogUtils.setLog2File(true);
        LogUtils.setLogFilePath("test");

        assertFalse(LogUtils.getInfo());
        assertFalse(LogUtils.getWarning());
        assertFalse(LogUtils.getError());
        assertFalse(LogUtils.getOther());
        assertTrue(LogUtils.getLog2File());
        assertEquals("test", LogUtils.getLogFilePath());
    }

    @Test
    void checkForUtilityClass(){
        Assertions.assertThrows(IllegalStateException.class, LogUtils::new);
    }

    @Test
    void testLog_disabled() {
        LogUtils.setOther(false);
        LogUtils.log("testLog");
        assertFalse(outContent.toString().contains("testLog"));
    }

    @Test
    void testLogInLine_disabled() {
        LogUtils.setOther(false);
        LogUtils.logInLine("inline");
        assertFalse(outContent.toString().contains("inline"));
    }

    @Test
    void testDebug_disabled() {
        LogUtils.setOther(false);
        LogUtils.debug("debug", true);
        assertFalse(outContent.toString().contains("debug"));
    }

    @Test
    void testDebugInLine_disabled() {
        LogUtils.setOther(false);
        LogUtils.debugInLine("debugInLine", true);
        assertFalse(outContent.toString().contains("debugInLine"));
    }

    @Test
    void testError_disabled() {
        LogUtils.setError(false);
        LogUtils.error("errorMessage");
        assertFalse(outContent.toString().contains("[ERROR]"));
    }

    @Test
    void testInfo_disabled() {
        LogUtils.setInfo(false);
        LogUtils.info("infoMessage");
        assertFalse(outContent.toString().contains("[INFO]"));
    }

    @Test
    void testTest_disabled() {
        LogUtils.setOther(false);
        LogUtils.log("testMessage");
        assertFalse(outContent.toString().contains("[TEST]"));
    }

    @Test
    void testWarning_disabled() {
        LogUtils.setWarning(false);
        LogUtils.warning("warnMessage");
        assertFalse(outContent.toString().contains("[WARNING]"));
    }

    @Test
    void testLine_disabled() {
        LogUtils.setOther(false);
        LogUtils.line("lineMessage");
        assertFalse(outContent.toString().contains("lineMessage"));
    }

    @Test
    void testList_disabled() {
        LogUtils.setOther(false);
        LogUtils.list(Arrays.asList("A", "B"));
        assertFalse(outContent.toString().contains("A"));
        assertFalse(outContent.toString().contains("B"));
    }

    @Test
    void testIntArray_disabled() {
        LogUtils.setOther(false);
        LogUtils.intArray(new Integer[]{1, 2});
        assertFalse(outContent.toString().contains("1"));
        assertFalse(outContent.toString().contains("2"));
    }

    @Test
    void testStringArray_disabled() {
        LogUtils.setOther(false);
        LogUtils.stringArray(new String[]{"X", "Y"});
        assertFalse(outContent.toString().contains("X"));
        assertFalse(outContent.toString().contains("Y"));
    }

    @Test
    void testWriteToLogFile() throws IOException {
        File tempDir = Files.createTempDirectory("testDir").toFile();
        tempDir.deleteOnExit();

        LogUtils.setLogFilePath(tempDir.getAbsolutePath());
        LogUtils.setLog2File(true);
        LogUtils.setOther(true);



        String message = "FileLogTestMessage";
        String message2 = "InLine";
        LogUtils.line(message2);
        LogUtils.log(message);


        File logFile = new File(tempDir, "RelluLib.log");
        assertTrue(logFile.exists());


        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String content = reader.readLine();
            Assertions.assertEquals(message2 + message, content);
            assertTrue(content.contains(message));
            assertTrue(content.contains(message2));
        }

        assertTrue(logFile.delete());
        assertTrue(tempDir.delete());

    }
}
