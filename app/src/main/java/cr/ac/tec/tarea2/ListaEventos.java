package cr.ac.tec.tarea2;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cr.ac.tec.tarea2.Dominio.Evento;
import cr.ac.tec.tarea2.Dominio.Sesion;

public class ListaEventos extends ListActivity {
    private DialogFragment initialDate;
    private DialogFragment finalDate;
    private TextView txtInitialDate;
    private TextView txtFinalDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);
        ArrayList<Evento> eventos = loadEventos();

        //eventos.add(new Evento(new Date(),"Reunin as1","React-Native",0,eventos.size()));
        //eventos.add(new Evento(new Date(),"Reunin as2","Processing",0,eventos.size()));
        //eventos.add(new Evento(new Date(),"Trabajo","Front-end mobil",0,eventos.size()));

        AdapterEvento adapter = new AdapterEvento(this,R.layout.activity_lista_eventos,eventos);
        setListAdapter(adapter);


        //inicializamos fragmento de fecha
        initialDate = new DatePickerFragment();
        finalDate = new DatePickerFragment();
        txtInitialDate = (TextView)findViewById(R.id.txv_ListaFechaInicial);
        txtFinalDate = (TextView)findViewById(R.id.txv_ListaFechaFinal);
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Evento> eventos =  loadEventos();
        AdapterEvento adapter = new AdapterEvento(this,R.layout.activity_lista_eventos,eventos);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
       // Log.d("Lista eventos","clicked: " + position + " - "+ id);
        ArrayList<Evento> eventos =  loadEventos();
        Sesion.getSesion(getApplicationContext()).setEventoSeleccionado(eventos.get(position));
        Intent newActivityCrearEvento = new Intent(getApplicationContext(), CrearEvento.class);
        startActivity(newActivityCrearEvento);
    }

    //retorna una copia de la lista mas reciente de los eventos filtrada por id
    private ArrayList<Evento> loadEventos(){
        return (ArrayList<Evento>)Sesion.getSesion(getApplicationContext()).getEventos(Sesion.getSesion(getApplicationContext()).getUsuario().getId()).clone();
    }

    public void showTimePickerDialogInicio(View v) {

        initialDate.show(getFragmentManager(), "FechaInicial");


    }

    public void showTimePickerDialogFin(View v) {
        finalDate.show(getFragmentManager(), "FechaFinal");
    }

    public void updateEvents(){
        Calendar iniDate = ((DatePickerFragment)initialDate).getCalendar();
        Calendar finDate = ((DatePickerFragment)finalDate).getCalendar();

        if(iniDate != null)
        txtInitialDate.setText(calendarToText(iniDate));
        if(finDate != null)
        txtFinalDate.setText(calendarToText(finDate));


        Log.d("Fecha inicial",iniDate.toString());
    }





    private String calendarToText(Calendar calendar){
        String newCalendarToString = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "/"
                        + Integer.toString(calendar.get(Calendar.MONTH)) + "/"
                        + Integer.toString(calendar.get(Calendar.YEAR)) + " -- "
                        + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                        + Integer.toString(calendar.get(Calendar.MINUTE));

        return newCalendarToString;
    }


    public void newEvento(View view){
        Sesion.getSesion(getApplicationContext()).setEventoSeleccionado(null);
        Intent newActivityCrearEvento = new Intent(view.getContext(), CrearEvento.class);
        startActivity(newActivityCrearEvento);

    }





    public class AdapterEvento extends ArrayAdapter<Evento> {
        private Activity activity;
        private ArrayList<Evento> lEvento;
        private  LayoutInflater inflater = null;

        public AdapterEvento(Activity activity, int textViewResourceId, ArrayList<Evento> _Evento){
            super(activity, textViewResourceId, _Evento);
            try {
                this.activity = activity;
                this.lEvento = _Evento;

                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            } catch (Exception e) {

            }
        }

        public int getCount() {
            return lEvento.size();
        }

        public Evento getItem(Evento position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder {
            public TextView display_name;
            public TextView display_date;
            public TextView display_number;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            final AdapterEvento.ViewHolder holder;
            try {
                if (convertView == null) {
                    vi = inflater.inflate(R.layout.item_evento, null);
                    holder = new AdapterEvento.ViewHolder();

                    holder.display_name = (TextView) vi.findViewById(R.id.titulo);
                    holder.display_number = (TextView) vi.findViewById(R.id.descripcion);
                    holder.display_date = (TextView) vi.findViewById(R.id.fecha);


                    vi.setTag(holder);
                } else {
                    holder = (AdapterEvento.ViewHolder) vi.getTag();
                }

                Date fechaEvento = lEvento.get(position).getFecha();
                String yearEvento = Integer.toString(fechaEvento.getYear());
                String monthEvento = Integer.toString(fechaEvento.getMonth());
                String dayEvento = Integer.toString(fechaEvento.getDay());
                String hourEvento = Integer.toString(fechaEvento.getHours());
                String minuteEvento = Integer.toString(fechaEvento.getMinutes());

                holder.display_name.setText(lEvento.get(position).getTitulo());
                holder.display_date.setText(dayEvento+"/"+monthEvento+"/"+yearEvento +"--"+hourEvento+":"+minuteEvento);
                holder.display_number.setText(lEvento.get(position).getDescripcion());


            } catch (Exception e) {


            }
            return vi;
        }
    }
}
