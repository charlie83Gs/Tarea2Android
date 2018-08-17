package cr.ac.tec.tarea2.Dominio;

import android.content.ContentValues;
import android.database.Cursor;

public class Usuario implements Almacenable{

    String Nombre;
    String Alias;
    String Correo;
    String Password;
    int id;

    public Usuario(){

    }

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

    @Override
    public ContentValues toStorage() {
        ContentValues value = new ContentValues();
        value.put("nombre", Nombre);
        value.put("alias", Alias);
        value.put("correo", Correo);
        value.put("password", Password);
        value.put("id", id);

        return value;
    }

    @Override
    public void fromStorage(Cursor datos) {
        Nombre = datos.getString(0);
        Alias = datos.getString(1);
        Correo = datos.getString(2);
        Password = datos.getString(3);
        id = datos.getInt(4);

    }

    @Override
    public Object clone() {
        return new Usuario( Nombre,  Alias,  Correo,  Password,  id);
    }
}
