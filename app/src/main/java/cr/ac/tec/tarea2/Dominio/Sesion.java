package cr.ac.tec.tarea2.Dominio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Sesion {
    private Usuario usuario = null;
    private SQLiteDatabase database;
    private static int lastId = 0;
    private Evento eventoSeleccionado = null;
    Almacenamiento<Usuario> almacenamientoUsuario;
    Almacenamiento<Evento> almacenamientoEvento;

    private static Sesion sesion = null;


    public static Sesion getSesion(Context context) {
        if(sesion == null){
            sesion = new Sesion(context);
        }

        return sesion;
    }

    /**
     * inicializa la base de datos sqlite
     */
    private Sesion(Context context) {
        //inicializacion del almacenamiento
        database = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().getPath() + "/Tarea2.db", null);
        //crearcion de tabla para usuarios
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS usuario (nombre VARCHAR(200), alias VARCHAR(200),correo VARCHAR(200),password VARCHAR(200), id INT)"
        );
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS evento (fecha VARCHAR(200), titulo VARCHAR(200),descripcion VARCHAR(200),idUsuario INT, id INT)"
        );

        almacenamientoUsuario = new Almacenamiento(Usuario.class,"usuario",database);
        almacenamientoEvento = new Almacenamiento(Evento.class,"evento",database);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean validateUser(){
        return false;
    }

    public Evento getEventoSeleccionado() {
        return eventoSeleccionado;
    }

    public void setEventoSeleccionado(Evento eventoSeleccionado) {
        this.eventoSeleccionado = eventoSeleccionado;
    }

    public  void addEvento(Evento nuevoEvento){
        almacenamientoEvento.agregarDato(nuevoEvento);
    }

    public void updateEvento(int id){
        almacenamientoEvento.updateElement(id);
    }

    public ArrayList<Evento> getEventos(){
        return almacenamientoEvento.getDatos();
    }

    public ArrayList<Evento> getEventos(int userId) {
        return getEventosAux(userId, null, null, null);
    }
    public ArrayList<Evento> getEventos(int userId, Date iniDate, Date finDate) {
        return getEventosAux(userId, iniDate, finDate, null);
    }
    public ArrayList<Evento> getEventos(int userId, Date iniDate, Date finDate, string word) {
        return getEventosAux(userId, iniDate, finDate, word);
    }

    public ArrayList<Evento> getEventosAux(int userId, Date iniDate, Date finDate, string word) {
        ArrayList<Evento> eventosFiltrados = new ArrayList<Evento>();
        lastId = 0;
        for (Evento evento : almacenamientoEvento.getDatos()) {
            if (evento.getIdUsuario() == userId) {
                if (iniDate != null && word != null) {
                    if (evento.getFecha() > iniDate && evento.getFecha() < finDate && evento.getTitulo().contains(word)) {
                        eventosFiltrados.add(evento);
                    }
                }
                if (iniDate != null) {
                    if (evento.getFecha() > iniDate && evento.getFecha() < finDate) {
                        eventosFiltrados.add(evento);
                    }
                } else if (word != null) {
                    if (evento.getTitulo().contains(word)) {
                        eventosFiltrados.add(evento);
                    }
                } else {
                    eventosFiltrados.add(evento);
                }
            }
            if (evento.getIdEvento() > lastId)  {
                lastId = evento.getIdEvento();
            }
        }

        return eventosFiltrados;
    }

    public int getLastId(){
        return lastId;
    }
}
