package cr.ac.tec.tarea2.Dominio;


import android.content.ContentValues;
import android.database.Cursor;

public interface Almacenable {


    public ContentValues toStorage();
    public void fromStorage(Cursor datos);
    public Object clone();
}
