import java.io.*;
import java.time.*;
import java.util.*;

public class TaskManager {

    private ArrayList<Task> taskList;
    private int currentTaskIndex = -1;


    public TaskManager(){
        taskList = new ArrayList<>();
    }

    public void addTask(Task t){ taskList.add(t); }

    public boolean editTask(int taskID, Task updatedTask){
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskID() == taskID) {
                taskList.set(i, updatedTask);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int taskID){
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskID() == taskID) {
                taskList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean markTaskDone(int taskID){
        for (Task task : taskList) {
            if (task.getTaskID() == taskID) {
                task.setStatus("Complete"); // Corrected to match the constant in Task class
                return true;
            }
        }
        return false;
    }

    public ArrayList<Task> searchTask(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        ArrayList<Task> searchResults = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getTaskName().toLowerCase().contains(lowerCaseKeyword) ||
                    task.getSubject().toLowerCase().contains(lowerCaseKeyword)) {
                searchResults.add(task);
            }
            else if (task.getTaskName().equalsIgnoreCase(keyword)) {
                searchResults.add(task);
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
            if (task.getStatus().equals("Complete")) {
                completed++;
            } else if (task.getDueDate() != null && task.getDueDate().isBefore(today)) {
                task.setStatus("Overdue");
                overdue++;
            } else {
                pending++;
            }
        }

        return new int[]{total, completed, pending, overdue};
    }
    
    public Task getNextTask() {
        if (taskList.isEmpty()) {
            return null; // No tasks to display.
        }

        // Increment the index. If it goes past the end, reset to 0.
        if (currentTaskIndex >= taskList.size() - 1) {
            currentTaskIndex = 0;
        } else {
            currentTaskIndex++;
        }

        return taskList.get(currentTaskIndex);
    }

    // You also need a method to get the previous task
    public Task getPreviousTask() {
        if (taskList.isEmpty()) {
            return null;
        }

        // Wrap around to the start if we are at the first task.
        if (currentTaskIndex <= 0) {
            currentTaskIndex = taskList.size() - 1;
        } else {
            currentTaskIndex--;
        }
        return taskList.get(currentTaskIndex);
    }

    // A method to get the currently selected task
    public Task getCurrentTask() {
        if (taskList.isEmpty() || currentTaskIndex == -1) {
            return null;
        }
        return taskList.get(currentTaskIndex);
    }

    // Add this method to load tasks from your file
    public void loadTasks(FileHandler fileHandler) {
        this.taskList = fileHandler.readTaskFile();
        if (!taskList.isEmpty()) {
            currentTaskIndex = 0; // Set initial task to the first one in the list
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

}
