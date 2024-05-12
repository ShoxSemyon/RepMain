package org.example.UIcomponent;

import javax.swing.*;
import java.awt.*;

public abstract class MainFrame extends JFrame {


    public MainFrame() throws HeadlessException {


    }

    public abstract void addSection(String sectionName, JPanel sectionFrame);


}
