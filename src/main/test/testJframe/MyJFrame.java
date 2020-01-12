package main.test.testJframe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyJFrame extends JFrame {
    public MyJFrame(){
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        int height = (int) screenSize.getHeight();
        int width = (int) screenSize.getWidth();
        setSize(width/2,height/2);
//        通知窗口系统定位框架
        setLocationByPlatform(true);
//        设置图标
        Image image = null;
        try {
            image = ImageIO.read(MyJFrame.class.getResource("icon.gif"));
            setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
