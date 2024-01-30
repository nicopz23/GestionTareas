package Modelos;

import bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task extends ModeloBase{
    private int idtask;
    private String title;
    private String description;
    private LocalDateTime datetime;
    private LocalDateTime deadline;
    private boolean status;

    public Task(String title, String description, LocalDateTime deadline) {
        this.idtask = idtask++;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = true;
    }

    public Task() {

    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getIdtask() {
        return idtask;
    }

    public void setIdtask(int idtask) {
        this.idtask = idtask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public static List<Task> gettask() {
        List<Task> taskList = new ArrayList<>();
        Connection conn = Conexion.conetar();
        String sql = "Select * from task";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Task task = new Task();
                task.setIdtask(respuesta.getInt("idtask"));
                task.setTitle(respuesta.getString("title"));
                task.setDescription(respuesta.getString("description"));
//                task.setDatetime(respuesta.("datetime"));
//                task.setDeadline(respuesta.("deadline"));
                task.setStatus(respuesta.getBoolean("status"));
                taskList.add(task);
            }
        } catch (SQLException e) {
            return taskList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
        }
        return taskList;
    }

    @Override
    public String toString() {
        return "Task{" +
                "idtask=" + idtask +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", datetime=" + datetime +
                ", deadline=" + deadline +
                ", status=" + status +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "task";
    }
}
