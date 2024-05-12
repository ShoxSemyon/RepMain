package org.example.UImanagers;

import lombok.AllArgsConstructor;
import org.example.UIcomponent.MainFrame;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
@Component
public class MainFrameManagerImpl implements MainFrameManager {
    MainFrame mainFrame;
    MemberFrameManager memberFrameManager;

    @Override
    public void mainStart() {
        EventQueue.invokeLater(() -> {
            mainFrame.setVisible(true);
            mainFrame.addSection("Управление участниками",
                    memberFrameManager.createMemberJPanel());
            mainFrame.addSection("Управление участниками",
                    new JPanel() {{
                        add(new Label("TeSt"));
                    }});
        });
    }
}
