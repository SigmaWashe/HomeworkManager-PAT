package UI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import Managers.DarkTheme;
import Managers.FileHandler;
import Managers.LightTheme;
import Managers.Task;
import Managers.TaskManager;
import Managers.ThemeManager;
import com.formdev.flatlaf.*;

import java.awt.*;
import java.awt.event.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;

import javax.swing.*;
import javax.swing.table.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.logging.*;

public class MainMenu extends javax.swing.JFrame {
    FileHandler fileHandler = new FileHandler("txtFiles/Tasks.txt");
    ArrayList<Task> tasks = fileHandler.readTaskFile();
    ThemeManager themeManager = new ThemeManager();
    TaskManager taskManager = new TaskManager();
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
        displayTasks(tasks);
        pieChart(tasks);

        themeManager.transparentButton(AddTaskBtn);

    }

    /**
     * Display the tasks onto the JTables
     * @param tasks
     */
    private void displayTasks(ArrayList<Task> tasks) {
        LocalDate today = LocalDate.now();


        ArrayList<Task> todayTasks = new ArrayList<>();
        ArrayList<Task> upcomingTasks = new ArrayList<>();

        // Filter today's and upcoming tasks
        for (Task task : tasks) {
            if (task.getDueDate().equals(today)) {
                todayTasks.add(task);
            } else if (task.getDueDate().isAfter(today)) {
                upcomingTasks.add(task);
            }
        }

        // ----- Today's Tasks -----
        DefaultTableModel todayModel = new DefaultTableModel(new String[]{"Task Name", "Due At", "Priority", "Status"}, 0);
        for (Task task : todayTasks) {
            todayModel.addRow(new Object[]{
                    task.getTaskName(),
                    task.getDueTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                    task.getPriority(),
                    task.getStatus()
            });
        }
        TodaysTasksTbl.setModel(todayModel);

        // ----- Upcoming Tasks -----
        DefaultTableModel upcomingModel = new DefaultTableModel(new String[]{"Task Name", "Due Date", "Priority", "Status"}, 0);
        for (Task task : upcomingTasks) {
            upcomingModel.addRow(new Object[]{
                    task.getTaskName(),
                    task.getDueDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    task.getPriority(),
                    task.getStatus()
            });
        }
        UpcomingTaskTbl.setModel(upcomingModel);

        //  ----- Progress Summary  -----
        int[] summary = taskManager.getProgressSummary();
        DefaultTableModel progressSummary = new DefaultTableModel(new String[]{"Total Tasks", "Complete", "Pending", "Overdue"}, 0);
        progressSummary.addRow(new Object[]{
                summary[0],  // total
                summary[1],  // completed
                summary[2],  // pending
                summary[3]   // overdue
        });
        ProgressSummaryTbl.setModel(progressSummary);

        // ----- Homework Tasks -----
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
        HomeworkTbl.setModel(homeworkModel);

    }

    /**
     * Switches between dak and light theme
     * @param themeName
     */
    private void applyTheme(String themeName) {
        try {
            if ("Dark".equalsIgnoreCase(themeName)) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SidePnl.setBackground(new Color(30, 31, 38));
                // Update icons for the dark theme
                dt.BackgroundDarkTheme(DashBG, HomeworkBG, ProgBG, SettingsBG);
                dt.DashboardDarkTheme(TodaysTasklbl, UpcomingTasklbl, ProgressSummaryLbl);
                dt.ProgressDarkTheme(Completedlbl, Pendinglbl, Overduelbl, PieChartPnl, TotalTasksbl, RateCompletelbl,
                        RatePendinglbl, RateOverduelbl);
                dt.SettingsDarkTheme(Themelbl);
                updateIcons("/darkThemeIcons/");
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR" , "Failed to load theme",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if ("Light".equalsIgnoreCase(themeName)) {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SidePnl.setBackground(new Color(204, 204, 204));
                // Update icons for the light theme
                lt.BackgroundLightTheme(DashBG, HomeworkBG, ProgBG, SettingsBG);
                lt.DashboardLightTheme(TodaysTasklbl, UpcomingTasklbl, ProgressSummaryLbl);
                lt.ProgressLightTheme(Completedlbl, Pendinglbl, Overduelbl, PieChartPnl, TotalTasksbl, RateCompletelbl,
                        RatePendinglbl, RateOverduelbl);
                lt.SettingsLightTheme(Themelbl);
                updateIcons("/lightThemeIcons/");
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR" , "Failed to load theme",
                            JOptionPane.ERROR_MESSAGE);
                }
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

    /**
     * Method to update the icons
     * @param iconPath
     */
    private void updateIcons(String iconPath) {
        DashboardIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "dashboard.png")));
        HomeworkIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "assignment.png")));
        ProgressIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "progress.png")));
        SettingsIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "settings.png")));
        LogoutIcon.setIcon(new ImageIcon(getClass().getResource(iconPath + "logout.png")));
    }

    /**
     * Method to display all the data on the pie chart panel
     * @param tasks
     */
    private void pieChart(ArrayList<Task> tasks) {

        // Get the progress summary using the getProgressSummary method
        int[] progressSummary = taskManager.getProgressSummary();

        int total = progressSummary[0];
        int complete = progressSummary[1];
        int pending = progressSummary[2];
        int overdue = progressSummary[3];

        DefaultPieDataset pieChart = new DefaultPieDataset();
        pieChart.setValue("Completed Tasks", complete);
        pieChart.setValue("Pending Tasks", pending);
        pieChart.setValue("Overdue Tasks", overdue);

        JFreeChart chart = ChartFactory.createPieChart("Tasks Pie Chart", pieChart, true, true, false);

        // ... Chart styling code
        chart.setBackgroundPaint(new Color(31, 30, 38));
        chart.getTitle().setPaint(new Color(204, 204, 204));
        chart.getLegend().setBackgroundPaint(new Color(31, 30, 38));
        chart.getLegend().setItemPaint(new Color(204, 204, 204));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(31, 30, 38));
        plot.setOutlineVisible(false);
        plot.setLabelBackgroundPaint(new Color(31, 30, 38));
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelPaint(new Color(204, 204, 204));
        plot.setShadowPaint(null);

        plot.setSectionPaint("Completed Tasks", new Color(60, 160, 90)); // Green
        plot.setSectionPaint("Pending Tasks", new Color(220, 180, 60)); // Yellow
        plot.setSectionPaint("Overdue Tasks", new Color(180, 50, 50)); // Red

        plot.setForegroundAlpha(0.9f);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(31, 30, 38));

        double rateCompleted = Math.round(((double) complete / total) * 1000) / 10.0;
        double ratePending = Math.round(((double) pending / total) * 1000) / 10.0;
        double rateOverdue = Math.round(((double) complete / total) * 1000) / 10.0;

        Completedlbl.setText("Completed: " + complete);
        Pendinglbl.setText("Pending: " + pending);
        Overduelbl.setText("Overdue: " + overdue);

        TotalTasksbl.setText("Total Tasks: " + total);
        RateCompletelbl.setText("Rate Completed: "  + rateCompleted + "%");
        RatePendinglbl.setText("Rate Pending:   " + ratePending + "%");
        RateOverduelbl.setText("Rate Overdue:  " + rateOverdue + "%");

        PieChartPnl.removeAll();
        PieChartPnl.setLayout(new BorderLayout());
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
        ProgressSunnary = new javax.swing.JScrollPane();
        ProgressSummaryTbl = new javax.swing.JTable();
        ProgressSummaryLbl = new javax.swing.JLabel();
        DashBG = new javax.swing.JLabel();
        HomeworkPnl = new javax.swing.JPanel();
        HomeworkFilter = new javax.swing.JComboBox<>();
        Homework = new javax.swing.JScrollPane();
        HomeworkTbl = new javax.swing.JTable();
        AddTaskBtn = new javax.swing.JButton();
        HomeworkBG = new javax.swing.JLabel();
        ProgressPnl = new javax.swing.JPanel();
        Completedlbl = new javax.swing.JLabel();
        Pendinglbl = new javax.swing.JLabel();
        Overduelbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PieChartPnl = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        TotalTasksbl = new javax.swing.JLabel();
        RateCompletelbl = new javax.swing.JLabel();
        RatePendinglbl = new javax.swing.JLabel();
        RateOverduelbl = new javax.swing.JLabel();
        ProgBG = new javax.swing.JLabel();
        SettingsPnl = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        Themelbl = new javax.swing.JLabel();
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
        TodaysTasks.setPreferredSize(new java.awt.Dimension(670, 200));

        TodaysTasksTbl.setAutoCreateRowSorter(true);
        TodaysTasksTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        TodaysTasksTbl.setMaximumSize(null);
        TodaysTasksTbl.setMinimumSize(null);
        TodaysTasksTbl.setPreferredSize(new java.awt.Dimension(670, 200));
        TodaysTasksTbl.setShowGrid(false);
        TodaysTasksTbl.setShowVerticalLines(true);
        TodaysTasks.setViewportView(TodaysTasksTbl);
        if (TodaysTasksTbl.getColumnModel().getColumnCount() > 0) {
            TodaysTasksTbl.getColumnModel().getColumn(1).setPreferredWidth(1);
            TodaysTasksTbl.getColumnModel().getColumn(2).setPreferredWidth(1);
            TodaysTasksTbl.getColumnModel().getColumn(3).setPreferredWidth(1);
        }

        DashboardPnl.add(TodaysTasks, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        TodaysTasklbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        TodaysTasklbl.setForeground(new java.awt.Color(31, 30, 38));
        TodaysTasklbl.setText("TODAYS TASKS");
        DashboardPnl.add(TodaysTasklbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 180, -1));

        UpcomingTask.setMinimumSize(new java.awt.Dimension(240, 250));
        UpcomingTask.setPreferredSize(new java.awt.Dimension(670, 200));

        UpcomingTaskTbl.setAutoCreateRowSorter(true);
        UpcomingTaskTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task Name", "Due Date", "Priority", "Staus"
            }
        ));
        UpcomingTaskTbl.setMaximumSize(null);
        UpcomingTaskTbl.setMinimumSize(null);
        UpcomingTaskTbl.setPreferredSize(new java.awt.Dimension(670, 200));
        UpcomingTaskTbl.setShowVerticalLines(true);
        UpcomingTask.setViewportView(UpcomingTaskTbl);
        if (UpcomingTaskTbl.getColumnModel().getColumnCount() > 0) {
            UpcomingTaskTbl.getColumnModel().getColumn(1).setPreferredWidth(5);
            UpcomingTaskTbl.getColumnModel().getColumn(2).setPreferredWidth(5);
            UpcomingTaskTbl.getColumnModel().getColumn(3).setPreferredWidth(5);
        }

        DashboardPnl.add(UpcomingTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        UpcomingTasklbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        UpcomingTasklbl.setForeground(new java.awt.Color(31, 30, 38));
        UpcomingTasklbl.setText("UPCOMING TASKS");
        DashboardPnl.add(UpcomingTasklbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 220, -1));

        ProgressSunnary.setPreferredSize(new java.awt.Dimension(670, 50));

        ProgressSummaryTbl.setAutoCreateRowSorter(true);
        ProgressSummaryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Total Tasks", "Completed", "Pending", "Overdue"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProgressSummaryTbl.setPreferredSize(new java.awt.Dimension(670, 30));
        ProgressSummaryTbl.setRowHeight(30);
        ProgressSummaryTbl.setShowGrid(true);
        ProgressSunnary.setViewportView(ProgressSummaryTbl);
        if (ProgressSummaryTbl.getColumnModel().getColumnCount() > 0) {
            ProgressSummaryTbl.getColumnModel().getColumn(0).setResizable(false);
            ProgressSummaryTbl.getColumnModel().getColumn(1).setResizable(false);
            ProgressSummaryTbl.getColumnModel().getColumn(2).setResizable(false);
            ProgressSummaryTbl.getColumnModel().getColumn(3).setResizable(false);
        }

        DashboardPnl.add(ProgressSunnary, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 670, 60));

        ProgressSummaryLbl.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        ProgressSummaryLbl.setForeground(new java.awt.Color(31, 30, 38));
        ProgressSummaryLbl.setText("PROGRESS SUMMARY");
        DashboardPnl.add(ProgressSummaryLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, -1, -1));

        DashBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BACKBROUND.png"))); // NOI18N
        DashBG.setOpaque(true);
        DashboardPnl.add(DashBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 700));

        MainPanel.add(DashboardPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 690, -1));

        HomeworkPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HomeworkFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Today", "Upcoming" }));
        HomeworkFilter.setMinimumSize(new java.awt.Dimension(95, 25));
        HomeworkFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeworkFilterActionPerformed(evt);
            }
        });
        HomeworkPnl.add(HomeworkFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 140, -1));

        Homework.setPreferredSize(new java.awt.Dimension(670, 350));

        HomeworkTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task Name", "Subject", "Due Date", "Priority", "Status"
            }
        ));
        HomeworkTbl.setMaximumSize(new java.awt.Dimension(490, 80));
        HomeworkTbl.setMinimumSize(new java.awt.Dimension(490, 80));
        HomeworkTbl.setPreferredSize(new java.awt.Dimension(670, 350));
        Homework.setViewportView(HomeworkTbl);

        HomeworkPnl.add(Homework, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        AddTaskBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        AddTaskBtn.setPreferredSize(new java.awt.Dimension(50, 50));
        AddTaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTaskBtnActionPerformed(evt);
            }
        });
        HomeworkPnl.add(AddTaskBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, -1, -1));

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
        Pendinglbl.setText("Pending: ");
        ProgressPnl.add(Pendinglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 150, 30));

        Overduelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Overduelbl.setText("Overdue:");
        ProgressPnl.add(Overduelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 150, 30));

        jSeparator1.setForeground(new java.awt.Color(31, 30, 38));
        ProgressPnl.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 650, 2));

        PieChartPnl.setBackground(new Color(0,0,0,0));
        PieChartPnl.setMinimumSize(new java.awt.Dimension(650, 410));
        PieChartPnl.setPreferredSize(new java.awt.Dimension(650, 410));
        PieChartPnl.setLayout(new javax.swing.BoxLayout(PieChartPnl, javax.swing.BoxLayout.LINE_AXIS));
        ProgressPnl.add(PieChartPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(31, 30, 38));
        ProgressPnl.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 650, 2));

        TotalTasksbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TotalTasksbl.setText("Total Tasks: ");
        ProgressPnl.add(TotalTasksbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 220, 40));

        RateCompletelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RateCompletelbl.setText("✅ Complete: ");
        ProgressPnl.add(RateCompletelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 220, 30));

        RatePendinglbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RatePendinglbl.setText("⌛ Pending:   ");
        ProgressPnl.add(RatePendinglbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 620, 210, 30));

        RateOverduelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RateOverduelbl.setText("⚠Overdue:  ");
        ProgressPnl.add(RateOverduelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 620, 200, 30));

        ProgBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BACKBROUND.png"))); // NOI18N
        ProgressPnl.add(ProgBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 700));

        MainPanel.add(ProgressPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 690, 700));

        SettingsPnl.setMinimumSize(new java.awt.Dimension(690, 700));
        SettingsPnl.setPreferredSize(new java.awt.Dimension(690, 700));
        SettingsPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator4.setForeground(new java.awt.Color(31, 30, 38));
        jSeparator4.setPreferredSize(new java.awt.Dimension(650, 3));
        SettingsPnl.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        Themelbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Themelbl.setText("Theme :");
        Themelbl.setMaximumSize(new java.awt.Dimension(100, 50));
        Themelbl.setMinimumSize(new java.awt.Dimension(100, 50));
        Themelbl.setPreferredSize(new java.awt.Dimension(100, 50));
        SettingsPnl.add(Themelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        ThemeSelector.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ThemeSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dark", "Light" }));
        ThemeSelector.setPreferredSize(new java.awt.Dimension(150, 50));
        ThemeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemeSelectorActionPerformed(evt);
            }
        });
        SettingsPnl.add(ThemeSelector, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(31, 30, 38));
        jSeparator5.setPreferredSize(new java.awt.Dimension(650, 3));
        SettingsPnl.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, -1));

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
        displayTasks(tasks);
        pieChart(tasks);
    }//GEN-LAST:event_DashboardIconMouseClicked

    private void HomeworkIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeworkIconMouseClicked
        // TODO add your handling code here:
        themeManager.showHomeworkPanel(DashboardPnl, HomeworkPnl, ProgressPnl, SettingsPnl);
        displayTasks(tasks);
        pieChart(tasks);
    }//GEN-LAST:event_HomeworkIconMouseClicked

    private void ProgressIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProgressIconMouseClicked
        // TODO add your handling code here:
        themeManager.showProgressPanel(DashboardPnl, HomeworkPnl, ProgressPnl, SettingsPnl);
        displayTasks(tasks);
        pieChart(tasks);
    }//GEN-LAST:event_ProgressIconMouseClicked

    private void SettingsIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SettingsIconMouseClicked
        // TODO add your handling code here:
        themeManager.showSettingsPanel(DashboardPnl, HomeworkPnl, ProgressPnl, SettingsPnl);
        displayTasks(tasks);
        pieChart(tasks);
    }//GEN-LAST:event_SettingsIconMouseClicked

    private void LogoutIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutIconMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new LoginPage().show(true);
    }//GEN-LAST:event_LogoutIconMouseClicked

    private void HomeworkFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeworkFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeworkFilterActionPerformed

    private void ThemeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemeSelectorActionPerformed
        // TODO add your handling code here:
        String selectedTheme = (String) ThemeSelector.getSelectedItem();

        // Apply the selected theme
        applyTheme(selectedTheme);
    }//GEN-LAST:event_ThemeSelectorActionPerformed

    private void AddTaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTaskBtnActionPerformed
        // TODO add your handling code here:
        new TaskFrame().setVisible(true);
    }//GEN-LAST:event_AddTaskBtnActionPerformed
    
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
            JOptionPane.showMessageDialog(null, "ERROR" , "Failed to load theme", JOptionPane.ERROR_MESSAGE);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddTaskBtn;
    private javax.swing.JLabel Completedlbl;
    private javax.swing.JLabel DashBG;
    private javax.swing.JLabel DashboardIcon;
    private javax.swing.JPanel DashboardPnl;
    private javax.swing.JScrollPane Homework;
    private javax.swing.JLabel HomeworkBG;
    private javax.swing.JComboBox<String> HomeworkFilter;
    private javax.swing.JLabel HomeworkIcon;
    private javax.swing.JPanel HomeworkPnl;
    private javax.swing.JTable HomeworkTbl;
    private javax.swing.JLabel LogoutIcon;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel Overduelbl;
    private javax.swing.JLabel Pendinglbl;
    private javax.swing.JPanel PieChartPnl;
    private javax.swing.JLabel ProgBG;
    private javax.swing.JLabel ProgressIcon;
    private javax.swing.JPanel ProgressPnl;
    private javax.swing.JLabel ProgressSummaryLbl;
    private javax.swing.JTable ProgressSummaryTbl;
    private javax.swing.JScrollPane ProgressSunnary;
    private javax.swing.JLabel RateCompletelbl;
    private javax.swing.JLabel RateOverduelbl;
    private javax.swing.JLabel RatePendinglbl;
    private javax.swing.JLabel SettingsBG;
    private javax.swing.JLabel SettingsIcon;
    private javax.swing.JPanel SettingsPnl;
    private javax.swing.JPanel SidePnl;
    private javax.swing.JComboBox<String> ThemeSelector;
    private javax.swing.JLabel Themelbl;
    private javax.swing.JLabel TodaysTasklbl;
    private javax.swing.JScrollPane TodaysTasks;
    private javax.swing.JTable TodaysTasksTbl;
    private javax.swing.JLabel TotalTasksbl;
    private javax.swing.JScrollPane UpcomingTask;
    private javax.swing.JTable UpcomingTaskTbl;
    private javax.swing.JLabel UpcomingTasklbl;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration//GEN-END:variables
}
