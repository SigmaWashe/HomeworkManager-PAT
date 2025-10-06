    package Managers;

    import java.time.*;

    public class Task {

        // All the variables for the task class
        private int taskID;
        private String taskName, subject;
        private LocalDate dueDate;
        private LocalTime dueTime;
        private String priority, status;

        // The constants for the Priority task
        /**
         * Static fields that store the Priority of the task
         */
        private static final String PRIORITY_HIGH = "High";
        private static final String PRIORITY_MEDIUM = "Medium";
        private static final String PRIORITY_LOW = "Low";
        private static final String DEFAULT_PRIORITY = PRIORITY_MEDIUM;

        /**
         * Static fields that store the STATUS of the task
         */
        private static final String STATUS_COMPLETE = "Complete";
        private static final String STATUS_PENDING = "Pending";
        private static final String STATUS_OVERDUE = "Overdue";
        private static final String DEFAULT_STATUS = STATUS_PENDING;

        // Contructor method to create the task using all the variables
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

           if (inStatus.equalsIgnoreCase(STATUS_COMPLETE) || inStatus.equalsIgnoreCase(STATUS_PENDING)
                   || inStatus.equalsIgnoreCase(STATUS_OVERDUE)) {
               this.status = inStatus;
           } else {
               this.status = DEFAULT_STATUS;
           }

       }

        /**
         * @return the taskID
         */
        public int getTaskID() { return taskID; }

        /**
         * @return taskName
         */
        public String getTaskName() { return taskName; }

        /**
         * @return subject
         */
        public String getSubject() { return subject; }

        /**
         * @return dueDate
         */
        public LocalDate getDueDate() { return dueDate; }

        /**
         * @return dueTime
         */
        public LocalTime getDueTime() { return dueTime; }

        /**
         * @return priority
         */
        public String getPriority() { return priority; }

        // Getter and setter methods for the Status of the task

        /**
         * @return status
         */
        public String getStatus() { return status; }

        /**
         * Set the status of the task
         * @param inStatus
         */
        public void setStatus(String inStatus) { this.status = inStatus; }

    }