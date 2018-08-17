package cr.ac.tec.tarea2.Dominio;

import android.content.ContentValues;
import android.database.Cursor;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Evento implements Almacenable{
    private Date fecha;
    private String titulo;
    private String descripcion;
    private int idUsuario;
    private int id;
    private static DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
    public Evento(Date fecha, String titulo, String descripcion, int idUsuario, int idEvento) {
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.id = idEvento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEvento() {
        return id;
    }

    public void setIdEvento(int idEvento) {
        this.id = idEvento;
    }

    @Override
    public ContentValues toStorage() {
        ContentValues value = new ContentValues();
        value.put("fecha", df.format(fecha));
        value.put("titulo", titulo);
        value.put("descripcion", descripcion);
        value.put("idUsuario", idUsuario);
        value.put("id", id);

        return value;
    }

    @Override
    public void fromStorage(Cursor datos) {
        try {
            fecha = df.parse(datos.getString(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        titulo = datos.getString(1);
        descripcion = datos.getString(2);
        idUsuario = datos.getInt(3);
        id = datos.getInt(4);
    }

    @Override
    public Object clone() {
        return new Evento( fecha,  titulo,  descripcion,  idUsuario,  id);
    }
}
