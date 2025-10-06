package Managers;

import javax.swing.*;
import java.awt.*;

public class LightTheme {

    /**
     * Sets the background of panels to that of the Light theme
     * @param dashBG
     * @param homeworkBG
     * @param progBG
     * @param settingsBG
     */
    public void BackgroundLightTheme(JLabel dashBG, JLabel homeworkBG, JLabel progBG, JLabel settingsBG){
        dashBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png")));
        homeworkBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png")));
        progBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png")));
        settingsBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png")));
    }

    /**
     * Sets the labels in the DASHBOARD panel to Light theme
     * @param todaysTaskLbl
     * @param upcomingTaskLbl
     * @param progSummaryLbl
     */
    public void DashboardLightTheme(JLabel todaysTaskLbl, JLabel upcomingTaskLbl, JLabel progSummaryLbl){
        todaysTaskLbl.setForeground( new Color(31, 30, 38));
        upcomingTaskLbl.setForeground( new Color(31, 30, 38));
        progSummaryLbl.setForeground( new Color(31, 30, 38));
    }

    /**
     * Sets the labels in the PROGRESS panel to Light theme
     * @param completeLbl
     * @param pendingLbl
     * @param overdueLbl
     * @param pieChartPnl
     * @param progSumLbl
     * @param progCompleteLbl
     * @param progPendingLbl
     * @param progSumOverdue
     */
    public void ProgressLightTheme(JLabel completeLbl, JLabel pendingLbl, JLabel overdueLbl, JPanel pieChartPnl,
                                  JLabel progSumLbl, JLabel progCompleteLbl, JLabel progPendingLbl, JLabel progSumOverdue){
        completeLbl.setForeground( new Color(31, 30, 38));
        pendingLbl.setForeground( new Color(31, 30, 38));
        overdueLbl.setForeground( new Color(31, 30, 38));
        pieChartPnl.setBackground( new Color(30, 30, 30));
        progSumLbl.setForeground( new Color(31, 30, 38));
        progCompleteLbl.setForeground( new Color(31, 30, 38));
        progPendingLbl.setForeground( new Color(31, 30, 38));
        progSumOverdue.setForeground( new Color(31, 30, 38));
    }

    /**
     * Sets the labels in the SETTINGS panel to Light theme
     *
     * @param nameLbl
     */
    public void SettingsLightTheme(JLabel nameLbl){
        nameLbl.setForeground( new Color(31, 30, 38));
    }



}
