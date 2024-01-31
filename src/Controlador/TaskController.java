package Controlador;

import Modelos.Admin;
import Modelos.Task;
import bbdd.Conexion;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private List<Admin> listnew;
    private List<Admin> adminList;
    private List<Task> taskList;

    public TaskController() {
        taskList = new ArrayList<>();
        adminList = new ArrayList<>();
        listnew = new ArrayList<>();
    }

    public boolean nuevoUsuario(String username, String password, int idrol) {
        Admin admin = new Admin();
        if (admin.insertar("(username,password,idrol) values(?,?,?)", username, password, idrol)) {
            return adminList.add(admin);
        } else {
            return false;
        }
    }

    public boolean borrarUsuario(String username, String password) {
        Admin users = new Admin();
        if (users.borrar("username = ? and password = ?", username, password)) {
            adminList = Admin.getuser();
            return true;
        } else {
            return false;
        }
    }

    public boolean editarcontraseñaUser(String username, String nuevacontraseña) {
        Admin users = new Admin();
        if (users.actualizar("password = ? where username = ? ", nuevacontraseña, username)) {
            adminList = Admin.getuser();
            return true;
        } else {
            return false;
        }
    }

    public List<Admin> verUsuarios(){
        return Admin.getuser();
    }
    public List<Task> verTareas(){
        return Task.gettask();
    }

    public boolean nuevaTarea(String titulo, String descripcion, LocalDateTime datetime, LocalDateTime deadline, boolean status, int iduser) {
        Task task = new Task();
        if (task.insertar("(title,description,datetime,deadline,status,iduser) values(?,?,?,?,?,?)", titulo, descripcion, datetime, deadline, status, iduser)) {
            return taskList.add(task);
        } else {
            return false;
        }
    }

    public boolean borrarTarea(String titulo, int idUsuario) {
        Task task = new Task();
        if (task.borrar("title = ? and iduser = ?", titulo, idUsuario)) {
            taskList = task.gettask();
            return true;
        } else {
            return false;
        }
    }

    public boolean editardescripcion(String titulo, String nuevadescripcion) {
        Task task = new Task();
        if (task.actualizar("description = ? where title = ? ", nuevadescripcion, titulo)) {
            taskList = Task.gettask();
            return true;
        } else {
            return false;
        }
    }

    public boolean editartitulo(int idtask, String nuevotitulo) {
        Task task = new Task();
        if (task.actualizar("title = ? where idtask = ? ", nuevotitulo, idtask)) {
            taskList = Task.gettask();
            return true;
        } else {
            return false;
        }
    }

    public List<Admin> adminOusuario(String username) {
        Connection conn = Conexion.conetar();
        String sql = "Select * from user where username = ?";
        try {
            listnew = new ArrayList<>();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Admin admin = new Admin();
                admin.setIduser(respuesta.getInt("iduser"));
                admin.setUsername(respuesta.getString("username"));
                admin.setPassword(respuesta.getString("password"));
                admin.setIdrol(respuesta.getInt("idrol"));
                listnew.add(admin);
            }
        } catch (SQLException e) {
            return listnew;
        }
        try {
            conn.close();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }
        return listnew;
    }

}
