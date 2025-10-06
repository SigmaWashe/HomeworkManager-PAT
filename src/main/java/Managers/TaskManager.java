package Managers;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.time.*;
import java.util.*;

public class TaskManager {

    // Variables for the task manager
    private ArrayList<Task> taskList; // All the tasks in an ArrayList
    private FileHandler fileHandler = new FileHandler("txtFiles/Tasks.txt"); // Reads the tasks

    //Constructor
    public TaskManager(){
        taskList = new ArrayList<>();
        taskList.addAll(fileHandler.readTaskFile()); // Adds all the tasks from the task file to the array
    }

    /**
     * Adds task to the text file and array
     * @param t
     */
    public void addTask(Task t){ taskList.add(t); }

    /**
     * Edits the task that the user has searched
     * @param taskID
     * @param updatedTask
     */
    public boolean editTask(int taskID, Task updatedTask){
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskID() == taskID) {
                taskList.set(i, updatedTask);
                return true;
            }
        }
        return false;
    }

    /**
     * Marks the task done and sets the STATUS of the task to COMPLETE
     * @param taskID
     */
    public boolean markTaskDone(int taskID) {
        for (Task task : taskList) {
            if (task.getTaskID() == taskID) {
                task.setStatus("COMPLETE");
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes the task from the task file
     * @param taskID
     * @return
     */
    public boolean deleteTask(int taskID) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskID() == taskID) {
                taskList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for the task by using a keyword
     * It will first search by looking for a matching taskID
     * Then it will search by looking for a matching taskName
     * Then it will search by looking for a matching subject
     * @param keyword
     * @return
     */
    public ArrayList<Task> searchTask(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();
        String searchKeyword = keyword.toUpperCase();

        // Try to match Task ID if the keyword is numeric
        try {
            int id = Integer.parseInt(keyword);
            for (Task task : taskList) {
                if (task.getTaskID() == id) {
                    searchResults.add(task);
                    break; // Exit after finding the first match
                }
            }
        } catch (NumberFormatException e) {
            // If it's not a number, proceed to search by name or subject
            for (Task task : taskList) {
                if (task.getTaskName().toUpperCase().contains(searchKeyword) ||
                        task.getSubject().toUpperCase().contains(searchKeyword)) {
                    searchResults.add(task);
                }
            }
        }

        return searchResults;
    }

    /**
     *
     * @return The Progress Summary of the user
     */
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

    /**
     *
     * @return The task List
     */
    public ArrayList<Task> getTaskList() { return taskList; }

}