package de.relluem94.rellulib.windows;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private Dimension minSize;
    private Dimension maxSize;
    private Dimension prefSize;
    private Dimension size;
    private boolean iconifiable;
    private boolean resizable;
    private boolean closeable;
    private boolean dispose;
    private boolean snapper;

    private JInternalFrame frame;

    public InternalFrame(String title, Dimension minSize, Dimension maxSize, Dimension prefSize, Dimension size,
            boolean iconifiable, boolean resizable, boolean closeable, boolean dispose, boolean snapper) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.prefSize = prefSize;
        this.size = size;
        this.iconifiable = iconifiable;
        this.resizable = resizable;
        this.closeable = closeable;
        this.dispose = dispose;
        this.snapper = snapper;
        this.frame = new JInternalFrame(title);
    }

    public void disposeFrame() {
        frame.dispose();
    }

    public JInternalFrame makeFrame(Container pane) {
        frame.setSize(size);
        frame.setPreferredSize(prefSize);
        frame.setMaximumSize(maxSize);
        frame.setMinimumSize(minSize);
        frame.add(pane);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setIconifiable(iconifiable);
        frame.setResizable(resizable);
        frame.setClosable(closeable);
        frame.setVisible(true);
        if (snapper) {
            frame.addComponentListener(new WindowSnapper());
        }

        if (dispose) {
            frame.dispose();
        } else {
            frame.show();
        }

        return frame;
    }

    public Dimension getSize() {
        return size;
    }
}
