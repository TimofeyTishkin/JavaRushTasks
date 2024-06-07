package com.javarush.task.task24.task2413;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class KeyboardObserver extends Thread {
    private Queue<KeyEvent> keyEvents = new ArrayBlockingQueue<KeyEvent>(100);

    private JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("KeyPress Tester");
        frame.setTitle("Transparent JFrame Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setUndecorated(true);
        frame.setSize(400, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new GridBagLayout());

        frame.setOpacity(0.0f);
        frame.setVisible(true);

        frame.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //do nothing
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.exit(0);
            }
        });


        frame.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
                //do nothing
            }

            public void keyReleased(KeyEvent e) {
                //do nothing
            }

            public void keyPressed(KeyEvent e) {
                keyEvents.add(e);
            }
        });
    }


    public boolean hasKeyEvents() {
        return !keyEvents.isEmpty();
    }

    public KeyEvent getEventFromTop() {
        return keyEvents.poll();
    }
}
