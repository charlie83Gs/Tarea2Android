package cr.ac.tec.tarea2.Dominio;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

public class Evento implements Almacenable{
    private Date fecha;
    private String titulo;
    private String descripcion;
    private int idUsuario;
    private int id;

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
        value.put("fecha", fecha.toString());
        value.put("titulo", titulo);
        value.put("descripcion", descripcion);
        value.put("idUsuario", idUsuario);
        value.put("id", id);

        return value;
    }

    @Override
    public void fromStorage(Cursor datos) {

    }
}
