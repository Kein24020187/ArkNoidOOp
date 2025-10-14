package main;

import javax.swing.*;
import ui.GameWindow;
import java.awt.*;

import static main.GameManager.windowHeight;
import static main.GameManager.windowWith;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Arkanoid Game");
        GameWindow panel = new GameWindow();
        panel.setPreferredSize(new Dimension(windowWith, windowHeight));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // canh giữa màn hình
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
