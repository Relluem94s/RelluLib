package de.relluem94.rellulib.windows;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import de.relluem94.rellulib.color.Color4i;
import de.relluem94.rellulib.utils.LogUtils;

public class SplashScreen {

    private JProgressBar progressBar;
    private boolean disposeAfterLoading = true;
    private boolean showProgressBar = true;
    private JFrame frame;

    public void init(BufferedImage logo, String title, String text, Color4i color) {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(425, 155);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(logo);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        Image newImage = logo.getScaledInstance(375, 75, java.awt.Image.SCALE_SMOOTH);
        frame.getContentPane().setBackground(new Color(color.r, color.g, color.b, color.a));
        frame.setLayout(null);
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GRAY);
        progressBar.setBackground(Color.LIGHT_GRAY);
        frame.add(progressBar);
        progressBar.setBounds(15, 125, 393, 20);

        JLabel label = new JLabel();
        label.setText(text);
        label.setForeground(Color.GRAY);
        frame.add(label);
        label.setBounds(15, 105, 384, 20);

        JLabel imgLabel = new JLabel(new ImageIcon(newImage));
        frame.add(imgLabel);
        imgLabel.setBounds(10, 10, 404, 75);
    }

    public void setDisposeAfterLoading(boolean dispose) {
        disposeAfterLoading = dispose;
    }

    public void showProgressBar(boolean progBar) {
        showProgressBar = progBar;
    }

    public void start() {
        Thread t;
        t = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i <= 100) {
                    if (showProgressBar) {
                        progressBar.setValue(i);
                    }
                    try {
                        sleep(90);
                    } catch (InterruptedException e) {
                        LogUtils.error(e.getMessage());
                    }
                    i++;
                }
                if (disposeAfterLoading) {
                    frame.dispose();
                }

                new SplashScreen().onFinish();
            }
        };
        t.start();
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public void onFinish() {
        //Tests t = new Tests();		//Only Temp weil Methode nicht geht.. 
        //t.onFinish();
    }
}