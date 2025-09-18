package Managers;

import Managers.ThemeManager;
import com.formdev.flatlaf.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Settings {

    private String currentTheme;
    public String getTheme(){ return currentTheme; }

    /**
     * This method sets the theme to the
     * @param newTheme
     * @param sidePanel
     * @param dashIcon
     * @param homeworkIcon
     * @param progressIcon
     * @param settingsIcon
     * @param logoutIcon
     * @param searchIcon
     * @throws UnsupportedLookAndFeelException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
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

    /**
     * @param pathPrefix
     * @param dashIcon
     * @param homeworkIcon
     * @param progressIcon
     * @param settingsIcon
     * @param logoutIcon
     * @param searchIcon
     */
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

}