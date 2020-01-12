package main.test.testJframe;

import javax.swing.*;
import java.awt.*;


public class testMyJFrame {
    public static void main(String[] args) {

            MyJFrame myJFrame = new MyJFrame();
            myJFrame.setTitle("MyJFrame");
            myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myJFrame.setVisible(true);
    }
}
