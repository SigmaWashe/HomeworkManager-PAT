package Managers;

import javax.swing.*;
import java.awt.*;

public class DarkTheme {

    /**
     * Sets the background of panels to that of the Dark theme
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
     * Sets the labels in the DASHBOARD panel to Dark theme
     * @param todaysTaskLbl
     * @param upcomingTaskLbl
     * @param progSummaryLbl
     */
    public void DashboardDarkTheme(JLabel todaysTaskLbl, JLabel upcomingTaskLbl, JLabel progSummaryLbl){
        todaysTaskLbl.setForeground( new Color(204, 204, 204));
        upcomingTaskLbl.setForeground( new Color(204, 204, 204));
        progSummaryLbl.setForeground( new Color(204, 204, 204));
    }

    /**
     * Sets the labels in the PROGRESS panel to Dark theme
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
        completeLbl.setForeground( new Color(204, 204, 204));
        pendingLbl.setForeground( new Color(204, 204, 204));
        overdueLbl.setForeground( new Color(204, 204, 204));
        pieChartPnl.setBackground( new Color(30, 30, 30));
        progSumLbl.setForeground( new Color(204, 204, 204));
        progCompleteLbl.setForeground( new Color(204, 204, 204));
        progPendingLbl.setForeground( new Color(204, 204, 204));
        progSumOverdue.setForeground( new Color(204, 204, 204));
    }

    /**
     * Sets the labels in the SETTINGS panel to Dark theme
     *
     * @param nameLbl
     */
    public void SettingsDarkTheme(JLabel nameLbl){
        nameLbl.setForeground( new Color(204, 204, 204));
    }


}
