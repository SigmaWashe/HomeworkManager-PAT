import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Authenticator {

    ///--------------Method Checks The logins.txt File For The Username and Password-------------------///
    public boolean checkLoginFile(String username, String password) {

        File loginFile = new File("txtFiles/logins.txt");
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

}
