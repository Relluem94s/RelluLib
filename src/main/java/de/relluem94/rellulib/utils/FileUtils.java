package de.relluem94.rellulib.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import de.relluem94.rellulib.Image;
import de.relluem94.rellulib.stores.DoubleStore;

public class FileUtils {

    private FileUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final OpenOption openOptions[] = {StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

    public static void writeDoubleStoreTextFile(File file, List<DoubleStore> content) throws IOException {
        List<String> str = new ArrayList<>();

        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).getSecondValue() == null) {
                str.add(content.get(i).getValue().toString().replace("\n", "").replace("\r", ""));
            } else {
                str.add(content.get(i).getValue().toString().replace("\n", "").replace("\r", "") + " = " + content.get(i).getSecondValue().toString().replace("\n", "").replace("\r", ""));
            }
        }

        writeText(file, str);
    }

    public static List<DoubleStore> readDoubleStoreTextFile(String path, Charset encoding) throws IOException {

        List<String> content = readText(path, encoding);

        List<DoubleStore> out = new ArrayList<>();

        String[] temp;

        for (int i = 0; i < content.size(); i++) {
            temp = content.get(i).split(" = ");
            if (temp.length == 2) {
                out.add(new DoubleStore(temp[0].replace("\n", "").replace("\r", ""), temp[1].replace("\n", "").replace("\r", "")));
            } else {
                out.add(new DoubleStore(temp[0].replace("\n", "").replace("\r", ""), null));
            }
        }

        return out;
    }

    public static void writeText(File file, List<String> content) throws IOException {
        writeText(file, content, "UTF-8");
    }

    public static void writeText(File file, List<String> content, String encoding) throws IOException {
        Path file2 = Paths.get(file.getPath());
        Files.write(file2, content, Charset.forName(encoding));
    }

    public static void writeText(File file, String content, String encoding) throws IOException {
        Path file2 = Paths.get(file.getPath());
        Files.write(file2, content.getBytes(Charset.forName(encoding)), openOptions);
    }

    public static void writeTextLine(String filepath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true))) {
            bw.append(content);
            bw.newLine();
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }
    }

    public static List<String> readText(String path, Charset encoding) throws IOException {
        String output = readTextString(path, encoding);
        List<String> out = new ArrayList<>();
        out.addAll(Arrays.asList(output.split(System.lineSeparator())));
        return out;
    }

    public static String readTextString(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        String output = new String(encoded, encoding);
        return output;
    }

    public static void writeImage(BufferedImage input, String format, File output) throws IOException {
        ImageIO.write(input, format, output);
    }

    /**
     * BufferedOutputStream (Much Faster)
     *
     * @param image
     */
    public static void writeImage(Image image) {
        try (BufferedOutputStream imageOutputStream = new BufferedOutputStream(new FileOutputStream(image.getFile()))) {
            String name = image.getFile().getName();
            String format = name.substring(name.lastIndexOf("."), name.length()).replace(".", "").toUpperCase();

            ImageIO.write(image.getImage(), format, imageOutputStream);

            imageOutputStream.flush();
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
        }
    }

    public static BufferedImage readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }

    public static List<File> listFiles(String directoryName, String[] types) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<>();

        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                for (String typ : types) {
                    if (getFileExtension(file).equals(typ)) {
                        resultList.add(file);
                    }
                }
            } else if (file.isDirectory()) {
                resultList.addAll(listFiles(file.getAbsolutePath(), types));
            }
        }
        return resultList;
    }

    public static List<File> listFiles(String directoryName, String type) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<>();

        File[] fList = directory.listFiles();

        for (File file : fList) {

            if (file.isFile()) {
                if (getFileExtension(file).equals(type)) {
                    resultList.add(file);
                }
            } else if (file.isDirectory()) {
                resultList.addAll(listFiles(file.getAbsolutePath(), type));
            }
        }
        return resultList;
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf).replace(".", "");
    }

}
