package Managers;

import javax.swing.*;
import java.awt.*;

public class LightTheme {

    public void BackgroundLightTheme(JLabel dashBG, JLabel homeworkBG, JLabel progBG, JLabel settingsBG){
        dashBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LightThemeIcons/BACKGROUND.png")));
        homeworkBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LightThemeIcons/BACKGROUND.png")));
        progBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LightThemeIcons/BACKGROUND.png")));
        settingsBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LightThemeIcons/BACKGROUND.png")));
    }

    public void DashboardLightTheme(JLabel todaysTaskLbl, JLabel upcomingTaskLbl, JLabel progSummaryLbl){
        todaysTaskLbl.setForeground( new Color(204, 204, 204));
        upcomingTaskLbl.setForeground( new Color(204, 204, 204));
        progSummaryLbl.setForeground( new Color(204, 204, 204));
    }

    public void ProgressLightTheme(JLabel completeLbl, JLabel pendingLbl, JLabel overdueLbl, JPanel pieChartPnl,
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

    public void SettingsLightTheme(JLabel nameLbl, JLabel schoolLbl, JLabel gradeLbl, JLabel themeLbl/*, JButton saveChangeBtn, JButton applyChangeBtn*/){
        nameLbl.setForeground( new Color(204, 204, 204));
        schoolLbl.setForeground( new Color(204, 204, 204));
        gradeLbl.setForeground( new Color(204, 204, 204));
        themeLbl.setForeground( new Color(204, 204, 204));
        /*saveChangeBtn.setBackground( new Color(31, 30, 38, 204));
        saveChangeBtn.setForeground( new Color(204, 204, 204));
        saveChangeBtn.setBackground( new Color(31, 30, 38, 204));
        applyChangeBtn.setForeground( new Color(204, 204, 204));*/
    }
    
}
