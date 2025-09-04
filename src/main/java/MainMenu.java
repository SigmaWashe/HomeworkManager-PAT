/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import com.formdev.flatlaf.*;

import java.awt.*;
import java.awt.event.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;

import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author china
 */
public class MainMenu extends javax.swing.JFrame {
    FileHandler fileHandler = new FileHandler("C:/Users/china/Downloads/HomeworkManager-PAT-master/HomeworkManager-PAT-master/txtFiles/data.txt");
    ArrayList<Task> tasks = fileHandler.readTaskFile();
    ThemeManager themeManager = new ThemeManager();
    TaskManager taskManager = new TaskManager();
    TaskFrame taskFrame = new TaskFrame();
    DarkTheme dt = new DarkTheme();
    LightTheme lt = new LightTheme();

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Homework Reminder App");

        applyTheme("Dark");
        applyFuncCos();
        displayTasks(tasks);

    }

    private void applyFuncCos() {

        themeManager.addPlaceholder(SearchFld, "Search");

        SearchBtn.setToolTipText("Search");
        themeManager.transparentButton(SearchBtn);
    }

    private void displayTasks(ArrayList<Task> tasks) {
    LocalDate today = LocalDate.now();

    // ----- 1. Today's Tasks -----
    DefaultTableModel todayModel = new DefaultTableModel(new String[]{"Task Name", "Due At", "Priority", "Status"}, 0);
    for (Task task : tasks) {
        if (task.getDueDate().equals(today)) {
            todayModel.addRow(new Object[]{
                task.getTaskName(),
                task.getDueTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                task.getPriority(),
                task.getStatus()
            });
        }
    }
    TodaysTasksTbl.setModel(todayModel);

    // ----- 2. Upcoming Tasks -----
    DefaultTableModel upcomingModel = new DefaultTableModel(new String[]{"Task Name", "Due Date", "Priority", "Status"}, 0);
    for (Task task : tasks) {
        if (task.getDueDate().isAfter(today)) {
            upcomingModel.addRow(new Object[]{
                task.getTaskName(),
                task.getDueDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                task.getPriority(),
                task.getStatus()
            });
        }
    }
    UpcomingTaskTbl.setModel(upcomingModel);

    // ----- 3. Homework Tasks -----
    DefaultTableModel homeworkModel = new DefaultTableModel(new String[]{"Task Name", "Subject", "Due Date", "Priority", "Status"}, 0);
    for (Task task : tasks) {
        homeworkModel.addRow(new Object[]{
                task.getTaskName(),
                task.getSubject(),
                task.getDueDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                task.getPriority(),
                task.getStatus()
        });
    }
    jTable2.setModel(homeworkModel);
    }

    private void applyTheme(String themeName) {
        try {
            if ("Dark".equalsIgnoreCase(themeName)) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SidePnl.setBackground(new Color(30, 31, 38));
                // Update icons for the dark theme
                SearchFld.setForeground(new Color(31, 30, 38, 128));
                dt.BackgroundDarkTheme(DashBG, HomeworkBG, ProgBG, SettingsBG);
                dt.DashboardDarkTheme(TodaysTasklbl, UpcomingTasklbl, ProgressSummaryLbl);
                dt.ProgressDarkTheme(Completedlbl, Pendinglbl, Overduelbl, PieChartPnl, ProgSumlbl, ProgCompletelbl,
                                     ProgPendinglbl, ProgOverduelbl);
                dt.SettingsDarkTheme(SettNamelbl, SettSchoolLbl, SettSchoolLbl, SettThemelbl/*, SettSaveChangeBtn, SettApplyBtn*/);
                updateIcons("/darkThemeIcons/");
            } else if ("Light".equalsIgnoreCase(themeName)) {
                UIManager.setLookAndFeel(new FlatLightLaf());
                lt.BackgroundLightTheme(DashBG, HomeworkBG, ProgBG, SettingsBG);
                SearchFld.setForeground(new Color(204, 204, 204, 128));
                lt.DashboardLightTheme(TodaysTasklbl, UpcomingTasklbl, ProgressSummaryLbl);
                lt.ProgressLightTheme(Completedlbl, Pendinglbl, Overduelbl, PieChartPnl, ProgSumlbl, ProgCompletelbl,
                        ProgPendinglbl, ProgOverduelbl);
                lt.SettingsLightTheme(SettNamelbl, SettSchoolLbl, SettSchoolLbl, SettThemelbl/*, SettSaveChangeBtn, SettApplyBtn*/);
                SidePnl.setBackground(new Color(204, 204, 204));
                // Update icons for the light theme
                updateIcons("/lightThemeIcons/");
            }

            // Update the entire UI tree of the current frame to apply the new Look and Feel
            SwingUtilities.updateComponentTreeUI(this);

            // Ensure the component colors are updated
            revalidate();
            repaint();

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateIcons(String iconPath) {
        DashboardIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "dashboard.png")));
        HomeworkIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "assignment.png")));
        ProgressIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "progress.png")));
        SettingsIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "settings.png")));
        LogoutIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "logout.png")));
        SearchBtn.setIcon(new ImageIcon(getClass().getResource(iconPath + "search.png")));
    }

    private void pieChart(int complete, int pending, int overdue) {

        ProgressTracker pt = new ProgressTracker(complete, pending, overdue);
        DefaultPieDataset pieChart = new DefaultPieDataset();
        pieChart.setValue("Completed Tasks", complete);
        pieChart.setValue("Pending Tasks", pending);
        pieChart.setValue("Overdue Tasks", overdue);
        complete = pt.getCompleted();
        pending = pt.getPending();
        overdue = pt.getOverdue();

        JFreeChart chart = ChartFactory.createPieChart(
                "Tasks Pie Chart", pieChart, true, true, false);

        // Apply dark theme styling
        chart.setBackgroundPaint(new Color(31, 30, 38));
        chart.getTitle().setPaint(new Color(204, 204, 204));
        chart.getLegend().setBackgroundPaint(new Color(31, 30, 38));;
        chart.getLegend().setItemPaint(new Color(204, 204, 204));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(31, 30, 38));
        plot.setOutlineVisible(false);
        plot.setLabelBackgroundPaint(new Color(31, 30, 38));
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelPaint(new Color(204, 204, 204));
        plot.setShadowPaint(null); // optional - removes pie slice shadow

        // Section colors - adjust for better visibility
        plot.setSectionPaint("Completed Tasks", new Color(60, 160, 90)); // Green
        plot.setSectionPaint("Pending Tasks", new Color(220, 180, 60));   // Yellow
        plot.setSectionPaint("Overdue Tasks", new Color(180, 50, 50));   // Red

        // Optional: transparency (0.0f is invisible, 1.0f is fully visible)
        plot.setForegroundAlpha(0.9f);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(31, 30, 38));

        PieChartPnl.removeAll();
        PieChartPnl.setLayout(new BorderLayout()); // Ensure proper layout
        PieChartPnl.add(chartPanel, BorderLayout.CENTER);
        PieChartPnl.revalidate();
        PieChartPnl.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        SidePnl = new javax.swing.JPanel();
        DashboardIcon = new javax.swing.JLabel();
        HomeworkIcon = new javax.swing.JLabel();
        ProgressIcon = new javax.swing.JLabel();
        SettingsIcon = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        LogoutIcon = new javax.swing.JLabel();
        DashboardPnl = new javax.swing.JPanel();
        TodaysTasks = new javax.swing.JScrollPane();
        TodaysTasksTbl = new javax.swing.JTable();
        TodaysTasklbl = new javax.swing.JLabel();
        UpcomingTask = new javax.swing.JScrollPane();
        UpcomingTaskTbl = new javax.swing.JTable();
        UpcomingTasklbl = new javax.swing.JLabel();
        ProgressSummary = new javax.swing.JScrollPane();
        ProgressSummaryTbl = new javax.swing.JTable();
        ProgressSummaryLbl = new javax.swing.JLabel();
        DashBG = new javax.swing.JLabel();
        HomeworkPnl = new javax.swing.JPanel();
        SearchBtn = new javax.swing.JButton();
        SearchFld = new javax.swing.JTextField();
        HomeworkFilter = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        HomeworkBG = new javax.swing.JLabel();
        ProgressPnl = new javax.swing.JPanel();
        Completedlbl = new javax.swing.JLabel();
        Pendinglbl = new javax.swing.JLabel();
        Overduelbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PieChartPnl = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        ProgSumlbl = new javax.swing.JLabel();
        ProgCompletelbl = new javax.swing.JLabel();
        ProgPendinglbl = new javax.swing.JLabel();
        ProgOverduelbl = new javax.swing.JLabel();
        ProgBG = new javax.swing.JLabel();
        SettingsPnl = new javax.swing.JPanel();
        SettNamelbl = new javax.swing.JLabel();
        SettSchoolLbl = new javax.swing.JLabel();
        SettGradelbl = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        SettThemelbl = new javax.swing.JLabel();
        ThemeSelector = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        SettingsBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        MainPanel.setPreferredSize(new java.awt.Dimension(750, 700));
        MainPanel.setVerifyInputWhenFocusTarget(false);
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SidePnl.setBackground(new java.awt.Color(31, 30, 38));
        SidePnl.setToolTipText("");
        SidePnl.setAutoscrolls(true);
        SidePnl.setMaximumSize(new java.awt.Dimension(60, 700));
        SidePnl.setMinimumSize(new java.awt.Dimension(60, 700));
        SidePnl.setPreferredSize(new java.awt.Dimension(60, 700));
        SidePnl.setVerifyInputWhenFocusTarget(false);
        SidePnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DashboardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/dashboard.png"))); // NOI18N
        DashboardIcon.setToolTipText("Dashboard");
        DashboardIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardIconMouseClicked(evt);
            }
        });
        SidePnl.add(DashboardIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 10, -1, -1));

        HomeworkIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/assignment.png"))); // NOI18N
        HomeworkIcon.setToolTipText("Homework");
        HomeworkIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeworkIconMouseClicked(evt);
            }
        });
        SidePnl.add(HomeworkIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 160, -1, -1));

        ProgressIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/progress.png"))); // NOI18N
        ProgressIcon.setToolTipText("Progress");
        ProgressIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProgressIconMouseClicked(evt);
            }
        });
        SidePnl.add(ProgressIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 310, -1, -1));

        SettingsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/settings.png"))); // NOI18N
        SettingsIcon.setToolTipText("Settings");
        SettingsIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SettingsIconMouseClicked(evt);
            }
        });
        SidePnl.add(SettingsIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 460, -1, -1));
        SidePnl.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 200, 20));

        LogoutIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/logout.png"))); // NOI18N
        LogoutIcon.setToolTipText("Logout");
        LogoutIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutIconMouseClicked(evt);
            }
        });
        SidePnl.add(LogoutIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 640, -1, -1));

        MainPanel.add(SidePnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        DashboardPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TodaysTasks.setMinimumSize(new java.awt.Dimension(240, 250));
        TodaysTasks.setPreferredSize(new java.awt.Dimension(240, 250));

        TodaysTasksTbl.setAutoCreateRowSorter(true);
        TodaysTasksTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Task Name", "Due At", "Priority", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TodaysTasksTbl.setMinimumSize(new java.awt.Dimension(240, 250));
        TodaysTasksTbl.setPreferredSize(new java.awt.Dimension(240, 250));
        TodaysTasksTbl.setShowGrid(false);
        TodaysTasksTbl.setShowVerticalLines(true);
        TodaysTasks.setViewportView(TodaysTasksTbl);
        if (TodaysTasksTbl.getColumnModel().getColumnCount() > 0) {
            TodaysTasksTbl.getColumnModel().getColumn(1).setPreferredWidth(1);
            TodaysTasksTbl.getColumnModel().getColumn(2).setPreferredWidth(1);
            TodaysTasksTbl.getColumnModel().getColumn(3).setPreferredWidth(1);
        }

        DashboardPnl.add(TodaysTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 670, 200));

        TodaysTasklbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        TodaysTasklbl.setForeground(new java.awt.Color(31, 30, 38));
        TodaysTasklbl.setText("TODAYS TASKS");
        DashboardPnl.add(TodaysTasklbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 180, -1));

        UpcomingTask.setMinimumSize(new java.awt.Dimension(240, 250));
        UpcomingTask.setPreferredSize(new java.awt.Dimension(240, 250));

        UpcomingTaskTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Task Name", "Due Date", "Priority", "Staus"
            }
        ));
        UpcomingTaskTbl.setMinimumSize(new java.awt.Dimension(240, 250));
        UpcomingTaskTbl.setPreferredSize(new java.awt.Dimension(240, 250));
        UpcomingTaskTbl.setShowVerticalLines(true);
        UpcomingTask.setViewportView(UpcomingTaskTbl);
        if (UpcomingTaskTbl.getColumnModel().getColumnCount() > 0) {
            UpcomingTaskTbl.getColumnModel().getColumn(1).setPreferredWidth(5);
            UpcomingTaskTbl.getColumnModel().getColumn(2).setPreferredWidth(5);
            UpcomingTaskTbl.getColumnModel().getColumn(3).setPreferredWidth(5);
        }

        DashboardPnl.add(UpcomingTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 670, 200));

        UpcomingTasklbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        UpcomingTasklbl.setForeground(new java.awt.Color(31, 30, 38));
        UpcomingTasklbl.setText("UPCOMING TASKS");
        DashboardPnl.add(UpcomingTasklbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 220, -1));

        ProgressSummaryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Total Tasks", "Completed", "Pending", "Overdue"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ProgressSummary.setViewportView(ProgressSummaryTbl);

        DashboardPnl.add(ProgressSummary, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 670, 70));

        ProgressSummaryLbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        ProgressSummaryLbl.setForeground(new java.awt.Color(31, 30, 38));
        ProgressSummaryLbl.setText("PROGRESS SUMMARY");
        DashboardPnl.add(ProgressSummaryLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, -1, -1));

        DashBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BACKBROUND.png"))); // NOI18N
        DashBG.setOpaque(true);
        DashboardPnl.add(DashBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 700));

        MainPanel.add(DashboardPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 690, -1));

        HomeworkPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SearchBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        SearchBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        SearchBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });
        HomeworkPnl.add(SearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 5, 30, 30));

        SearchFld.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SearchFld.setForeground(new java.awt.Color(255, 255, 255));
        SearchFld.setToolTipText("Search");
        SearchFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFldActionPerformed(evt);
            }
        });
        HomeworkPnl.add(SearchFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 5, 150, 30));

        HomeworkFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Today", "This Week", "This Month" }));
        HomeworkFilter.setMinimumSize(new java.awt.Dimension(95, 25));
        HomeworkFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeworkFilterActionPerformed(evt);
            }
        });
        HomeworkPnl.add(HomeworkFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 120, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "English", "Math", "FAL", "Life Orientation", "Other" }));
        jComboBox1.setMinimumSize(new java.awt.Dimension(120, 25));
        jComboBox1.setPreferredSize(new java.awt.Dimension(120, 22));
        HomeworkPnl.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jScrollPane4.setPreferredSize(new java.awt.Dimension(459, 402));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Task Name", "Subject", "Due Date", "Priority", "Status"
            }
        ));
        jTable2.setMaximumSize(new java.awt.Dimension(490, 80));
        jTable2.setMinimumSize(new java.awt.Dimension(490, 80));
        jTable2.setPreferredSize(new java.awt.Dimension(490, 80));
        jScrollPane4.setViewportView(jTable2);

        HomeworkPnl.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 670, 310));

        HomeworkBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BACKBROUND.png"))); // NOI18N
        HomeworkPnl.add(HomeworkBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 700));

        MainPanel.add(HomeworkPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 690, 700));

        ProgressPnl.setForeground(new java.awt.Color(31, 30, 38));
        ProgressPnl.setMinimumSize(new java.awt.Dimension(690, 700));
        ProgressPnl.setPreferredSize(new java.awt.Dimension(690, 700));
        ProgressPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Completedlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Completedlbl.setText("Completed: ");
        ProgressPnl.add(Completedlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 30));

        Pendinglbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Pendinglbl.setForeground(new java.awt.Color(210, 210, 210));
        Pendinglbl.setText("Pending:");
        ProgressPnl.add(Pendinglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 150, 30));

        Overduelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Overduelbl.setText("Overdue:");
        ProgressPnl.add(Overduelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 150, 30));

        jSeparator1.setForeground(new java.awt.Color(31, 30, 38));
        ProgressPnl.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 650, 2));

        PieChartPnl.setMinimumSize(new java.awt.Dimension(650, 410));
        PieChartPnl.setPreferredSize(new java.awt.Dimension(650, 410));
        PieChartPnl.setLayout(new javax.swing.BoxLayout(PieChartPnl, javax.swing.BoxLayout.LINE_AXIS));
        ProgressPnl.add(PieChartPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(31, 30, 38));
        ProgressPnl.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 650, 2));

        ProgSumlbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ProgSumlbl.setText("Progress Summary:");
        ProgressPnl.add(ProgSumlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 250, 40));

        ProgCompletelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ProgCompletelbl.setText("✅ Complete: ");
        ProgressPnl.add(ProgCompletelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 150, 30));

        ProgPendinglbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ProgPendinglbl.setText("⌛ Pending:   ");
        ProgressPnl.add(ProgPendinglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 150, 30));

        ProgOverduelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ProgOverduelbl.setText("⚠Overdue:  ");
        ProgressPnl.add(ProgOverduelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 150, 30));

        ProgBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BACKBROUND.png"))); // NOI18N
        ProgressPnl.add(ProgBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 700));

        MainPanel.add(ProgressPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 690, 700));

        SettingsPnl.setMinimumSize(new java.awt.Dimension(690, 700));
        SettingsPnl.setPreferredSize(new java.awt.Dimension(690, 700));
        SettingsPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SettNamelbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SettNamelbl.setText("Name :");
        SettNamelbl.setPreferredSize(new java.awt.Dimension(100, 50));
        SettingsPnl.add(SettNamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        SettSchoolLbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SettSchoolLbl.setText("School:");
        SettSchoolLbl.setPreferredSize(new java.awt.Dimension(100, 50));
        SettingsPnl.add(SettSchoolLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        SettGradelbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SettGradelbl.setText("Grade :");
        SettGradelbl.setMaximumSize(new java.awt.Dimension(100, 50));
        SettGradelbl.setMinimumSize(new java.awt.Dimension(100, 50));
        SettGradelbl.setPreferredSize(new java.awt.Dimension(100, 50));
        SettingsPnl.add(SettGradelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "9", "10", "11", "12" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(150, 50));
        SettingsPnl.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(31, 30, 38));
        jSeparator4.setPreferredSize(new java.awt.Dimension(650, 3));
        SettingsPnl.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        SettThemelbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SettThemelbl.setText("Theme :");
        SettThemelbl.setMaximumSize(new java.awt.Dimension(100, 50));
        SettThemelbl.setMinimumSize(new java.awt.Dimension(100, 50));
        SettThemelbl.setPreferredSize(new java.awt.Dimension(100, 50));
        SettingsPnl.add(SettThemelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 325, -1, -1));

        ThemeSelector.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ThemeSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dark", "Light" }));
        ThemeSelector.setPreferredSize(new java.awt.Dimension(150, 50));
        ThemeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemeSelectorActionPerformed(evt);
            }
        });
        SettingsPnl.add(ThemeSelector, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 325, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(31, 30, 38));
        jSeparator5.setPreferredSize(new java.awt.Dimension(650, 3));
        SettingsPnl.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        SettingsBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BACKBROUND.png"))); // NOI18N
        SettingsPnl.add(SettingsBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        MainPanel.add(SettingsPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DashboardIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardIconMouseClicked
        // TODO add your handling code here:
        themeManager.showDashboardPanel(DashboardPnl, HomeworkPnl, ProgressPnl, SettingsPnl);
    }//GEN-LAST:event_DashboardIconMouseClicked

    private void HomeworkIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeworkIconMouseClicked
        // TODO add your handling code here:
        themeManager.showHomeworkPanel(DashboardPnl, HomeworkPnl, ProgressPnl, SettingsPnl);
    }//GEN-LAST:event_HomeworkIconMouseClicked

    private void ProgressIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProgressIconMouseClicked
        // TODO add your handling code here:
        themeManager.showProgressPanel(DashboardPnl, HomeworkPnl, ProgressPnl, SettingsPnl);

    }//GEN-LAST:event_ProgressIconMouseClicked

    private void SettingsIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SettingsIconMouseClicked
        // TODO add your handling code here:
        themeManager.showSettingsPanel(DashboardPnl, HomeworkPnl, ProgressPnl, SettingsPnl);
    }//GEN-LAST:event_SettingsIconMouseClicked

    private void LogoutIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutIconMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new LoginPage().show(true);
    }//GEN-LAST:event_LogoutIconMouseClicked

    private void SearchFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchFldActionPerformed

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        /*
        private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {
    TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable2.getModel());
    jTable2.setRowSorter(sorter);
    String text = SearchFld.getText();
    if (text.trim().length() == 0) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); // case-insensitive
    }
}


        */
    }
    private void HomeworkFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeworkFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeworkFilterActionPerformed

    private void ThemeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemeSelectorActionPerformed
        // TODO add your handling code here:
        String selectedTheme = (String) ThemeSelector.getSelectedItem();

        // Apply the selected theme
        applyTheme(selectedTheme);
    }//GEN-LAST:event_ThemeSelectorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("\n\n\n\n\n\n\n\t\t\t\t\tFailed to load theme");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Completedlbl;
    private javax.swing.JLabel DashBG;
    private javax.swing.JLabel DashboardIcon;
    private javax.swing.JPanel DashboardPnl;
    private javax.swing.JLabel HomeworkBG;
    private javax.swing.JComboBox<String> HomeworkFilter;
    private javax.swing.JLabel HomeworkIcon;
    private javax.swing.JPanel HomeworkPnl;
    private javax.swing.JLabel LogoutIcon;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel Overduelbl;
    private javax.swing.JLabel Pendinglbl;
    private javax.swing.JPanel PieChartPnl;
    private javax.swing.JLabel ProgBG;
    private javax.swing.JLabel ProgCompletelbl;
    private javax.swing.JLabel ProgOverduelbl;
    private javax.swing.JLabel ProgPendinglbl;
    private javax.swing.JLabel ProgSumlbl;
    private javax.swing.JLabel ProgressIcon;
    private javax.swing.JPanel ProgressPnl;
    private javax.swing.JScrollPane ProgressSummary;
    private javax.swing.JLabel ProgressSummaryLbl;
    private javax.swing.JTable ProgressSummaryTbl;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JTextField SearchFld;
    private javax.swing.JLabel SettGradelbl;
    private javax.swing.JLabel SettNamelbl;
    private javax.swing.JLabel SettSchoolLbl;
    private javax.swing.JLabel SettThemelbl;
    private javax.swing.JLabel SettingsBG;
    private javax.swing.JLabel SettingsIcon;
    private javax.swing.JPanel SettingsPnl;
    private javax.swing.JPanel SidePnl;
    private javax.swing.JComboBox<String> ThemeSelector;
    private javax.swing.JLabel TodaysTasklbl;
    private javax.swing.JScrollPane TodaysTasks;
    private javax.swing.JTable TodaysTasksTbl;
    private javax.swing.JScrollPane UpcomingTask;
    private javax.swing.JTable UpcomingTaskTbl;
    private javax.swing.JLabel UpcomingTasklbl;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
