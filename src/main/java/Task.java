
import java.time.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author china
 */
public class Task {

    private int taskID;
    private String taskName, subject;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private String priority, status;

    private static final String PRIORITY_HIGH = "High";
    private static final String PRIORITY_MEDIUM = "Medium";
    private static final String PRIORITY_LOW = "Low";
    private static final String DEFAULT_PRIORITY = PRIORITY_MEDIUM;

    private static final String STATUS_COMPLETE = "Complete";
    private static final String STATUS_PENDING = "Pending";
    private static final String STATUS_OVERDUE = "Overdue";
    private static final String DEFAULT_STATUS = STATUS_PENDING;

   public Task(int inTID, String inName, String inSub, LocalDate inDate,
                   LocalTime inTime, String inPriority, String inStatus)
   {
       this.taskID = inTID;
       this.taskName = inName;
       this.subject = inSub;
       this.dueDate = inDate;
       this.dueTime = inTime;

       if (inPriority.equalsIgnoreCase(PRIORITY_HIGH) || inPriority.equalsIgnoreCase(PRIORITY_MEDIUM)
               || inPriority.equalsIgnoreCase(PRIORITY_LOW)){
           this.priority = inPriority;
       } else {
           this.priority = DEFAULT_PRIORITY;
       }

       // Assign status with validation and a default fallback
       if (inStatus.equalsIgnoreCase(STATUS_COMPLETE) || inStatus.equalsIgnoreCase(STATUS_PENDING) || inStatus.equalsIgnoreCase(STATUS_OVERDUE)) {
           this.status = inStatus;
       } else {
           this.status = DEFAULT_STATUS;
       }

   }
    public int getTaskID() { return taskID; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String inTaskName) { this.taskName = inTaskName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate inDate) { this.dueDate = inDate; }

    public LocalTime getDueTime() { return dueTime; }
    public void setDueTime(LocalTime inTime) { this.dueTime = inTime; }

    public String getPriority() { return priority; }
    public void setPriority(String inPriority) { this.priority = inPriority; }

    public String getStatus() { return status; }
    public void setStatus(String inStatus) { this.status = inStatus; }

}
