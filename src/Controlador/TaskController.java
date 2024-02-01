package Controlador;

import Modelos.Admin;
import Modelos.Rol;
import Modelos.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskController {
    private List<Rol> rolList;
    private List<Admin> listnew;
    private List<Admin> adminList;
    private List<Task> taskList;
    public Admin userlogged;

    public TaskController() {
        taskList = new ArrayList<>();
        adminList = new ArrayList<>();
        listnew = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        Admin user = new Admin();
        userlogged = user.login(username, password);
        if (userlogged != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nuevoUsuario(String username, String password, Rol idrol) {
        Admin admin = new Admin();
        return (admin.insertar("(username,password,idrol) values(?,?,?)", username, password, idrol));
    }

    public boolean borrarUsuario(String username, String password) {
        Admin users = new Admin();
        return (users.borrar("username = ? and password = ?", username, password));
    }

    public boolean editarcontraseñaUser(String username, String nuevacontraseña) {
        Admin users = new Admin();
        return (users.actualizar("password = ? where username = ? ", nuevacontraseña, username));
    }

    public boolean nuevorol(String name) {
        Rol rol = new Rol();
        return (rol.insertar("(name) values(?)", name));
    }

    public List<Admin> verUsuarios() {
        return Admin.getuser();
    }

    public List<Task> verTareasByUser() {
        return Task.gettaskByUser(userlogged.getIduser());
    }
    public List<Task> getalltask(){
        return Task.getallTasks();
    }

    public boolean nuevaTarea(String titulo, String descripcion, Date deadline) {
        Task task = new Task();
        return (task.insertar("(title,description,deadline,iduser) values(?,?,?,?)", titulo, descripcion, deadline, userlogged.getIduser()));
    }

    public boolean borrarTarea(String titulo) {
        Task task = new Task();
        return (task.borrar("title = ? and iduser = ?", titulo, userlogged.getIduser()));
    }

    public boolean editardescripcion(String titulo, String nuevadescripcion) {
        Task task = new Task();
        return (task.actualizar("description = ? where title = ? ", nuevadescripcion, titulo));
    }

    public boolean editartitulo(int idtask, String nuevotitulo) {
        Task task = new Task();
        return (task.actualizar("title = ? where idtask = ? ", nuevotitulo, idtask));
    }
}
