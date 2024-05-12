package org.example.UIcomponent;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

@Component
public class MainFrameImpl extends MainFrame {
    @Getter
    private JTree leftVerticalItemJTree;
    private JScrollPane leftVerticalMenuPanel;

    public MainFrameImpl() {
        leftVerticalMenuPanel = createLeftVerticalMenuPanel();
        add(leftVerticalMenuPanel, BorderLayout.WEST);

        setSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BIO ADMIN GUI");


    }

    private JScrollPane createLeftVerticalMenuPanel() {

        leftVerticalItemJTree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode("root",
                true)));
        leftVerticalItemJTree.setRootVisible(false);
        leftVerticalItemJTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        leftVerticalItemJTree.addTreeSelectionListener(e -> {
            JTree tree = ((JTree) e.getSource());

            var nodeSection = ((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent());

            if (nodeSection.getUserObject() instanceof JPanel) {

                var centerComponent = ((BorderLayout) MainFrameImpl.this.getContentPane()
                        .getLayout())
                        .getLayoutComponent(BorderLayout.CENTER);

                if (centerComponent != null) {
                    centerComponent.setVisible(false);
                    MainFrameImpl.this.getContentPane().remove(centerComponent);

                }
                MainFrameImpl.this.add(((JPanel) nodeSection.getUserObject()));
                ((JPanel) nodeSection.getUserObject()).setVisible(true);
                MainFrameImpl.this.revalidate();

            }
        });

        return new JScrollPane(leftVerticalItemJTree,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) {


            @Override
            public Dimension getPreferredSize() {
                var x = ((int) MainFrameImpl.this.getSize().getWidth());

                return new Dimension(x / 5, 0);
            }

        };
    }

    @Override
    public void addSection(String rootSectionName, JPanel leafSectionFrame) {
        var model = ((DefaultTreeModel) leftVerticalItemJTree.getModel());

        DefaultMutableTreeNode defaultMutableRoot = getRootSectionNode(rootSectionName, model);

        var newLeaf = new DefaultMutableTreeNode(leafSectionFrame, false);
        defaultMutableRoot.add(newLeaf);
        model.insertNodeInto(newLeaf, defaultMutableRoot, defaultMutableRoot.getChildCount() - 1);
        leftVerticalItemJTree.scrollPathToVisible(new TreePath(newLeaf.getPath()));


    }

    private DefaultMutableTreeNode getRootSectionNode(String rootSectionName, DefaultTreeModel model) {
        DefaultMutableTreeNode defaultMutableRoot;
        var rootSectionIndex = getRootSectionIndex(rootSectionName, model);
        if (rootSectionIndex == -1) {
            defaultMutableRoot = new DefaultMutableTreeNode(rootSectionName, true);
            model.insertNodeInto(defaultMutableRoot, (DefaultMutableTreeNode) model.getRoot(),
                    ((DefaultMutableTreeNode) model.getRoot()).getChildCount());

        } else {
            defaultMutableRoot = ((DefaultMutableTreeNode) model.getChild(model.getRoot(), rootSectionIndex));
        }
        return defaultMutableRoot;
    }

    private int getRootSectionIndex(String rootSectionName, DefaultTreeModel model) {
        var childCount = model.getChildCount(model.getRoot());
        if (childCount == 0) return -1;

        for (int i = 0; i <= childCount - 1; i++) {
            if (((DefaultMutableTreeNode) model.getChild(model.getRoot(), i)).getUserObject()
                    .equals(rootSectionName)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public Dimension getPreferredSize() {
        /**
         * Define the value of the home screen taking into account the taskbar
         */
        var toolKit = Toolkit.getDefaultToolkit();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        Dimension dimension = new Dimension(toolKit.getScreenSize().width - scnMax.right,
                toolKit.getScreenSize().height - scnMax.bottom);
        return dimension;
    }
}
