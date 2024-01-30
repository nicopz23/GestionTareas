package Vista;

import Controlador.LoginController;

public class Main {
    public static void main(String[] args) {
        if (LoginController.login("nicolas","1524")){
            System.out.println("Se conecto");
        }else{
            System.out.println("no se pudo conectar");
        }
    }
}