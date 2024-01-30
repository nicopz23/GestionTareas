package Modelos;

import bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Admin extends ModeloBase {
    private int iduser;
    private String username;
    private String password;
    private int idrol;

    public Admin(String username, String password) {
        this.iduser = iduser++;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    @Override
    protected String getNombreTabla() {
        return "user";
    }

    public static List<Admin> getuser() {
        List<Admin> adminList = new ArrayList<>();
        Connection conn = Conexion.conetar();
        String sql = "Select * from user";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Admin users = new Admin();
                users.setIduser(respuesta.getInt("iduser"));
                users.setUsername(respuesta.getString("username"));
                users.setPassword(respuesta.getString("password"));
                users.setIdrol(respuesta.getInt("idrol"));
                adminList.add(users);
            }
        } catch (SQLException e) {
            return adminList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }
        return adminList;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "iduser=" + iduser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idrol=" + idrol +
                '}';
    }
}
