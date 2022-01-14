package ua.edu.sumdu.j2se.kushnir.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kushnir.tasks.controller.impl.MainControllerImpl;
import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kushnir.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.kushnir.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.kushnir.tasks.model.TaskListFactory;
import ua.edu.sumdu.j2se.kushnir.tasks.view.views.MainView;
import ua.edu.sumdu.j2se.kushnir.tasks.view.impl.MainViewImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class Aplication {
    private static final Logger log = Logger.getLogger(Aplication.class);
    private static final String JSON_FILE = "data.json";
    private final Path jsonPath;
    private final AbstractTaskList atl;

    public Aplication() {
        atl = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        jsonPath = FileSystems.getDefault().getPath(JSON_FILE).toAbsolutePath();
    }

    public void start() {
        log.info("Starting Task Manager application");
        loadTaskData();

        log.info("Declare View and Controller");
        MainView mainView = new MainViewImpl();
        MainControllerImpl controller = new MainControllerImpl(atl, mainView);
        controller.execute();

        saveTaskData();
        log.info("Task Manager application has finished its work");
    }

    private void saveTaskData() {
        try {
            log.info("Deleting json file if exist");
            Files.deleteIfExists(jsonPath);

            log.info("Write tasks' data into json file");
            TaskIO.writeText(atl, new File(String.valueOf(jsonPath.toAbsolutePath())));
            log.info("Writing data was successful");
        } catch (IOException e) {
            log.error(e);
            System.out.println("Error. Can't read tasks' data");
        }
    }

    private void loadTaskData() {
        log.info("Reading tasks' data from json file");
        TaskIO.readText(atl, new File(String.valueOf(jsonPath.toAbsolutePath())));
        log.info("Reading data was successful");
    }
}
