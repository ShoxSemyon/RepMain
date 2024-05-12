package org.example.UImanagers;

import lombok.AllArgsConstructor;
import org.example.UIcomponent.MemberJPanel;
import org.example.httpClient.MemberClient;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

@Component
@AllArgsConstructor
public class MemberFrameManagerImpl implements MemberFrameManager {
    private MemberJPanel memberJPanel;

    private MemberClient memberClient;

    @Override
    public JPanel createMemberJPanel() {
        memberJPanel.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {

                memberJPanel.setDataToJTable(memberClient
                        .getMembers()
                        .getBody());
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
        return memberJPanel;

    }
}
