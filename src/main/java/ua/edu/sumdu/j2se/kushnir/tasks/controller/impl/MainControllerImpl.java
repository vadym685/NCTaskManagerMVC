package ua.edu.sumdu.j2se.kushnir.tasks.controller.impl;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kushnir.tasks.controller.controllers.MainControler;
import ua.edu.sumdu.j2se.kushnir.tasks.controller.controllers.TaskController;
import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kushnir.tasks.view.views.MainView;
import ua.edu.sumdu.j2se.kushnir.tasks.view.util.Output;

import java.util.Scanner;

public class MainControllerImpl implements MainControler {

    private static final Logger log = Logger.getLogger(MainControllerImpl.class);
    private static final String QUESTION = "Are you confident in your choice? (Yes:No) ";
    private final TaskController mainControler;
    private final MainView view;
    private final Scanner scanner;

    public MainControllerImpl(AbstractTaskList list, MainView view) {
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.mainControler = new TaskControllerImpl(list, view);
    }


    @Override
    public void execute() {
        log.info("Executing main controller command");
        while (true) {
            view.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                System.out.print("Do you want to finish? (Yes:No): ");
                if (view.checkUserChoice()) {
                    System.out.println("Program was finished");
                    log.info("The Task Manager application is getting ready to end");
                    break;
                }
            } else {
                switch (choice) {
                    case 1:
                        mainControler.addTask();
                        break;
                    case 2:
                        mainControler.editTask();
                        break;
                    case 3:
                        System.out.print(QUESTION);
                        if (view.checkUserChoice()) mainControler.deleteTask();
                        break;
                    case 4:
                        mainControler.showTaskList();
                        break;
                    case 5:
                        mainControler.showCalendar();
                        break;
                }
            }
        }

    }
}
