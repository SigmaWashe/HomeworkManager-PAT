package Managers;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.time.*;
import java.util.*;

public class TaskManager {

    private ArrayList<Task> taskList;
    private FileHandler fileHandler = new FileHandler("txtFiles/data.txt");

    public TaskManager(){
        taskList = new ArrayList<>();
        taskList.addAll(fileHandler.readTaskFile());
    }

    public void addTask(Task t){ taskList.add(t); }

    public void editTask(int taskID, Task updatedTask){
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskID() == taskID) {
                taskList.set(i, updatedTask);
            }
        }
    }

    public boolean deleteTask(int taskID) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskID() == taskID) {
                taskList.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> searchTask(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();
        String searchKeyword = keyword.toUpperCase();

        for (Task task : taskList) {
            // Try to parse as int for task ID search
            try {
                // Match by task name or subject, case-insensitive
                if (task.getTaskName().toUpperCase().contains(searchKeyword) ||
                        task.getSubject().toUpperCase().contains(keyword)) {
                    searchResults.add(task);
                }
                int id = Integer.parseInt(keyword);
                if (task.getTaskID() == id) {
                    searchResults.add(task);
                    continue; // Skip other checks if matched by ID
                }
            } catch (NumberFormatException e) {
                // Not an int, continue to string match
            }

        }
        return searchResults;
    }

    public ArrayList<Task> filterTasks(String period, String subject) {
        ArrayList<Task> filteredList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Task task : taskList) {
            boolean matchesPeriod = false;
            boolean matchesSubject = subject.equals("All") || task.getSubject().equalsIgnoreCase(subject);

            if (period.equals("All")) {
                matchesPeriod = true;
            } else if (period.equals("Today") && task.getDueDate().isEqual(today)) {
                matchesPeriod = true;
            } else if (period.equals("Upcoming") && task.getDueDate().isAfter(today)) {
                matchesPeriod = true;
            }

            if (matchesPeriod && matchesSubject) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }

    public ArrayList<Task> getTodaysTasks() {
        LocalDate today = LocalDate.now();
        ArrayList<Task> todaysTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDueDate().isEqual(today)) {
                todaysTasks.add(task);
            }
        }
        return todaysTasks;
    }

    public ArrayList<Task> getUpcomingTasks() {
        LocalDate today = LocalDate.now();
        ArrayList<Task> upcomingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDueDate().isAfter(today)) {
                upcomingTasks.add(task);
            }
        }
        return upcomingTasks;
    }

    public int[] getProgressSummary() {
        int total = taskList.size();
        int completed = 0;
        int pending = 0;
        int overdue = 0;
        LocalDate today = LocalDate.now();

        for (Task task : taskList) {
            // If the task status is "Complete", increment completed count.
            if (task.getStatus().equalsIgnoreCase("COMPLETE")) {
                completed++;
            }
            // If the task is NOT complete and its due date has passed, it is overdue.
            else if (task.getDueDate() != null && task.getDueDate().isBefore(today)) {
                overdue++;
            }
            // If the task is NOT complete and NOT overdue, it is pending.
            else {
                pending++;
            }
        }

        return new int[]{total, completed, pending, overdue};
    }

    public ArrayList<Task> getTaskList() { return taskList; }

    public void addSearchListeners(JFrame frame, JButton searchBtn, JTextField usernameField, String keyword) {
        KeyAdapter keyHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchTask(keyword);
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }
            }
        };

        searchBtn.addKeyListener(keyHandler);
        usernameField.addKeyListener(keyHandler);
    }

}
