package UI;


import Managers.FileHandler;
import Managers.Task;
import Managers.TaskUtils;
import Managers.TaskManager;
import Managers.ThemeManager;
import com.formdev.flatlaf.*;
import com.toedter.calendar.*;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

public class TaskFrame extends javax.swing.JFrame {

    ThemeManager themeManager = new ThemeManager();
    TaskManager taskManager = new TaskManager();
    FileHandler fileHandler = new FileHandler("txtFiles/Tasks.txt");
    ArrayList<Task> tasks = fileHandler.readTaskFile();


    /**
     * Creates new form TaskFrame
     */
    public TaskFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        themeManager.transparentButton(EditTaskSearchBtn);
        themeManager.transparentButton(DeleteTaskSearchBtn);
        themeManager.transparentButton(MarkDoneSearchBtn);
        
        clearInputFields(TaskName, Subject, DueDate, HourChooser, MinuteChooser, PriorityChooser, StatusChooser);
        clearInputFields(TaskName, Subject, DueDate, EditTaskDueHour, EditTaskDueMinute, EditTaskPriority, EditTaskStatus);
        
        /// Show AddTaskPnl by default
        setPanel(AddTaskPnl, new Dimension(450, 500));
        this.pack();
        
        themeManager.addPlaceholder(TaskName, "e.g. Literary Essay");
        themeManager.addPlaceholder(Subject, "e.g. English");
        
        themeManager.addPlaceholder(EditTaskSearchFld, "Search");
        themeManager.addPlaceholder(EditTaskNamefld, "e.g. Literary Essay");
        themeManager.addPlaceholder(EditTaskSubjectfld, "e.g. English");
        
        themeManager.addPlaceholder(DeleteTaskSearchFld, "Search");
        themeManager.addPlaceholder(MarkDoneSearchFld, "Search");
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose(); // Close the frame
                    new MainMenu().setVisible(true);
                }
            }
        });
        
        setFocusable(true);
        requestFocusInWindow();
        
    }

    /**
     * Clears the task labels on the panel
     * @param taskIDlbl
     * @param taskNamelbl
     * @param subjectlbl
     * @param dueDatelbl
     * @param dueTimelbl
     * @param prioritylbl
     * @param statuslbl
     */
    private void clearTaskLabels(JLabel taskIDlbl, JLabel taskNamelbl, JLabel subjectlbl, JLabel dueDatelbl,
                                 JLabel dueTimelbl, JLabel prioritylbl, JLabel statuslbl) {
        taskIDlbl.setText("Task ID       :  ");
        taskIDlbl.setForeground(new Color(204, 204, 204));
        taskNamelbl.setText("Task Name :  ");
        taskNamelbl.setForeground(new Color(204, 204, 204));
        subjectlbl.setText("Subject       :  ");
        subjectlbl.setForeground(new Color(204, 204, 204));
        dueDatelbl.setText("Due Date    :  ");
        dueDatelbl.setForeground(new Color(204, 204, 204));
        dueTimelbl.setText("Due Time    :  ");
        dueTimelbl.setForeground(new Color(204, 204, 204));
        prioritylbl.setText("Priority      :  ");
        prioritylbl.setForeground(new Color(204, 204, 204));
        statuslbl.setText("Status        :  ");
        statuslbl.setForeground(new Color(204, 204, 204));
    }

    /**
     * Clears the input fields on the panel
     * @param taskName
     * @param subject
     * @param dateChooser
     * @param hourChooser
     * @param minuteChooser
     * @param priorityChooser
     * @param statusChooser
     */
    private void clearInputFields(JTextField taskName, JTextField subject, JDateChooser dateChooser, JComboBox hourChooser,
                                  JComboBox minuteChooser, JComboBox priorityChooser, JComboBox statusChooser) {
        taskName.setText("");
        taskName.setForeground(new Color(204, 204, 204));
        subject.setText("");
        subject.setForeground(new Color(204, 204, 204));
        dateChooser.setDate(null);
        dateChooser.setForeground(new Color(204, 204, 204));
        hourChooser.setSelectedIndex(0);
        hourChooser.setForeground(new Color(204, 204, 204));
        minuteChooser.setSelectedIndex(0);
        minuteChooser.setForeground(new Color(204, 204, 204));
        priorityChooser.setSelectedIndex(0);
        priorityChooser.setForeground(new Color(204, 204, 204));
        statusChooser.setSelectedIndex(0);
        statusChooser.setForeground(new Color(204, 204, 204));
    }

    /**
     * Sets the selected panel visible
     * @param visblePanel
     * @param frameSize
     */
    private void setPanel(JPanel visblePanel, Dimension frameSize) {
        AddTaskPnl.setVisible(false);
        EditTaskPnl.setVisible(false);
        DeleteTaskPnl.setVisible(false);
        MarkDonePnl.setVisible(false);

        visblePanel.setVisible(true);
        this.setPreferredSize(frameSize);
        this.setSize(frameSize);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddTaskPnl = new javax.swing.JPanel();
        TaskNamelbl = new javax.swing.JLabel();
        TaskName = new javax.swing.JTextField();
        Subjectlbl = new javax.swing.JLabel();
        Subject = new javax.swing.JTextField();
        DueDatelbl = new javax.swing.JLabel();
        DueDate = new com.toedter.calendar.JDateChooser();
        DueTime = new javax.swing.JLabel();
        HourChooser = new javax.swing.JComboBox<>();
        MinuteChooser = new javax.swing.JComboBox<>();
        Prioritylbl = new javax.swing.JLabel();
        PriorityChooser = new javax.swing.JComboBox<>();
        Statuslbl = new javax.swing.JLabel();
        StatusChooser = new javax.swing.JComboBox<>();
        AddTaskBtn = new javax.swing.JButton();
        AddTaskBG = new javax.swing.JLabel();
        EditTaskPnl = new javax.swing.JPanel();
        EditTaskSearchBtn = new javax.swing.JButton();
        EditTaskSearchFld = new javax.swing.JTextField();
        EditTaskIDlbl = new javax.swing.JLabel();
        EditTaskNamefld = new javax.swing.JTextField();
        EditTaskNamelbl = new javax.swing.JLabel();
        EditTaskSubjectfld = new javax.swing.JTextField();
        EditSubjectlbl = new javax.swing.JLabel();
        EditTaskDueDate = new com.toedter.calendar.JDateChooser();
        EditDueDatelbl = new javax.swing.JLabel();
        EditTaskDueHour = new javax.swing.JComboBox<>();
        EditTaskDueMinute = new javax.swing.JComboBox<>();
        EditDueTimelbl = new javax.swing.JLabel();
        EditTaskPriority = new javax.swing.JComboBox<>();
        EditPrioritylbl = new javax.swing.JLabel();
        EditTaskStatus = new javax.swing.JComboBox<>();
        EditStatuslbl = new javax.swing.JLabel();
        EditTaskBtn = new javax.swing.JButton();
        BG1 = new javax.swing.JLabel();
        DeleteTaskPnl = new javax.swing.JPanel();
        DeleteTaskSearchBtn = new javax.swing.JButton();
        DeleteTaskSearchFld = new javax.swing.JTextField();
        TaskID1 = new javax.swing.JLabel();
        TaskNamelbl1 = new javax.swing.JLabel();
        Subjectlbl1 = new javax.swing.JLabel();
        DueDatelbl1 = new javax.swing.JLabel();
        DueTime1 = new javax.swing.JLabel();
        Prioritylbl1 = new javax.swing.JLabel();
        Statuslbl1 = new javax.swing.JLabel();
        DeleteTaskBtn = new javax.swing.JButton();
        DeleteTaskBG = new javax.swing.JLabel();
        MarkDonePnl = new javax.swing.JPanel();
        MarkDoneSearchBtn = new javax.swing.JButton();
        MarkDoneSearchFld = new javax.swing.JTextField();
        TaskID2 = new javax.swing.JLabel();
        TaskNamelbl2 = new javax.swing.JLabel();
        Subjectlbl2 = new javax.swing.JLabel();
        DueDatelbl2 = new javax.swing.JLabel();
        DueTime2 = new javax.swing.JLabel();
        Prioritylbl2 = new javax.swing.JLabel();
        Statuslbl2 = new javax.swing.JLabel();
        MarkDoneBtn = new javax.swing.JButton();
        MarkDoneBG = new javax.swing.JLabel();
        SidePnl = new javax.swing.JPanel();
        AddTaskIcn = new javax.swing.JLabel();
        EditTaskIcn = new javax.swing.JLabel();
        DeleteTaskIcn = new javax.swing.JLabel();
        MarkDoneTaskIcn = new javax.swing.JLabel();
        TaskFrameBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(550, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddTaskPnl.setName(""); // NOI18N
        AddTaskPnl.setOpaque(false);
        AddTaskPnl.setPreferredSize(new java.awt.Dimension(350, 500));
        AddTaskPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TaskNamelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TaskNamelbl.setText("Task Name :");
        TaskNamelbl.setPreferredSize(new java.awt.Dimension(110, 30));
        AddTaskPnl.add(TaskNamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 110, 40));

        TaskName.setPreferredSize(new java.awt.Dimension(210, 40));
        AddTaskPnl.add(TaskName, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 25, -1, -1));

        Subjectlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Subjectlbl.setText("Subject       :");
        Subjectlbl.setPreferredSize(new java.awt.Dimension(120, 40));
        AddTaskPnl.add(Subjectlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 110, -1));

        Subject.setPreferredSize(new java.awt.Dimension(210, 40));
        AddTaskPnl.add(Subject, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 75, -1, -1));

        DueDatelbl.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        DueDatelbl.setText("Due Date    :");
        DueDatelbl.setPreferredSize(new java.awt.Dimension(110, 40));
        AddTaskPnl.add(DueDatelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 125, 110, -1));

        DueDate.setToolTipText("Date : dd/MM/yyyy");
        DueDate.setMinSelectableDate(new java.util.Date(1577829600000L));
        AddTaskPnl.add(DueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 125, 210, 40));

        DueTime.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        DueTime.setText("Due Time    :");
        DueTime.setPreferredSize(new java.awt.Dimension(110, 40));
        AddTaskPnl.add(DueTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 175, 110, -1));

        HourChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        HourChooser.setToolTipText("Hours");
        HourChooser.setPreferredSize(new java.awt.Dimension(70, 40));
        AddTaskPnl.add(HourChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 175, -1, -1));

        MinuteChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        MinuteChooser.setToolTipText("Minutes");
        MinuteChooser.setPreferredSize(new java.awt.Dimension(70, 40));
        AddTaskPnl.add(MinuteChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 175, -1, -1));

        Prioritylbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Prioritylbl.setText("Priority      :");
        AddTaskPnl.add(Prioritylbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 225, 110, 40));

        PriorityChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "High", "Medium", "Low" }));
        AddTaskPnl.add(PriorityChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 225, 140, 40));

        Statuslbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Statuslbl.setText("Status        :");
        AddTaskPnl.add(Statuslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, 110, 40));

        StatusChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Complete", "Pending", "Overdue" }));
        AddTaskPnl.add(StatusChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 275, 140, 40));

        AddTaskBtn.setBackground(new Color(30,31,38, 128));
        AddTaskBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        AddTaskBtn.setText("Add Task");
        AddTaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTaskBtnActionPerformed(evt);
            }
        });
        AddTaskPnl.add(AddTaskBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, -1, 40));

        AddTaskBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png"))); // NOI18N
        AddTaskBG.setToolTipText("");
        AddTaskPnl.add(AddTaskBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 400, 550));

        getContentPane().add(AddTaskPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        EditTaskPnl.setPreferredSize(new java.awt.Dimension(450, 550));
        EditTaskPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        EditTaskSearchBtn.setForeground(new java.awt.Color(30, 31, 38));
        EditTaskSearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/search.png"))); // NOI18N
        EditTaskSearchBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        EditTaskSearchBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        EditTaskSearchBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        EditTaskSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditTaskSearchBtnActionPerformed(evt);
            }
        });
        EditTaskPnl.add(EditTaskSearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 10, -1, -1));

        EditTaskSearchFld.setBackground(new Color(40,40,40,128));
        EditTaskSearchFld.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditTaskSearchFld.setForeground(new java.awt.Color(255, 255, 255));
        EditTaskSearchFld.setToolTipText("Search");
        EditTaskSearchFld.setPreferredSize(new java.awt.Dimension(150, 30));
        EditTaskSearchFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditTaskSearchFldActionPerformed(evt);
            }
        });
        EditTaskPnl.add(EditTaskSearchFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 10, -1, -1));

        EditTaskIDlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditTaskIDlbl.setText("Task ID       :");
        EditTaskIDlbl.setPreferredSize(new java.awt.Dimension(300, 40));
        EditTaskPnl.add(EditTaskIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        EditTaskNamefld.setPreferredSize(new java.awt.Dimension(210, 40));
        EditTaskPnl.add(EditTaskNamefld, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 190, -1));

        EditTaskNamelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditTaskNamelbl.setText("Task Name :");
        EditTaskNamelbl.setPreferredSize(new java.awt.Dimension(300, 40));
        EditTaskPnl.add(EditTaskNamelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        EditTaskSubjectfld.setPreferredSize(new java.awt.Dimension(210, 40));
        EditTaskPnl.add(EditTaskSubjectfld, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 190, -1));

        EditSubjectlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditSubjectlbl.setText("Subject       :");
        EditSubjectlbl.setPreferredSize(new java.awt.Dimension(300, 40));
        EditTaskPnl.add(EditSubjectlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        EditTaskDueDate.setToolTipText("Date : dd/MM/yyyy");
        EditTaskDueDate.setMinSelectableDate(new java.util.Date(1577829600000L));
        EditTaskPnl.add(EditTaskDueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 190, 40));

        EditDueDatelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditDueDatelbl.setText("Due Date    :");
        EditDueDatelbl.setPreferredSize(new java.awt.Dimension(300, 40));
        EditTaskPnl.add(EditDueDatelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        EditTaskDueHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        EditTaskDueHour.setToolTipText("Hours");
        EditTaskDueHour.setPreferredSize(new java.awt.Dimension(70, 40));
        EditTaskPnl.add(EditTaskDueHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        EditTaskDueMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        EditTaskDueMinute.setToolTipText("Minutes");
        EditTaskDueMinute.setPreferredSize(new java.awt.Dimension(70, 40));
        EditTaskPnl.add(EditTaskDueMinute, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, -1, -1));

        EditDueTimelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditDueTimelbl.setText("Due Time   :");
        EditDueTimelbl.setPreferredSize(new java.awt.Dimension(300, 40));
        EditTaskPnl.add(EditDueTimelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        EditTaskPriority.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "High", "Medium", "Low" }));
        EditTaskPnl.add(EditTaskPriority, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 190, 40));

        EditPrioritylbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditPrioritylbl.setText("Priority      :");
        EditPrioritylbl.setPreferredSize(new java.awt.Dimension(300, 40));
        EditTaskPnl.add(EditPrioritylbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        EditTaskStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Complete", "Pending", "Overdue" }));
        EditTaskPnl.add(EditTaskStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 190, 40));

        EditStatuslbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditStatuslbl.setText("Status        :");
        EditStatuslbl.setPreferredSize(new java.awt.Dimension(300, 40));
        EditTaskPnl.add(EditStatuslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        EditTaskBtn.setBackground(new Color(30,31,38, 128));
        EditTaskBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EditTaskBtn.setText("Edit Tasks");
        EditTaskBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        EditTaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditTaskBtnActionPerformed(evt);
            }
        });
        EditTaskPnl.add(EditTaskBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, -1, -1));

        BG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png"))); // NOI18N
        BG1.setToolTipText("");
        EditTaskPnl.add(BG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 500, 550));

        getContentPane().add(EditTaskPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        DeleteTaskPnl.setPreferredSize(new java.awt.Dimension(400, 500));
        DeleteTaskPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DeleteTaskSearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/search.png"))); // NOI18N
        DeleteTaskSearchBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        DeleteTaskSearchBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        DeleteTaskSearchBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        DeleteTaskSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteTaskSearchBtnActionPerformed(evt);
            }
        });
        DeleteTaskPnl.add(DeleteTaskSearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 10, -1, -1));

        DeleteTaskSearchFld.setBackground(new Color(40,40,40,128));
        DeleteTaskSearchFld.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteTaskSearchFld.setForeground(new java.awt.Color(255, 255, 255));
        DeleteTaskSearchFld.setToolTipText("Search");
        DeleteTaskSearchFld.setPreferredSize(new java.awt.Dimension(150, 30));
        DeleteTaskSearchFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteTaskSearchFldActionPerformed(evt);
            }
        });
        DeleteTaskPnl.add(DeleteTaskSearchFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 10, -1, -1));

        TaskID1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TaskID1.setText("Task ID       :");
        TaskID1.setPreferredSize(new java.awt.Dimension(300, 40));
        DeleteTaskPnl.add(TaskID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        TaskNamelbl1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TaskNamelbl1.setText("Task Name :");
        TaskNamelbl1.setPreferredSize(new java.awt.Dimension(110, 30));
        DeleteTaskPnl.add(TaskNamelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 400, 40));

        Subjectlbl1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Subjectlbl1.setText("Subject       :");
        Subjectlbl1.setPreferredSize(new java.awt.Dimension(120, 40));
        DeleteTaskPnl.add(Subjectlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 380, -1));

        DueDatelbl1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        DueDatelbl1.setText("Due Date    :");
        DueDatelbl1.setPreferredSize(new java.awt.Dimension(110, 40));
        DeleteTaskPnl.add(DueDatelbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 380, -1));

        DueTime1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        DueTime1.setText("Due Time    :");
        DueTime1.setPreferredSize(new java.awt.Dimension(110, 40));
        DeleteTaskPnl.add(DueTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 380, -1));

        Prioritylbl1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Prioritylbl1.setText("Priority      :");
        Prioritylbl1.setPreferredSize(new java.awt.Dimension(98, 40));
        DeleteTaskPnl.add(Prioritylbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 380, 40));

        Statuslbl1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Statuslbl1.setText("Status        :");
        DeleteTaskPnl.add(Statuslbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 380, 40));

        DeleteTaskBtn.setBackground(new Color(30,31,38, 128));
        DeleteTaskBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        DeleteTaskBtn.setText("DELETE TASK");
        DeleteTaskBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        DeleteTaskBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteTaskBtnActionPerformed(evt);
            }
        });
        DeleteTaskPnl.add(DeleteTaskBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 405, -1, -1));

        DeleteTaskBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png"))); // NOI18N
        DeleteTaskBG.setToolTipText("");
        DeleteTaskPnl.add(DeleteTaskBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 450, 550));

        getContentPane().add(DeleteTaskPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        MarkDonePnl.setPreferredSize(new java.awt.Dimension(400, 500));
        MarkDonePnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MarkDoneSearchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/darkThemeIcons/search.png"))); // NOI18N
        MarkDoneSearchBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        MarkDoneSearchBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        MarkDoneSearchBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        MarkDoneSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarkDoneSearchBtnActionPerformed(evt);
            }
        });
        MarkDonePnl.add(MarkDoneSearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 10, -1, -1));

        MarkDoneSearchFld.setBackground(new Color(40,40,40,128));
        MarkDoneSearchFld.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MarkDoneSearchFld.setForeground(new java.awt.Color(255, 255, 255));
        MarkDoneSearchFld.setToolTipText("Search");
        MarkDoneSearchFld.setPreferredSize(new java.awt.Dimension(150, 30));
        MarkDoneSearchFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarkDoneSearchFldActionPerformed(evt);
            }
        });
        MarkDonePnl.add(MarkDoneSearchFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 10, -1, -1));

        TaskID2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TaskID2.setText("Task ID        :");
        TaskID2.setPreferredSize(new java.awt.Dimension(380, 40));
        MarkDonePnl.add(TaskID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        TaskNamelbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TaskNamelbl2.setText("Task Name :");
        TaskNamelbl2.setPreferredSize(new java.awt.Dimension(110, 30));
        MarkDonePnl.add(TaskNamelbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 400, 40));

        Subjectlbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Subjectlbl2.setText("Subject       :");
        Subjectlbl2.setPreferredSize(new java.awt.Dimension(120, 40));
        MarkDonePnl.add(Subjectlbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 700, -1));

        DueDatelbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        DueDatelbl2.setText("Due Date    :");
        DueDatelbl2.setPreferredSize(new java.awt.Dimension(110, 40));
        MarkDonePnl.add(DueDatelbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 380, -1));

        DueTime2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        DueTime2.setText("Due Time    :");
        DueTime2.setPreferredSize(new java.awt.Dimension(110, 40));
        MarkDonePnl.add(DueTime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 380, -1));

        Prioritylbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Prioritylbl2.setText("Priority      :");
        Prioritylbl2.setPreferredSize(new java.awt.Dimension(98, 40));
        MarkDonePnl.add(Prioritylbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 380, 40));

        Statuslbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Statuslbl2.setText("Status        :");
        MarkDonePnl.add(Statuslbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 380, 40));

        MarkDoneBtn.setBackground(new Color(30,31,38, 128));
        MarkDoneBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MarkDoneBtn.setText("Mark Done");
        MarkDoneBtn.setPreferredSize(new java.awt.Dimension(150, 40));
        MarkDoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarkDoneBtnActionPerformed(evt);
            }
        });
        MarkDonePnl.add(MarkDoneBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 405, -1, -1));

        MarkDoneBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png"))); // NOI18N
        MarkDoneBG.setToolTipText("");
        MarkDonePnl.add(MarkDoneBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 450, 550));

        getContentPane().add(MarkDonePnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        SidePnl.setBackground(new java.awt.Color(31, 30, 38));
        SidePnl.setToolTipText("");
        SidePnl.setAutoscrolls(true);
        SidePnl.setMaximumSize(new java.awt.Dimension(60, 700));
        SidePnl.setMinimumSize(new java.awt.Dimension(60, 700));
        SidePnl.setPreferredSize(new java.awt.Dimension(50, 550));
        SidePnl.setVerifyInputWhenFocusTarget(false);
        SidePnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddTaskIcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        AddTaskIcn.setToolTipText("Add Task");
        AddTaskIcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddTaskIcnMouseClicked(evt);
            }
        });
        SidePnl.add(AddTaskIcn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 60, -1, -1));

        EditTaskIcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        EditTaskIcn.setToolTipText("Edit Task");
        EditTaskIcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditTaskIcnMouseClicked(evt);
            }
        });
        SidePnl.add(EditTaskIcn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 170, -1, -1));

        DeleteTaskIcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        DeleteTaskIcn.setToolTipText("Delete Task");
        DeleteTaskIcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteTaskIcnMouseClicked(evt);
            }
        });
        SidePnl.add(DeleteTaskIcn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 280, -1, -1));

        MarkDoneTaskIcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/done.png"))); // NOI18N
        MarkDoneTaskIcn.setToolTipText("Mark Done");
        MarkDoneTaskIcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MarkDoneTaskIcnMouseClicked(evt);
            }
        });
        SidePnl.add(MarkDoneTaskIcn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 390, -1, -1));

        getContentPane().add(SidePnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        TaskFrameBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lightThemeIcons/LightBG.png"))); // NOI18N
        TaskFrameBG.setToolTipText("");
        TaskFrameBG.setPreferredSize(new java.awt.Dimension(700, 550));
        getContentPane().add(TaskFrameBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddTaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTaskBtnActionPerformed
        // TODO add your handling code here:
        try {
            // Generate a unique ID for the new task. Using the current time in milliseconds is a simple way to do this.
            int taskID = Math.abs(UUID.randomUUID().hashCode()) & Integer.MAX_VALUE;

            // Get and validate the task name.
            String taskName = TaskName.getText().trim().toUpperCase();
            if (taskName.isEmpty() || taskName.equalsIgnoreCase("e.g. Literary Essay")) {
                JOptionPane.showMessageDialog(this, "Please enter a task name.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get and validate the subject.
            String subject = Subject.getText().trim().toUpperCase();
            if (subject.isEmpty() || subject.equalsIgnoreCase("e.g. English")) {
                JOptionPane.showMessageDialog(this, "Please enter a subject.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get and validate the due date.
            Date dueDate = DueDate.getDate();
            if (dueDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a due date.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Use the TaskUtils.formatDate() method
            String formattedDate = TaskUtils.formatDate(dueDate);
            System.out.println("Task due date: " + formattedDate);

            String hour = (String) HourChooser.getSelectedItem();
            String minute = (String) MinuteChooser.getSelectedItem();

            // Use the TaskUtils.formatTime() method
            String dueTime = TaskUtils.formatTime(hour, minute);
            System.out.println("Task due time: " + dueTime);

            // Get the selected priority and status.
            String priority = (String) PriorityChooser.getSelectedItem();
            String status = (String) StatusChooser.getSelectedItem();

            // Convert the Date to LocalDate and hour/minute to LocalTime for the Task object.
            LocalDate localDueDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime localDueTime = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));

            // Create a new Task object with the collected data.
            Task newTask = new Task(taskID, taskName, subject, localDueDate, localDueTime, priority, status);

            // Add the new task to the TaskManager.
            taskManager.addTask(newTask);

            // Write the entire updated task list to the file
            fileHandler.writeToTaskFile(taskManager.getTaskList());

            clearInputFields(TaskName, Subject, DueDate, HourChooser, MinuteChooser, PriorityChooser, StatusChooser);
            JOptionPane.showMessageDialog(this, "Task added and saved successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            // Catch any potential errors and display an error message.
            JOptionPane.showMessageDialog(this, "Error adding task: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_AddTaskBtnActionPerformed

    private void AddTaskIcnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddTaskIcnMouseClicked
        // TODO add your handling code here:
        themeManager.showAddTaskPanel(AddTaskPnl, EditTaskPnl, DeleteTaskPnl, MarkDonePnl);
        setPanel(AddTaskPnl, new Dimension(450, 550));
    }//GEN-LAST:event_AddTaskIcnMouseClicked

    private void EditTaskIcnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditTaskIcnMouseClicked
        // TODO add your handling code here:
        themeManager.showEditTaskPanel(AddTaskPnl, EditTaskPnl, DeleteTaskPnl, MarkDonePnl);
        setPanel(EditTaskPnl, new Dimension(450, 550));
    }//GEN-LAST:event_EditTaskIcnMouseClicked

    private void DeleteTaskIcnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteTaskIcnMouseClicked
        // TODO add your handling code here:
        themeManager.showDeleteTaskPanel(AddTaskPnl, EditTaskPnl, DeleteTaskPnl, MarkDonePnl);
        setPanel(DeleteTaskPnl, new Dimension(450, 550));
    }//GEN-LAST:event_DeleteTaskIcnMouseClicked

    private void MarkDoneTaskIcnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarkDoneTaskIcnMouseClicked
        // TODO add your handling code here:
        themeManager.showMarkDonePanel(AddTaskPnl, EditTaskPnl, DeleteTaskPnl, MarkDonePnl);
        setPanel(MarkDonePnl, new Dimension(450, 550));
    }//GEN-LAST:event_MarkDoneTaskIcnMouseClicked

    private void EditTaskSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // Clear previous input fields and labels
        clearInputFields(TaskName, Subject, DueDate, EditTaskDueHour, EditTaskDueMinute,
                EditTaskPriority, EditTaskStatus);
        clearTaskLabels(EditTaskIDlbl, EditTaskNamelbl, EditSubjectlbl, EditDueDatelbl, EditDueTimelbl, EditPrioritylbl, EditStatuslbl);

        String searchKeyword = EditTaskSearchFld.getText().trim();

        // Check if the search field is empty
        if (searchKeyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Perform the search
            ArrayList<Task> results = taskManager.searchTask(searchKeyword);

            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No tasks found matching the search term.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Task foundTask = results.get(0);

                // Set the task details in the UI
                EditTaskIDlbl.setText("Task ID       : " + foundTask.getTaskID());
                EditTaskNamefld.setText(foundTask.getTaskName());
                EditTaskSubjectfld.setText(foundTask.getSubject());

                // Check for non-null due date
                if (foundTask.getDueDate() != null) {
                    DueDate.setDate(java.sql.Date.valueOf(foundTask.getDueDate()));
                }

                // Check for non-null due time
                if (foundTask.getDueTime() != null) {
                    String hour = String.format("%02d", foundTask.getDueTime().getHour());
                    String minute = String.format("%02d", foundTask.getDueTime().getMinute());
                    EditTaskDueHour.setSelectedItem(hour);
                    EditTaskDueMinute.setSelectedItem(minute);
                }

                EditTaskPriority.setSelectedItem(foundTask.getPriority());
                EditTaskStatus.setSelectedItem(foundTask.getStatus());

                int taskID = foundTask.getTaskID();
                String name = foundTask.getTaskName();
                String subject = foundTask.getSubject();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while searching: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void EditTaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditTaskBtnActionPerformed
        try {

            int taskID = Integer.parseInt(EditTaskIDlbl.getText().replaceAll("\\D+", ""));

            String taskName = EditTaskNamefld.getText().trim().toUpperCase();
            if (taskName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a task name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String subject = EditTaskSubjectfld.getText().trim().toUpperCase();
            if (subject.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a subject.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date dueDate = DueDate.getDate();
            if (dueDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a due date.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hour = (String) EditTaskDueHour.getSelectedItem();
            String minute = (String) EditTaskDueMinute.getSelectedItem();

            if (hour == null || minute == null) {
                JOptionPane.showMessageDialog(this, "Please select a due time.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String priority = (String) EditTaskPriority.getSelectedItem();
            String status = (String) EditTaskStatus.getSelectedItem();

            LocalDate localDueDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime localDueTime = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));

            Task updatedTask = new Task(taskID, taskName, subject, localDueDate, localDueTime, priority, status);

            boolean success = taskManager.editTask(taskID, updatedTask);
            if (!success) {
                JOptionPane.showMessageDialog(this, "Task with ID " + taskID + " not found.",
                        "Edit Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            fileHandler.writeToTaskFile(taskManager.getTaskList());

            JOptionPane.showMessageDialog(this, "Task edited and saved successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter an valid task to edit", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_EditTaskBtnActionPerformed


    private void EditTaskSearchFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditTaskSearchFldActionPerformed
        // TODO add your handling code here:
        EditTaskSearchBtnActionPerformed(evt);
    }//GEN-LAST:event_EditTaskSearchFldActionPerformed

    private void DeleteTaskSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteTaskSearchBtnActionPerformed
        // TODO add your handling code here:
        clearTaskLabels(TaskID1, TaskNamelbl1, Subjectlbl1, DueDatelbl1, DueTime1, Prioritylbl1, Statuslbl1);
        String searchKeyword = DeleteTaskSearchFld.getText();

        try {
            ArrayList<Task> results = taskManager.searchTask(searchKeyword);

            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid search term.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Task foundTask = results.get(0);
                TaskID1.setText("Task ID       : " + foundTask.getTaskID());
                TaskNamelbl1.setText("Task Name : " + foundTask.getTaskName());
                Subjectlbl1.setText("Subject       : " + foundTask.getSubject());

                // Correct way to format and set the due date label
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                DueDatelbl1.setText("Due Date    : " + foundTask.getDueDate().format(dateFormatter));

                // Correct way to format and set the due time label
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                DueTime1.setText("Due Time    : " + foundTask.getDueTime().format(timeFormatter));

                Prioritylbl1.setText("Priority      : " + foundTask.getPriority());
                Statuslbl1.setText("Status        : " + foundTask.getStatus());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while searching: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_DeleteTaskSearchBtnActionPerformed

    private void DeleteTaskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteTaskBtnActionPerformed
        // TODO add your handling code here:
        try {
            int taskID = Integer.parseInt(TaskID1.getText().replaceAll("\\D+", ""));

            if (taskManager.deleteTask(taskID)) {
                fileHandler.writeToTaskFile(taskManager.getTaskList());
                JOptionPane.showMessageDialog(this, "Task deleted successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearTaskLabels(TaskID1, TaskNamelbl1, Subjectlbl1, DueDatelbl1, DueTime1, Prioritylbl1, Statuslbl1);
            } else {
                JOptionPane.showMessageDialog(this, "Task with ID " + taskID + " not found.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please select a valid task to delete.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while deleting task: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_DeleteTaskBtnActionPerformed

    private void DeleteTaskSearchFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteTaskSearchFldActionPerformed
        // TODO add your handling code here:
        DeleteTaskSearchBtnActionPerformed(evt);
    }//GEN-LAST:event_DeleteTaskSearchFldActionPerformed

    private void MarkDoneSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarkDoneSearchBtnActionPerformed
        // TODO add your handling code here:
        String searchKeyword = MarkDoneSearchFld.getText();

        try {
            ArrayList<Task> results = taskManager.searchTask(searchKeyword);

            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid search term.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Task foundTask = results.get(0);
                TaskID2.setText("Task ID       : " + foundTask.getTaskID());
                TaskNamelbl2.setText("Task Name : " + foundTask.getTaskName());
                Subjectlbl2.setText("Subject       : " + foundTask.getSubject());

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                DueDatelbl2.setText("Due Date    : " + foundTask.getDueDate().format(dateFormatter));

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                DueTime2.setText("Due Time    : " + foundTask.getDueTime().format(timeFormatter));

                Prioritylbl2.setText("Priority      : " + foundTask.getPriority());
                Statuslbl2.setText("Status        : " + foundTask.getStatus());
                
                
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while searching: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_MarkDoneSearchBtnActionPerformed

    private void MarkDoneSearchFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarkDoneSearchFldActionPerformed
        // TODO add your handling code here:
        MarkDoneSearchBtnActionPerformed(evt);
    }//GEN-LAST:event_MarkDoneSearchFldActionPerformed

    private void MarkDoneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarkDoneBtnActionPerformed
        // TODO add your handling code here:
        try {
            int taskID = Integer.parseInt(TaskID2.getText().replaceAll("\\D+", ""));

            if (taskManager.markTaskDone(taskID)) {
                fileHandler.writeToTaskFile(taskManager.getTaskList());
                JOptionPane.showMessageDialog(this,
                        "Task completed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearTaskLabels(TaskID1, TaskNamelbl1, Subjectlbl1, DueDatelbl1, DueTime1, Prioritylbl1, Statuslbl1);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Task with ID " + taskID + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please select a valid task to mark completed.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error while completing task: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_MarkDoneBtnActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            SwingUtilities.updateComponentTreeUI(new MainMenu());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaskFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddTaskBG;
    private javax.swing.JButton AddTaskBtn;
    private javax.swing.JLabel AddTaskIcn;
    private javax.swing.JPanel AddTaskPnl;
    private javax.swing.JLabel BG1;
    private javax.swing.JLabel DeleteTaskBG;
    private javax.swing.JButton DeleteTaskBtn;
    private javax.swing.JLabel DeleteTaskIcn;
    private javax.swing.JPanel DeleteTaskPnl;
    private javax.swing.JButton DeleteTaskSearchBtn;
    private javax.swing.JTextField DeleteTaskSearchFld;
    private com.toedter.calendar.JDateChooser DueDate;
    private javax.swing.JLabel DueDatelbl;
    private javax.swing.JLabel DueDatelbl1;
    private javax.swing.JLabel DueDatelbl2;
    private javax.swing.JLabel DueTime;
    private javax.swing.JLabel DueTime1;
    private javax.swing.JLabel DueTime2;
    private javax.swing.JLabel EditDueDatelbl;
    private javax.swing.JLabel EditDueTimelbl;
    private javax.swing.JLabel EditPrioritylbl;
    private javax.swing.JLabel EditStatuslbl;
    private javax.swing.JLabel EditSubjectlbl;
    private javax.swing.JButton EditTaskBtn;
    private com.toedter.calendar.JDateChooser EditTaskDueDate;
    private javax.swing.JComboBox<String> EditTaskDueHour;
    private javax.swing.JComboBox<String> EditTaskDueMinute;
    private javax.swing.JLabel EditTaskIDlbl;
    private javax.swing.JLabel EditTaskIcn;
    private javax.swing.JTextField EditTaskNamefld;
    private javax.swing.JLabel EditTaskNamelbl;
    private javax.swing.JPanel EditTaskPnl;
    private javax.swing.JComboBox<String> EditTaskPriority;
    private javax.swing.JButton EditTaskSearchBtn;
    private javax.swing.JTextField EditTaskSearchFld;
    private javax.swing.JComboBox<String> EditTaskStatus;
    private javax.swing.JTextField EditTaskSubjectfld;
    private javax.swing.JComboBox<String> HourChooser;
    private javax.swing.JLabel MarkDoneBG;
    private javax.swing.JButton MarkDoneBtn;
    private javax.swing.JPanel MarkDonePnl;
    private javax.swing.JButton MarkDoneSearchBtn;
    private javax.swing.JTextField MarkDoneSearchFld;
    private javax.swing.JLabel MarkDoneTaskIcn;
    private javax.swing.JComboBox<String> MinuteChooser;
    private javax.swing.JComboBox<String> PriorityChooser;
    private javax.swing.JLabel Prioritylbl;
    private javax.swing.JLabel Prioritylbl1;
    private javax.swing.JLabel Prioritylbl2;
    private javax.swing.JPanel SidePnl;
    private javax.swing.JComboBox<String> StatusChooser;
    private javax.swing.JLabel Statuslbl;
    private javax.swing.JLabel Statuslbl1;
    private javax.swing.JLabel Statuslbl2;
    private javax.swing.JTextField Subject;
    private javax.swing.JLabel Subjectlbl;
    private javax.swing.JLabel Subjectlbl1;
    private javax.swing.JLabel Subjectlbl2;
    private javax.swing.JLabel TaskFrameBG;
    private javax.swing.JLabel TaskID1;
    private javax.swing.JLabel TaskID2;
    private javax.swing.JTextField TaskName;
    private javax.swing.JLabel TaskNamelbl;
    private javax.swing.JLabel TaskNamelbl1;
    private javax.swing.JLabel TaskNamelbl2;
    // End of variables declaration//GEN-END:variables
}
