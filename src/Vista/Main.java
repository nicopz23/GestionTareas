package Vista;

import Controlador.LoginController;
import Controlador.TaskController;
import Modelos.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        if (LoginController.login("nicolas","1524")){
            System.out.println("Se conecto");
            TaskController taskController = new TaskController();
            Task task = new Task();
            System.out.println(taskController.verTareas());
            //System.out.println(task.setformatDate(String.valueOf(LocalDateTime.now())));

        }else{
            System.out.println("no se pudo conectar");
        }
    }
}