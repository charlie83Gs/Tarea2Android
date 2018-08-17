package cr.ac.tec.tarea2;

import android.annotation.TargetApi;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.Date;

import cr.ac.tec.tarea2.Dominio.Evento;
import cr.ac.tec.tarea2.Dominio.Sesion;

public class CrearEvento extends AppCompatActivity {

    EditText titulo;
    TextInputEditText descripcion;
    DatePicker dpFechaEvento;
    TimePicker tpFechaEvento;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        titulo = (EditText) findViewById(R.id.edt_CrearEventoTitulo);
        descripcion = (TextInputEditText)findViewById(R.id.edt_CrearEventoDescripcion);
        dpFechaEvento = (DatePicker) findViewById(R.id.dtp_crearEventoFecha);
        tpFechaEvento = (TimePicker)findViewById(R.id.tmp_crearEventoHora);
        Sesion sesion = Sesion.getSesion(getApplicationContext());
        Evento eventoAntiguo = sesion.getEventoSeleccionado();
        if(eventoAntiguo != null){
            titulo.setText(eventoAntiguo.getTitulo());
            descripcion.setText(eventoAntiguo.getDescripcion());
            Date fechaAntigua = eventoAntiguo.getFecha();

            dpFechaEvento.init(fechaAntigua.getYear(),fechaAntigua.getMonth(),fechaAntigua.getDay(),null);
            tpFechaEvento.setHour(fechaAntigua.getHours());
            tpFechaEvento.setMinute(fechaAntigua.getMinutes());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void crearActualizar(View view){
        String sTitulo = titulo.getText().toString();
        String sDescripcion = descripcion.getText().toString();
        //se le la fecha
        int year = dpFechaEvento.getYear();
        int month = dpFechaEvento.getMonth();
        int day = dpFechaEvento.getDayOfMonth();
        int hour = tpFechaEvento.getHour();
        int minute = tpFechaEvento.getMinute();
        Date nuevaFecha = new Date(year,month,day,hour,minute);
        Sesion sesion = Sesion.getSesion(getApplicationContext());
        if(sesion.getEventoSeleccionado()== null){
            Evento nuevoEvento = new Evento(nuevaFecha,sTitulo,sDescripcion,sesion.getUsuario().getId(),sesion.getLastId());
            sesion.addEvento(nuevoEvento);
            finish();
        }else{
            Evento antiguoEvento = sesion.getEventoSeleccionado();
            //se actualizan los valores del evento
            antiguoEvento.setFecha(nuevaFecha);
            antiguoEvento.setTitulo(sTitulo);
            antiguoEvento.setDescripcion(sDescripcion);


            //se guardan en la base de datos
            sesion.updateEvento(antiguoEvento.getIdEvento());
            finish();
        }




    }
    @Override
    public void onDestroy() {

        super.onDestroy();
        //destroy references to help memory manager
        titulo = null;
        descripcion = null;
        dpFechaEvento = null;
        tpFechaEvento = null;


    }
}
