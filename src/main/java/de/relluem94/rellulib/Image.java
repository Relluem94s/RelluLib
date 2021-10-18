package de.relluem94.rellulib;

import java.awt.image.BufferedImage;
import java.io.File;

import de.relluem94.rellulib.utils.FileUtils;

public class Image {

    private final BufferedImage img;
    private final File file;

    public Image(File file) {
        this.file = file;
        this.img = FileUtils.readImage(file);
    }

    public Image(BufferedImage img, File file) {
        this.img = img;
        this.file = file;
    }

    public BufferedImage getImage() {
        return img;
    }

    public File getFile() {
        return file;
    }

    public void writeImage() {
        FileUtils.writeImage(this);
    }

}
