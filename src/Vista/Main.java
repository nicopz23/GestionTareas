package Vista;

import Controlador.LoginController;
import Controlador.TaskController;
import Modelos.Admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        if (LoginController.login("nicolas","1524")){
            System.out.println("Se conecto");
            TaskController taskController = new TaskController();
            System.out.println(taskController.adminOusuario("nicolas","1524"));
        }else{
            System.out.println("no se pudo conectar");
        }
    }
}