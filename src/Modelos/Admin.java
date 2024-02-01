package Modelos;

import bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin extends ModeloBase {
    private int iduser;
    private String username;
    private String password;
    private Rol idrol;

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

    public Rol getIdrol() {
        return idrol;
    }

    public void setIdrol(Rol idrol) {
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
                users.setIdrol((Rol) respuesta.getObject("idrol"));
                adminList.add(users);
            }
        } catch (SQLException e) {
            return adminList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
        }
        return adminList;
    }
    public static Admin login(String usuario, String password) {
        Admin admin = new Admin();
        Rol rol=new Rol();
        Connection conn = Conexion.conetar();
        String sql = "Select u.iduser,u.username,r.idrol,r.name from user as u " +
                "join rol as r on u.idrol=r.idrol " +
                "where username = ? and password = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, password);
            ResultSet respuesta = pst.executeQuery();
            if (respuesta.next()) {
                admin.iduser=respuesta.getInt("iduser");
                admin.username=respuesta.getString("username");
                rol.setIdrol(respuesta.getInt("idrol"));
                rol.setName(respuesta.getString("name"));
                admin.idrol=rol;
                return admin;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
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
