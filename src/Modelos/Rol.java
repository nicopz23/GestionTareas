package Modelos;

public class Rol extends ModeloBase{
    private int idrol;
    private String name;

    public Rol() {
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idrol=" + idrol +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "rol";
    }
}
