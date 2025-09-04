import com.formdev.flatlaf.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Settings {

    private String username, school, currentTheme;
    private int grade;

    public Settings(String inUsername, String inSchool, int inGrade, String inTheme){
        this.username = inUsername;
        this.school = inSchool;
        this.grade = inGrade;
        this.currentTheme = inTheme;
    }

    public String getUsername(){ return username; }
    public String getSchool(){ return school; }
    public int getGrade(){ return grade; }
    public String getTheme(){ return currentTheme; }

    public void setTheme(String newTheme, JPanel sidePanel, JLabel dashIcon, JLabel homeworkIcon, JLabel progressIcon,
                         JLabel settingsIcon, JLabel logoutIcon, JButton searchIcon)
            throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String lookAndFeelClassName = "";

        switch (newTheme.toLowerCase()) {
            case "dark":
                lookAndFeelClassName = FlatDarkLaf.class.getName();
                sidePanel.setBackground(new Color(31, 30, 34));
                setSidePanelIcons("/darkIcons/", dashIcon, homeworkIcon, progressIcon, settingsIcon, logoutIcon, searchIcon);
                break;
            case "light":
                lookAndFeelClassName = FlatLightLaf.class.getName();
                sidePanel.setBackground(new Color(240, 240, 240));
                setSidePanelIcons("/lightIcons/", dashIcon, homeworkIcon, progressIcon, settingsIcon, logoutIcon, searchIcon);
                break;
            default:
                lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
                break;
        }

        UIManager.setLookAndFeel(lookAndFeelClassName);
        SwingUtilities.updateComponentTreeUI(SwingUtilities.getRoot(null));
        this.currentTheme = newTheme;
    }

    private void setSidePanelIcons(String pathPrefix, JLabel dashIcon, JLabel homeworkIcon, JLabel progressIcon,
                                   JLabel settingsIcon, JLabel logoutIcon, JButton searchIcon) {
        ImageIcon scaledDashIcon = ThemeManager.ResizeImage(pathPrefix + "dashboard.png", 50, 50);
        dashIcon.setIcon(scaledDashIcon);

        ImageIcon scaledHomeworkIcon = ThemeManager.ResizeImage(pathPrefix + "assignment.png", 50, 50);
        homeworkIcon.setIcon(scaledHomeworkIcon);

        ImageIcon scaledProgressIcon = ThemeManager.ResizeImage(pathPrefix + "progress.png", 50, 50);
        progressIcon.setIcon(scaledProgressIcon);

        ImageIcon scaledSettingsIcon = ThemeManager.ResizeImage(pathPrefix + "settings.png", 50, 50);
        settingsIcon.setIcon(scaledSettingsIcon);

        ImageIcon scaledLogoutIcon = ThemeManager.ResizeImage(pathPrefix + "logout.png", 50, 50);
        logoutIcon.setIcon(scaledLogoutIcon);

        ImageIcon scaledSearchIcon = ThemeManager.ResizeImage(pathPrefix + "search.png", 20, 20);
        searchIcon.setIcon(scaledSearchIcon);
    }

    public void saveSettings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("settings.txt"))) {
            writer.println("theme=" + this.currentTheme);
            System.out.println("Settings saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }

    public void loadSettings(JPanel sidePanel, JLabel dashIcon, JLabel homeworkIcon, JLabel progressIcon,
                             JLabel settingsIcon, JLabel logoutIcon, JButton searchIcon) {
        try (Scanner scanner = new Scanner(new File("settings.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (key.equalsIgnoreCase("theme")) {
                        try {
                            this.setTheme(value, sidePanel, dashIcon, homeworkIcon, progressIcon, settingsIcon, logoutIcon, searchIcon);
                        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                            System.err.println("Failed to apply theme: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Settings loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Settings file not found. Using default settings.");
        }
    }
}