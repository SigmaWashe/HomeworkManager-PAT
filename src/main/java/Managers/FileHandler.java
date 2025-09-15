package Managers;

import Managers.Task;
import javax.swing.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class FileHandler {

    private String filePath;

    public FileHandler(String inPath) {
        this.filePath = inPath;
    }

    public ArrayList<Task> readTaskFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (Scanner scFile = new Scanner(new File(filePath))) {
            while (scFile.hasNextLine()) {
                String scLine = scFile.nextLine();
                String tokens[] = scLine.split("#");

                if (tokens.length == 7) {
                    try {
                        int inTID = Integer.parseInt(tokens[0]);
                        String inName = tokens[1];
                        String inSub = tokens[2];
                        LocalDate inDate = LocalDate.parse(tokens[3], DateTimeFormatter.ofPattern("dd MM yyyy"));
                        LocalTime inTime = LocalTime.parse(tokens[4], DateTimeFormatter.ofPattern("HH:mm"));
                        String inPriority = tokens[5];
                        String inStatus = tokens[6];

                        Task task = new Task(inTID, inName, inSub, inDate, inTime, inPriority, inStatus);
                        taskList.add(task);
                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("Skipping malformed line: " + scLine);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return taskList;
    }

    public void writeToTaskFile(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                // Now consistent with the read method
                String line = task.getTaskID() + "#" +
                        task.getTaskName() + "#" +
                        task.getSubject() + "#" +
                        task.getDueDate().format(DateTimeFormatter.ofPattern("dd MM yyyy")) + "#" +
                        task.getDueTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "#" +
                        task.getPriority() + "#" +
                        task.getStatus();
                writer.println(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void overwriteTaskFile(ArrayList<Task> taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                String line = task.getTaskID() + "#" +
                        task.getTaskName() + "#" +
                        task.getSubject() + "#" +
                        task.getDueDate().format(DateTimeFormatter.ofPattern("dd MM yyyy")) + "#" +
                        task.getDueTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "#" +
                        task.getPriority() + "#" +
                        task.getStatus();
                writer.println(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}