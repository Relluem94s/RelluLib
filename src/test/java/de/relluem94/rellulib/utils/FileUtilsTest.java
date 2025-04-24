package de.relluem94.rellulib.utils;

import de.relluem94.rellulib.Image;
import de.relluem94.rellulib.stores.DoubleStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileUtilsTest {

    private File tempFile;
    private Path symbolicLink;
    private Path targetFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
    }

    @Test
    void checkForUtilityClass(){
        Assertions.assertThrows(IllegalStateException.class, FileUtils::new);
    }

    @Test
    public void testWriteAndReadDoubleStoreTextFile() throws IOException {
        List<DoubleStore<String, String>> list = new ArrayList<>();
        list.add(new DoubleStore<>("key", "value"));
        list.add(new DoubleStore<>("key2", null));

        FileUtils.writeDoubleStoreTextFile(tempFile, new ArrayList<>(list));
        List<DoubleStore<?, ?>> result = FileUtils.readDoubleStoreTextFile(tempFile.getAbsolutePath(), StandardCharsets.UTF_8);

        assertEquals(2, result.size());
        assertEquals("key", result.get(0).getValue());
        assertEquals("value", result.get(0).getSecondValue());
        assertEquals("key2", result.get(1).getValue());
        assertNull(result.get(1).getSecondValue());
    }

    @Test
    public void testWriteTextWithEncoding() throws IOException {
        List<String> content = Collections.singletonList("Test123");
        FileUtils.writeText(tempFile, content, "UTF-8");
        String result = FileUtils.readTextString(tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
        assertEquals("Test123", result.trim());
    }

    @Test
    public void testWriteTextList() throws IOException {
        List<String> content = Collections.singletonList("Line");
        FileUtils.writeText(tempFile, content);
        String result = FileUtils.readTextString(tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
        assertEquals("Line", result.trim());
    }

    @Test
    public void testWriteTextSingleLine() throws IOException {
        FileUtils.writeText(tempFile, "SingleLine", "UTF-8");
        String result = FileUtils.readTextString(tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
        assertEquals("SingleLine", result);
    }

    @Test
    public void testWriteTextLine() throws IOException {
        FileUtils.writeTextLine(tempFile.getAbsolutePath(), "AppendLine");
        String result = FileUtils.readTextString(tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
        assertTrue(result.contains("AppendLine"));
    }

    @Test
    public void testReadText() throws IOException {
        Files.writeString(tempFile.toPath(), "line1\nline2");
        List<String> result = FileUtils.readText(tempFile.getAbsolutePath(), StandardCharsets.UTF_8);
        assertEquals(2, result.size());
        assertEquals("line1", result.get(0));
    }

    @Test
    public void testWriteAndReadImage() throws IOException {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        File imageFile = File.createTempFile("test", ".png");
        imageFile.deleteOnExit();

        FileUtils.writeImage(img, "png", imageFile);
        BufferedImage loaded = FileUtils.readImage(imageFile);
        assertNotNull(loaded);
    }

    @Test
    public void testWriteImageWithWrapper() throws IOException {
        File imageFile = File.createTempFile("test", ".jpg");
        imageFile.deleteOnExit();

        Image mockImage = mock(Image.class);
        Mockito.when(mockImage.getFile()).thenReturn(imageFile);
        Mockito.when(mockImage.getImage()).thenReturn(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));

        try (MockedStatic<ImageIO> imageIO = mockStatic(ImageIO.class)) {
            imageIO.when(() -> ImageIO.write(Mockito.any(), Mockito.anyString(), Mockito.any(OutputStream.class))).thenReturn(true);
            FileUtils.writeImage(mockImage);
            imageIO.verify(() -> ImageIO.write(Mockito.any(), Mockito.anyString(), Mockito.any(OutputStream.class)));
        }
    }

    @Test
    public void testReadImageWithException() {
        File notAnImage = new File("not_exists.png");
        BufferedImage img = FileUtils.readImage(notAnImage);
        assertNull(img);
    }

    @Test
    public void testListFilesWithExtension() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        dir.deleteOnExit();
        File file = new File(dir, "file.test");
        file.createNewFile();
        file.deleteOnExit();

        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), "test");
        assertEquals(1, result.size());
        assertEquals(file.getName(), result.get(0).getName());
    }

    @Test
    public void testListFilesNoFiles() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        dir.deleteOnExit();

        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), "test");
        assertEquals(0, result.size());
    }

    @Test
    public void testListFilesDir() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        dir.deleteOnExit();

        File dir2 = new File(dir, "testDir2.test");
        dir2.mkdir();
        dir2.deleteOnExit();

        File file = new File(dir2, "file.test");
        file.createNewFile();
        file.deleteOnExit();

        File file2 = new File(dir2, "file.not");
        file2.createNewFile();
        file2.deleteOnExit();

        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), "test");
        assertEquals(1, result.size());
        assertEquals(file.getName(), result.get(0).getName());
    }

    @Test
    public void testListFilesDir2() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        dir.deleteOnExit();

        File dir2 = new File(dir, "testDir2.test");
        dir2.mkdir();
        dir2.deleteOnExit();

        File file = new File(dir2, "file.test");
        file.createNewFile();
        file.deleteOnExit();

        File file2 = new File(dir2, "file.not");
        file2.createNewFile();
        file2.deleteOnExit();

        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), new String[]{"test"});
        assertEquals(1, result.size());
        assertEquals(file.getName(), result.get(0).getName());
    }

    @Test
    public void testListFilesWithMultipleExtensions() throws IOException {
        File dir = Files.createTempDirectory("testDirMulti").toFile();
        dir.deleteOnExit();
        File file = new File(dir, "file.test");
        file.createNewFile();
        file.deleteOnExit();

        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), new String[]{"test"});
        assertEquals(1, result.size());
    }

    @Test
    public void testGetFileExtension() throws IOException {
        File file = new File("file.txt");
        assertEquals("txt", FileUtils.getFileExtension(file));
    }

    @Test
    public void testGetFileExtensionWithoutDot() {
        File file = new File("file");
        assertEquals("", FileUtils.getFileExtension(file));
    }

    @Test
    public void testListFilesReturnsEmptyIfListFilesIsNull() {
        File dir = new File("irrelevant") {
            @Override
            public File[] listFiles() {
                return null;
            }
        };

        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), "txt");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testListFilesReturnsEmptyIfListFilesIsNullDueToInvalidPath() {
        File invalidDir = new File("/definitely/invalid/path/that/does/not/exist");

        assertFalse(invalidDir.exists()); // sicherstellen

        List<File> result = FileUtils.listFiles(invalidDir.getAbsolutePath(), "txt");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testListFilesReturnsEmptyIfListFilesIsNullDueToInvalidPath2() {
        File invalidDir = new File("/definitely/invalid/path/that/does/not/exist");

        assertFalse(invalidDir.exists()); // sicherstellen

        List<File> result = FileUtils.listFiles(invalidDir.getAbsolutePath(), new String[]{"txt"});
        assertTrue(result.isEmpty());
    }

    @Test
    public void testListFilesHandlesNonFileNonDirectory() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        dir.deleteOnExit();

        targetFile = Files.createTempFile("target", ".txt");
        symbolicLink = Paths.get(dir.getAbsolutePath(), "target_symlink.txt");
        Files.createSymbolicLink(symbolicLink, targetFile);
        assertTrue(Files.isSymbolicLink(symbolicLink));


        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), "txt");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testListFilesHandlesNonFileNonDirectory2() throws IOException {
        File dir = Files.createTempDirectory("testDir").toFile();
        dir.deleteOnExit();

        targetFile = Files.createTempFile("target", ".txt");
        symbolicLink = Paths.get(dir.getAbsolutePath(), "target_symlink.txt");
        Files.createSymbolicLink(symbolicLink, targetFile);
        assertTrue(Files.isSymbolicLink(symbolicLink));

        List<File> result = FileUtils.listFiles(dir.getAbsolutePath(), new String[]{"txt"});
        assertTrue(result.isEmpty());
    }

    @Test
    public void testWriteTextLineHandlesIOException() throws IOException {
        try (MockedStatic<LogUtils> mockedLogUtils = mockStatic(LogUtils.class)) {
            FileUtils.writeTextLine("some/filepath.txt", "Hello World");
            mockedLogUtils.verify(() -> LogUtils.error(Mockito.anyString()));
        }
    }


}