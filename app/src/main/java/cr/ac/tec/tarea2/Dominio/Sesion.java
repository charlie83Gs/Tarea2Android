package cr.ac.tec.tarea2.Dominio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Sesion {
    private Usuario usuario = null;
    private SQLiteDatabase database;

    private static Sesion sesion = null;


    public static Sesion getSesion() {
        if(sesion == null){
            sesion = new Sesion();
        }

        return sesion;
    }

    /**
     * inicializa la base de datos sqlite
     */
    private Sesion() {
        //inicializacion del almacenamiento
        database = SQLiteDatabase.openOrCreateDatabase("Tarea2.db", null);
        //crearcion de tabla para usuarios
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS usuario (nombre VARCHAR(200), alias VARCHAR(200),correo VARCHAR(200),password VARCHAR(200), id INT)"
        );
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS evento (fecha DATETIME, titulo VARCHAR(200),descripcion VARCHAR(200),idUsuario VARCHAR(200), id INT)"
        );

        Almacenamiento<Usuario> almacenamientoUsuario = new Almacenamiento(Usuario.class,"usuario",database);
        Almacenamiento<Evento> almacenamientoEvento = new Almacenamiento(Usuario.class,"usuario",database);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
