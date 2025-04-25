package de.relluem94.rellulib.windows;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.Serial;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Dimension minSize;
    private final Dimension maxSize;
    private final Dimension prefSize;
    private final Dimension size;
    private final boolean isResizable;
    private final boolean dispose;
    private final boolean snapper;

    private final JFrame frame;
    private final JPanel p;

    public Frame(String title, Dimension minSize, Dimension maxSize, Dimension prefSize, Dimension size,
            boolean isResizable, boolean dispose, boolean snapper) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.prefSize = prefSize;
        this.size = size;
        this.isResizable = isResizable;
        this.dispose = dispose;
        this.snapper = snapper;
        this.frame = new JFrame(title);
        this.p = new JPanel();
        this.p.setBounds(0, 0, size.width, size.height);
    }

    public void addToPanel(Component c) {
        p.add(c);
    }

    public void disposeFrame() {
        frame.dispose();
    }

    public JFrame makeFrame(Container pane) {
        frame.setSize(size);
        frame.setPreferredSize(prefSize);
        frame.setMaximumSize(maxSize);
        frame.setMinimumSize(minSize);
        frame.add(pane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(isResizable);
        frame.setVisible(true);
        if (snapper) {
            frame.addComponentListener(new WindowSnapper());
        }

        frame.setVisible(!dispose);

        return frame;
    }

    @Override
    public Dimension getSize() {
        return size; //TODO is this needed?
    }

    public JPanel getPanel() {
        return p;
    }
}
