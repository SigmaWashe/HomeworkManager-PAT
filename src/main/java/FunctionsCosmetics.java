/*
import com.formdev.flatlaf.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import javax.swing.UIManager.*;
import javax.swing.table.*;

public class FunctionsCosmetics {

    ///--------------Method writes the new users username and password onto the logins.txt File-------------------///
    public boolean writeSignupToFile(String username, String password) {
        File loginFile = new File("logins.txt");

        // Check if username already exists
        try {
            Scanner scanner = new Scanner(loginFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                if (lineScanner.hasNext()) {
                    String existingUsername = lineScanner.next().trim();
                    if (existingUsername.equalsIgnoreCase(username)) {
                        JOptionPane.showMessageDialog(null, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                        lineScanner.close();
                        scanner.close();
                        return false;
                    }
                }
                lineScanner.close();
            }
            scanner.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File not found while checking: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Write the username and password onto the logins.txt file
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(loginFile, true));  // true = append mode
            writer.println(username + "," + password);
            writer.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to write user to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void handleSignupAction(JFrame frame, JTextField usernameField, JPasswordField passwordField) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (writeSignupToFile(username, password)) {
            JOptionPane.showMessageDialog(frame, "Signup Successful!");
            frame.dispose();
            new MainMenu().setVisible(true);
        }
    }

    ///--------------Method Checks The logins.txt File For The Username and Password-------------------///
    public boolean checkLoginFile(String username, String password) {

        File loginFile = new File("logins.txt");
        if (!loginFile.exists()) {
            JOptionPane.showMessageDialog(null, "Login file not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (Scanner fileScanner = new Scanner(loginFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                if (lineScanner.hasNext()) {
                    String fileUsername = lineScanner.next().trim();
                    if (lineScanner.hasNext()) {
                        String filePassword = lineScanner.next().trim();
                        if (fileUsername.equals(username) && filePassword.equals(password)) {
                            return true;  // Match found
                        }
                    }
                }
                lineScanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  // No match found
    }

    public void handleLoginAction(JFrame frame, JTextField usernameField, JPasswordField passwordField) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (checkLoginFile(username, password)) {
            JOptionPane.showMessageDialog(frame, "Login Successful");
            frame.dispose();
            new MainMenu().setVisible(true); // Optional
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    /**
     * public void addEExitListeners(final JFrame frame){
     KeyAdapter keyHandler = new KeyAdapter() {
     public void keyPressed(KeyEvent e) {
     if (e.getKeyCode() == KeyEvent.VK_ESCAPE + k){
     frame.dispose();
     }
     }
     };
     frame.addKeyListener(keyHandler);
     }


    public void addLoginExitListeners(final JFrame frame, final JTextField usernameField, final JPasswordField passwordField) {
        KeyAdapter keyHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLoginAction(frame, usernameField, passwordField);
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }
            }
        };

        usernameField.addKeyListener(keyHandler);
        passwordField.addKeyListener(keyHandler);
    }

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

    public void darkTheme(JPanel sidePanel,JLabel dashIcon, JLabel homeworkIcon, JLabel progressIcon,
                          JLabel settingsIcon, JLabel logoutIcon){

        sidePanel.setBackground(new java.awt.Color(31, 30, 34));
        dashIcon.setForeground(new java.awt.Color(204, 204, 204));
        homeworkIcon.setForeground(new java.awt.Color(204, 204, 204));
        progressIcon.setForeground(new java.awt.Color(204, 204, 204));
        settingsIcon.setForeground(new java.awt.Color(204, 204, 204));
        logoutIcon.setForeground(new java.awt.Color(204, 204, 204));

        dashIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/dashboard.png")));
        homeworkIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/assignment.png")));
        progressIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/progress.png")));
        settingsIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/settings.png")));
        logoutIcon.setIcon(new ImageIcon(getClass().getResource("/lightIcons/logout.png")));

    }

    public void RoundedTextField(JTextField textField, int radius) {
        textField.setOpaque(false);
        textField.setBorder(new RoundedBorder(radius));
        textField.setBackground(new Color(255, 255, 255, 230));
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(Color.BLACK);
    }

    public void roundedTable(JScrollPane scrollPane, JTable table, int radius){

        scrollPane.setBorder(new RoundedBorder(radius));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        table.setOpaque(false);
        table.setBorder(new RoundedBorder(radius));
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
    
}
*/