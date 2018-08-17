package cr.ac.tec.tarea2.Dominio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Sesion {
    private Usuario usuario = null;
    private SQLiteDatabase database;
    private int lastId = 0;
    private int lastIdUsuario = 0;
    private Evento eventoSeleccionado = null;
    Almacenamiento<Usuario> almacenamientoUsuario;
    Almacenamiento<Evento> almacenamientoEvento;
    private HashMap<String,Usuario> hsUsuarios;

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

        hsUsuarios = new HashMap<>();

        inicializar();
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

    public void updateEvento(Evento evento){
        Evento oldEv = null;
        for(Evento ev: getEventos()){
            if(ev.getIdEvento() == evento.getIdEvento()){
                oldEv = evento;
                break;
            }
        }
        almacenamientoEvento.updateElement(evento.getIdEvento(),evento);
    }

    public void deleteEvento(Evento evento){
        almacenamientoEvento.eliminarDato(evento.getIdEvento() , evento);
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
    public ArrayList<Evento> getEventos(int userId, Date iniDate, Date finDate, String word) {
        return getEventosAux(userId, iniDate, finDate, word);
    }

    public ArrayList<Evento> getEventosAux(int userId, Date iniDate, Date finDate, String word) {
        ArrayList<Evento> eventosFiltrados = new ArrayList<Evento>();
        lastId = 0;
        for (Evento evento : almacenamientoEvento.getDatos()) {
            if (evento.getIdUsuario() == userId) {
               /*if (iniDate != null &&  finDate != null && word != null) {
                    if (evento.getFecha().after(iniDate)   && evento.getFecha().before(finDate)  && evento.getTitulo().contains(word)) {
                        eventosFiltrados.add(evento);
                    }
                }
                if (iniDate != null && finDate != null) {
                    if (evento.getFecha().after(iniDate) && evento.getFecha().before(finDate)) {
                        eventosFiltrados.add(evento);
                    }
                }else if (iniDate != null) {
                    if (evento.getFecha().after(iniDate)) {
                        eventosFiltrados.add(evento);
                    }
                }else if (finDate  != null) {
                    if (evento.getFecha().before(finDate)) {
                        eventosFiltrados.add(evento);
                    }
                }else if (word != null) {
                    if (evento.getTitulo().contains(word)) {
                        eventosFiltrados.add(evento);
                    }
                }  else {
                    eventosFiltrados.add(evento);
                }*/

            //The query in one line :v
            if((iniDate == null || evento.getFecha().after(iniDate)) && (finDate == null || evento.getFecha().before(finDate)) && (word==null || evento.getTitulo().contains(word))){
                eventosFiltrados.add(evento);
            }
            }
            if (evento.getIdEvento() > lastId)  {
                lastId = evento.getIdEvento();
            }
        }

        return eventosFiltrados;
    }

    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuari =almacenamientoUsuario.getDatos();
        lastIdUsuario = 0;
        for(Usuario usuario:usuari){
            if (usuario.getId() > lastIdUsuario)  {
                lastIdUsuario = usuario.getId();
            }
        }
        return usuari;
    }

    private void inicializar(){

            lastIdUsuario = 0;
            for(Usuario usuario:almacenamientoUsuario.getDatos()){
                if (usuario.getId() > lastIdUsuario)  {
                    lastIdUsuario = usuario.getId();
                    hsUsuarios.put(usuario.getAlias(),usuario);

                }
            }

            lastId = 0;
            for(Evento evento: almacenamientoEvento.getDatos()){
                if (evento.getIdEvento() > lastId)  {
                    lastId = evento.getIdEvento();
                }
            }

        }


    public void addUsuario(Usuario usuario){
        almacenamientoUsuario.agregarDato(usuario);
        hsUsuarios.put(usuario.getAlias(),usuario);
    }

    public Usuario getUsuarioByAlias(String Alias){
        Usuario usuario = hsUsuarios.get(Alias);
        return usuario;
    }

    public int getLastId(){
        return lastId;
    }
    public int getLastUserId(){
        return lastIdUsuario;
    }

    public void setLastId(int lastId) {
        lastId = lastId;
    }

    public void setLastIdUsuario(int lastIdUsuario) {
       lastIdUsuario = lastIdUsuario;
    }
}
