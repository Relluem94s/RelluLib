package de.relluem94.rellulib.windows;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowSnapper extends ComponentAdapter {

    private boolean locked = false;

    public void componentMoved(ComponentEvent evt) {
        if (locked) {
            return;
        }

        Dimension size = evt.getComponent().getParent().getSize();
        int nx = evt.getComponent().getX();
        int ny = evt.getComponent().getY();
        // top
        int sd = 10;
        if (ny < sd) {
            ny = 0;
            evt.getComponent().repaint();
        }
        // left
        if (nx < sd) {
            nx = 0;
            evt.getComponent().repaint();
        }

        // right
        if (nx > size.getWidth() - evt.getComponent().getWidth() - sd) {
            nx = (int) size.getWidth() - evt.getComponent().getWidth();
            evt.getComponent().repaint();
        }
        // bottom
        if (ny > size.getHeight() - evt.getComponent().getHeight() - sd) {
            ny = (int) size.getHeight() - evt.getComponent().getHeight();
            evt.getComponent().repaint();
        }

        locked = true;
        evt.getComponent().setLocation(nx, ny);
        evt.getComponent().getParent().repaint();
        locked = false;

    }

}
