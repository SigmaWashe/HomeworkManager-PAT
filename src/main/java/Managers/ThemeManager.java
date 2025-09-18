package Managers;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class ThemeManager {

    ///--------------Method makes components transparent-------------------///
    /**
     * Makes the button transparent
     * @param button
     */
    public void transparentButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    /**
     * Makes the toggle button transparent
     * @param button
     */
    public void toggle(JToggleButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    /**
     * Makes the textField transparent
     * @param field
     */
    public void transparentTextField(JTextField field) {
        field.setOpaque(false);
        field.setBackground(new Color(0, 0, 0, 0));
        field.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    /**
     * Makes the password field transparent
     * @param field
     */
    public void transparentPasswordField(JPasswordField field) {
        field.setOpaque(false);
        field.setBackground(new Color(0, 0, 0, 0));
        field.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    /**
     * Adds place holder text to the textField
     * @param field
     * @param placeholderText
     */
    public void addPlaceholder(final JTextField field, final String placeholderText) {
        field.setText(placeholderText);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholderText)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholderText);
                }
            }
        });
    }

    /**
     * Adds toggle functionality to the password toggle button
     *      - if the toggle button is selected it shows the password
     *      - else it will hide the password
     * @param passwordField
     * @param toggleButton
     * @param showIcon
     * @param hideIcon
     */
    public void addPasswordToggle(final JPasswordField passwordField, final JToggleButton toggleButton,
                                  final ImageIcon showIcon, final ImageIcon hideIcon) {
        toggleButton.setIcon(showIcon);

        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toggleButton.isSelected()) {
                    passwordField.setEchoChar((char) 0);  // Show password
                    toggleButton.setIcon(hideIcon);
                } else {
                    passwordField.setEchoChar('•');       // Hide password
                    toggleButton.setIcon(showIcon);
                }
            }
        });
    }

    /**
     * This method scales the image
     * @param path
     * @param width
     * @param height
     * @return the ImageIcon
     */
    public static ImageIcon ResizeImage(String path, int width, int height){
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    /**
     * It sets the Dashboard panel as the only visible panel on the MainMenu frame
     * @param DashboardPnl
     * @param HomeworkPnl
     * @param ProgressPnl
     * @param SettingsPnl
     */
    public void showDashboardPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(true);
        HomeworkPnl.setVisible(false);
        ProgressPnl.setVisible(false);
        SettingsPnl.setVisible(false);
    }

    /**
     * It sets the Homework panel as the only visible panel on the MainMenu frame
     * @param DashboardPnl
     * @param HomeworkPnl
     * @param ProgressPnl
     * @param SettingsPnl
     */
    public void showHomeworkPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(false);
        HomeworkPnl.setVisible(true);
        ProgressPnl.setVisible(false);
        SettingsPnl.setVisible(false);
    }

    /**
     * It sets the Progress panel as the only visible panel on the MainMenu frame
     * @param DashboardPnl
     * @param HomeworkPnl
     * @param ProgressPnl
     * @param SettingsPnl
     */
    public void showProgressPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(false);
        HomeworkPnl.setVisible(false);
        ProgressPnl.setVisible(true);
        SettingsPnl.setVisible(false);
    }

    /**
     * It sets the Settings panel as the only visible panel on the MainMenu frame
     * @param DashboardPnl
     * @param HomeworkPnl
     * @param ProgressPnl
     * @param SettingsPnl
     */
    public void showSettingsPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(false);
        HomeworkPnl.setVisible(false);
        ProgressPnl.setVisible(false);
        SettingsPnl.setVisible(true);
    }

    /**
     * It sets the AddTask panel as the only visible panel on the TaskFrame
     * @param AddTaskPnl
     * @param EditTaskPnl
     * @param DeleteTaskPnl
     * @param MarkDonePnl
     */
    public void showAddTaskPanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(true);
        EditTaskPnl.setVisible(false);
        DeleteTaskPnl.setVisible(false);
        MarkDonePnl.setVisible(false);
    }

    /**
     * It sets the EditTask panel as the only visible panel on the TaskFrame
     * @param AddTaskPnl
     * @param EditTaskPnl
     * @param DeleteTaskPnl
     * @param MarkDonePnl
     */
    public void showEditTaskPanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(false);
        EditTaskPnl.setVisible(true);
        DeleteTaskPnl.setVisible(false);
        MarkDonePnl.setVisible(false);
    }

    /**
     * It sets the DeleteTask panel as the only visible panel on the TaskFrame
     * @param AddTaskPnl
     * @param EditTaskPnl
     * @param DeleteTaskPnl
     * @param MarkDonePnl
     */
    public void showDeleteTaskPanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(false);
        EditTaskPnl.setVisible(false);
        DeleteTaskPnl.setVisible(true);
        MarkDonePnl.setVisible(false);
    }

    /**
     * It sets the MarkDone panel as the only visible panel on the TaskFrame
     * @param AddTaskPnl
     * @param EditTaskPnl
     * @param DeleteTaskPnl
     * @param MarkDonePnl
     */
    public void showMarkDonePanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(false);
        EditTaskPnl.setVisible(false);
        DeleteTaskPnl.setVisible(false);
        MarkDonePnl.setVisible(true);
    }

}
