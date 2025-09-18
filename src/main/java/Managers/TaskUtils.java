package Managers;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskUtils {
    /**
     * Method to convert Date to formatted LocalDate string (dd MM yyyy)
     * @param dueDate
     * @return
     */
    public static String formatDate(Date dueDate) {
        // Check if the input Date is null
        if (dueDate == null) {
            return "No date selected";
        }

        // Convert Date to LocalDate
        LocalDate localDueDate = dueDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        // Format the LocalDate using DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        return localDueDate.format(formatter);
    }

    /**
     * Method to format time as "HH:mm"
     * @param hour
     * @param minute
     * @return
     */
    public static String formatTime(String hour, String minute) {
        // Convert hour and minute to LocalTime
        LocalTime localTime = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));

        // Format the LocalTime using DateTimeFormatter
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(timeFormatter);
    }


}
