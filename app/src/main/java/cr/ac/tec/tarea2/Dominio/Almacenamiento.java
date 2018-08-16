package cr.ac.tec.tarea2.Dominio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * clase utilizada para almacenar cualquier clase que implemente Almacenable
 * requiere crear las tablas que se van a utilizar antes de pretender utilizarlo
 * asume una columna llamada id
 * @param <T>
 */
public class Almacenamiento<T extends Almacenable> {
    Class<T> tClass;
    String tableName;
    SQLiteDatabase actualDB;
    ArrayList<T> datos;

    /**
     * requiere crear las tablas que se van a utilizar antes de pretender utilizarlo
     * asume una columna llamada id
     * @param Tclass
     * @param tableName
     * @param db
     */
    public Almacenamiento(Class<T> Tclass, String tableName, SQLiteDatabase db) {
        tClass = Tclass;
        this.tableName = tableName;
        actualDB = db;
        datos = new ArrayList<T>();

        cargarDatos();
    }

    public void cargarDatos(){
        //leemmos la tabla
        Cursor Dato =
                actualDB.rawQuery("select * from " + tableName, null);
        //vaciamos el array si hay datos viejos
        datos.clear();

        while(Dato.moveToNext()) {
            try {
                //cargamos los datos en el objeto
                T nuevoDato = tClass.newInstance();
                nuevoDato.fromStorage(Dato);
                datos.add(nuevoDato);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public void guardarDatos(){


    }

    public void agregarDato(T dato){
        datos.add(dato);
        actualDB.insert(tableName,null,dato.toStorage());
        guardarDatos();

    }

    public void updateElement(int id){
        actualDB.update(tableName,datos.get(id).toStorage(),"id = "+ Integer.toString(id),null);
    }

    public void eliminarDato(int id){


    }

    public ArrayList<T> getDatos() {
        return datos;
    }


}
