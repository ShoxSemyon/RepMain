package org.example.UIcomponent;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class MemberJPanel extends JPanel {
    private JButton add;
    private JButton remove;
    private JButton copy;
    private JPanel toolJPanel;
    private JTable tableMember;

    public MemberJPanel() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        setLayout(new BorderLayout());
        setName("Участники");

        toolJPanel = getToolPanel();
        add(toolJPanel, BorderLayout.NORTH);
        add = ((JButton) toolJPanel.add(getAddButton()));
        remove = ((JButton) toolJPanel.add(getRemoveButton()));
        copy = ((JButton) toolJPanel.add(getCopyButton()));

        tableMember = getJTableMemeber();
        add(new JScrollPane(tableMember));

        revalidate();

    }

    public void setDataToJTable(List<?> data) {
        if (tableMember == null) {
            return;
        }
        var tableModel = new DefaultTableModel() {
            @Override
            public int getRowCount() {
                return data.size();
            }

            @Override
            public int getColumnCount() {
                return data.get(0)
                        .getClass()
                        .getDeclaredFields()
                        .length;
            }

            @Override
            public String getColumnName(int column) {
                return data.get(0)
                        .getClass()
                        .getDeclaredFields()[column]
                        .getName();
            }

            @SneakyThrows
            @Override
            public Object getValueAt(int row, int column) {

                var cellData = data.get(row)
                        .getClass()
                        .getDeclaredFields()[column];
                cellData.setAccessible(true);

                return cellData.get(data.get(row));
            }
        };
        tableMember.setModel(tableModel);

    }

    private JTable getJTableMemeber() {
        var table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }


    private JPanel getToolPanel() {
        return new JPanel() {
            {
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            }
        };
    }

    @Override
    public String toString() {
        return this.getName();
    }

    private JButton getAddButton() {
        return new JButton("add");
    }

    private JButton getRemoveButton() {
        return new JButton("remove");
    }

    private JButton getCopyButton() {
        return new JButton("copy");

    }


}
