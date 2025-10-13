package main;
import javax.swing.*;
import ui.GameWindow;

public class Main {
    public static void main(String[] args) {
       
        JFrame frame = new JFrame("Arkanoid Game");
        GameWindow panel = new GameWindow();
        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
