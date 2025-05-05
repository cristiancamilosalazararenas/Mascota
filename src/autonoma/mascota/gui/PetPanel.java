package autonoma.mascota.gui;

import autonoma.mascota.elements.Snack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;


public class PetPanel extends JPanel implements MouseMotionListener {

    private Point mousePos = new Point(0, 0);
    private final Point petPos = new Point(100, 100);
    private final int petSize = 30;
    private final List<Snack> snacks = new ArrayList<>();

    public PetPanel() {
        for (int i = 0; i < 5; i++) {
            snacks.add(new Snack(50 + i * 80, 200, 20));
        }
        addMouseMotionListener(this);

        Thread animator = new Thread(() -> {
            while (true) {
                movePet();
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        animator.setDaemon(true);
        animator.start();
    }

    private void movePet() {
        Snack target = findNearestSnack();
        Point dest;
        if (target != null && petPos.distance(target.getX(), target.getY()) < 150) {
            dest = new Point(target.getX(), target.getY());
            if (petPos.distance(dest) < (petSize + target.getSize()) / 2.0) {
                target.eat();
            }
        } else {
            dest = mousePos;
        }
        double dx = dest.x - petPos.x;
        double dy = dest.y - petPos.y;
        double dist = Math.hypot(dx, dy);
        if (dist > 1) {
            petPos.x += (int) (dx / dist * 2);
            petPos.y += (int) (dy / dist * 2);
        }
    }

    private Snack findNearestSnack() {
        Snack nearest = null;
        double minDist = Double.MAX_VALUE;
        for (Snack s : snacks) {
            if (s.isEaten()) {
                continue;
            }
            double d = petPos.distance(s.getX(), s.getY());
            if (d < minDist) {
                minDist = d;
                nearest = s;
            }
        }
        return nearest;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Snack s : snacks) {
            if (!s.isEaten()) {
                g.setColor(Color.ORANGE);
                g.fillOval(s.getX() - s.getSize() / 2, s.getY() - s.getSize() / 2, s.getSize(), s.getSize());
            }
        }
        g.setColor(Color.BLUE);
        g.fillOval(petPos.x - petSize / 2, petPos.y - petSize / 2, petSize, petSize);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
