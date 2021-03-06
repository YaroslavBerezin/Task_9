package ru.sc.vsu.berezin_y_a;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

public class FrameMain extends JFrame {

    private JButton buttonRun;
    private JTable tableInput;
    private JPanel panelMain;
    private JButton buttonSaveInFile;
    private JButton buttonRandomNumbers;
    private JButton buttonUploadFromFile;
    private JTextField textFieldForAnswer;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain() {

        this.setTitle("Task 9");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(450, 200, 300, 300);
        this.setResizable(false);
        this.pack();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);


        Util.initJTableForArray(tableInput, 40, false, true,
                false, true);
        tableInput.setRowHeight(30);
        int[] arr = new int[]{1, 2, 3};
        Util.writeArrayToJTable(tableInput, arr);

        this.pack();

        Logic logic = new Logic();

        buttonRun.addActionListener(actionEvent -> {
            try {
                int[] arr1 = Util.readIntArrayFromJTable(tableInput);
                List<Integer> list = logic.createNewList(Util.intArrayToList(arr1));
                String listString = list.stream().map(Object::toString).collect(Collectors.joining(", "));
                textFieldForAnswer.setText(listString);
            } catch (Exception e) {
                Util.showErrorMessageBox(e);
            }
        });

        buttonUploadFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] arr12 = Util.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    assert arr12 != null;
                    Util.writeArrayToJTable(tableInput, arr12, "%d");
                }
            } catch (Exception e) {
                Util.showErrorMessageBox(e);
            }
        });

        buttonRandomNumbers.addActionListener(actionEvent -> {
            try {
                int[][] matrix = Util.createRandomIntMatrix(
                        tableInput.getRowCount(), tableInput.getColumnCount(), 0, 10);
                Util.writeArrayToJTable(tableInput, matrix, "%d");
            } catch (Exception e) {
                Util.showErrorMessageBox(e);
            }
        });

        buttonSaveInFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    Integer[][] matrix = Util.readIntMatrixFromJTable(tableInput, Integer.class,
                            Integer::parseInt, false, 0);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    Util.writeArrayToFile(file, matrix);
                }
            } catch (Exception e) {
                Util.showErrorMessageBox(e);
            }
        });

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(4, 1, new Insets(10, 10, 10, 10), 10, 10));
        panelMain.setMaximumSize(new Dimension(500, 300));
        panelMain.setMinimumSize(new Dimension(200, 200));
        panelMain.setPreferredSize(new Dimension(500, 300));
        panelMain.setRequestFocusEnabled(false);
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableInput = new JTable();
        scrollPane1.setViewportView(tableInput);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonSaveInFile = new JButton();
        buttonSaveInFile.setText("Save in file");
        panel1.add(buttonSaveInFile, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textFieldForAnswer = new JTextField();
        textFieldForAnswer.setEditable(false);
        panel2.add(textFieldForAnswer, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonRun = new JButton();
        buttonRun.setText("Run");
        panel3.add(buttonRun, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonRandomNumbers = new JButton();
        buttonRandomNumbers.setText("Random numbers");
        panel3.add(buttonRandomNumbers, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonUploadFromFile = new JButton();
        buttonUploadFromFile.setText("Upload from file");
        panel3.add(buttonUploadFromFile, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
