package Vista;

import Controlador.TaskController;
import Modelos.Admin;
import Modelos.Task;

public class Main {
    public static void main(String[] args) {
        TaskController taskController = new TaskController();
        taskController.login("nicolas","1524");
        System.out.println(taskController.userlogged.toString());
        System.out.println(taskController.verTareasByUser());

    }
    }