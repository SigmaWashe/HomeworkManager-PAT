import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class ThemeManager {

    ///--------------Method makes components transparent-------------------///
    public void transparentButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    public void toggle(JToggleButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    public void transparentTextField(JTextField field) {
        field.setOpaque(false);
        field.setBackground(new Color(0, 0, 0, 0));
        field.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void transparentPasswordField(JPasswordField field) {
        field.setOpaque(false);
        field.setBackground(new Color(0, 0, 0, 0));
        field.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void transparentPanel(JPanel panel) {
        panel.setOpaque(false);
        panel.setBackground(new Color(0, 0, 0, 0));  // Fully transparent background
    }


    /**
     * public void transparentTable(JTable table){
     table.setOpaque(false);
     table.setBackground(new Color(0, 0, 0, 0));
     table.setShowGrid(false);

     // Make cell renderer transparent
     DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
     renderer.setOpaque(false);
     table.setDefaultRenderer(Object.class, renderer);

     // Make table header transparent
     JTableHeader header = table.getTableHeader();
     header.setOpaque(false);
     header.setBackground(new Color(0, 0, 0, 0));
     header.setForeground(Color.WHITE);

     }
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

    //This method scales the image
    public static ImageIcon ResizeImage(String path, int width, int height){
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }



    public void showDashboardPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(true);
        HomeworkPnl.setVisible(false);
        ProgressPnl.setVisible(false);
        SettingsPnl.setVisible(false);
    }

    public void showHomeworkPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(false);
        HomeworkPnl.setVisible(true);
        ProgressPnl.setVisible(false);
        SettingsPnl.setVisible(false);
    }

    public void showProgressPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(false);
        HomeworkPnl.setVisible(false);
        ProgressPnl.setVisible(true);
        SettingsPnl.setVisible(false);
    }

    public void showSettingsPanel(JPanel DashboardPnl, JPanel HomeworkPnl, JPanel ProgressPnl, JPanel SettingsPnl) {
        DashboardPnl.setVisible(false);
        HomeworkPnl.setVisible(false);
        ProgressPnl.setVisible(false);
        SettingsPnl.setVisible(true);
    }

    public void RoundedTextField(JTextField textField, int radius) {
        textField.setOpaque(false);
        textField.setBorder(new ThemeManager.RoundedBorder(radius));
        textField.setBackground(new Color(255, 255, 255, 230));
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(Color.BLACK);
    }

    public void roundedTable(JScrollPane scrollPane, JTable table, int radius){

        scrollPane.setBorder(new ThemeManager.RoundedBorder(radius));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        table.setOpaque(false);
        table.setBorder(new ThemeManager.RoundedBorder(radius));
        table.setBackground(new Color(255, 255, 255, 230));
        table.setForeground(Color.BLACK);

    }

    // Custom Border class with rounded corners
    class RoundedBorder implements Border {

        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded rectangle border
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    /// TaskFrame switch panels
    public void showAddTaskPanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(true);
        EditTaskPnl.setVisible(false);
        DeleteTaskPnl.setVisible(false);
        MarkDonePnl.setVisible(false);
    }

    public void showEditTaskPanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(false);
        EditTaskPnl.setVisible(true);
        DeleteTaskPnl.setVisible(false);
        MarkDonePnl.setVisible(false);
    }

    public void showDeleteTaskPanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(false);
        EditTaskPnl.setVisible(false);
        DeleteTaskPnl.setVisible(true);
        MarkDonePnl.setVisible(false);
    }

    public void showMarkDonePanel(JPanel AddTaskPnl, JPanel EditTaskPnl, JPanel DeleteTaskPnl, JPanel MarkDonePnl) {
        AddTaskPnl.setVisible(false);
        EditTaskPnl.setVisible(false);
        DeleteTaskPnl.setVisible(false);
        MarkDonePnl.setVisible(true);
    }

}
