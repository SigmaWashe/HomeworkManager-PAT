package Managers;

import com.formdev.flatlaf.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.logging.*;

public class DarkTheme {

    /**
     * Sets the background of panels to that of the DARK theme
     * @param dashBG
     * @param homeworkBG
     * @param progBG
     * @param settingsBG
     */
    public void BackgroundDarkTheme(JLabel dashBG, JLabel homeworkBG, JLabel progBG, JLabel settingsBG){
        dashBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/DarkBG.png")));
        homeworkBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/DarkBG.png")));
        progBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/DarkBG.png")));
        settingsBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/DarkBG.png")));
    }

    /**
     * Sets the labels in the DASHBOARD panel to DARK theme
     * @param todaysTaskLbl
     * @param upcomingTaskLbl
     * @param progSummaryLbl
     */
    public void DashboardDarkTheme(JLabel todaysTaskLbl, JLabel upcomingTaskLbl, JLabel progSummaryLbl){
        todaysTaskLbl.setForeground( new Color(31, 30, 38));
        upcomingTaskLbl.setForeground( new Color(31, 30, 38));
        progSummaryLbl.setForeground( new Color(31, 30, 38));
    }

    /**
     * Sets the labels in the PROGRESS panel to DARK theme
     * @param completeLbl
     * @param pendingLbl
     * @param overdueLbl
     * @param pieChartPnl
     * @param progSumLbl
     * @param progCompleteLbl
     * @param progPendingLbl
     * @param progSumOverdue
     */
    public void ProgressDarkTheme(JLabel completeLbl, JLabel pendingLbl, JLabel overdueLbl, JPanel pieChartPnl,
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
     * Sets the labels in the SETTINGS panel to DARK theme
     * @param nameLbl
     * @param schoolLbl
     * @param gradeLbl
     * @param themeLbl
     */
    public void SettingsDarkTheme(JLabel nameLbl, JLabel schoolLbl, JLabel gradeLbl, JLabel themeLbl/*, JButton saveChangeBtn, JButton applyChangeBtn*/){
        nameLbl.setForeground( new Color(31, 30, 38));
        schoolLbl.setForeground( new Color(31, 30, 38));
        gradeLbl.setForeground( new Color(31, 30, 38));
        themeLbl.setForeground( new Color(31, 30, 38));
        /*saveChangeBtn.setBackground( new Color(204, 204, 204, 204));
        saveChangeBtn.setForeground( new Color(31, 30, 38));
        saveChangeBtn.setBackground( new Color(204, 204, 204, 204));
        applyChangeBtn.setForeground( new Color(31, 30, 38));*/
    }


}
