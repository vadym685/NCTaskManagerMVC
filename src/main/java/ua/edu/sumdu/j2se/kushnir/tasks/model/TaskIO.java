package ua.edu.sumdu.j2se.kushnir.tasks.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class TaskIO {
    private static final Logger log = Logger.getLogger(TaskIO.class);

    // a method that writes tasks from a list to a stream in binary format
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            outputStream.writeInt(tasks.size());
            for (Task task : tasks) {
                if (task == null) continue;
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    outputStream.writeLong(task.getStartTime().atZone(ZoneId.systemDefault()).toEpochSecond());
                    outputStream.writeLong(task.getEndTime().atZone(ZoneId.systemDefault()).toEpochSecond());
                } else {
                    outputStream.writeLong(task.getTime().atZone(ZoneId.systemDefault()).toEpochSecond());
                }
            }
            outputStream.flush();
        } catch (IOException e) {
            log.log(Level.ERROR,"IOException:",e);
//            e.printStackTrace();
        }
    }

    // a method that reads tasks from a stream a given task list
    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream inputStream = new DataInputStream(in)) {
            Task task;
            int counter = inputStream.readInt();
            for (int i = 0; i < counter; i++) {
                int titleLength = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean active = inputStream.readBoolean();
                int interval = inputStream.readInt();
                if (interval != 0) {
                    LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(inputStream.readLong()), ZoneId.systemDefault());
                    LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochSecond(inputStream.readLong()), ZoneId.systemDefault());
                    task = new Task(title, start, end, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(inputStream.readLong()), ZoneId.systemDefault());
                    task = new Task(title, time);

                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException e) {
            log.log(Level.ERROR,"IOException:",e);
//            e.printStackTrace();
        }
    }

    // a method that writes tasks from a list to a file
    public static void writeBinary(AbstractTaskList tasks, File file) {
        if (!file.exists()) {
            file = new File(file.getName());
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(tasks, fos);
        } catch (IOException e) {
            log.error(e.getStackTrace());
//            e.printStackTrace();
        }
    }

    // a method that reads tasks from a file into a task list
    public static void readBinary(AbstractTaskList tasks, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            read(tasks, fileInputStream);
        } catch (IOException e) {
            log.log(Level.ERROR,"IOException:",e);
//            e.printStackTrace();
        }
    }

    // a method that writes tasks from a list to a stream in JSON format
    public static void write(AbstractTaskList tasks, Writer out) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        AbstractTaskList tempList = new ArrayTaskList();
        tasks.getStream().filter(Objects::nonNull).forEach(tempList::add);
        try (Writer writer = new BufferedWriter(out)) {
            writer.write(gson.toJson(tempList));
            writer.flush();
        } catch (IOException e) {
            log.log(Level.ERROR,"IOException:",e);
//            e.printStackTrace();
        }
    }

    // a method that reads tasks from a stream into a list
    public static void read(AbstractTaskList tasks, Reader in) {
        Gson gson = new Gson();
        AbstractTaskList tempList = gson.fromJson(in, ArrayTaskList.class);
        tempList.getStream().filter(Objects::nonNull).forEach(tasks::add);
    }

    // a method that writes tasks to a file in JSON format
    public static void writeText(AbstractTaskList tasks, File file) {
        if (!file.exists()) {
            file = new File(file.getName());
        }
        try (Writer fileWriter = new FileWriter(file)) {
            write(tasks, fileWriter);
        } catch (IOException e) {
            log.log(Level.ERROR,"IOException:",e);
//            e.printStackTrace();
        }
    }

    // method that reads tasks from a file
    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        } catch (IOException e) {
            log.log(Level.ERROR,"IOException:",e);
        }
    }
}