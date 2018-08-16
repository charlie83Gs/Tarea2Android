package cr.ac.tec.tarea2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private Calendar date = null;
    private DialogFragment hour;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        if(date == null) {
            date = Calendar.getInstance();
        }
        if(hour == null){
        hour = new TimePickerFragment();
        }

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        //Log.d("Time","choosen");
        date.set(year,month,day);
        hour.show(((ListaEventos)getActivity()).getFragmentManager(), "HoraInicial");

    }

    public Calendar getCalendar(){
        if(hour != null) {
            Calendar hourCalendar = ((TimePickerFragment) hour).getCalendar();
            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH),
                    hourCalendar.get(Calendar.HOUR_OF_DAY), hourCalendar.get(Calendar.MINUTE));
        }
        return date;

    }
}