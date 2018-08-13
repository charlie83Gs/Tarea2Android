package cr.ac.tec.tarea2.Dominio;

public class Usuario {

    String Nombre;
    String Alias;
    String Correo;
    String Password;
    int id;

    public Usuario(String nombre, String alias, String correo, String password, int id) {
        Nombre = nombre;
        Alias = alias;
        Correo = correo;
        Password = password;
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean validatePassword(String attempt){
        return Password.equals(attempt);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
