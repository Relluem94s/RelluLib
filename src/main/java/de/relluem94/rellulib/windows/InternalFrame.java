package de.relluem94.rellulib.windows;

import java.awt.Container;
import java.awt.Dimension;
import java.io.Serial;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Dimension minSize;
    private final Dimension maxSize;
    private final Dimension prefSize;
    private final Dimension size;
    private final boolean iconifyAble;
    private final boolean isResizable;
    private final boolean closeable;
    private final boolean dispose;
    private final boolean snapper;

    private final JInternalFrame frame;

    public InternalFrame(String title, Dimension minSize, Dimension maxSize, Dimension prefSize, Dimension size,
            boolean iconifyAble, boolean isResizable, boolean closeable, boolean dispose, boolean snapper) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.prefSize = prefSize;
        this.size = size;
        this.iconifyAble = iconifyAble;
        this.isResizable = isResizable;
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
        frame.setIconifiable(iconifyAble);
        frame.setResizable(isResizable);
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

    @Override
    public Dimension getSize() {
        return size; //TODO is this needed?
    }
}
