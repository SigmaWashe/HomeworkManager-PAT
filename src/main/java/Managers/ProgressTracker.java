package Managers;

import java.time.*;
import java.util.*;

public class ProgressTracker {

    // All the variables for the ProgressTracker class
    private int total, completed, pending, overdue;

    /**
     * Parameterised constructor to assign default values to the users progress
     * @param inT
     * @param inC
     * @param inP
     * @param inO
     */
    public ProgressTracker(int inT, int inC, int inP, int inO) {
        this.total = inT;
        this.completed = inC;
        this.pending = inP;
        this.overdue = inO;
    }
    /**
     * Calculates the Progress of the user
     * @param tasks
     */
    public void calculateProgress(ArrayList<Task> tasks) {
        this.total = tasks.size();
        this.completed = 0;
        this.pending = 0;
        this.overdue = 0;
        LocalDate today = LocalDate.now();

        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase("COMPLETE")) {
                this.completed++;
            } else if (task.getDueDate().isBefore(today)) {
                task.setStatus("Overdue");
                this.overdue++;
            } else {
                this.pending++;
            }
        }
    }
    /**
     *
     * @return the total, completed, pending and overdue tasks
     */
    public int getTotal() { return total; }
    public int getCompleted() { return completed; }
    public int getPending() { return pending; }
    public int getOverdue() { return overdue; }

    // Calculates the completion rate of the users tasks

    /**
     *  Calculates the completion rate of the users tasks
     * @return the completion rate
     */
    public double getCompletionRate() {
        double completionRate = 0;
        int totalTasks = this.completed + this.pending + this.overdue;

        if (totalTasks > 0) {
            completionRate = ((double) this.completed / totalTasks) * 100.0;
        }

        return completionRate;
    }
}