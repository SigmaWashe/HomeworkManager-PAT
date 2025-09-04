import java.time.*;
import java.util.*;

public class ProgressTracker {

    private int completed, pending, overdue;

    public ProgressTracker(int inC, int inP, int inO){
        this.completed = inC;
        this.pending = inP;
        this.overdue = inO;
    }

    public void calculateProgress(ArrayList<Task> tasks){
            this.completed = 0;
            this.pending = 0;
            this.overdue = 0;
            LocalDate today = LocalDate.now();

            for (Task task : tasks) {
                if (task.getStatus().equals("Completed")) {
                    this.completed++;
                } else if (task.getDueDate().isBefore(today)) {
                    task.setStatus("Overdue");
                    this.overdue++;
                } else {
                    this.pending++;
                }
            }
    }

    public int getCompleted() { return completed; }
    public int getPending() { return pending; }
    public int getOverdue() { return overdue; }

    public double getCompletionRate(){
        double completionRate = 0;
        int totalTasks = this.completed + this.pending + this.overdue;

        if (totalTasks > 0) {
            completionRate = ((double) this.completed / totalTasks) * 100.0;
        }

        return completionRate;
    }

}
