package Modelos;

import bbdd.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends ModeloBase{
    private int idtask;
    private Admin iduser;
    private String title;
    private String description;
    private Date datetime;
    private Date deadline;
    private boolean status;

    public Task() {

    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Admin getIduser() {
        return iduser;
    }

    public void setIduser(Admin iduser) {
        this.iduser = iduser;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public static List<Task> getallTasks() {
        return null;
    }

    public static List<Task> gettaskByUser(int iduser) {
            List<Task> taskList=new ArrayList<>();
            Task task=new Task();
            Admin user=new Admin();
            Rol rol=new Rol();
            Connection conn = Conexion.conetar();
            String sql="SELECT idtask,title,T0.description,datetime,deadline,status,\n" +
                    "T1.iduser,username,T2.idrol,T2.name \n" +
                    "from task T0 \n" +
                    "left join user T1 on T0.iduser=T1.iduser\n" +
                    "left join rol T2 on T1.idrol=T2.idrol where T1.iduser=?";
            try {
                PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1,iduser);
                ResultSet resultSet=pst.executeQuery();
                while (resultSet.next()){
                    task.idtask=resultSet.getInt("idtask");
                    task.title=resultSet.getString("title");
                    task.description=resultSet.getString("description");
                    task.datetime=resultSet.getDate("datetime");
                    task.deadline=resultSet.getDate("deadline");
                    task.status=resultSet.getBoolean("status");
                    user.setIduser(resultSet.getInt("iduser"));
                    user.setUsername(resultSet.getString("username"));
                    rol.setIdrol(resultSet.getInt("idrol"));
                    rol.setName(resultSet.getString("name"));
                    user.setIdrol(rol);
                    task.setIduser(user);
                    taskList.add(task);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return taskList;
        }

    @Override
    public String toString() {
        return "Task{" +
                "idtask=" + idtask +
                ", iduser=" + iduser +
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
